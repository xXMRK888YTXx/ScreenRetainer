package com.xxmrk888ytxx.packageinfoprovider

import android.content.Context
import android.content.Intent
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.graphics.drawable.Drawable
import com.xxmrk888ytxx.packageinfoprovider.models.AppInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


/**
 * [Ru]
 * Данный класс является реализацией интерфейса [PackageInfoProvider]
 * Для работы этого класс необходимо на версии android 11+ необходимо
 * разрешение [android.permission.QUERY_ALL_PACKAGES] (уже прописано в манифесте модуля)
 */

/**
 * [En]
 * This class is an implementation of the [PackageInfoProvider] interface
 * For this class to work, it is necessary on android version 11+
 * permission [android.permission.QUERY_ALL_PACKAGES] (already set in the module manifest)
 */
@Suppress("DEPRECATION")
class PackageInfoProvider @Inject constructor(
    private val context: Context,
) {

    /**
     * [Ru]
     *  Данная функция расширения предназначена для получения названия приложение
     *  по его имени пакета.
     */
    /**
     *  [En]
     *  This extension function is assigned to get the name of the application
     * by its package name.
     *
     */

    fun getAppName(packageName: String): String? {
        return try {
            val applicationInfo = context.packageManager.getApplicationInfo(packageName, 0)

            context.packageManager.getApplicationLabel(applicationInfo).toString()
        } catch (e: PackageManager.NameNotFoundException) {
            null
        }
    }

    /**
     * [Ru]
     *  Данная функция расширения предназначена для получения иконки приложение
     *  по его имени пакета.
     */
    /**
     *  [En]
     *  This extension function is designed to get the application icon
     * by its package name.
     */

    fun getAppIcon(packageName: String): Drawable? {
        return try {
            context.packageManager.getApplicationIcon(packageName)
        } catch (e: PackageManager.NameNotFoundException) {
            null
        }
    }

    /**
     * [Ru]
     * Вызвращает [Intent] для запуска приложения. Если его найти не удалось возвращает null
     *
     * @param packageName - Имя покета, у которого будет выполенен поиск стартого [Intent]
     */

    /**
     * [En]
     * Returns [Intent] to start the application. Returns null if not found
     *
     * @param packageName - The name of the package to search for the starting [Intent]
     */
    fun getLaunchIntent(packageName: String): Intent? =
        context.packageManager.getLaunchIntentForPackage(packageName)

    /**
     * [Ru]
     * Возвращает список приложений, у которых был найдет стартовый [Intent]
     */

    /**
     * [En]
     * Returns a list of applications that had a start [Intent]
     */
    suspend fun getAllApplicationInfoOnlyWithLaunchActivity(): List<AppInfo> =
        withContext(Dispatchers.Default) {
            val packages: List<ApplicationInfo> =
                context.packageManager.getInstalledApplications(PackageManager.GET_META_DATA)

            return@withContext packages
                .filter { getLaunchIntent(it.packageName) != null }
                .map {
                    val packageName = it.packageName
                    AppInfo(
                        appName = getAppName(packageName),
                        packageName = packageName,
                        icon = getAppIcon(packageName)
                    )
                }
        }

}