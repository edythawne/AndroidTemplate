package edy.app.change.utilities.adapters

import android.content.Context
import androidx.appcompat.app.AppCompatDelegate

object ThemeAdapter {

    // TAG
    private val TAG: String = ThemeAdapter::class.java.name

    /**
     * setNightMode
     * @param mode Int
     * @return Runnable
     */
    fun setNightMode(mode: Int): Runnable {
        return Runnable {
            when (mode) {
                0 -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
                1 -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY)
                2 -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
        }
    }

    /**
     * getNightModeStatus
     * @param context
     * @return
     */
    fun getNightModeStatus(context: Context): Int {
        return context.resources.configuration.uiMode
    }

}