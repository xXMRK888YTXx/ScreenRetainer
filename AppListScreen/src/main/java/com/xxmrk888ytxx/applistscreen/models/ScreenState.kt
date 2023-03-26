package com.xxmrk888ytxx.applistscreen.models

internal sealed class ScreenState {
    object RequestPermission : ScreenState()

    object LoadingAppList : ScreenState()

    object AppList : ScreenState()
}