package com.xxmrk888ytxx.screenretainer.glue.AppListScreen

import com.xxmrk888ytxx.applistscreen.contract.AppListProvider
import com.xxmrk888ytxx.applistscreen.models.AppInfoModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AppListProviderImpl @Inject constructor(

) : AppListProvider {
    override fun provide(): Flow<AppInfoModel> {
        TODO("Not yet implemented")
    }
}