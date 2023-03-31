package com.xxmrk888ytxx.screenretainer.glue.AppListScreen

import android.Manifest
import android.accessibilityservice.AccessibilityServiceInfo
import android.app.admin.DevicePolicyManager
import android.content.Context
import android.view.accessibility.AccessibilityManager
import com.xxmrk888ytxx.applistscreen.contract.CheckPermissionContract
import com.xxmrk888ytxx.screenretainer.domain.PermissionManager.PermissionManager
import javax.inject.Inject

class CheckPermissionContractImpl @Inject constructor(
    private val permissionManager: PermissionManager
) : CheckPermissionContract {
    override fun isAdminPermissionGranted(): Boolean  = permissionManager.isAdminPermissionGranted()

    override fun isAccessibilityPermissionGranted(): Boolean = permissionManager.isAccessibilityPermissionGranted()
}