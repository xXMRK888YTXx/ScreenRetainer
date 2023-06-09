package com.xxmrk888ytxx.screenretainer.domain.FixationManager

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.xxmrk888ytxx.preferencesstorage.PreferencesStorage
import com.xxmrk888ytxx.screenretainer.domain.QuickSettingsButtonSwitcher.QuickSettingsButtonSwitcher
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class FixationManagerImpl @Inject constructor(
    private val preferencesStorage: PreferencesStorage,
    private val quickSettingsButtonSwitcher: QuickSettingsButtonSwitcher
) : FixationManager {

    private val fixationPackageName = stringPreferencesKey("isFixationActiveKey")

    override suspend fun getFixationPackageName(): String? {
        return preferencesStorage.getPropertyOrNull(fixationPackageName).first()
    }

    override suspend fun activeFixation(packageName:String) {
        preferencesStorage.writeProperty(fixationPackageName,packageName)
        quickSettingsButtonSwitcher.activateQuickButton()
    }

    override suspend fun disableFixation() {
        preferencesStorage.removeProperty(fixationPackageName)
        quickSettingsButtonSwitcher.deactivateQuickButton()
    }


}