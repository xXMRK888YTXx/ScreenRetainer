package com.xxmrk888ytxx.quicksettingsservice

interface ButtonActiveStateController {

    suspend fun enableButton()

    suspend fun disableButton()
}