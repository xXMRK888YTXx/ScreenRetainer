package com.xxmrk888ytxx.applistscreen.models

data class NeededPermissionModel(
    val isGranted:Boolean,
    val title:String,
    val onRequest:() -> Unit
)
