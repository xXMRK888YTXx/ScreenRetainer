package com.xxmrk888ytxx.screenretainer.domain.LocalizationManager

import android.annotation.SuppressLint
import androidx.annotation.IdRes
import com.xxmrk888ytxx.screenretainer.R
import java.util.*

@SuppressLint("ResourceType")
sealed class SupportedLanguage(@IdRes val title:Int,val code:String)  {
    
    object System : SupportedLanguage(R.string.System,"xx")
    
    object EN : SupportedLanguage(R.string.English,Locale.ENGLISH.language)

    object RU : SupportedLanguage(R.string.Russian,"ru")

    companion object {
        val localeList = listOf(
            System,
            EN,
            RU
        )
    }
}