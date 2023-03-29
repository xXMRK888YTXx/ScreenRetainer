package com.xxmrk888ytxx.corecompose.theme.ShareComponents

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.xxmrk888ytxx.corecompose.theme.AppTheme
import com.xxmrk888ytxx.corecompose.theme.LocalTheme
import com.xxmrk888ytxx.corecompose.theme.StyleComponents.ButtonText
import com.xxmrk888ytxx.corecompose.theme.themeColors
import com.xxmrk888ytxx.corecompose.theme.themeShapes
import com.xxmrk888ytxx.corecompose.theme.types.*

@Composable
fun YesNoButtons(
    modifier: Modifier,
    yesButtonText:String,
    noButtonText:String,
    isYesButtonEnabled:Boolean = true,
    onNoButtonClick:() -> Unit,
    onYesButtonClick:() -> Unit,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        OutlinedButton(
            onClick = onNoButtonClick,
            modifier = Modifier
                .weight(1f)
                .padding(start = 5.dp, end = 5.dp),
            shape = themeShapes.outlineButtonShape,
            colors = ButtonDefaults.buttonColors(
                backgroundColor = themeColors.cancelButtonColor,
            )
        ) {
            ButtonText(text = noButtonText)
        }


        OutlinedButton(
            colors = ButtonDefaults.buttonColors(
                backgroundColor = themeColors.yesButtonColor,
                disabledBackgroundColor = themeColors.yesButtonColor.copy(0.5f)
            ),
            onClick = onYesButtonClick,
            modifier = Modifier
                .weight(1f)
                .padding(start = 5.dp, end = 5.dp),
            shape = RoundedCornerShape(80),
            enabled = isYesButtonEnabled
        ) {
            ButtonText(text = yesButtonText)
        }
    }
}