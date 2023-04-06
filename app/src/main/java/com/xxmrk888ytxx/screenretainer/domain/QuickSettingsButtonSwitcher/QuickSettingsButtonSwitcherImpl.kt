package com.xxmrk888ytxx.screenretainer.domain.QuickSettingsButtonSwitcher

import com.xxmrk888ytxx.quicksettingsservice.ButtonActiveStateController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Inject

class QuickSettingsButtonSwitcherImpl @Inject constructor(
    private val buttonActiveStateController: ButtonActiveStateController
) : QuickSettingsButtonSwitcher {

    private val scope = CoroutineScope( Dispatchers.Default + SupervisorJob() )

    override fun activateQuickButton() {
        scope.launch { buttonActiveStateController.enableButton() }
    }

    override fun deactivateQuickButton() {
        scope.launch { buttonActiveStateController.disableButton() }
    }
}