package com.xxmrk888ytxx.applistscreen

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.xxmrk888ytxx.applistscreen.models.ScreenState
import com.xxmrk888ytxx.corecompose.theme.StyleComponents.StyleCard
import com.xxmrk888ytxx.corecompose.theme.StyleComponents.StyleHeadText

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AppListScreen(appListViewModel: AppListViewModel) {
    val screenState = appListViewModel.screenState.collectAsState()

    AnimatedContent(targetState = screenState.value) { state ->
        when (state) {
            is ScreenState.RequestPermission -> RequestPermissionState(appListViewModel)
            is ScreenState.LoadingAppList -> LoadingAppListState()
            is ScreenState.AppList -> AppList(appListViewModel)
        }
    }
}

@Composable
internal fun AppList(appListViewModel: AppListViewModel) {

}

@Composable
internal fun RequestPermissionState(appListViewModel: AppListViewModel) {
    Column(
        Modifier
            .fillMaxSize()
            .background(Color.Transparent),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        StyleCard(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
        ) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {

            }
        }
    }
}

@Composable
internal fun LoadingAppListState() {

}