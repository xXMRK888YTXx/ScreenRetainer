package com.xxmrk888ytxx.screenretainer.domain.AdManager

import android.app.Activity

interface AdManager {

    fun initAdService()

    fun showStartInterstitialAd(activity: Activity)
}