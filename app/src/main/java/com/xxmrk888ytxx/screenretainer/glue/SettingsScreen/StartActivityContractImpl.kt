package com.xxmrk888ytxx.screenretainer.glue.SettingsScreen

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import com.xxmrk888ytxx.screenretainer.R
import com.xxmrk888ytxx.settingsscreen.contracts.StartActivityContract
import javax.inject.Inject

class StartActivityContractImpl @Inject constructor(
    private val context: Context
) : StartActivityContract {

    private fun openSite(url:String) {
        try {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
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
            context.startActivity(Intent.createChooser(emailIntent,chooserDescription))
        }catch (e:Exception) {
            Log.e(LOG_TAG,"Exception when try send ACTION_SENDTO intent ${e.stackTraceToString()}")
        }
    }

    override fun openSiteWithPrivacyPolicy() {
        val url = context.getString(R.string.Privacy)
        openSite(url)
    }

    override fun openSiteWithTermsUse() {
        val url = context.getString(R.string.Terms)
        openSite(url)
    }

    companion object {
        const val LOG_TAG = "StartActivityContractImpl"
    }

}