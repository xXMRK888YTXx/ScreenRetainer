package com.xxmrk888ytxx.applistscreen.contract

import kotlinx.coroutines.flow.Flow

interface IgnoreBatteryDialogManageContract {

    suspend fun isDialogHiddenForever() : Boolean

    suspend fun hideDialogForever()

    fun openBatterySettings()
}