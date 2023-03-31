package com.xxmrk888ytxx.screenretainer.domain.PermissionManager

import android.content.Intent
import androidx.activity.result.ActivityResultLauncher

interface PermissionManager {

    fun isAdminPermissionGranted() : Boolean

    fun isAccessibilityPermissionGranted() : Boolean

    fun requestAdminPermission(activityLauncher: ActivityResultLauncher<Intent>)

    fun requestAccessibilityPermission()
}