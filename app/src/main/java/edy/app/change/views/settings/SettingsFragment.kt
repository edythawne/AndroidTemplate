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

class SettingsFragment : PreferenceFragmentCompat(), SharedPreferences.OnSharedPreferenceChangeListener {

    // TAG Variable
    private val TAG: String = SettingsFragment::class.java.name

    // Variables
    private lateinit var preferences: SharedPreferences

    /**
     * onResume
     */
    override fun onResume() {
        super.onResume()
        preferenceScreen.sharedPreferences.registerOnSharedPreferenceChangeListener(this)
    }

    /**
     * onPause
     */
    override fun onPause() {
        super.onPause()
        preferenceScreen.sharedPreferences.registerOnSharedPreferenceChangeListener(this)
    }

    /**
     * onCreatePreferences
     * @param savedInstanceState
     * @param rootKey
     */
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences_settings, rootKey)
        preferences = PreferenceManager.getDefaultSharedPreferences(requireContext())
        configPreferences()
    }

    /**
     * onViewCreated
     * @param view View
     * @param savedInstanceState Bundle?
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Toolbar Init
        configToolbar(view)
    }

    /**
     * Ha ocurrido un cambio en las preferencias de la aplicacion
     */
    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
        try {
            when (key) {
                getString(R.string.key_pref_theme) -> changePrefTheme(sharedPreferences!!)
            }
        } catch (ex: Exception) {
            Log.d(TAG, "Exception has been occurred ${ex.localizedMessage}")
        }
    }

    /**
     * configPreferences
     */
    private fun configPreferences() {
        prefTheme(null)
    }

    /**
     * Theme Preference
     */
    private fun prefTheme(shared: SharedPreferences?): String? {
        val preference: Preference = findPreference(getString(R.string.key_pref_theme))!!
        val item: String = if (shared == null) {
            preferences.getString(getString(R.string.key_pref_theme), "")!!
        } else {
            shared.getString(getString(R.string.key_pref_theme), "")!!
        }

        preference.summary = item
        return item
    }

    /**
     * changePrefTheme
     * @param shared SharedPreferences
     */
    private fun changePrefTheme(shared: SharedPreferences) {
        val selected = prefTheme(shared)
        val items = requireContext().resources.getStringArray(R.array.app_theme)

        when (selected) {
            items[0] -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
            items[1] -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY)
            items[2] -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }
    }

    /**
     * initToolbar
     * @param view View
     */
    private fun configToolbar(view: View) {
        view.findViewById<MaterialToolbar>(R.id.tlr)
            .setNavigationOnClickListener {
                NavHostFragment.findNavController(this)
                    .navigateUp()
            }
    }
}