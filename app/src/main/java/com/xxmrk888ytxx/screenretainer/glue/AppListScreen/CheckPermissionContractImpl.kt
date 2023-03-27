package com.xxmrk888ytxx.screenretainer.glue.AppListScreen

import android.Manifest
import android.accessibilityservice.AccessibilityServiceInfo
import android.app.admin.DevicePolicyManager
import android.content.Context
import android.view.accessibility.AccessibilityManager
import com.xxmrk888ytxx.applistscreen.contract.CheckPermissionContract
import javax.inject.Inject

class CheckPermissionContractImpl @Inject constructor(
    private val context: Context,
) : CheckPermissionContract {
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
}