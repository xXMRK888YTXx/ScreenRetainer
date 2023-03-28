package com.xxmrk888ytxx.screenretainer.share

import android.annotation.SuppressLint
import android.content.Context
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import com.xxmrk888ytxx.coreandroid.DepsProvider.ToastManager
import javax.inject.Inject

internal class ToastManagerImpl @Inject constructor(
    private val context:Context
) : ToastManager {

    private val handler = Handler(Looper.getMainLooper())

    override fun showToast(text: String) {
        handler.post { Toast.makeText(context, text, Toast.LENGTH_SHORT).show() }
    }

    @SuppressLint("ResourceType")
    override fun showToast(resId: Int) {
        val string = context.resources.getString(resId)

        showToast(string)
    }
}