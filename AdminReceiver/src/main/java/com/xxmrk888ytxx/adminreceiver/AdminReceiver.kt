package com.xxmrk888ytxx.adminreceiver

import android.app.admin.DeviceAdminReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.UserHandle
import android.util.Log

/**
 * [Ru]
 *  * Ресивер который является администратором устройства, используется для блокировки экрана
 *  устройства.
 */

/**
 * [En]
 * The receiver who is the device administrator is used to lock the screen
 * devices.
 */
class AdminReceiver : DeviceAdminReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        super.onReceive(context, intent)
        try {

            when(intent.action) {
                disableAdminAction -> {
                    getManager(context).activeAdmins?.forEach {
                        if(it.packageName == context.packageName) {
                            getManager(context).removeActiveAdmin(it)
                            return
                        }
                    }
                }

                lockDeviceAction -> {
                    getManager(context).lockNow()
                }
            }
        }catch (e:Exception) {
            Log.e(LOG_TAG,e.stackTraceToString())
        }
    }

    companion object {
        const val disableAdminAction = "disableAdminAction"
        const val lockDeviceAction = "lockDeviceAction"
        const val LOG_TAG = "AdminReceiver"
    }
}