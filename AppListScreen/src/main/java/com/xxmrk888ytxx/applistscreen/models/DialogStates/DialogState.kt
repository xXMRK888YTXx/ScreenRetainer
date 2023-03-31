package com.xxmrk888ytxx.applistscreen.models.DialogStates

internal data class DialogState(
    val warmingAdminPermissionDialogState: WarmingAdminPermissionDialogState
        = WarmingAdminPermissionDialogState.Hidden,
    val warmingAccessibilityPermissionDialogState: WarmingAccessibilityPermissionDialogState
        = WarmingAccessibilityPermissionDialogState.Hidden
)