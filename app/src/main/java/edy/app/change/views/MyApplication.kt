package edy.app.change.views

import android.content.SharedPreferences
import android.os.Handler
import androidx.multidex.MultiDexApplication
import androidx.preference.PreferenceManager
import edy.app.change.R
import edy.app.change.utilities.adapters.ThemeAdapter

class MyApplication : MultiDexApplication() {

    // TAG Variables
    private val TAG: String = MyApplication::class.java.name

    // Variables
    private val handler: Handler by lazy { Handler() }

    /**
     * onCreate
     */
    override fun onCreate() {
        super.onCreate()
        configTheme()
    }

    /**
     * InitTheme
     */
    private fun configTheme() {
        val preferences: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        val items = resources.getStringArray(R.array.app_theme)
        var runnable: Runnable? = null

        when (preferences.getString(getString(R.string.key_pref_theme), "Default")!!) {
            items[0] -> runnable = ThemeAdapter.setNightMode(0)
            items[1] -> runnable = ThemeAdapter.setNightMode(1)
            items[2] -> runnable = ThemeAdapter.setNightMode(2)
        }

        Handler().post(runnable)
    }

}