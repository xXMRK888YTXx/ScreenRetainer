package com.xxmrk888ytxx.settingsscreen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import com.xxmrk888ytxx.settingsscreen.models.SettingsParamType

@SuppressLint("ResourceType")
@Composable
fun SettingsScreen(settingsViewModel: SettingsViewModel) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        backgroundColor = Color.Transparent
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    start = it.calculateStartPadding(LocalLayoutDirection.current),
                    end = it.calculateEndPadding(LocalLayoutDirection.current),
                    top = it.calculateTopPadding(),
                    bottom = it.calculateBottomPadding()
                )
        ) {
            item {
                SettingsCategory(
                    categoryName = "Кастомизация",
                    settingsParams = getThemeSettings(settingsViewModel)
                )
            }

            item {
                SettingsCategory(
                    categoryName = "Локализация",
                    settingsParams = getLanguageSettings(settingsViewModel)
                )
            }

            item {
                SettingsCategory(
                    categoryName = "Об приложении",
                    settingsParams = getAppInfoSettings(settingsViewModel)
                )
            }
        }
    }
}