package com.xxmrk888ytxx.screenretainer.glue.SettingsScreen

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import com.xxmrk888ytxx.screenretainer.R
import com.xxmrk888ytxx.screenretainer.UseCases.OpenPrivatePolicySiteUseCase.OpenPrivatePolicySiteUseCase
import com.xxmrk888ytxx.screenretainer.UseCases.OpenTermsOfUseSiteUseCase.OpenTermsOfUseSiteUseCase
import com.xxmrk888ytxx.settingsscreen.contracts.StartActivityContract
import javax.inject.Inject

class StartActivityContractImpl @Inject constructor(
    private val context: Context,
    private val openPrivatePolicySiteUseCase: OpenPrivatePolicySiteUseCase,
    private val openTermsOfUseSiteUseCase: OpenTermsOfUseSiteUseCase
) : StartActivityContract {

    private fun openSite(url:String) {
        try {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url)).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
            }
            context.startActivity(browserIntent)
        }catch (e:Exception) {
            Log.e(LOG_TAG,"Exception when try send ACTION_VIEW intent ${e.stackTraceToString()}")
        }
    }

    override fun openSiteWithSourceCode() {
        val url = context.getString(R.string.Source_code_url)
        openSite(url)
    }

    override fun openSendEmailActivity() {
        try {
            val email = context.getString(R.string.my_email)
            val chooserDescription = context.getString(R.string.Choose_a_client)

            val emailIntent = Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:$email"))
            val chooserIntent = Intent.createChooser(emailIntent,chooserDescription).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
            }
            context.startActivity(chooserIntent)
        }catch (e:Exception) {
            Log.e(LOG_TAG,"Exception when try send ACTION_SENDTO intent ${e.stackTraceToString()}")
        }
    }

    override fun openSiteWithPrivacyPolicy() {
        openPrivatePolicySiteUseCase.execute()
    }

    override fun openSiteWithTermsUse() {
        openTermsOfUseSiteUseCase.execute()
    }

    companion object {
        const val LOG_TAG = "StartActivityContractImpl"
    }

}