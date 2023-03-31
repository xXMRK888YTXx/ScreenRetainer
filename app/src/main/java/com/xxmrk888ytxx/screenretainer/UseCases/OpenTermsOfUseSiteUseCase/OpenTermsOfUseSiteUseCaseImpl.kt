package com.xxmrk888ytxx.screenretainer.UseCases.OpenTermsOfUseSiteUseCase

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import com.xxmrk888ytxx.screenretainer.R
import com.xxmrk888ytxx.screenretainer.glue.SettingsScreen.StartActivityContractImpl
import javax.inject.Inject

class OpenTermsOfUseSiteUseCaseImpl @Inject constructor(
    private val context: Context
) : OpenTermsOfUseSiteUseCase {

    override fun execute() {
        val url = context.getString(R.string.Terms)

        try {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            context.startActivity(browserIntent)
        }catch (e:Exception) {
            Log.e(StartActivityContractImpl.LOG_TAG,"Exception when try send ACTION_VIEW intent ${e.stackTraceToString()}")
        }
    }
}