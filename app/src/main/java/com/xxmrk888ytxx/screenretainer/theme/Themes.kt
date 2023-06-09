package com.xxmrk888ytxx.screenretainer.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.W300
import androidx.compose.ui.text.font.FontWeight.Companion.W400
import androidx.compose.ui.text.font.FontWeight.Companion.W500
import androidx.compose.ui.text.font.FontWeight.Companion.W600
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
                bottomBar = TextStyle(
                    fontSize = 15.sp,
                    fontWeight = FontWeight.W500
                ),
                settingCategory = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = W300
                ),
                settingsParam = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = W400
                ),
                button = TextStyle(
                    fontSize = 15.sp,
                    fontWeight = W500
                ),
                selectDialog = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = W400
                ),
                yesNoDialog = TextStyle(
                    fontSize = 17.sp,
                    fontWeight = W500
                )
            )

        val shapes: Shapes
            get() = Shapes(
                cardShape = RoundedCornerShape(20.dp),
                buttonShape = RoundedCornerShape(15.dp),
                textFieldShape = RoundedCornerShape(10.dp),
                outlineButtonShape = RoundedCornerShape(80)
            )

        val dimensions: Dimensions
            get() = Dimensions(
                iconSize = 30.dp,
                inCardPadding = 10.dp,
                outCardPadding = 10.dp,
                appIconSize = 55.dp,
                searchFieldPaddings = 25.dp,
                bottomIconSize = 25.dp,
                settingsParamShape = 10.dp,
                categoryPadding = 15.dp,
                paddingBetweenLabelAndSettingsField = 10.dp
            )
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
                primaryColor = Color(0xFF5849C2),
                errorColor =  Color(0xFFC64851),
                bottomBarColor = Color(0xFF1B252D),
                bottomBarSelectedContentColor = Color(0xFF5849C2),
                bottomBarUnselectedContentColor = Color(0x99FFFFFF),
                disableColor = Color(0xFF303F4F),
                cancelButtonColor = Color(0xFF303F4F),
                yesButtonColor = Color(0xFF5849C2)
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