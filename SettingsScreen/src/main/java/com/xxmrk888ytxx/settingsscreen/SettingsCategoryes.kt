package com.xxmrk888ytxx.settingsscreen

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.xxmrk888ytxx.settingsscreen.models.SettingsParamType

@SuppressLint("ResourceType")
@Composable
internal fun getLanguageSettings(settingsViewModel: SettingsViewModel) : List<SettingsParamType> {

    return listOf(
        SettingsParamType.Button(
            text = stringResource(R.string.Application_language),
            icon = R.drawable.language,
            onClick = settingsViewModel::showLanguageDialog
        )
    )
}

@SuppressLint("ResourceType")
@Composable
internal fun getAppInfoSettings(settingsViewModel: SettingsViewModel) : List<SettingsParamType> {

    return listOf(
        SettingsParamType.Label(
            text = stringResource(R.string.Application_version),
            icon = R.drawable.info,
            secondaryText = settingsViewModel.getAppVersion()
        ),

        SettingsParamType.Button(
            text = stringResource(R.string.Source_code),
            icon = R.drawable.code,
            onClick = settingsViewModel::openSiteWithSourceCode
        ),

        SettingsParamType.Button(
            text = stringResource(R.string.Write_to_the_developer),
            icon = R.drawable.email,
            onClick = settingsViewModel::openSendEmailActivity
        ),

        SettingsParamType.Button(
            text = stringResource(R.string.Privacy_policy),
            icon = R.drawable.shield,
            onClick = settingsViewModel::openSiteWithPrivacyPolicy
        ),

        SettingsParamType.Button(
            text = stringResource(R.string.Terms_of_use),
            icon = R.drawable.terms,
            onClick = settingsViewModel::openSiteWithTermsUse
        ),

        SettingsParamType.Button(
            text = stringResource(R.string.Delete_app),
            icon = R.drawable.delete,
            onClick = settingsViewModel::removeApp
        )

    )
}