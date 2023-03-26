package com.xxmrk888ytxx.screenretainer.utils

import android.content.Context
import com.xxmrk888ytxx.screenretainer.App
import com.xxmrk888ytxx.screenretainer.DI.AppComponent

val Context.appComponent : AppComponent
    get() = when(this) {
        is App -> appComponent
        else -> applicationContext.appComponent
    }