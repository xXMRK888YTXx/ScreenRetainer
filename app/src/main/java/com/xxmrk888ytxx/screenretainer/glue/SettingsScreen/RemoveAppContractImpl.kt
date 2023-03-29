package com.xxmrk888ytxx.screenretainer.glue.SettingsScreen

import android.content.Context
import com.xxmrk888ytxx.screenretainer.UseCases.RemoveAppUseCase.RemoveAppUseCase
import com.xxmrk888ytxx.settingsscreen.contracts.RemoveAppContract
import javax.inject.Inject

class RemoveAppContractImpl @Inject constructor(
    private val removeAppUseCase: RemoveAppUseCase
) : RemoveAppContract {

    override fun removeApp() {
        removeAppUseCase.execute()
    }
}