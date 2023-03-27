package com.xxmrk888ytxx.screenretainer.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.xxmrk888ytxx.corecompose.theme.AppTheme
import com.xxmrk888ytxx.corecompose.theme.types.*

object Themes {

    private object BaseValues {
        val typography: Typography
            get() = Typography(
                head = TextStyle(
                    fontSize = 22.sp,
                    fontWeight = FontWeight.W800,
                ),
                body = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.W600
                ),
            )

        val shapes: Shapes
            get() = Shapes(
                cardShape = RoundedCornerShape(20.dp),
                buttonShape = RoundedCornerShape(15.dp),
                textFieldShape = RoundedCornerShape(10.dp)
            )

        val dimensions: Dimensions
            get() = Dimensions(
                iconSize = 30.dp,
                inCardPadding = 10.dp,
                outCardPadding = 10.dp,
                appIconSize = 55.dp,
                searchFieldPaddings = 25.dp
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
                cardColor = cardWhite,
                primaryColor = Color(0xFF5849C2)
            )
        override val typography: Typography
            get() = BaseValues.typography
        override val shapes: Shapes
            get() = BaseValues.shapes
        override val dimensions: Dimensions
            get() = BaseValues.dimensions
        override val gradients: Gradients
            get() = TODO("Not yet implemented")
    }

    @Immutable
    object Dark : AppTheme {
        override val colors: Colors
            get() = Colors(
                background = Color(0xFF1B252D),
                statusBar = Color(0xFF1B252D),
                navigationBar = Color(0xFF1B252D),
                primaryFontColor = Color(0xFFFFFFFF),
                secondFontColor = Color.Gray,
                iconsColor = Color(0xFFFFFFFF),
                cardColor = Color(0xFF25313D),
                primaryColor = Color(0xFF5849C2)
            )
        override val typography: Typography
            get() = BaseValues.typography
        override val shapes: Shapes
            get() = BaseValues.shapes
        override val dimensions: Dimensions
            get() = BaseValues.dimensions
        override val gradients: Gradients
            get() = Gradients(
                primaryGradient = Brush.linearGradient(
                    listOf(
                        Color(0xFF5849C2),
                        Color(0xFF4871CC)
                    )
                ),

                disableGradient = Brush.linearGradient(
                    listOf(
                        Color(0xFF25313D),
                        Color(0xFF25313D)
                    )
                )
            )
    }
}