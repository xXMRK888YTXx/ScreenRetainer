package com.xxmrk888ytxx.screenretainer.glue.AppListScreen

import android.app.StatusBarManager
import android.content.ComponentName
import android.content.Context
import android.graphics.drawable.Icon
import android.os.Build
import androidx.core.content.ContextCompat
import androidx.core.content.getSystemService
import androidx.datastore.preferences.core.booleanPreferencesKey
import com.xxmrk888ytxx.applistscreen.contract.AddQuickButtonToNotificationBarContract
import com.xxmrk888ytxx.preferencesstorage.PreferencesStorage
import com.xxmrk888ytxx.quicksettingsservice.LockCurrentAppQuickButtonService
import com.xxmrk888ytxx.quicksettingsservice.R
import kotlinx.coroutines.flow.first
import javax.inject.Inject


class AddQuickButtonToNotificationBarContractImpl @Inject constructor(
    private val context: Context,
    private val preferencesStorage: PreferencesStorage,
) : AddQuickButtonToNotificationBarContract {

    private val isAppDialogForAddQuickButtonHideForever = booleanPreferencesKey("isAppDialogForAddQuickButtonHideForever")

    override val isFeatureAvailable: Boolean by lazy { Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU }

    override suspend fun isAppDialogForAddQuickButtonHideForever(): Boolean =
        preferencesStorage.getProperty(isAppDialogForAddQuickButtonHideForever,false).first()

    override fun openAndroidDialogForQuickButtonToNotificationBar() {
        if (!isFeatureAvailable) return
        try {
            val statusBarManager: StatusBarManager =
                context.getSystemService<StatusBarManager>() as StatusBarManager
            val componentName = ComponentName(context, LockCurrentAppQuickButtonService::class.java)
            val icon: Icon = Icon.createWithResource(context, R.drawable.screen_lock)
            val tileLabel: CharSequence = context.getString(R.string.Lock_on_the_current_application)
            val executor = ContextCompat.getMainExecutor(context)
            statusBarManager.requestAddTileService(
                componentName, tileLabel, icon, executor
            ) { _: Int? -> }
        }catch (_: Exception) {}
    }

    override suspend fun hideDialogForever() {
        preferencesStorage.writeProperty(isAppDialogForAddQuickButtonHideForever,true)
    }
}