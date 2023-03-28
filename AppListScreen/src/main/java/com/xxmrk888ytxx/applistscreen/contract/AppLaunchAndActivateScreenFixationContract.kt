package com.xxmrk888ytxx.applistscreen.contract

import com.xxmrk888ytxx.applistscreen.Exceptions.LaunchActivityNotFoundException

interface AppLaunchAndActivateScreenFixationContract {
    @kotlin.jvm.Throws(LaunchActivityNotFoundException::class)
    fun launchAppAndFixation(packageName:String)
}