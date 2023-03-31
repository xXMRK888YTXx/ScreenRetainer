package com.xxmrk888ytxx.screenretainer.UseCases.RemoveAppUseCase

import android.content.Context
import android.content.Intent
import android.net.Uri
import com.xxmrk888ytxx.adminreceiver.AdminReceiver
import javax.inject.Inject

class RemoveAppUseCaseImpl @Inject constructor(
    private val context: Context
) : RemoveAppUseCase {

    override fun execute() {
        try {
            val disableAdminIntent = Intent(context, AdminReceiver::class.java)
            disableAdminIntent.action = AdminReceiver.disableAdminAction
            context.sendBroadcast(disableAdminIntent)

            val intent = Intent(Intent.ACTION_DELETE)
            intent.data = Uri.parse("package:${context.packageName}")
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        }catch (_:Exception) {}
    }
}