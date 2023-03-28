package com.xxmrk888ytxx.screenretainer.domain.FixationManager

interface FixationManager {

    suspend fun getFixationPackageName() : String?

    suspend fun activeFixation(packageName:String)

    suspend fun disableFixation()
}