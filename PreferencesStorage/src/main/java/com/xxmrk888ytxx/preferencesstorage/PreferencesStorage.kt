package com.xxmrk888ytxx.preferencesstorage

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * [Ru]
 * Класс для управления пользовательскими предпочтениями
 */

/**
 * [En]
 * Class for managing user preferences
 */
class PreferencesStorage private constructor(
    private val context: Context,
    private val name:String
) {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = name)

    /**
     * [Ru]
     * Записывает значение по ключу
     *
     * @param key - Ключ по которому будет записано заначение
     * @param value - Значение которое будет записано
     */

    /**
     * [En]
     * Writes value by key
     *
     * @param key - The key by which the value will be written
     * @param value - Value to be written
     */
    suspend fun <TYPE> writeProperty(key:Preferences.Key<TYPE>,value:TYPE) {
        context.dataStore.edit {
            it[key] = value
        }
    }

    /**
     * [Ru]
     * Получает значение по ключу
     *
     * @param key - Ключ по которому будет записано заначение
     * @param defValue - Стандартное значение, которое будет возвращено если,
     * значение по ключу не будет найдено
     */

    /**
     * [En]
     * Gets value by key
     *
     * @param key - The key by which the value will be written
     * @param defValue - Default value that will be returned if,
     * key value will not be found
     */
    fun <TYPE> getProperty(key:Preferences.Key<TYPE>,defValue:TYPE) : Flow<TYPE> {
        return context.dataStore.data.map {
            it[key] ?: defValue
        }
    }

    /**
     * [Ru]
     * Возвращает значение по ключу, если значение не будет найдено возвращает null
     *
     * @param key - Ключ по которому будет записано заначение
     */

    /**
     * [En]
     * Returns a value by key, if the value is not found returns null
     *
     * @param key - The key by which the value will be written
     */
    fun <TYPE> getPropertyOrNull(key:Preferences.Key<TYPE>) : Flow<TYPE?> {
        return context.dataStore.data.map {
            it[key]
        }
    }

    /**
     * [Ru]
     * Удаляет значение по ключу.
     *
     * @param key - Ключ, по которому будут удалены данные.
     */

    /**
     * [En]
     * Removes a value by key.
     *
     * @param key - The key by which the data will be deleted.
     */
    suspend fun <TYPE> removeProperty(key:Preferences.Key<TYPE>) {
        context.dataStore.edit {
            it.remove(key)
        }
    }

    /**
     * [Ru]
     * Метод для проверки, существуют ли данные по определённому ключу.
     *
     * @param key - ключ для проверки
     */

    /**
     * [En]
     * A method for checking if data exists for a specific key.
     *
     * @param key - key to check
     */
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