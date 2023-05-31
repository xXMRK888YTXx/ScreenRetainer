package com.xxmrk888ytxx.corecompose.theme.ShareComponents

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import com.xxmrk888ytxx.corecompose.R
import com.xxmrk888ytxx.corecompose.theme.themeColors

@Composable
fun AgreeDialog(
    openPrivacyPolicySite:() -> Unit,
    openTermsOfUseSite:() -> Unit,
    onConfirm:() -> Unit,
    onCancel:() -> Unit
) {
    var isPrivacyPolicyAgreed by rememberSaveable() {
        mutableStateOf(false)
    }

    var isTermsOfUseAgreed by rememberSaveable() {
        mutableStateOf(false)
    }



    YesNoDialog(
        confirmButtonText = stringResource(R.string.I_accept),
        cancelButtonText = stringResource(R.string.I_dont_accept),
        onCancel = onCancel,
        onConfirm = onConfirm,
        isConfirmButtonEnabled = isPrivacyPolicyAgreed && isTermsOfUseAgreed,
        dialogProperties = DialogProperties(
            dismissOnBackPress = false,
            dismissOnClickOutside = false
        )
    ) {

        Row(
            Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = isTermsOfUseAgreed,
                onCheckedChange = { isTermsOfUseAgreed = it },
                colors = CheckboxDefaults.colors(
                    checkedColor = themeColors.primaryColor,
                    uncheckedColor = themeColors.primaryColor
                )
            )

            LazySpacer(width = 10)

            Column() {
                Text(
                    text = stringResource(R.string.I_agree),
                    modifier = Modifier,
                    fontSize = 17.sp,
                    color = themeColors.primaryFontColor,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = stringResource(R.string.Terms_of_Use),
                    modifier = Modifier.clickable {
                        openTermsOfUseSite()
                    },
                    fontSize = 17.sp,
                    color = themeColors.primaryColor,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        LazySpacer(5)

        Row(
            Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = isPrivacyPolicyAgreed,
                onCheckedChange = {
                    isPrivacyPolicyAgreed = it
                },
                colors = CheckboxDefaults.colors(
                    checkedColor = themeColors.primaryColor,
                    uncheckedColor = themeColors.primaryColor
                )
            )

            LazySpacer(width = 10)

            Column() {
                Text(
                    text = stringResource(R.string.I_agree),
                    modifier = Modifier,
                    fontSize = 17.sp,
                    color = themeColors.primaryFontColor,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = stringResource(R.string.Privacy_policy),
                    modifier = Modifier.clickable {
                        openPrivacyPolicySite()
                    },
                    fontSize = 17.sp,
                    color = themeColors.primaryColor,
                    fontWeight = FontWeight.Bold
                )
            }
        }

    }
}