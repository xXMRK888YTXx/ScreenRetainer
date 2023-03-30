package com.xxmrk888ytxx.screenretainer

import android.app.Application
import com.xxmrk888ytxx.coredeps.DepsProvider.DepsProvider
import com.xxmrk888ytxx.coredeps.Exceptions.DepsProviderNotFoundDeps
import com.xxmrk888ytxx.screenretainer.DI.DaggerAppComponent
import kotlin.reflect.KClass

class App : Application(),DepsProvider {

    val appComponent by lazy {
        DaggerAppComponent.factory().create(applicationContext)
    }

    private val depsMap:Map<KClass<*>,() -> Any> by lazy {
        mapOf(
            appComponent.openAppChangedCallback.toProvidedDeps(),
            appComponent.lockCurrentAppButtonClickedCallback.toProvidedDeps()
        )
    }

    @Suppress("UNCHECKED_CAST")
    override fun <DEPS : Any> provide(classType: KClass<DEPS>): DEPS {
        val value = depsMap[classType]?.invoke()

        if(value != null)
            return value as DEPS

        throw DepsProviderNotFoundDeps("DepsProvider cant provide ${classType.simpleName}")
    }

    private inline fun <reified T : Any> T.toProvidedDeps() : Pair<KClass<*>,() -> Any> {
        return Pair(T::class) { this }
    }

    private inline fun <reified TYPE : Any, LAZY : dagger.Lazy<TYPE>> LAZY.toProvidedDeps() : Pair<KClass<*>,() -> Any> {
        return Pair(TYPE::class) { this.get() }
    }
}