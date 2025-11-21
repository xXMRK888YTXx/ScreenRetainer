package com.xxmrk888ytxx.screenretainer.domain.OpenAppChangedCallback

import com.xxmrk888ytxx.eventdevicetracker.OpenAppChangedCallback
import com.xxmrk888ytxx.eventdevicetracker.OpenAppChangedTrackerParams
import com.xxmrk888ytxx.screenretainer.UseCases.LockDeviceUseCase.LockDeviceUseCase
import com.xxmrk888ytxx.screenretainer.domain.FixationManager.FixationManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Inject

class OpenAppChangedCallbackImpl @Inject constructor(
    private val fixationManager: FixationManager,
    private val lockDeviceUseCase: LockDeviceUseCase
) : OpenAppChangedCallback {

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    override fun onOpenAppChanged(packageName: String) {
        scope.launch {
            val fixationPackageName = fixationManager.getFixationPackageName() ?: return@launch
            if(fixationPackageName == packageName) return@launch

            fixationManager.disableFixation()
            lockDeviceUseCase.execute()
        }
    }

    override fun onTrackingStarted() {
        scope.launch { fixationManager.disableFixation() }
    }

    override val params: OpenAppChangedTrackerParams
        get() = OpenAppChangedTrackerParams.Builder().setIgnoreList(ignoreList).build()

    private val ignoreList : List<String>
        get() = listOf(
            "com.android.vending",
            "com.google.android.permissioncontroller"
        )
}