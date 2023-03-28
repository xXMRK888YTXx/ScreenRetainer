package com.xxmrk888ytxx.screenretainer.UseCases.LockDeviceUseCase

import android.content.Context
import android.content.Intent
import com.xxmrk888ytxx.adminreceiver.AdminReceiver
import javax.inject.Inject

class LockDeviceUseCaseImpl @Inject constructor(
    private val context:Context
) : LockDeviceUseCase {
    override fun execute() {
        val intent = Intent(context,AdminReceiver::class.java).apply {
            action = AdminReceiver.lockDeviceAction
        }

        context.sendBroadcast(intent)
    }
}