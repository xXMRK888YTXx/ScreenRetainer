package com.xxmrk888ytxx.screenretainer.domain.FixationManager

/**
 * [Ru]
 * Интерфейс который используется для управлением фиксации экрана
 */

/**
 * [En]
 *  The interface that is used to manage screen capture
 */
interface FixationManager {

    /**
     * [Ru]
     * Возвращает имя покета на котором захвачен экран. Если возвращает null, то фиксация отключена.
     */

    /**
     * [En]
     * Returns the name of the packet that the screen is captured on.
     * If it returns null, then commit is disabled.
     */
    suspend fun getFixationPackageName() : String?

    /**
     * [Ru]
     * Включает фиксацию для переданого пакета
     *
     * @param packageName - пакет для которого активируется фиксация
     */

    /**
     * [En]
     * Enables commit for transmitted packet
     *
     * @param packageName - the package for which the commit is being activated
     */
    suspend fun activeFixation(packageName:String)

    /**
     * [Ru]
     * Отключает фиксацию экрана
     */

    /**
     * [En]
     * Disables screen lock
     */
    suspend fun disableFixation()

    companion object {
        const val ANY = "ANY"
    }
}