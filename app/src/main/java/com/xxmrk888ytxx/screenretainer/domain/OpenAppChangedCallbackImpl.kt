package com.xxmrk888ytxx.screenretainer.domain

import com.xxmrk888ytxx.eventdevicetracker.OpenAppChangedCallback
import javax.inject.Inject

class OpenAppChangedCallbackImpl @Inject constructor(

) : OpenAppChangedCallback {
    override fun onOpenAppChanged(packageName: String) {
        println(packageName)
    }
}