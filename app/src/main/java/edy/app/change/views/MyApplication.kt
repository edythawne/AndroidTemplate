package edy.app.change.views

import android.content.SharedPreferences
import androidx.multidex.MultiDexApplication
import androidx.preference.PreferenceManager
import edy.app.change.R
import edy.app.change.adapters.ThemeAdapter

class MyApplication : MultiDexApplication() {

    // TAG Variables
    private val TAG: String = MyApplication::class.java.name

    /**
     * onCreate
     */
    override fun onCreate() {
        super.onCreate()
        initTheme()
    }

    /**
     * InitTheme
     */
    private fun initTheme() {
        val preferences: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        val listAppThemes = resources.getStringArray(R.array.app_theme)

        when (preferences.getString(getString(R.string.key_pref_theme), "Default")!!) {
            listAppThemes[0] -> ThemeAdapter.setNightMode(0)
            listAppThemes[1] -> ThemeAdapter.setNightMode(1)
            listAppThemes[2] -> ThemeAdapter.setNightMode(2)
        }
    }

}