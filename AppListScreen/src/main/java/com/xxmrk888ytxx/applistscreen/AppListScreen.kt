package com.xxmrk888ytxx.applistscreen

import android.annotation.SuppressLint
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.xxmrk888ytxx.applistscreen.models.AppInfoModel
import com.xxmrk888ytxx.applistscreen.models.DialogStates.AddQuickButtonDialogState
import com.xxmrk888ytxx.applistscreen.models.DialogStates.BatteryIgnoreOptimizationDialogState
import com.xxmrk888ytxx.applistscreen.models.DialogStates.WarmingAccessibilityPermissionDialogState
import com.xxmrk888ytxx.applistscreen.models.DialogStates.WarmingAdminPermissionDialogState
import com.xxmrk888ytxx.applistscreen.models.NeededPermissionModel
import com.xxmrk888ytxx.applistscreen.models.ScreenState
import com.xxmrk888ytxx.corecompose.theme.*
import com.xxmrk888ytxx.corecompose.theme.ShareComponents.GradientButton
import com.xxmrk888ytxx.corecompose.theme.ShareComponents.LazySpacer
import com.xxmrk888ytxx.corecompose.theme.ShareComponents.YesNoDialog
import com.xxmrk888ytxx.corecompose.theme.StyleComponents.BodyText
import com.xxmrk888ytxx.corecompose.theme.StyleComponents.StyleCard
import com.xxmrk888ytxx.corecompose.theme.StyleComponents.HeadText
import com.xxmrk888ytxx.corecompose.theme.StyleComponents.StyleIcon
import kotlinx.collections.immutable.ImmutableList

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AppListScreen(appListViewModel: AppListViewModel) {
    val screenState = appListViewModel.screenState.collectAsState()
    val dialogState = appListViewModel.dialogState.collectAsState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        backgroundColor = Color.Transparent

    ) {
        AnimatedContent(
            targetState = screenState.value,
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    start = it.calculateStartPadding(LocalLayoutDirection.current),
                    end = it.calculateEndPadding(LocalLayoutDirection.current),
                    top = it.calculateTopPadding(),
                    bottom = it.calculateBottomPadding()
                )
        ) { state ->
            when (state) {
                is ScreenState.RequestPermission -> RequestPermissionState(appListViewModel)
                is ScreenState.LoadingAppList -> LoadingAppListState()
                is ScreenState.AppList -> AppList(appListViewModel)
            }
        }
    }

    if (dialogState.value.warmingAdminPermissionDialogState is WarmingAdminPermissionDialogState.Visible) {
        WarmingAdminPermissionDialog(appListViewModel)
    }

    if (dialogState.value.warmingAccessibilityPermissionDialogState is WarmingAccessibilityPermissionDialogState.Visible) {
        WarmingAccessibilityPermissionDialog(appListViewModel)
    }

    if(dialogState.value.batteryIgnoreOptimizationDialogState is BatteryIgnoreOptimizationDialogState.Visible) {
        BatteryIgnoreOptimizationDialog(appListViewModel)
    }

    if (dialogState.value.addQuickButtonDialogState is AddQuickButtonDialogState.Visible) {
        AddQuickButtonDialog(appListViewModel)
    }
}

@Composable
fun WarmingAccessibilityPermissionDialog(appListViewModel: AppListViewModel) {
    YesNoDialog(
        text = buildString {
            append(stringResource(R.string.First_part_Accessibility_permission_description) + "\n\n")
            append(stringResource(R.string.This_permission_will_be_used) + "\n\n")
            append(stringResource(R.string.first_item) + "\n\n")
            append(stringResource(R.string.The_information_collected_is_not_saved))
            append(stringResource(R.string.For_the_application_to_work_you_must_agree))
        },
        confirmButtonText = stringResource(R.string.OK),
        cancelButtonText = stringResource(R.string.Cancel),
        onCancel = appListViewModel::hideWarmingAccessibilityPermissionDialog,
        onConfirm = {
            appListViewModel.requestAccessibilityPermission()
            appListViewModel.hideWarmingAccessibilityPermissionDialog()
        }
    )
}

@Composable
fun WarmingAdminPermissionDialog(appListViewModel: AppListViewModel) {
    YesNoDialog(
        text = buildString {
            append(stringResource(R.string.Warning) + "\n\n")
            append(stringResource(R.string.Admin_permission_warming))
        },
        confirmButtonText = stringResource(R.string.OK),
        cancelButtonText = stringResource(R.string.Cancel),
        onCancel = appListViewModel::hideWarmingAdminPermissionDialog,
        onConfirm = {
            appListViewModel.requestAdminPermission()
            appListViewModel.hideWarmingAdminPermissionDialog()
        }
    )
}

@Composable
internal fun AppList(appListViewModel: AppListViewModel) {
    val appList = appListViewModel.appList.collectAsState()
    val searchText = appListViewModel.searchLineText.collectAsState()

    LaunchedEffect(Unit) {
        appListViewModel.showAddQuickButtonDialog()
    }

    Column(Modifier.fillMaxSize()) {

        SearchLine(value = searchText.value, onChangeValue = appListViewModel::onSearchTextUpdated)

        LazySpacer(10)

        LazyColumn(modifier = Modifier.fillMaxSize()) {
            itemsIndexed(appList.value, key = { index, item ->
                item.appPackageName
            }) { index, item ->
                AppItem(appInfo = item,
                    isShowSeparateLine = index != appList.value.lastIndex,
                    onActivateFixation = appListViewModel::activateFixation,
                    onChangeFavoriteState = {
                        if (it) {
                            appListViewModel.removeInFavorite(item.appPackageName)
                        } else {
                            appListViewModel.addInFavorite(item.appPackageName)
                        }
                    }
                )
            }
        }
    }
}

@Composable
internal fun SearchLine(value: String, onChangeValue: (String) -> Unit) {
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
fun LazyItemScope.AppItem(
    appInfo: AppInfoModel,
    isShowSeparateLine: Boolean,
    onActivateFixation: (String) -> Unit,
    onChangeFavoriteState: (Boolean) -> Unit,
) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(themeDimensions.outCardPadding)
            .animateItem()
            .clickable {
                onActivateFixation(appInfo.appPackageName)
            },
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

            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd) {
                IconButton(onClick = {
                    onChangeFavoriteState(appInfo.isFavorite)
                }) {
                    StyleIcon(
                        icon = painterResource(id = R.drawable.star),
                        tint = if (appInfo.isFavorite) themeColors.primaryColor
                        else themeColors.secondFontColor
                    )
                }
            }
        }

        LazySpacer(5)

        if (isShowSeparateLine) {
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .height(1.dp)
            )
        }
    }
}

@Composable
internal fun RequestPermissionState(appListViewModel: AppListViewModel) {
    val neededPermissions by appListViewModel.neededPermissionList.collectAsState()
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
                    neededPermissions = neededPermissions
                )
            }
        }
    }
}

@SuppressLint("ResourceType")
@Composable
internal fun NeededPermissionWidget(neededPermissions: ImmutableList<NeededPermissionModel>) {
    neededPermissions.forEach {
        BodyText(
            text = stringResource(id = it.title),
            textAlign = TextAlign.Center,
            color = themeColors.primaryFontColor
        )

        GradientButton(
            backgroundGradient = if (!it.isGranted) themeGradients.primaryGradient
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
                    text = if (!it.isGranted) stringResource(R.string.Grant)
                    else stringResource(R.string.Granted),
                    color = if (!it.isGranted) themeColors.primaryFontColor
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
        HeadText(
            text = stringResource(R.string.Gathering_information_about_applications),
            textAlign = TextAlign.Center
        )

        LazySpacer(5)

        BodyText(text = stringResource(R.string.Please_wait))

        LazySpacer(15)

        CircularProgressIndicator(
            color = themeColors.primaryColor
        )
    }
}



@Composable
fun AddQuickButtonDialog(appListViewModel: AppListViewModel) {
    val notShowInFuture = rememberSaveable {
        mutableStateOf(false)
    }

    YesNoDialog(
        confirmButtonText = stringResource(R.string.OK),
        cancelButtonText = stringResource(R.string.Cancel),
        onCancel = { appListViewModel.hideAddQuickButtonDialog(notShowInFuture.value) },
        onConfirm = {
            appListViewModel.openAndroidDialogForAddQuickSettingsButton()
            appListViewModel.hideAddQuickButtonDialog(true)
        }
    ) {
        Text(
            text = stringResource(R.string.for_faster_access_to_the_lock_function_you_can_add_a_quick_lock_button_to_the_notification_bar_when_you_click_on_it_the_application_you_are_currently_in_will_be_fixed_would_you_like_to_try_it),
            color = themeColors.primaryFontColor,
            style = themeTypography.yesNoDialog,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = notShowInFuture.value,
                onCheckedChange = { notShowInFuture.value = it },
                colors = CheckboxDefaults.colors(
                    checkedColor = themeColors.primaryColor,
                    uncheckedColor = themeColors.primaryColor
                )
            )

            Text(
                text = stringResource(R.string.Dont_show_any_more),
                color = themeColors.primaryFontColor,
                style = themeTypography.yesNoDialog,
            )
        }
    }
}


@Composable
fun BatteryIgnoreOptimizationDialog(appListViewModel: AppListViewModel) {
    val notShowInFuture = rememberSaveable {
        mutableStateOf(false)
    }

    YesNoDialog(
        confirmButtonText = stringResource(R.string.OK),
        cancelButtonText = stringResource(R.string.Cancel),
        onCancel = { appListViewModel.hideBatteryIgnoreOptimizationDialog(notShowInFuture.value) },
        onConfirm = {
            appListViewModel.openIgnoreBatteryOptimizationSettings()
            appListViewModel.hideBatteryIgnoreOptimizationDialog(notShowInFuture.value)
        }
    ) {
        Text(
            text = stringResource(R.string.Ignore_battery_dialog_text),
            color = themeColors.primaryFontColor,
            style = themeTypography.yesNoDialog,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = notShowInFuture.value,
                onCheckedChange = { notShowInFuture.value = it },
                colors = CheckboxDefaults.colors(
                    checkedColor = themeColors.primaryColor,
                    uncheckedColor = themeColors.primaryColor
                )
            )

            Text(
                text = stringResource(R.string.Dont_show_any_more),
                color = themeColors.primaryFontColor,
                style = themeTypography.yesNoDialog,
            )
        }
    }
}