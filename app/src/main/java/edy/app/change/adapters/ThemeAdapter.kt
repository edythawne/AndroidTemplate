package edy.app.change.adapters

import android.content.Context
import androidx.appcompat.app.AppCompatDelegate

object ThemeAdapter {

    // TAG
    private val TAG: String = ThemeAdapter::class.java.name

    /**
     *
     * @param mode Int
     */
    fun setNightMode(mode: Int) {
        val runnable: Thread = Thread(Runnable {
            when (mode) {
                0 -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
                1 -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY)
                2 -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
        })

        runnable.start()
        runnable.join()
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