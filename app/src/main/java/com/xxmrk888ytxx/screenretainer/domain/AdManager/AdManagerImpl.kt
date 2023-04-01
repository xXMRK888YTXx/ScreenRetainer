package com.xxmrk888ytxx.screenretainer.domain.AdManager

import android.app.Activity
import android.content.Context
import com.xxmrk888ytxx.admobmanager.AdMobManager
import com.xxmrk888ytxx.screenretainer.R
import javax.inject.Inject

class AdManagerImpl @Inject constructor(
    private val adMobManager: AdMobManager,
    private val context: Context
) : AdManager {
    override fun initAdService() {
        adMobManager.initAdmob()
    }

    override fun showStartInterstitialAd(activity: Activity) {
        adMobManager.showInterstitialAd(context.getString(R.string.StartAppAd_key),activity)
    }
}