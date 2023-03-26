package com.xxmrk888ytxx.screenretainer

import android.app.Application
import com.xxmrk888ytxx.screenretainer.DI.DaggerAppComponent

class App : Application() {

    val appComponent by lazy {
        DaggerAppComponent.factory().create(applicationContext)
    }
}