package com.visitrack.settings

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.edit
import com.visitrack.R
import com.visitrack.databinding.ActivitySettingsBinding

class SettingsActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivitySettingsBinding
    companion object {
        private const val Visitrack_REMINDER = "Visitrack_pref"
    }
    private lateinit var Visitrackpreference: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setNotification(this)

        setUpToolbarVisitrack()
        setUpToolbarTitleVisitrack(resources.getString((R.string.setting)))

        val changeLanguage: TextView = findViewById(R.id.textView3)
        changeLanguage.setOnClickListener(this)
//        supportFragmentManager.beginTransaction().add(R.id.lv_settings_holder, SettingsFragment()).commit()
    }

    private fun setUpToolbarVisitrack(){
        setSupportActionBar(binding.settingToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun setUpToolbarTitleVisitrack(title: String){
        supportActionBar?.title = title
    }

    private fun setNotification(context: Context) {
        Visitrackpreference = getSharedPreferences(Visitrack_REMINDER, Context.MODE_PRIVATE)
        binding.swDaily.apply {
            isChecked = Visitrackpreference.getBoolean(Visitrack_REMINDER, false)
            setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    Visitrackpreference.edit { putBoolean(Visitrack_REMINDER, true) }
                } else {
                    Visitrackpreference.edit { putBoolean(Visitrack_REMINDER, false) }
                }
            }
        }
    }

    override fun onClick (v: View?) {
        when (v?.id) {
            R.id.textView3 -> {
                val moveIntent = Intent(Settings.ACTION_LOCALE_SETTINGS)
                startActivity(moveIntent)
            }
        }
    }
}