package com.xxmrk888ytxx.screenretainer

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.xxmrk888ytxx.applistscreen.AppListScreen
import com.xxmrk888ytxx.applistscreen.AppListViewModel
import com.xxmrk888ytxx.corecompose.theme.AppTheme
import com.xxmrk888ytxx.corecompose.theme.themeColors
import com.xxmrk888ytxx.coredeps.SharedInterfaces.ActivityLifecycleCallback.ActivityLifecycleCallback
import com.xxmrk888ytxx.coredeps.SharedInterfaces.ActivityLifecycleCallback.ActivityLifecycleRegister
import com.xxmrk888ytxx.screenretainer.theme.AppTheme
import com.xxmrk888ytxx.screenretainer.theme.Themes
import com.xxmrk888ytxx.screenretainer.utils.appComponent
import composeViewModel
import javax.inject.Inject
import javax.inject.Provider

class MainActivity : ComponentActivity(),ActivityLifecycleRegister {

    @Inject lateinit var appListViewModel: AppListViewModel.Factory

    @Inject lateinit var activityViewModelFactory: ActivityViewModel.Factory

    private val activityViewModel by viewModels<ActivityViewModel> { activityViewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)

        setContent {
            AppTheme(appTheme = provideAppTheme()) {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Screen.AppListScreen.route,
                    modifier = Modifier
                        .fillMaxSize()
                        .background(themeColors.background)
                ) {
                    composable(Screen.AppListScreen.route) {
                        AppListScreen(
                            appListViewModel = composeViewModel {
                                appListViewModel.create(this@MainActivity)
                            }
                        )
                    }

                    composable(Screen.SettingsScreen.route) {

                    }
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

    @Composable
    private fun provideAppTheme() : AppTheme {
        return Themes.Dark
//        return if(isSystemInDarkTheme()) Themes.Dark
//            else Themes.White
    }

    override fun registerCallback(activityLifecycleCallback: ActivityLifecycleCallback) {
        activityViewModel.registerCallback(activityLifecycleCallback,this)
    }

    override fun unregisterCallback(activityLifecycleCallback: ActivityLifecycleCallback) {
        activityViewModel.unregisterCallback(activityLifecycleCallback)
    }
}