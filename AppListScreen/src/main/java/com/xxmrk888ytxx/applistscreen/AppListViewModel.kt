package com.xxmrk888ytxx.applistscreen

import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.ActivityResultRegistry
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xxmrk888ytxx.applistscreen.contract.AppLaunchContract
import com.xxmrk888ytxx.applistscreen.contract.AppListProvideContract
import com.xxmrk888ytxx.applistscreen.contract.CheckPermissionContract
import com.xxmrk888ytxx.applistscreen.contract.RequestPermissionContract
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
import javax.inject.Inject

class AppListViewModel @AssistedInject constructor(
    private val appListProvideContract: AppListProvideContract,
    private val appLaunchContract: AppLaunchContract,
    private val checkPermissionContract: CheckPermissionContract,
    private val requestPermissionContract: RequestPermissionContract,
    @Assisted private val activityLifecycleRegister: ActivityLifecycleRegister
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
            title = "Администратор устройства",
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
            title = "Специальные возможности",
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

    internal fun onAdminPermissionRequested() {

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