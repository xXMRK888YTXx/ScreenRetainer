package com.xxmrk888ytxx.corecompose.theme.StyleComponents

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import com.xxmrk888ytxx.corecompose.theme.themeColors
import com.xxmrk888ytxx.corecompose.theme.themeTypography

@Composable
fun ButtonText(
    text:String,
    textStyle: TextStyle = themeTypography.button,
    color: Color = themeColors.primaryFontColor
) {
    Text(
        text = text,
        style = textStyle,
        color = color
    )
}