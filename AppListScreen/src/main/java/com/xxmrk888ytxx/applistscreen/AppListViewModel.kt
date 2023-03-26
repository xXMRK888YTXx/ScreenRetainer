package com.xxmrk888ytxx.applistscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xxmrk888ytxx.applistscreen.contract.AppLaunchContract
import com.xxmrk888ytxx.applistscreen.contract.AppListProvideContract
import com.xxmrk888ytxx.applistscreen.models.ScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

class AppListViewModel @Inject constructor(
    private val appListProvideContract: AppListProvideContract,
    private val appLaunchContract: AppLaunchContract,
) : ViewModel() {

    private val _screenState = MutableStateFlow<ScreenState>(ScreenState.RequestPermission)

    internal val screenState = _screenState.asStateFlow()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            ScreenState.RequestPermission
        )
}