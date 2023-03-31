package com.xxmrk888ytxx.screenretainer.domain.PermissionManager

import android.Manifest
import android.accessibilityservice.AccessibilityServiceInfo
import android.app.admin.DevicePolicyManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.provider.Settings
import android.view.accessibility.AccessibilityManager
import androidx.activity.result.ActivityResultLauncher
import com.xxmrk888ytxx.adminreceiver.AdminReceiver
import javax.inject.Inject

class PermissionManagerImpl @Inject constructor(
    private val context: Context
) : PermissionManager {
    override fun isAdminPermissionGranted(): Boolean {
        val dpm = context.getSystemService(Context.DEVICE_POLICY_SERVICE) as? DevicePolicyManager
        val adminList = (dpm?.activeAdmins ?: emptyList()).map {
            it.packageName
        }

        return context.packageName in adminList
    }

    override fun isAccessibilityPermissionGranted(): Boolean {
        var isAccessibilityEnabled = false
        val accessibilityManager = context.getSystemService(Context.ACCESSIBILITY_SERVICE)
                as? AccessibilityManager ?: return false
        (accessibilityManager).apply {
            installedAccessibilityServiceList.forEach { installedService ->
                installedService.resolveInfo.serviceInfo.apply {
                    if (getEnabledAccessibilityServiceList(AccessibilityServiceInfo.FEEDBACK_ALL_MASK)
                            .any {
                                it.resolveInfo.serviceInfo.packageName == packageName
                                        && it.resolveInfo.serviceInfo.name == name
                                        && permission == Manifest.permission.BIND_ACCESSIBILITY_SERVICE
                                        && it.resolveInfo.serviceInfo.packageName == context.packageName
                            }
                    )
                        isAccessibilityEnabled = true
                }
            }
        }

        return isAccessibilityEnabled
    }


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