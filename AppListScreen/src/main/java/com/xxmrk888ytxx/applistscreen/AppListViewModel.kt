package com.xxmrk888ytxx.applistscreen

import android.annotation.SuppressLint
import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xxmrk888ytxx.applistscreen.Exceptions.LaunchActivityNotFoundException
import com.xxmrk888ytxx.applistscreen.contract.*
import com.xxmrk888ytxx.applistscreen.models.AppInfoModel
import com.xxmrk888ytxx.applistscreen.models.DialogStates.BatteryIgnoreOptimizationDialogState
import com.xxmrk888ytxx.applistscreen.models.DialogStates.DialogState
import com.xxmrk888ytxx.applistscreen.models.DialogStates.WarmingAccessibilityPermissionDialogState
import com.xxmrk888ytxx.applistscreen.models.DialogStates.WarmingAdminPermissionDialogState
import com.xxmrk888ytxx.applistscreen.models.NeededPermissionModel
import com.xxmrk888ytxx.applistscreen.models.ScreenState
import com.xxmrk888ytxx.coreandroid.DepsProvider.ToastManager
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
    private val appLaunchAndActivateScreenFixationContract: AppLaunchAndActivateScreenFixationContract,
    private val checkPermissionContract: CheckPermissionContract,
    private val requestPermissionContract: RequestPermissionContract,
    private val manageFavoriteAppContract: ManageFavoriteAppContract,
    @Assisted private val activityLifecycleRegister: ActivityLifecycleRegister,
    private val toastManager: ToastManager,
    @Assisted private val showAdContract: ShowAdContract,
    private val ignoreBatteryDialogManageContract: IgnoreBatteryDialogManageContract
) : ViewModel(),ActivityLifecycleCallback {

    private val _screenState = MutableStateFlow<ScreenState>(ScreenState.RequestPermission)

    private var isIgnoreBatteryDialogHasBeenShowed = false

    internal val screenState = _screenState.asStateFlow()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            ScreenState.RequestPermission
        )

    @SuppressLint("ResourceType")
    private val adminPermissionState = MutableStateFlow(
        NeededPermissionModel(
            isGranted = checkPermissionContract.isAdminPermissionGranted(),
            title = R.string.Device_administrator,
            onRequest = ::showWarmingAdminPermissionDialog
        )
    )

    @SuppressLint("ResourceType")
    private val accessibilityPermissionState = MutableStateFlow(
        NeededPermissionModel(
            isGranted = checkPermissionContract.isAccessibilityPermissionGranted(),
            title = R.string.Accessibility,
            onRequest = ::showWarmingAccessibilityPermissionDialog
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

    private val favoriteAppList = manageFavoriteAppContract.getFavoriteAppFlow()

    internal val appList = _appList.asStateFlow()
        .combine(searchLineText) { appList, searchText ->

            if(searchText.isEmpty()) return@combine appList

            appList.filter { searchText.lowercase() in (it.appName ?: "").lowercase() }

        }.combine(favoriteAppList) { appList, favoriteApps ->
            appList.map {
                return@map if(it.appPackageName in favoriteApps) {
                    it.copy(isFavorite = true)
                } else it
            }
        }.map {
            it.sortedByDescending { it.isFavorite }
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    private val _dialogState = MutableStateFlow(DialogState())

    internal val dialogState = _dialogState.asStateFlow()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), DialogState())

    internal fun requestAdminPermission() {
        requestAdminPermissionLauncher?.let {
            requestPermissionContract.requestAdminPermission(it)
        }
    }

    internal fun requestAccessibilityPermission() {
        requestPermissionContract.requestAccessibilityPermission()
    }

    internal fun showWarmingAccessibilityPermissionDialog() {
        viewModelScope.launch {
            _dialogState.emit(
                dialogState.value.copy(
                    warmingAccessibilityPermissionDialogState
                    = WarmingAccessibilityPermissionDialogState.Visible
                )
            )
        }
    }

    internal fun hideWarmingAccessibilityPermissionDialog() {
        viewModelScope.launch {
            _dialogState.emit(
                dialogState.value.copy(
                    warmingAccessibilityPermissionDialogState
                    = WarmingAccessibilityPermissionDialogState.Hidden
                )
            )
        }
    }


    internal fun showWarmingAdminPermissionDialog() {
        viewModelScope.launch {
            _dialogState.emit(
                dialogState.value.copy(
                    warmingAdminPermissionDialogState = WarmingAdminPermissionDialogState.Visible
                )
            )
        }
    }

    internal fun hideWarmingAdminPermissionDialog() {
        viewModelScope.launch {
            _dialogState.emit(
                dialogState.value.copy(
                    warmingAdminPermissionDialogState = WarmingAdminPermissionDialogState.Hidden
                )
            )
        }
    }

    internal fun showBatteryIgnoreOptimizationDialog() {
        viewModelScope.launch {
            _dialogState.emit(
                dialogState.value.copy(
                    batteryIgnoreOptimizationDialogState = BatteryIgnoreOptimizationDialogState.Visible
                )
            )
        }
    }

    internal fun hideBatteryIgnoreOptimizationDialog(isHideDialogForever:Boolean) {
        viewModelScope.launch {
            if(isHideDialogForever) {
                ignoreBatteryDialogManageContract.hideDialogForever()
            }

            _dialogState.emit(
                dialogState.value.copy(
                    batteryIgnoreOptimizationDialogState = BatteryIgnoreOptimizationDialogState.Hidden
                )
            )
        }
    }

    internal fun openIgnoreBatteryOptimizationSettings() {
        ignoreBatteryDialogManageContract.openBatterySettings()
    }

    internal fun onSearchTextUpdated(text:String) {
        viewModelScope.launch(Dispatchers.Main) { _searchLineText.emit(text) }
    }

    internal fun addInFavorite(packageName: String) {
        viewModelScope.launch {
            manageFavoriteAppContract.addInFavoriteApp(packageName)
        }
    }

    internal fun removeInFavorite(packageName: String) {
        viewModelScope.launch {
            manageFavoriteAppContract.removeFromFavoriteApp(packageName)
        }
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
                showAdContract.showAd()
            }


        }
    }

    @SuppressLint("ResourceType")
    internal fun activateFixation(packageName:String) {
        if(!checkPermissionContract.isAdminPermissionGranted() ||
            !checkPermissionContract.isAccessibilityPermissionGranted()) {
            toastManager.showToast(R.string.Some_permissions_have_been_withdrawn)

            viewModelScope.launch {
                _screenState.emit(ScreenState.RequestPermission)

                updatePermissionState()
            }

            return
        }

        viewModelScope.launch {
            if(isNeedShowIgnoreBatteryDialog()) {
                isIgnoreBatteryDialogHasBeenShowed = true

                showBatteryIgnoreOptimizationDialog()

                return@launch
            }

                withContext(Dispatchers.Main) {
                    try {
                        appLaunchAndActivateScreenFixationContract.launchAppAndFixation(packageName)
                        toastManager.showToast(R.string.Screen_lock_activated)
                    }catch (e: LaunchActivityNotFoundException) {
                        toastManager.showToast(R.string.Could_not_find_start_screen)
                    }catch (e:Exception) {
                        toastManager.showToast(R.string.Application_opening_error)
                    }
                }

        }
    }

    private suspend fun isNeedShowIgnoreBatteryDialog(): Boolean {
        if(isIgnoreBatteryDialogHasBeenShowed) return false

        return !ignoreBatteryDialogManageContract.isDialogHiddenForever()
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
        fun create(
            activityResultRegistry: ActivityLifecycleRegister,
            showAdContract: ShowAdContract
        ) : AppListViewModel
    }
}