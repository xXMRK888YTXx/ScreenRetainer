package com.xxmrk888ytxx.screenretainer.glue.AppListScreen

import android.content.Context
import android.content.Intent
import com.xxmrk888ytxx.applistscreen.Exceptions.LaunchActivityNotFoundException
import com.xxmrk888ytxx.applistscreen.contract.AppLaunchAndActivateScreenFixationContract
import com.xxmrk888ytxx.packageinfoprovider.PackageInfoProvider
import javax.inject.Inject

class AppLaunchAndActivateScreenFixationContractImpl @Inject constructor(
    private val packageInfoProvider: PackageInfoProvider,
    private val context:Context
) : AppLaunchAndActivateScreenFixationContract {
    override fun launchAppAndFixation(packageName: String) {
        val launchIntent = packageInfoProvider.getLaunchIntent(packageName)
            ?: throw LaunchActivityNotFoundException()

        launchIntent.apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
        }

        context.startActivity(launchIntent)
    }

}