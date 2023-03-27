package com.xxmrk888ytxx.applistscreen

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.xxmrk888ytxx.applistscreen.models.NeededPermissionModel
import com.xxmrk888ytxx.applistscreen.models.ScreenState
import com.xxmrk888ytxx.corecompose.theme.ShareComponents.GradientButton
import com.xxmrk888ytxx.corecompose.theme.ShareComponents.LazySpacer
import com.xxmrk888ytxx.corecompose.theme.StyleComponents.BodyText
import com.xxmrk888ytxx.corecompose.theme.StyleComponents.StyleCard
import com.xxmrk888ytxx.corecompose.theme.StyleComponents.HeadText
import com.xxmrk888ytxx.corecompose.theme.themeColors
import com.xxmrk888ytxx.corecompose.theme.themeDimensions
import com.xxmrk888ytxx.corecompose.theme.themeGradients
import com.xxmrk888ytxx.corecompose.theme.themeShapes

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
    val neededPermissions = appListViewModel.neededPermissionList.collectAsState()
    val requestAdminPermissionContract = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult(),
        onResult = {
            appListViewModel.onAdminPermissionRequested()
        }
    )

    LaunchedEffect(key1 = requestAdminPermissionContract, block = {
        appListViewModel.initRequestAdminPermissionLauncher(requestAdminPermissionContract)
    })

    DisposableEffect(key1 = Unit, effect = {
        onDispose { appListViewModel.clearRequestAdminPermissionLauncher() }
    })

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
                .padding(themeDimensions.outCardPadding),
        ) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(themeDimensions.inCardPadding),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                HeadText(
                    text = "Требуемые разрешения",
                    textAlign = TextAlign.Center
                )

                LazySpacer(10)

                NeededPermissionWidget(
                    neededPermissions = neededPermissions.value
                )
            }
        }
    }
}

@Composable
internal fun NeededPermissionWidget(neededPermissions: List<NeededPermissionModel>) {
    neededPermissions.forEach {

        BodyText(text = it.title, textAlign = TextAlign.Center,color = themeColors.primaryFontColor)

        GradientButton(
            backgroundGradient = if(!it.isGranted) themeGradients.primaryGradient
            else themeGradients.disableGradient,
            onClick = it.onRequest,
            enabled = !it.isGranted,
            shape = themeShapes.buttonShape,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .border(
                    width = 1.dp,
                    brush = if (!it.isGranted) themeGradients.primaryGradient
                    else themeGradients.disableGradient, RoundedCornerShape(15.dp)
                )
        ) {
            Row(
                Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                HeadText(
                    text = if(!it.isGranted) "Предоставить"
                    else "Предоставлено",
                    color = themeColors.primaryFontColor
                )
            }
        }
    }
}

@Composable
internal fun LoadingAppListState() {

}