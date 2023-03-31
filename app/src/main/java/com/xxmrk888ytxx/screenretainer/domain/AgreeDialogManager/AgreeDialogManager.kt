package com.xxmrk888ytxx.screenretainer.domain.AgreeDialogManager

import kotlinx.coroutines.flow.Flow

interface AgreeDialogManager {
    val isNeedShowDialog : Flow<Boolean>

    suspend fun hideForever()
}