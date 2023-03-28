package com.xxmrk888ytxx.screenretainer.glue.AppListScreen

import android.content.Context
import android.content.Intent
import com.xxmrk888ytxx.applistscreen.Exceptions.LaunchActivityNotFoundException
import com.xxmrk888ytxx.applistscreen.contract.AppLaunchAndActivateScreenFixationContract
import com.xxmrk888ytxx.packageinfoprovider.PackageInfoProvider
import com.xxmrk888ytxx.screenretainer.domain.FixationManager.FixationManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Inject

class AppLaunchAndActivateScreenFixationContractImpl @Inject constructor(
    private val packageInfoProvider: PackageInfoProvider,
    private val context:Context,
    private val fixationManager: FixationManager
) : AppLaunchAndActivateScreenFixationContract {

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    override fun launchAppAndFixation(packageName: String) {
        val launchIntent = packageInfoProvider.getLaunchIntent(packageName)
            ?: throw LaunchActivityNotFoundException()

        launchIntent.apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
        }

        context.startActivity(launchIntent)

        scope.launch { fixationManager.activeFixation(packageName) }
    }

}