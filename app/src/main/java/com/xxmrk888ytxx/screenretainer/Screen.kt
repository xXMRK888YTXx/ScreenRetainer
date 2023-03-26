package com.xxmrk888ytxx.screenretainer

sealed class Screen(val route:String) {

    object AppListScreen : Screen("AppListScreen")

    object SettingsScreen : Screen("SettingsScreen")
}
