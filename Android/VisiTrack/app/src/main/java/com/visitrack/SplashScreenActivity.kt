package com.visitrack

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.visitrack.core.utils.TokenPreference
import com.visitrack.databinding.ActivitySplashscreenBinding
import com.visitrack.main.MainActivity
import com.visitrack.start.ui.LoginActivity

class SplashScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashscreenBinding
    private lateinit var visitrackHandler: Handler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashscreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        //For Login Preference
        val tokenPreference = TokenPreference(this)

        visitrackHandler = Handler(mainLooper)
        visitrackHandler.postDelayed({
            //Checking if already logged in or not
            if (tokenPreference.getToken().isNullOrBlank()){
                startActivity(Intent(this, LoginActivity::class.java))
            } else {
                startActivity(Intent(this, MainActivity::class.java))
            }
            finish()
        }, 3000)
    }
}