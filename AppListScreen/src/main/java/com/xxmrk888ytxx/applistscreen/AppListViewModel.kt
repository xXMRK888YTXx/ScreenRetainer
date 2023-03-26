package com.xxmrk888ytxx.applistscreen

import androidx.lifecycle.ViewModel
import com.xxmrk888ytxx.applistscreen.contract.AppLaunchManager
import com.xxmrk888ytxx.applistscreen.contract.AppListProvider
import javax.inject.Inject

class AppListViewModel @Inject constructor(
    private val appListProvider: AppListProvider,
    private val appLaunchManager: AppLaunchManager
) : ViewModel() {
}