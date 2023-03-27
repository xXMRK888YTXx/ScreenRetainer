package com.xxmrk888ytxx.preferencesstorage

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PreferencesStorage private constructor(
    private val context: Context,
    private val name:String
) {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = name)

    suspend fun <TYPE> writeProperty(key:Preferences.Key<TYPE>,value:TYPE) {
        context.dataStore.edit {
            it[key] = value
        }
    }

    fun <TYPE> getProperty(key:Preferences.Key<TYPE>,defValue:TYPE) : Flow<TYPE> {
        return context.dataStore.data.map {
            it[key] ?: defValue
        }
    }

    fun <TYPE> getPropertyOrNull(key:Preferences.Key<TYPE>) : Flow<TYPE?> {
        return context.dataStore.data.map {
            it[key]
        }
    }

    suspend fun <TYPE> removeProperty(key:Preferences.Key<TYPE>) {
        context.dataStore.edit {
            it.remove(key)
        }
    }

    fun <TYPE> isPropertyExist(key:Preferences.Key<TYPE>) : Flow<Boolean> {
        return context.dataStore.data.map {
            it.contains(key)
        }
    }

    class Builder(
        private val fileName:String,
        private val context: Context
    ) {
        fun build() : PreferencesStorage {
            return PreferencesStorage(context,fileName)
        }
    }

}