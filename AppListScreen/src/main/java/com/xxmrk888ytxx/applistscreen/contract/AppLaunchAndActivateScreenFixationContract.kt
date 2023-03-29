package com.xxmrk888ytxx.applistscreen.contract

import com.xxmrk888ytxx.applistscreen.Exceptions.LaunchActivityNotFoundException

/**
 * [Ru]
 * Контракт для запуска приложения и фиксации экрана на нем.
 */

/**
 * [En]
 * Contract to launch the application and fix the screen on it.
 */
interface AppLaunchAndActivateScreenFixationContract {

    /**
     * [Ru]
     * Запускает приложение и фискирует экран на нем.
     *
     * @param packageName - имя пакета для запуска приложения и фиксации экрана
     *
     * @throws LaunchActivityNotFoundException - будет выбрашено,
     * если не удалость найти точку входа в приложение
     *
     * @throws ActivityNotFoundException - будет выбрашено, при ошибке старта activity
     */

    /**
     * [En]
     * Launches the application and captures the screen on it.
     *
     * @param packageName - package name for launching the application and fixing the screen
     *
     * @throws LaunchActivityNotFoundException - will be thrown,
     * if you fail to find the entry point to the application
     *
     * @throws ActivityNotFoundException - will be thrown when an activity fails to start
     */
    @kotlin.jvm.Throws(LaunchActivityNotFoundException::class)
    fun launchAppAndFixation(packageName:String)
}