package com.xxmrk888ytxx.corecompose.theme.StyleComponents

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import com.xxmrk888ytxx.corecompose.theme.themeColors
import com.xxmrk888ytxx.corecompose.theme.themeTypography

@Composable
fun BodyText(
    text:String,
    modifier: Modifier = Modifier,
    textAlign: TextAlign = TextAlign.Start,
    color: Color = themeColors.secondFontColor
) {
    Text(
        text = text,
        modifier = modifier,
        style = themeTypography.body.copy(
            color = color,
            textAlign = textAlign
        )
    )
}