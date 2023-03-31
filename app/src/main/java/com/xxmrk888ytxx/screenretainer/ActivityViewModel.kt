package com.xxmrk888ytxx.screenretainer

import android.app.Activity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.xxmrk888ytxx.coredeps.SharedInterfaces.ActivityLifecycleCallback.ActivityLifecycleCallback
import com.xxmrk888ytxx.coredeps.SharedInterfaces.ActivityLifecycleCallback.ActivityLifecycleRegister
import javax.inject.Inject
import javax.inject.Provider

@Suppress("UNCHECKED_CAST")
class ActivityViewModel @Inject constructor(

) : ViewModel() {

    class Factory @Inject constructor(
        private val activityViewModel: Provider<ActivityViewModel>
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return activityViewModel.get() as T
        }
    }

    private val activityLifecycleCallbacks:MutableSet<ActivityLifecycleCallback> = mutableSetOf()

    fun registerCallback(activityLifecycleCallback: ActivityLifecycleCallback,activity: Activity) {
        if(activityLifecycleCallbacks.add(activityLifecycleCallback))
            activityLifecycleCallback.onRegister(activity)
    }

    fun unregisterCallback(activityLifecycleCallback: ActivityLifecycleCallback) {
        activityLifecycleCallbacks.remove(activityLifecycleCallback)
    }

    fun onCreate(activity: Activity) {
        activityLifecycleCallbacks.forEach { it.onCreate(activity) }
    }

    fun onStart() {
        activityLifecycleCallbacks.forEach { it.onStart() }
    }

    fun onResume() {
        activityLifecycleCallbacks.forEach { it.onResume() }
    }

    fun onPause() {
        activityLifecycleCallbacks.forEach { it.onPause() }
    }

    fun onStop() {
        activityLifecycleCallbacks.forEach { it.onStop() }
    }

    fun onDestroy() {
        activityLifecycleCallbacks.forEach { it.onDestroy() }
    }

}