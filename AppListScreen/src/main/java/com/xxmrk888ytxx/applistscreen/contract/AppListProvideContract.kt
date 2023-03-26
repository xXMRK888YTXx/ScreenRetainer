package com.xxmrk888ytxx.applistscreen.contract

import com.xxmrk888ytxx.applistscreen.models.AppInfoModel

interface AppListProvideContract {
    suspend fun provide() : List<AppInfoModel>
}