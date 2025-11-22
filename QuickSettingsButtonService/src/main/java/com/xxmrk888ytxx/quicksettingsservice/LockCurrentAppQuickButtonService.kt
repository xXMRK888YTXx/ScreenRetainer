package com.xxmrk888ytxx.quicksettingsservice

import android.content.Context
import android.content.Intent
import android.os.Binder
import android.os.Build
import android.os.IBinder
import android.service.quicksettings.Tile
import android.service.quicksettings.TileService
import androidx.annotation.RequiresApi
import com.xxmrk888ytxx.coredeps.DepsProvider.getDepsByApplication

/**
 * [Ru]
 *  Сервис для добавление кнопки в панель быстрых настоек
 */
/**
 * [En]
 * Service for adding a button to the quick settings panel
 */
@RequiresApi(Build.VERSION_CODES.N)
class LockCurrentAppQuickButtonService : TileService() {

    private val callback: LockCurrentAppButtonClickedCallback by lazy {
        applicationContext.getDepsByApplication()
    }

    internal inner class LockCurrentAppQuickButtonBinder : Binder() {
        val service: LockCurrentAppQuickButtonService = this@LockCurrentAppQuickButtonService
    }


    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onBind(intent: Intent?): IBinder? {
        return if (intent?.action == REQUEST_BIND_ACTION)
            LockCurrentAppQuickButtonBinder()
        else
            super.onBind(intent)
    }

    override fun onTileAdded() {
        super.onTileAdded()

        unlockAndRun { }
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

    internal fun changeActiveState(isActive: Boolean) {
        if (isActive) {
            qsTile.state = Tile.STATE_ACTIVE
        } else {
            qsTile.state = Tile.STATE_INACTIVE
        }

        qsTile.updateTile()
    }

    companion object {
        private const val REQUEST_BIND_ACTION = "REQUEST_BIND_ACTION"

        internal fun getBindIntent(context: Context) =
            Intent(context, LockCurrentAppQuickButtonService::class.java).apply {
                action = REQUEST_BIND_ACTION
            }

        @RequiresApi(Build.VERSION_CODES.N)
        class ButtonActiveStateControllerFactory {
            fun create(context: Context): ButtonActiveStateController =
                ButtonActiveStateControllerImpl(context)
        }
    }
}