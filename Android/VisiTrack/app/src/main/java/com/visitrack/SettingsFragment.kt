package com.visitrack

import android.content.SharedPreferences
import android.os.Bundle
import androidx.preference.DropDownPreference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
import androidx.preference.SwitchPreference

class SettingsFragment : PreferenceFragmentCompat(), SharedPreferences.OnSharedPreferenceChangeListener {
    private lateinit var LANGUAGE: String
    private lateinit var NOTIFICATION: String
    private lateinit var languagePreferences: DropDownPreference
    private lateinit var notificationPreference: SwitchPreference

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)

        LANGUAGE = resources.getString(R.string.language_key)
        NOTIFICATION = resources.getString(R.string.notification_key)
        languagePreferences = findPreference<DropDownPreference>(LANGUAGE) as DropDownPreference
        notificationPreference = findPreference<SwitchPreference>(NOTIFICATION) as SwitchPreference

        val sh = preferenceManager.sharedPreferences
        languagePreferences.summary = sh.getString(LANGUAGE, "English")
        notificationPreference.isChecked = sh.getBoolean(NOTIFICATION, false)
    }

    override fun onResume() {
        super.onResume()
        preferenceScreen.sharedPreferences.registerOnSharedPreferenceChangeListener(this)
    }

    override fun onPause() {
        super.onPause()
        preferenceScreen.sharedPreferences.unregisterOnSharedPreferenceChangeListener(this)
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences, key: String?) {
        if (key == LANGUAGE) {
            languagePreferences.summary = sharedPreferences.getString(LANGUAGE, "English")
            when (PreferenceManager.getDefaultSharedPreferences(context).getString(LANGUAGE, "English")) {
                "English" -> {}
                "Bahasa Indonesia" -> {}
            }
        }
        if (key == NOTIFICATION) {
            notificationPreference.isChecked = sharedPreferences.getBoolean(NOTIFICATION, false)
            when (PreferenceManager.getDefaultSharedPreferences(context).getBoolean(NOTIFICATION, false)) {
                true -> {}
                false -> {}
            }
        }
    }
}