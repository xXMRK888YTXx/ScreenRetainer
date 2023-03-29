package com.xxmrk888ytxx.applistscreen.contract

import android.content.Intent
import androidx.activity.result.ActivityResultLauncher

/**
 * [Ru]
 * Контракт для запроса разрешений
 */

/**
 * [En]
 * Contract for requesting permissions
 */
interface RequestPermissionContract {

    fun requestAdminPermission(activityLauncher:ActivityResultLauncher<Intent>)

    fun requestAccessibilityPermission()
}