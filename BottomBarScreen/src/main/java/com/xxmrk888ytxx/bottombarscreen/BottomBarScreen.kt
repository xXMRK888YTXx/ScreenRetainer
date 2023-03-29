package com.xxmrk888ytxx.bottombarscreen

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import com.xxmrk888ytxx.bottombarscreen.models.BottomBarScreenModel
import com.xxmrk888ytxx.corecompose.theme.StyleComponents.StyleIcon
import com.xxmrk888ytxx.corecompose.theme.themeColors
import com.xxmrk888ytxx.corecompose.theme.themeDimensions
import com.xxmrk888ytxx.corecompose.theme.themeTypography
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BottomBarScreen(bottomBarScreens:List<BottomBarScreenModel>) {
    val pager = rememberPagerState()
    val scope = rememberCoroutineScope()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        backgroundColor = Color.Transparent,
        bottomBar = { BottomBar(
            bottomBarScreens = bottomBarScreens,
            currentPage = pager.currentPage,
            onScrollPage = {
                scope.launch { pager.animateScrollToPage(it) }
            }
        ) }
    ) {
        HorizontalPager(
            pageCount = bottomBarScreens.size,
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    start = it.calculateStartPadding(LocalLayoutDirection.current),
                    end = it.calculateEndPadding(LocalLayoutDirection.current),
                    top = it.calculateTopPadding(),
                    bottom = it.calculateBottomPadding()
                ),
            state = pager
        ) { screen ->
            bottomBarScreens[screen].content()
        }
    }
}

@SuppressLint("ResourceType")
@Composable
fun BottomBar(
    bottomBarScreens:List<BottomBarScreenModel>,
    currentPage:Int,
    onScrollPage:(Int) -> Unit
) {
    BottomNavigation(
        backgroundColor = themeColors.bottomBarColor,
    ) {
        bottomBarScreens.forEachIndexed { index, bottomBarScreenModel ->
            BottomNavigationItem(
                selected = index == currentPage,
                onClick = { onScrollPage(index) },
                icon = { Icon(
                    painter = painterResource(bottomBarScreenModel.icon),
                    contentDescription = null,
                    modifier = Modifier.size(themeDimensions.bottomIconSize)
                ) },
                selectedContentColor = themeColors.bottomBarSelectedContentColor,
                unselectedContentColor = themeColors.bottomBarUnselectedContentColor,
                label = {
                    Text(
                        text = bottomBarScreenModel.title,
                        style = themeTypography.bottomBar
                    )
                }
            )
        }
    }
}
