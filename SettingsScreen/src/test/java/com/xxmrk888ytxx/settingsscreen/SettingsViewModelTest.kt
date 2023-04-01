@file:OptIn(ExperimentalCoroutinesApi::class)

package com.xxmrk888ytxx.settingsscreen

import com.xxmrk888ytxx.settingsscreen.contracts.LanguageManageContract
import com.xxmrk888ytxx.settingsscreen.contracts.ProvideAppInfoContract
import com.xxmrk888ytxx.settingsscreen.contracts.RemoveAppContract
import com.xxmrk888ytxx.settingsscreen.contracts.StartActivityContract
import com.xxmrk888ytxx.settingsscreen.models.AppLanguage
import com.xxmrk888ytxx.settingsscreen.models.DialogState.LanguageDialogState
import io.mockk.*
import kotlinx.coroutines.*
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SettingsViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val provideAppInfoContract: ProvideAppInfoContract = mockk(relaxed = true)
    private val languageManageContract: LanguageManageContract = mockk(relaxed = true)
    private val startActivityContract: StartActivityContract = mockk(relaxed = true)
    private val removeAppContract: RemoveAppContract = mockk(relaxed = true)


    lateinit var viewModel:SettingsViewModel

    @Before
    fun init() {
        viewModel = spyk(SettingsViewModel(
            provideAppInfoContract,
            languageManageContract,
            startActivityContract,
            removeAppContract
        ))
    }

    @Test
    fun checkViewModelReturnsAppVersionEqualsProvider() = runTest {
        val appVersion = "test"

        every { provideAppInfoContract.appVersion } returns appVersion

        Assert.assertEquals(appVersion,viewModel.getAppVersion())
    }

    @Test
    fun checkWhatViewModelOpenActivity() {
        viewModel.openSendEmailActivity()
        verify { startActivityContract.openSendEmailActivity() }

        viewModel.openSiteWithPrivacyPolicy()
        verify { startActivityContract.openSiteWithPrivacyPolicy() }

        viewModel.openSiteWithSourceCode()
        verify { startActivityContract.openSiteWithSourceCode() }

        viewModel.openSiteWithTermsUse()
        verify { startActivityContract.openSiteWithTermsUse() }
    }

    @Test
    fun checkLanguageDialogMethods() = runTest {

        GlobalScope.launch {
            viewModel.dialogState.collect {

            }
        }

        val languageList = listOf(AppLanguage("test","test"),AppLanguage("test2","test2"))
        every { languageManageContract.supportedLanguage } returns languageList
        every { languageManageContract.currentLanguage } returns languageList[0]

        Assert.assertTrue(viewModel.dialogState.value.languageDialogState is LanguageDialogState.Hidden)

        viewModel.showLanguageDialog()
        println(viewModel.dialogState.value)
        Assert.assertTrue(viewModel.dialogState.value.languageDialogState is LanguageDialogState.Showed)
        val currentLanguage = (viewModel.dialogState.value.languageDialogState as LanguageDialogState.Showed).currentSelectedLanguage
        Assert.assertEquals(languageList[0],currentLanguage)

        viewModel.changeCurrentSelectedLanguage(languageList[1])
        val currentLanguage2 = (viewModel.dialogState.value.languageDialogState as LanguageDialogState.Showed).currentSelectedLanguage
        Assert.assertEquals(languageList[1],currentLanguage2)

        viewModel.changeCurrentLanguage()
        verify(exactly = 1) { languageManageContract.setupLanguage(languageList[1]) }
    }
}