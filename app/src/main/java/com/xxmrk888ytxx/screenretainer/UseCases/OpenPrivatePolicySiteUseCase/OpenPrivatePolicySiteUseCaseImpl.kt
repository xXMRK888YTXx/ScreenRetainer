package com.xxmrk888ytxx.screenretainer.UseCases.OpenPrivatePolicySiteUseCase

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import com.xxmrk888ytxx.screenretainer.R
import com.xxmrk888ytxx.screenretainer.glue.SettingsScreen.StartActivityContractImpl
import javax.inject.Inject

class OpenPrivatePolicySiteUseCaseImpl @Inject constructor(
    private val context: Context
) : OpenPrivatePolicySiteUseCase {

    override fun execute() {
        val url = context.getString(R.string.Privacy)
        try {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            context.startActivity(browserIntent)
        }catch (e:Exception) {
            Log.e(StartActivityContractImpl.LOG_TAG,"Exception when try send ACTION_VIEW intent ${e.stackTraceToString()}")
        }
    }
}