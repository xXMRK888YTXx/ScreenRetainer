package com.xxmrk888ytxx.applistscreen

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xxmrk888ytxx.applistscreen.contract.AppLaunchContract
import com.xxmrk888ytxx.applistscreen.contract.AppListProvideContract
import com.xxmrk888ytxx.applistscreen.contract.CheckPermissionContract
import com.xxmrk888ytxx.applistscreen.contract.RequestPermissionContract
import com.xxmrk888ytxx.applistscreen.models.AppInfoModel
import com.xxmrk888ytxx.applistscreen.models.NeededPermissionModel
import com.xxmrk888ytxx.applistscreen.models.ScreenState
import com.xxmrk888ytxx.coredeps.SharedInterfaces.ActivityLifecycleCallback.ActivityLifecycleCallback
import com.xxmrk888ytxx.coredeps.SharedInterfaces.ActivityLifecycleCallback.ActivityLifecycleRegister
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AppListViewModel @SuppressLint("StaticFieldLeak")
@AssistedInject constructor(
    private val appListProvideContract: AppListProvideContract,
    private val appLaunchContract: AppLaunchContract,
    private val checkPermissionContract: CheckPermissionContract,
    private val requestPermissionContract: RequestPermissionContract,
    @Assisted private val activityLifecycleRegister: ActivityLifecycleRegister,
    private val context: Context
) : ViewModel(),ActivityLifecycleCallback {

    private val _screenState = MutableStateFlow<ScreenState>(ScreenState.RequestPermission)

    internal val screenState = _screenState.asStateFlow()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            ScreenState.RequestPermission
        )

    private val adminPermissionState = MutableStateFlow(
        NeededPermissionModel(
            isGranted = checkPermissionContract.isAdminPermissionGranted(),
            title = context.getString(R.string.Device_administrator),
            onRequest = {
                requestAdminPermissionLauncher?.let {
                    requestPermissionContract.requestAdminPermission(it)
                }
            }
        )
    )

    private val accessibilityPermissionState = MutableStateFlow(
        NeededPermissionModel(
            isGranted = checkPermissionContract.isAccessibilityPermissionGranted(),
            title = context.getString(R.string.Accessibility),
            onRequest = requestPermissionContract::requestAccessibilityPermission
        )
    )

    private var requestAdminPermissionLauncher: ActivityResultLauncher<Intent>? = null

    internal fun initRequestAdminPermissionLauncher(requestAdminPermissionLauncher: ActivityResultLauncher<Intent>) {
        this.requestAdminPermissionLauncher = requestAdminPermissionLauncher
    }

    internal fun clearRequestAdminPermissionLauncher() {
        requestAdminPermissionLauncher = null
    }


    internal val neededPermissionList =
        combine(adminPermissionState, accessibilityPermissionState) { admin, accessibility ->
            listOf(admin, accessibility)
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    private val _appList = MutableStateFlow<List<AppInfoModel>>(emptyList())

    private val _searchLineText = MutableStateFlow("")

    internal val searchLineText = _searchLineText.asStateFlow()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000),"")

    internal val appList = _appList.asStateFlow()
        .combine(searchLineText) { appList, searchText ->
            if(searchText.isEmpty()) return@combine appList

            appList.filter { searchText.lowercase() in (it.appName ?: "").lowercase() }
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    internal fun onSearchTextUpdated(text:String) {
        viewModelScope.launch(Dispatchers.Main) { _searchLineText.emit(text) }
    }


    private fun updatePermissionState() {
        viewModelScope.launch {
            val adminState = checkPermissionContract.isAdminPermissionGranted()
            val accessibilityState = checkPermissionContract.isAccessibilityPermissionGranted()

            withContext(Dispatchers.Main) {
                adminPermissionState.emit(adminPermissionState.value.copy(isGranted = adminState))
                accessibilityPermissionState.emit(
                    accessibilityPermissionState.value.copy(isGranted = accessibilityState)
                )
            }

            if(adminState && accessibilityState) {
               loadAppList()
            }
        }
    }

    private fun loadAppList() {
        viewModelScope.launch(Dispatchers.Default) {
            withContext(Dispatchers.Main) {
                _screenState.emit(ScreenState.LoadingAppList)
            }

            val appList = appListProvideContract.provide()

            withContext(Dispatchers.Main) {
                _appList.emit(appList)
                _screenState.emit(ScreenState.AppList)
            }


        }
    }

    override fun onResume() {
        super.onResume()
        if(_screenState.value is ScreenState.RequestPermission)
            updatePermissionState()
    }

    override fun onCleared() {
        super.onCleared()
        activityLifecycleRegister.unregisterCallback(this)
    }

    init {
        activityLifecycleRegister.registerCallback(this)

        updatePermissionState()
    }

    @AssistedFactory
    interface Factory {
        fun create(activityResultRegistry: ActivityLifecycleRegister) : AppListViewModel
    }
}