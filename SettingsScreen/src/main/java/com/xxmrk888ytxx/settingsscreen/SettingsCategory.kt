package com.xxmrk888ytxx.settingsscreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.xxmrk888ytxx.corecompose.theme.ShareComponents.LazySpacer
import com.xxmrk888ytxx.corecompose.theme.StyleComponents.BodyText
import com.xxmrk888ytxx.corecompose.theme.themeColors
import com.xxmrk888ytxx.corecompose.theme.themeDimensions
import com.xxmrk888ytxx.corecompose.theme.themeTypography
import com.xxmrk888ytxx.settingsscreen.models.SettingsParamShape
import com.xxmrk888ytxx.settingsscreen.models.SettingsParamType
import kotlinx.collections.immutable.ImmutableList

@Composable
internal fun SettingsCategory(categoryName: String, settingsParams: ImmutableList<SettingsParamType>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        Text(
            text = categoryName,
            color = themeColors.secondFontColor,
            style = themeTypography.settingCategory
        )

        LazySpacer(height = themeDimensions.paddingBetweenLabelAndSettingsField)

        settingsParams.forEachIndexed  { index, param ->

            val shape = if (settingsParams.visibleParamsSize == 1) SettingsParamShape.AllShape
            else when (index) {
                0 -> SettingsParamShape.TopShape
                settingsParams.visibleParamsLastIndex -> SettingsParamShape.BottomShape
                else -> SettingsParamShape.None
            }

            SettingsParam(param, shape)

        }

        LazySpacer(height = themeDimensions.categoryPadding)
    }
}

internal inline val List<SettingsParamType>.visibleParamsSize : Int
    get() {
        return this.filter { it.isVisible }.size
    }

internal inline val List<SettingsParamType>.visibleParamsLastIndex : Int
    get() = this.filter { it.isVisible }.lastIndex