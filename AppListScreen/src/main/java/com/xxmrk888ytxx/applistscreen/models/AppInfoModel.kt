package com.xxmrk888ytxx.applistscreen.models

import android.graphics.Bitmap

data class AppInfoModel(
    val appName:String?,
    val appPackageName:String,
    val appIcon: Bitmap?,
    val isFavorite:Boolean
)
