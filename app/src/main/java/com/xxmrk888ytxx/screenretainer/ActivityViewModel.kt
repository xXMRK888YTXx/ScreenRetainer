package com.xxmrk888ytxx.screenretainer

import android.app.Activity
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.xxmrk888ytxx.admobmanager.ConsentFormLoader
import com.xxmrk888ytxx.coredeps.SharedInterfaces.ActivityLifecycleCallback.ActivityLifecycleCallback
import com.xxmrk888ytxx.coredeps.SharedInterfaces.ActivityLifecycleCallback.ActivityLifecycleRegister
import com.xxmrk888ytxx.screenretainer.UseCases.OpenPrivatePolicySiteUseCase.OpenPrivatePolicySiteUseCase
import com.xxmrk888ytxx.screenretainer.UseCases.OpenTermsOfUseSiteUseCase.OpenTermsOfUseSiteUseCase
import com.xxmrk888ytxx.screenretainer.domain.AdManager.AdManager
import com.xxmrk888ytxx.screenretainer.domain.AgreeDialogManager.AgreeDialogManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider


@Suppress("UNCHECKED_CAST")
class ActivityViewModel @Inject constructor(
    private val agreeDialogManager: AgreeDialogManager,
    private val openTermsOfUseSiteUseCase: OpenTermsOfUseSiteUseCase,
    private val openPrivatePolicySiteUseCase: OpenPrivatePolicySiteUseCase,
    private val adManager: AdManager,
) : ViewModel() {

    class Factory @Inject constructor(
        private val activityViewModel: Provider<ActivityViewModel>,
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return activityViewModel.get() as T
        }
    }

    private var isConsentChecked:Boolean = false

    fun loadConsentForm(activity: Activity) {
        if(isConsentChecked) return

        isConsentChecked = true

        val logTag = "ConsentFormLoader"

        val loader = ConsentFormLoader.create(
            activity,
            BuildConfig.DEBUG,
            true
        )

        loader.checkFormState(
            onFormPrepared = {
                Log.i(logTag, "onFormPrepared")

                loader.loadAndShowForm(
                    onSuccessLoad = {
                        Log.i(logTag, "onSuccessLoad")
                    },
                    onLoadError = {
                        Log.e(logTag, "onLoadError")

                    },
                    onDismissed = {
                        Log.i(logTag, "onDismissed")
                    }
                )
            },
            onFormNotAvailable = {
                Log.e(logTag, "onFormNotAvailable")
            },
            onError = {
                Log.e(logTag, "onError")
            }
        )
    }

    private val activityLifecycleCallbacks: MutableSet<ActivityLifecycleCallback> = mutableSetOf()

    fun registerCallback(activityLifecycleCallback: ActivityLifecycleCallback, activity: Activity) {
        if (activityLifecycleCallbacks.add(activityLifecycleCallback))
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

    internal val isNeedShowAgreeDialog = agreeDialogManager.isNeedShowDialog
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), false)

    fun hideAgreeDialogForever() {
        viewModelScope.launch { agreeDialogManager.hideForever() }
    }

    fun openTermsOfUse() {
        openTermsOfUseSiteUseCase.execute()
    }

    fun openPrivacyPolicy() {
        openPrivatePolicySiteUseCase.execute()
    }

    fun initAdService() {
        adManager.initAdService()
    }

    fun showStartAd(activity: Activity) {
        adManager.showStartInterstitialAd(activity)
    }

}