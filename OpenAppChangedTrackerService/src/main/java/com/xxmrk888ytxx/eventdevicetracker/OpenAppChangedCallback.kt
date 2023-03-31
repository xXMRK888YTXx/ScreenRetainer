package com.xxmrk888ytxx.eventdevicetracker

/**
 * [Ru]
 * Интерфейс для получение событий устройства таких как:
 * - Смена текущего открытого приложения
 */
/**
 * [En]
 * Interface for receiving device events such as:
 * - Change the current open application
 */
interface OpenAppChangedCallback {
    /**
     * [Ru]
     * Дополнительный параметры для работы трекера
     */
    /**
     * [En]
     * Additional parameters for tracker operation
     */
    val params: OpenAppChangedTrackerParams
        get() = OpenAppChangedTrackerParams.Builder().build()

    /**
     * [Ru]
     * Метод который должен вызываться при смене текущего открытого приложения
     */
    /**
     * [En]
     * Method to be called when changing the currently open application
     */
    fun onOpenAppChanged(packageName:String)
}