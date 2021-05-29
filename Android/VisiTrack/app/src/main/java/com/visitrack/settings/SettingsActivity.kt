package com.visitrack.settings

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.visitrack.R
import com.visitrack.databinding.ActivitySettingsBinding

class SettingsActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySettingsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction().add(R.id.lv_settings_holder, SettingsFragment()).commit()
    }
}