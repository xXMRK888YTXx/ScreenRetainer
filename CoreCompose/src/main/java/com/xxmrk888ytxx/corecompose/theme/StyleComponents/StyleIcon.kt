package com.xxmrk888ytxx.corecompose.theme.StyleComponents

import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import com.xxmrk888ytxx.corecompose.theme.themeColors
import com.xxmrk888ytxx.corecompose.theme.themeDimensions

@Composable
fun StyleIcon(icon:Painter,modifier: Modifier = Modifier) {
    Icon(
        painter = icon,
        contentDescription = "",
        tint = themeColors.iconsColor,
        modifier = Modifier.size(themeDimensions.iconSize).then(modifier)
    )
}