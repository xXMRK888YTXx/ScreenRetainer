package com.xxmrk888ytxx.applistscreen.contract

import com.xxmrk888ytxx.applistscreen.models.AppInfoModel
import kotlinx.coroutines.flow.Flow

interface AppListProvider {
    fun provide() : Flow<AppInfoModel>
}