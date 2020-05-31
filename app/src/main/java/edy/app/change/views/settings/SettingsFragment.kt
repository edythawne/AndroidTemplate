package edy.app.change.views.settings

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.fragment.NavHostFragment
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
import com.google.android.material.appbar.MaterialToolbar
import edy.app.change.R

class SettingsFragment : PreferenceFragmentCompat(),
    SharedPreferences.OnSharedPreferenceChangeListener {

    // TAG Variable
    private val TAG: String = SettingsFragment::class.java.name

    // Variables
    private lateinit var preferences: SharedPreferences

    /**
     * Estado en Resume
     */
    override fun onResume() {
        super.onResume()
        preferenceScreen.sharedPreferences.registerOnSharedPreferenceChangeListener(this)
    }

    /**
     * Estado en Pausa
     */
    override fun onPause() {
        super.onPause()
        preferenceScreen.sharedPreferences.registerOnSharedPreferenceChangeListener(this)
    }

    /**
     * onCreatePreferences
     *
     * @param savedInstanceState
     * @param rootKey
     */
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences_settings, rootKey)
        initPreferences()
    }

    /**
     * onViewCreated
     * @param view View
     * @param savedInstanceState Bundle?
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Toolbar Init
        initToolbar(view)
    }

    /**
     * Ha ocurrido un cambio en las preferencias de la aplicacion
     */
    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
        try {
            when (key) {
                getString(R.string.key_pref_theme) -> preferenceAppThemeSelected(
                    sharedPreferences!!
                )
            }
        } catch (ex: Exception) {
            Log.d(TAG, "Exception has been occurred ${ex.localizedMessage}")
        }
    }

    /**
     * Iniciarlizar Preferencia
     */
    private fun initPreferences() {
        preferences = PreferenceManager.getDefaultSharedPreferences(activity)

        // Preference AppTheme
        initPreferenceAppTheme(null)
    }

    /**
     * Iniciar Preferencia de Tema
     */
    private fun initPreferenceAppTheme(sharedPreferences: SharedPreferences?): String? {
        var appThemePreferenceSelected: String? = null
        val appThemePreferenceKey = getString(R.string.key_pref_theme)
        val appThemePreference = findPreference<Preference>(appThemePreferenceKey)


        appThemePreferenceSelected = if (sharedPreferences == null) {
            preferences.getString(appThemePreferenceKey, "")
        } else {
            sharedPreferences.getString(appThemePreferenceKey, "")
        }

        appThemePreference!!.summary = appThemePreferenceSelected
        return appThemePreferenceSelected
    }

    /**
     * SetTheme
     */
    private fun setTheme(mode: Int) {
        AppCompatDelegate.setDefaultNightMode(mode)
    }

    /**
     * preferenceAppThemeSelected
     */
    private fun preferenceAppThemeSelected(sharedPreferences: SharedPreferences) {
        val optionSelected = initPreferenceAppTheme(sharedPreferences)
        val listAppTheme = requireContext().resources.getStringArray(R.array.app_theme)

        when (optionSelected) {
            listAppTheme[0] -> setTheme(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
            listAppTheme[1] -> setTheme(AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY)
            listAppTheme[2] -> setTheme(AppCompatDelegate.MODE_NIGHT_YES)
        }
    }

    /**
     * initToolbar
     * @param view View
     */
    private fun initToolbar(view: View) {
        val toolbar: MaterialToolbar = view.findViewById(R.id.tlr)
        toolbar.setNavigationOnClickListener {
            NavHostFragment.findNavController(this).navigateUp()
        }
    }
}