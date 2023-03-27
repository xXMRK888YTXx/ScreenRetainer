package com.xxmrk888ytxx.applistscreen.contract

import android.content.Intent
import androidx.activity.result.ActivityResultLauncher

interface RequestPermissionContract {

    fun requestAdminPermission(activityLauncher:ActivityResultLauncher<Intent>)

    fun requestAccessibilityPermission()
}