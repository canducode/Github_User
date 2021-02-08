package com.submission.githubuser.ui

import android.content.SharedPreferences
import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreference
import com.submission.githubuser.R
import com.submission.githubuser.service.alarm.AlarmReceiver

class SettingFragment : PreferenceFragmentCompat(), SharedPreferences.OnSharedPreferenceChangeListener{
    companion object {
        private const val DEFAULT_VALUE = false
    }

    private lateinit var reminderPreference: SwitchPreference
    private lateinit var redminder: String
    private lateinit var alarmReceiver: AlarmReceiver

    override fun onCreatePreferences(p0: Bundle?, p1: String?) {
        addPreferencesFromResource(R.xml.setting)
        init()
        setSummaries()
        alarmReceiver = AlarmReceiver()
    }

    private fun init() {
        redminder = resources.getString(R.string.key_reminder)
        reminderPreference = findPreference<SwitchPreference>(redminder) as SwitchPreference
    }

    private fun setSummaries() {
        val sh = preferenceManager.sharedPreferences
        reminderPreference.isChecked = sh.getBoolean(redminder, DEFAULT_VALUE)
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences, key: String?) {
        if (key == redminder) {
            if (sharedPreferences.getBoolean(redminder, DEFAULT_VALUE)) {
                alarmReceiver.setRepeatingReminder(this.context, getString(R.string.reminder_message))
            } else {
                alarmReceiver.cancelReminder(this.context)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        preferenceScreen.sharedPreferences.registerOnSharedPreferenceChangeListener(this)
    }

    override fun onPause() {
        super.onPause()
        preferenceScreen.sharedPreferences.unregisterOnSharedPreferenceChangeListener(this)
    }
}