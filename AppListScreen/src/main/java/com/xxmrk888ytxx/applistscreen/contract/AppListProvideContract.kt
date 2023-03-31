package com.xxmrk888ytxx.applistscreen.contract

import com.xxmrk888ytxx.applistscreen.models.AppInfoModel

/**
 * [Ru]
 * Контракт для предоставление списка приложений
 */

/**
 * [En]
 * Contract for providing application list
 */
interface AppListProvideContract {
    suspend fun provide() : List<AppInfoModel>
}