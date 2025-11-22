package com.xxmrk888ytxx.quicksettingsservice

import android.app.Service
import android.content.ComponentName
import android.content.Context
import android.content.ServiceConnection
import android.os.Build
import android.os.IBinder
import android.service.quicksettings.TileService
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

    private fun changeState(isActive:Boolean) = try {
        if(service != null) {
            service?.changeActiveState(isActive)
        } else {
            delayState = isActive
            bindService()
        }
    } catch (_: Exception) {}

    private fun bindService() {
        try {
            TileService.requestListeningState(context,ComponentName(context, LockCurrentAppQuickButtonService::class.java))
        }catch (_: Exception) {}
        context.bindService(
            LockCurrentAppQuickButtonService.getBindIntent(context),
            this, Service.BIND_AUTO_CREATE
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

    init {
        bindService()
    }

    companion object {
        const val LOG_TAG = "ButtonActiveStateControllerImpl"
    }

}