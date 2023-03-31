package com.xxmrk888ytxx.screenretainer.domain.LockCurrentAppButtonClickedCallback

import android.annotation.SuppressLint
import com.xxmrk888ytxx.coreandroid.DepsProvider.ToastManager
import com.xxmrk888ytxx.quicksettingsservice.LockCurrentAppButtonClickedCallback
import com.xxmrk888ytxx.screenretainer.R
import com.xxmrk888ytxx.screenretainer.domain.FixationManager.FixationManager
import com.xxmrk888ytxx.screenretainer.domain.PermissionManager.PermissionManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class LockCurrentAppButtonClickedCallbackImpl @Inject constructor(
    private val fixationManager: FixationManager,
    private val permissionManager: PermissionManager,
    private val toastManager: ToastManager
) : LockCurrentAppButtonClickedCallback {

    private val scope = CoroutineScope(Dispatchers.Default)

    @SuppressLint("ResourceType")
    override fun onClick() {
        scope.launch {
            if(!permissionManager.isAdminPermissionGranted() || !permissionManager.isAccessibilityPermissionGranted()) {
                toastManager.showToast(R.string.Permission_is_not_granted)
                return@launch
            }

            if(fixationManager.getFixationPackageName() != null) {
                toastManager.showToast(R.string.The_screen_lock_is_already_activated)

                return@launch
            }

            fixationManager.activeFixation(FixationManager.ANY)

            toastManager.showToast(R.string.Fixation_activated)
        }
    }
}