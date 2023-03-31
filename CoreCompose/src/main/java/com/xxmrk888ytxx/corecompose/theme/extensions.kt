package com.xxmrk888ytxx.corecompose.theme

import androidx.compose.runtime.Composable
import com.xxmrk888ytxx.corecompose.theme.types.*

val themeColors : Colors
    @Composable get() = LocalTheme.current.colors

val themeTypography : Typography
    @Composable get() = LocalTheme.current.typography

val themeShapes : Shapes
    @Composable get() = LocalTheme.current.shapes

val themeDimensions : Dimensions
    @Composable get() = LocalTheme.current.dimensions

val themeGradients : Gradients
    @Composable get() = LocalTheme.current.gradients