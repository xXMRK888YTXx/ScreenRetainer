package com.xxmrk888ytxx.applistscreen.models.DialogStates

sealed class WarmingAccessibilityPermissionDialogState {

    object Hidden : WarmingAccessibilityPermissionDialogState()

    object Visible : WarmingAccessibilityPermissionDialogState()
}