package com.xxmrk888ytxx.screenretainer.glue.AppListScreen

import android.app.admin.DevicePolicyManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.provider.Settings
import androidx.activity.result.ActivityResultLauncher
import com.xxmrk888ytxx.adminreceiver.AdminReceiver
import com.xxmrk888ytxx.applistscreen.contract.RequestPermissionContract
import javax.inject.Inject

class RequestPermissionContractImpl @Inject constructor(
    private val context: Context
) : RequestPermissionContract {

    override fun requestAdminPermission(activityLauncher: ActivityResultLauncher<Intent>) {
        val component = ComponentName(context, AdminReceiver::class.java)
        val intent = Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN).apply {
            putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, component)
        }

        activityLauncher.launch(intent)
    }

    override fun requestAccessibilityPermission() {
        context.startActivity(Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
        })
    }
}