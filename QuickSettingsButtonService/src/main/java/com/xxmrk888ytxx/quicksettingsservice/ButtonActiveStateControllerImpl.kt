package com.xxmrk888ytxx.quicksettingsservice

import android.content.ComponentName
import android.content.Context
import android.content.ServiceConnection
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.annotation.RequiresApi

@RequiresApi(Build.VERSION_CODES.N)
internal class ButtonActiveStateControllerImpl(
    private val context: Context,
) : ButtonActiveStateController,
    ServiceConnection {

    private var service:LockCurrentAppQuickButtonService? = null

    private var delayState:Boolean? = null

    override suspend fun enableButton() {
        changeState(true)
    }

    override suspend fun disableButton() {
        changeState(false)
    }

    private fun changeState(isActive:Boolean) {
        if(service != null) {
            service?.changeActiveState(isActive)
        } else {
            delayState = isActive
            bindService()
        }
    }

    private fun bindService() {
        context.bindService(
            LockCurrentAppQuickButtonService.getBindIntent(context),
            this, 0
        )
    }

    override fun onServiceConnected(name: ComponentName?, binder: IBinder?) {
        val serviceBinder = binder as? LockCurrentAppQuickButtonService.LockCurrentAppQuickButtonBinder
        service = serviceBinder?.service

        delayState?.let { changeState(it); delayState = null }
    }

    override fun onServiceDisconnected(name: ComponentName?) {
        service = null

        bindService()
    }

    companion object {
        const val LOG_TAG = "ButtonActiveStateControllerImpl"
    }

}