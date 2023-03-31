package com.xxmrk888ytxx.corecompose.theme.ShareComponents

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.window.Dialog
import com.xxmrk888ytxx.corecompose.theme.StyleComponents.StyleCard
import com.xxmrk888ytxx.corecompose.theme.themeColors
import com.xxmrk888ytxx.corecompose.theme.themeDimensions
import com.xxmrk888ytxx.corecompose.theme.themeTypography

@Composable
fun YesNoDialog(
    text:String,
    confirmButtonText:String,
    cancelButtonText:String,
    isConfirmButtonEnabled:Boolean = true,
    onCancel:() -> Unit,
    onConfirm:() -> Unit
) {
    Dialog(onDismissRequest = onCancel) {
        StyleCard(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(themeDimensions.outCardPadding)
        ) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(themeDimensions.inCardPadding)
            ) {
                Text(
                    text = text,
                    color = themeColors.primaryFontColor,
                    style = themeTypography.yesNoDialog,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )

                LazySpacer(10)

                YesNoButtons(
                    modifier = Modifier.fillMaxWidth(),
                    yesButtonText = confirmButtonText,
                    noButtonText = cancelButtonText,
                    isYesButtonEnabled = isConfirmButtonEnabled,
                    onNoButtonClick = onCancel,
                    onYesButtonClick = onConfirm
                )
            }
        }
    }
}