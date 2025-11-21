package com.xxmrk888ytxx.screenretainer

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.xxmrk888ytxx.applistscreen.AppListScreen
import com.xxmrk888ytxx.applistscreen.AppListViewModel
import com.xxmrk888ytxx.bottombarscreen.BottomBarScreen
import com.xxmrk888ytxx.bottombarscreen.models.BottomBarScreenModel
import com.xxmrk888ytxx.corecompose.theme.ShareComponents.AgreeDialog
import com.xxmrk888ytxx.corecompose.theme.themeColors
import com.xxmrk888ytxx.coredeps.SharedInterfaces.ActivityLifecycleCallback.ActivityLifecycleCallback
import com.xxmrk888ytxx.coredeps.SharedInterfaces.ActivityLifecycleCallback.ActivityLifecycleRegister
import com.xxmrk888ytxx.screenretainer.theme.AppTheme
import com.xxmrk888ytxx.screenretainer.theme.Themes
import com.xxmrk888ytxx.screenretainer.utils.appComponent
import com.xxmrk888ytxx.settingsscreen.SettingsScreen
import com.xxmrk888ytxx.settingsscreen.SettingsViewModel
import composeViewModel
import kotlinx.collections.immutable.persistentListOf
import javax.inject.Inject
import javax.inject.Provider

class MainActivity : AppCompatActivity(),ActivityLifecycleRegister {

    @Inject lateinit var appListViewModel: AppListViewModel.Factory
    @Inject lateinit var settingsViewModel: Provider<SettingsViewModel>

    @Inject lateinit var activityViewModelFactory: ActivityViewModel.Factory

    private val activityViewModel by viewModels<ActivityViewModel> { activityViewModelFactory }

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        appComponent.inject(this)
        setContent {
            val agreeDialogState = activityViewModel.isNeedShowAgreeDialog.collectAsState()
            AppTheme(appTheme = Themes.Dark) {
                val navController = rememberNavController()

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(themeColors.background)
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = Screen.BottomBarScreen.route,
                        modifier = Modifier
                            .fillMaxSize()
                            .systemBarsPadding()
                    ) {
                        composable(Screen.BottomBarScreen.route) {
                            BottomBarScreen(
                                bottomBarScreens = persistentListOf(
                                    BottomBarScreenModel(
                                        title = getString(R.string.Applications),
                                        icon = R.drawable.apps,
                                        content = {
                                            AppListScreen(
                                                appListViewModel = composeViewModel {
                                                    appListViewModel.create(
                                                        activityResultRegistry = this@MainActivity
                                                    )
                                                }
                                            )
                                        }
                                    ),

                                    BottomBarScreenModel(
                                        title = getString(R.string.Settings),
                                        icon = R.drawable.settings,
                                        content = {
                                            SettingsScreen(
                                                settingsViewModel = composeViewModel {
                                                    settingsViewModel.get()
                                                }
                                            )
                                        }
                                    )
                                ),
                            )
                        }
                    }
                }

                if(agreeDialogState.value) {
                    AgreeDialog(
                        openPrivacyPolicySite = activityViewModel::openPrivacyPolicy,
                        openTermsOfUseSite = activityViewModel::openTermsOfUse,
                        onConfirm = activityViewModel::hideAgreeDialogForever,
                        onCancel = this::finish
                    )
                }
            }
        }

        activityViewModel.onCreate(this)
    }

    override fun onStart() {
        super.onStart()
        activityViewModel.onStart()
    }

    override fun onResume() {
        super.onResume()
        activityViewModel.onResume()
    }

    override fun onPause() {
        super.onPause()
        activityViewModel.onPause()
    }

    override fun onStop() {
        super.onStop()
        activityViewModel.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        activityViewModel.onDestroy()
    }

    override fun registerCallback(activityLifecycleCallback: ActivityLifecycleCallback) {
        activityViewModel.registerCallback(activityLifecycleCallback,this)
    }

    override fun unregisterCallback(activityLifecycleCallback: ActivityLifecycleCallback) {
        activityViewModel.unregisterCallback(activityLifecycleCallback)
    }
}