package com.xxmrk888ytxx.settingsscreen

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.remember
import com.xxmrk888ytxx.settingsscreen.models.SettingsParamType

@SuppressLint("ResourceType")
@Composable
internal fun getLanguageSettings(settingsViewModel: SettingsViewModel) : List<SettingsParamType> {

    return listOf(
        SettingsParamType.Button(
            text = "Язык приложения",
            icon = R.drawable.language,
            onClick = settingsViewModel::showLanguageDialog
        )
    )
}

@SuppressLint("ResourceType")
@Composable
internal fun getAppInfoSettings(settingsViewModel: SettingsViewModel) : List<SettingsParamType> {

    return remember {
        listOf(
            SettingsParamType.Label(
                text = "Версия приложения",
                icon = R.drawable.info,
                secondaryText = settingsViewModel.appVersion
            ),

            SettingsParamType.Button(
                text = "Исходный код",
                icon = R.drawable.code,
                onClick = {  }
            ),

            SettingsParamType.Button(
                text = "Написать разработчику",
                icon = R.drawable.email,
                onClick = {}
            ),

            SettingsParamType.Button(
                text = "Политика конфиденциальности",
                icon = R.drawable.shield,
                onClick = {}
            ),

            SettingsParamType.Button(
                text = "Условия пользования",
                icon = R.drawable.terms,
                onClick = {}
            ),

            SettingsParamType.Button(
                text = "Удалить приложение",
                icon = R.drawable.delete,
                onClick = {}
            )

        )
    }
}