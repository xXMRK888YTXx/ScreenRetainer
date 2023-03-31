package com.xxmrk888ytxx.screenretainer.domain.AgreeDialogManager

import androidx.datastore.preferences.core.booleanPreferencesKey
import com.xxmrk888ytxx.preferencesstorage.PreferencesStorage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AgreeDialogManagerImpl @Inject constructor (
    private val preferencesStorage: PreferencesStorage
) : AgreeDialogManager {

    private val dialogStateKey = booleanPreferencesKey("agreeDialogKey")

    override val isNeedShowDialog: Flow<Boolean>
        get() = preferencesStorage.getProperty(dialogStateKey,true)

    override suspend fun hideForever() = withContext(Dispatchers.IO) {
        preferencesStorage.writeProperty(dialogStateKey,false)
    }
}