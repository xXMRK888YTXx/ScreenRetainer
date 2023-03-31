package com.xxmrk888ytxx.applistscreen.models

import androidx.annotation.IdRes

data class NeededPermissionModel(
    val isGranted:Boolean,
    @IdRes val title:Int,
    val onRequest:() -> Unit
)
