package com.xxmrk888ytxx.applistscreen

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.xxmrk888ytxx.applistscreen.models.AppInfoModel
import com.xxmrk888ytxx.applistscreen.models.NeededPermissionModel
import com.xxmrk888ytxx.applistscreen.models.ScreenState
import com.xxmrk888ytxx.corecompose.theme.*
import com.xxmrk888ytxx.corecompose.theme.ShareComponents.GradientButton
import com.xxmrk888ytxx.corecompose.theme.ShareComponents.LazySpacer
import com.xxmrk888ytxx.corecompose.theme.StyleComponents.BodyText
import com.xxmrk888ytxx.corecompose.theme.StyleComponents.StyleCard
import com.xxmrk888ytxx.corecompose.theme.StyleComponents.HeadText

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
    val appList = appListViewModel.appList.collectAsState()
    val searchText = appListViewModel.searchLineText.collectAsState()

    Column(Modifier.fillMaxSize()) {

        SearchLine(value = searchText.value, onChangeValue = appListViewModel::onSearchTextUpdated)

        LazySpacer(10)

        LazyColumn(modifier = Modifier.fillMaxSize()) {
            itemsIndexed(appList.value) { index,item ->
                AppItem(item,index != appList.value.lastIndex)
            }
        }
    }
}

@Composable
internal fun SearchLine(value:String,onChangeValue:(String) -> Unit) {
    val focusManager = LocalFocusManager.current
    OutlinedTextField(
        value = value,
        onValueChange = onChangeValue,
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = themeDimensions.searchFieldPaddings,
                end = themeDimensions.searchFieldPaddings
            ),
        singleLine = true,
        label = {
            Text(
                text = stringResource(R.string.Search),
                style = themeTypography.body
            )
        },
        shape = themeShapes.textFieldShape,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = themeColors.primaryFontColor,
            placeholderColor = themeColors.primaryFontColor.copy(0.6f),
            focusedBorderColor = themeColors.primaryColor,
            unfocusedBorderColor = themeColors.primaryFontColor,
            unfocusedLabelColor = themeColors.primaryFontColor,
            focusedLabelColor = themeColors.primaryColor
        ),
        textStyle = themeTypography.body.copy(
            color = themeColors.primaryFontColor
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                focusManager.clearFocus()
            }
        )
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LazyItemScope.AppItem(appInfo: AppInfoModel,isShowSeparateLine:Boolean) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(themeDimensions.outCardPadding)
            .animateItemPlacement(),
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(bottom = themeDimensions.inCardPadding),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            AsyncImage(
                model = appInfo.appIcon ?: R.drawable.default_icon,
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(themeDimensions.appIconSize)
            )

            LazySpacer(width = 15)

            BodyText(
                text = appInfo.appName ?: "",
                textAlign = TextAlign.Center,
                color = themeColors.primaryFontColor
            )
        }

        LazySpacer(5)

        if(isShowSeparateLine) {
            Spacer(modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .height(1.dp))
        }
    }
}

@Composable
internal fun RequestPermissionState(appListViewModel: AppListViewModel) {
    val neededPermissions = appListViewModel.neededPermissionList.collectAsState()
    val requestAdminPermissionContract = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult(),
        onResult = {}
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
                    text = stringResource(R.string.Required_permissions),
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
                    text = if(!it.isGranted) stringResource(R.string.Grant)
                    else stringResource(R.string.Granted),
                    color = if(!it.isGranted) themeColors.primaryFontColor
                    else themeColors.errorColor
                )
            }
        }
    }
}

@Composable
internal fun LoadingAppListState() {
    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HeadText(text = stringResource(R.string.Gathering_information_about_applications), textAlign = TextAlign.Center)

        LazySpacer(5)

        BodyText(text = stringResource(R.string.Please_wait))

        LazySpacer(15)

        CircularProgressIndicator(
            color = themeColors.primaryColor
        )
    }
}