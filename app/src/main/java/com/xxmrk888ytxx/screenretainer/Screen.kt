package com.xxmrk888ytxx.screenretainer

sealed class Screen(val route:String) {
    object BottomBarScreen : Screen("BottomBarScreen")
}
