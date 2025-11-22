@file:Suppress("DEPRECATION")

package com.xxmrk888ytxx.eventdevicetracker

import android.accessibilityservice.AccessibilityService
import android.annotation.SuppressLint
import android.content.*
import android.view.accessibility.AccessibilityEvent
import com.xxmrk888ytxx.coredeps.DepsProvider.getDepsByApplication

@SuppressLint("AccessibilityPolicy")
internal class OpenAppChangedTrackerService : AccessibilityService() {

    private var lastTrackedPackageName = ""

    private val openAppChangedCallback : OpenAppChangedCallback by lazy {
        applicationContext.getDepsByApplication()
    }

    override fun onCreate() {
        super.onCreate()
        openAppChangedCallback.onTrackingStarted()
    }

    override fun onAccessibilityEvent(event: AccessibilityEvent) {
        if(!isEventValid(event)) return

        lastTrackedPackageName = event.packageName.toString()
        openAppChangedCallback.onOpenAppChanged(event.packageName.toString())
    }

    override fun onInterrupt() {}

    private fun isEventValid(event: AccessibilityEvent) : Boolean {
        val isCorrectType = event.eventType == AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED
        val isNotIgnorePackage = event.packageName !in openAppChangedCallback.params.ignoreList
        val isNewPackage = lastTrackedPackageName != event.packageName
        val isActivity = if(!openAppChangedCallback.params.isActivityOnly) true
            else isActivity(event)
        return isCorrectType && isNotIgnorePackage && isNewPackage && isActivity
    }

    private fun isActivity(event: AccessibilityEvent): Boolean {
        return try {
            val component = ComponentName(event.packageName.toString(),event.className.toString())

            packageManager.getActivityInfo(component,0).isEnabled
        } catch (e:Exception) {
            false
        }
    }
}