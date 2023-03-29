package com.xxmrk888ytxx.corecompose.theme.ShareComponents.models

data class SelectDialogModel(
    val title:String,
    val isSelected:Boolean,
    val onClick:() -> Unit
)
