package com.xxmrk888ytxx.quicksettingsservice

import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.service.quicksettings.TileService
import androidx.annotation.RequiresApi
import com.xxmrk888ytxx.coredeps.DepsProvider.getDepsByApplication

@RequiresApi(Build.VERSION_CODES.N)
class LockCurrentAppQuickButtonService : TileService() {

    private val callback:LockCurrentAppButtonClickedCallback by lazy {
        applicationContext.getDepsByApplication()
    }


    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onBind(intent: Intent?): IBinder? {
        return super.onBind(intent)
    }

    override fun onTileAdded() {
        super.onTileAdded()

        unlockAndRun {  }
    }

    override fun onTileRemoved() {
        super.onTileRemoved()
    }

    override fun onStartListening() {
        super.onStartListening()
    }

    override fun onStopListening() {
        super.onStopListening()
    }

    override fun onClick() {
        super.onClick()
        callback.onClick()
    }
}