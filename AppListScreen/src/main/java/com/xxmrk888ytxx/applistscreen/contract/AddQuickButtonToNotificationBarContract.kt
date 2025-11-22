package com.xxmrk888ytxx.applistscreen.contract

interface AddQuickButtonToNotificationBarContract {
    val isFeatureAvailable: Boolean
    suspend fun isAppDialogForAddQuickButtonHideForever(): Boolean
    fun openAndroidDialogForQuickButtonToNotificationBar()
    suspend fun hideDialogForever()
}