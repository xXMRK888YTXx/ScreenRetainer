package com.xxmrk888ytxx.screenretainer.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.xxmrk888ytxx.corecompose.theme.AppTheme
import com.xxmrk888ytxx.corecompose.theme.LocalTheme

@Composable
fun AppTheme(
    appTheme: AppTheme,
    content: @Composable () -> Unit
) {

    val systemUiController = rememberSystemUiController()

    systemUiController.setStatusBarColor(appTheme.colors.statusBar)
    systemUiController.setNavigationBarColor(appTheme.colors.navigationBar)

    CompositionLocalProvider(
        values = arrayOf(LocalTheme provides appTheme),
        content = content
    )
}