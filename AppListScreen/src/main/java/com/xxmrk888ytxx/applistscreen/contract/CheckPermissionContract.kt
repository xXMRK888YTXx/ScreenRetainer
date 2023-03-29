package com.xxmrk888ytxx.applistscreen.contract

/**
 * [Ru]
 * Контракт для проверки наличия разрешений у приложения
 */

/**
 * [En]
 * Contract to check if the application has permissions
 */
interface CheckPermissionContract {

    fun isAdminPermissionGranted() : Boolean

    fun isAccessibilityPermissionGranted() : Boolean
}