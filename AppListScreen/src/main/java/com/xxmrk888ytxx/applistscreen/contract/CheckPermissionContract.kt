package com.xxmrk888ytxx.applistscreen.contract

interface CheckPermissionContract {

    fun isAdminPermissionGranted() : Boolean

    fun isAccessibilityPermissionGranted() : Boolean
}