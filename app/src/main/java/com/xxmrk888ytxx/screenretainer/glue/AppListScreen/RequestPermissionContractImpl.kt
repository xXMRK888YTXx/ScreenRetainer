package com.xxmrk888ytxx.screenretainer.glue.AppListScreen

import android.app.admin.DevicePolicyManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.provider.Settings
import androidx.activity.result.ActivityResultLauncher
import com.xxmrk888ytxx.adminreceiver.AdminReceiver
import com.xxmrk888ytxx.applistscreen.contract.RequestPermissionContract
import com.xxmrk888ytxx.screenretainer.domain.PermissionManager.PermissionManager
import javax.inject.Inject

class RequestPermissionContractImpl @Inject constructor(
    private val permissionManager:PermissionManager
) : RequestPermissionContract {

    override fun requestAdminPermission(activityLauncher: ActivityResultLauncher<Intent>) {
        permissionManager.requestAdminPermission(activityLauncher)
    }

    override fun requestAccessibilityPermission() {
        permissionManager.requestAccessibilityPermission()
    }
}