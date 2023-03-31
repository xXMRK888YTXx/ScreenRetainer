package com.xxmrk888ytxx.bottombarscreen.models

import androidx.annotation.IdRes
import androidx.compose.runtime.Composable

data class BottomBarScreenModel(
    val title:String,
    @IdRes val icon:Int,
    val content:@Composable () -> Unit
)
