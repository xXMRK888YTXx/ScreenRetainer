package com.xxmrk888ytxx.applistscreen.models.DialogStates

sealed class AddQuickButtonDialogState {
    object Hidden : AddQuickButtonDialogState()

    object Visible : AddQuickButtonDialogState()
}