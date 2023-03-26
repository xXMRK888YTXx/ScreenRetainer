package com.xxmrk888ytxx.screenretainer.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Immutable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.xxmrk888ytxx.corecompose.theme.AppTheme
import com.xxmrk888ytxx.corecompose.theme.types.Colors
import com.xxmrk888ytxx.corecompose.theme.types.Dimensions
import com.xxmrk888ytxx.corecompose.theme.types.Shapes
import com.xxmrk888ytxx.corecompose.theme.types.Typography

object Themes {

    private object BaseValues {
        val typography: Typography
            get() = Typography(
                head = TextStyle(),
                body = TextStyle()
            )

        val shapes : Shapes
            get() = Shapes(
                cardShape = RoundedCornerShape(20.dp)
            )

        val dimensions : Dimensions
            get() = Dimensions(
                iconSize = 30.dp
            )
    }

    @Immutable
    object White : AppTheme {
        override val colors: Colors
            get() = Colors(
                background = white,
                statusBar = white,
                navigationBar = white,
                primaryFontColor = black,
                secondFontColor = black.copy(0.6f),
                iconsColor = black,
                cardColor = cardWhite
            )
        override val typography: Typography
            get() = BaseValues.typography
        override val shapes: Shapes
            get() = BaseValues.shapes
        override val dimensions: Dimensions
            get() = BaseValues.dimensions
    }

    @Immutable
    object Dark : AppTheme {
        override val colors: Colors
            get() = Colors(
                background = black,
                statusBar = black,
                navigationBar = black,
                primaryFontColor = white,
                secondFontColor = white.copy(0.6f),
                iconsColor = white,
                cardColor = cardBlack
            )
        override val typography: Typography
            get() = BaseValues.typography
        override val shapes: Shapes
            get() = BaseValues.shapes
        override val dimensions: Dimensions
            get() = BaseValues.dimensions
    }
}