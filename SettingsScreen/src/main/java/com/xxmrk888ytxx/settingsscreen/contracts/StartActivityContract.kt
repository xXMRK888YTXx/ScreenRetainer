package com.xxmrk888ytxx.settingsscreen.contracts

/** * [Ru] * Контракт для запуска сторонних activity */

/** * [Eu] * Contract for launching third-party activities */
interface StartActivityContract {

    /**
     * [RU]
     * Открывает сайт с исходным кодом
     */

    /**
     * [Eu]
     * Opens source code site
     */
    fun openSiteWithSourceCode()

    /**
     * [Ru]
     * Открывает почтовый клиент для отправки письма разработчику
     */

    /**
     * [Eu]
     * Opens an email client to send an email to the developer
     */
    fun openSendEmailActivity()

    /** * [Ru] * Открывает сайт с политикой конфиденциальности */

    /** * [Eu] * Opens a site with a privacy policy */
    fun openSiteWithPrivacyPolicy()

    /**
     * [Ru]
     * Открывает сайт с условиями использования
     */

    /**
     * [Eu]
     * Opens a site with terms of use
     */
    fun openSiteWithTermsUse()
}