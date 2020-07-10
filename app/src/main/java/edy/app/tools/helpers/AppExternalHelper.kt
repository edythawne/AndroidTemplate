package edy.app.tools.helpers

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.net.Uri

object AppExternalHelper {
    // TAG
    private val TAG: String = AppExternalHelper::class.java.name

    /**
     * Buscar una aplicacion en Play Store
     * @param context Context
     * @param packageName String
     */
    fun searchOnPlayStore(context: Context, packageName: String) {
        try {
            context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=${packageName}")))
        } catch (ex: ActivityNotFoundException) {
            context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=${packageName}")))
        }
    }

    /**
     * Check App Installed
     * @param context Context
     * @return Boolean
     */
    fun verifyAppInstall(context: Context, packageName: String): Boolean {
        return try {
            val packageInfo: PackageInfo = context.packageManager.getPackageInfo(packageName, 0)
            true
        } catch (ex: PackageManager.NameNotFoundException) {
            false
        }
    }
}