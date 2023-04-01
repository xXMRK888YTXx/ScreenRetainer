package com.xxmrk888ytxx.screenretainer.glue.AppListScreen

import androidx.datastore.preferences.core.booleanPreferencesKey
import com.xxmrk888ytxx.applistscreen.contract.IgnoreBatteryDialogManageContract
import com.xxmrk888ytxx.preferencesstorage.PreferencesStorage
import com.xxmrk888ytxx.screenretainer.domain.PermissionManager.PermissionManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import javax.inject.Inject

class IgnoreBatteryDialogManageContractImpl @Inject constructor(
    private val preferencesStorage: PreferencesStorage,
    private val permissionManager: PermissionManager
) : IgnoreBatteryDialogManageContract {

    private val ignoreBatteryDialogStateKey = booleanPreferencesKey("ignoreBatteryDialogState")

    override suspend fun isDialogHiddenForever(): Boolean {
        if(permissionManager.isIgnoreBatteryOptimizationEnable()) return true

        return preferencesStorage.getProperty(ignoreBatteryDialogStateKey,false).first()
    }


    override suspend fun hideDialogForever() = withContext(Dispatchers.IO) {
        preferencesStorage.writeProperty(ignoreBatteryDialogStateKey,true)
    }

    override fun openBatterySettings() {
        permissionManager.requestIgnoreBatteryOptimization()
    }
}