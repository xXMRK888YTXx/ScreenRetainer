package com.xxmrk888ytxx.corecompose.theme.ShareComponents

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.xxmrk888ytxx.corecompose.theme.ShareComponents.models.SelectDialogModel
import com.xxmrk888ytxx.corecompose.theme.StyleComponents.BodyText
import com.xxmrk888ytxx.corecompose.theme.StyleComponents.StyleCard
import com.xxmrk888ytxx.corecompose.theme.themeColors
import com.xxmrk888ytxx.corecompose.theme.themeDimensions
import com.xxmrk888ytxx.corecompose.theme.themeTypography

@Composable
fun SelectDialog(
    confirmButtonText:String,
    cancelButtonText:String,
    isConfirmButtonEnabled:Boolean = true,
    onConfirm:() -> Unit,
    onCancel:() -> Unit,
    items:List<SelectDialogModel>
) {
    Dialog(onDismissRequest = onCancel) {
        StyleCard(
            modifier = Modifier
                .fillMaxWidth()
                .padding(themeDimensions.outCardPadding)
        ) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(themeDimensions.inCardPadding)
            ) {
                LazyColumn(Modifier.fillMaxWidth()) {
                    items(items) {
                        Row(
                            Modifier
                                .fillMaxWidth()
                                .clickable(onClick = it.onClick),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            RadioButton(
                                selected = it.isSelected,
                                onClick = it.onClick,
                                colors = RadioButtonDefaults.colors(
                                    selectedColor = themeColors.yesButtonColor,
                                    unselectedColor = themeColors.cancelButtonColor
                                )
                            )

                            LazySpacer(width = 15)

                            Text(
                                text = it.title,
                                color = themeColors.primaryFontColor,
                                style = themeTypography.selectDialog
                            )
                        }
                    }
                }

                YesNoButtons(
                    modifier = Modifier.fillMaxWidth().padding(top = 5.dp),
                    yesButtonText = confirmButtonText,
                    noButtonText = cancelButtonText,
                    onNoButtonClick = onCancel,
                    onYesButtonClick = onConfirm,
                    isYesButtonEnabled = isConfirmButtonEnabled
                )
            }
        }
    }
}