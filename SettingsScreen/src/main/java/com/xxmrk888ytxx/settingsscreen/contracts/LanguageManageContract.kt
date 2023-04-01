package com.xxmrk888ytxx.settingsscreen.contracts

import com.xxmrk888ytxx.settingsscreen.models.AppLanguage

/**
 * [Ru]
 * Контракт для управления языком приложения
 */
/**
 * [En]
 * Contract to control the language of the application
 */
interface LanguageManageContract {

    /** * [Ru] * Поддерживаемые языки приложения */

    /** * [En] * Supported application languages */
    val supportedLanguage:List<AppLanguage>

    /** * [Ru] * Метод для установки языка приложения */

    /** * [En] * Method for setting the application language */
    fun setupLanguage(appLanguage: AppLanguage)

    /**
     * [Ru]
     * Получение текущего языка приложения
     */

    /**
     * [En]
     * Get current application language
     */
    val currentLanguage: AppLanguage
}