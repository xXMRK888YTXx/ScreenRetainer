package com.xxmrk888ytxx.settingsscreen

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import com.xxmrk888ytxx.settingsscreen.models.SettingsParamType

@SuppressLint("ResourceType")
@Composable
internal fun getThemeSettings(settingsViewModel: SettingsViewModel) : List<SettingsParamType> {

    return listOf(
        SettingsParamType.Button(
            text = "Тема приложения",
            icon = R.drawable.sun,
            onClick = {}
        )
    )
}

@SuppressLint("ResourceType")
@Composable
internal fun getLanguageSettings(settingsViewModel: SettingsViewModel) : List<SettingsParamType> {

    return listOf(
        SettingsParamType.Button(
            text = "Язык приложения",
            icon = R.drawable.language,
            onClick = {}
        )
    )
}

@SuppressLint("ResourceType")
@Composable
internal fun getAppInfoSettings(settingsViewModel: SettingsViewModel) : List<SettingsParamType> {

    return listOf(
        SettingsParamType.Label(
            text = "Язык приложения",
            icon = R.drawable.info,
            secondaryText = settingsViewModel.appVersion
        )
    )
}