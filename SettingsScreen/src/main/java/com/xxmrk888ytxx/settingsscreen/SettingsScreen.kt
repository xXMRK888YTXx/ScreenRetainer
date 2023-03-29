package com.xxmrk888ytxx.settingsscreen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.stringResource
import com.xxmrk888ytxx.corecompose.theme.ShareComponents.SelectDialog
import com.xxmrk888ytxx.corecompose.theme.ShareComponents.models.SelectDialogModel
import com.xxmrk888ytxx.settingsscreen.models.AppLanguage
import com.xxmrk888ytxx.settingsscreen.models.DialogState.LanguageDialogState
import com.xxmrk888ytxx.settingsscreen.models.SettingsParamType

@SuppressLint("ResourceType")
@Composable
fun SettingsScreen(settingsViewModel: SettingsViewModel) {
    val dialogState = settingsViewModel.dialogState.collectAsState()

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
                    categoryName = stringResource(R.string.Localization),
                    settingsParams = getLanguageSettings(settingsViewModel)
                )
            }

            item {
                SettingsCategory(
                    categoryName = stringResource(R.string.About_the_app),
                    settingsParams = getAppInfoSettings(settingsViewModel)
                )
            }
        }
    }

    if (dialogState.value.languageDialogState is LanguageDialogState.Showed) {
        SelectLanguageDialog(
            settingsViewModel = settingsViewModel,
            currentSelectedLanguage = (dialogState.value.languageDialogState
                    as LanguageDialogState.Showed).currentSelectedLanguage
        )
    }
}

@Composable
internal fun SelectLanguageDialog(
    settingsViewModel: SettingsViewModel,
    currentSelectedLanguage: AppLanguage,
) {
    SelectDialog(
        confirmButtonText = "Ок",
        cancelButtonText = "Отмена",
        onConfirm = settingsViewModel::changeCurrentLanguage,
        onCancel = settingsViewModel::hideLanguageDialog,
        items = settingsViewModel.supportedLanguage.map {
            SelectDialogModel(
                title = it.name,
                isSelected = currentSelectedLanguage == it,
                onClick = { settingsViewModel.changeCurrentSelectedLanguage(it) }
            )
        }
    )
}
