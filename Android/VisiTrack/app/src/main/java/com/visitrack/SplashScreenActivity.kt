package com.visitrack

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Toast
import com.google.firebase.messaging.FirebaseMessaging
import com.visitrack.core.utils.TokenPreference
import com.visitrack.databinding.ActivitySplashscreenBinding
import com.visitrack.list.MainActivity
import com.visitrack.start.ui.LoginActivity

class SplashScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashscreenBinding
    private lateinit var visitrackHandler: Handler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashscreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        //Subscribe Push Notification
        FirebaseMessaging.getInstance().subscribeToTopic("news")
        FirebaseMessaging.getInstance().token.addOnSuccessListener { deviceToken ->
            Log.d(MainActivity::class.java.simpleName, "Refreshed token: $deviceToken")
            Toast.makeText(this, "Refreshed token: $deviceToken", Toast.LENGTH_LONG).show()
            val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val token: ClipData = ClipData.newPlainText("token", deviceToken)
            clipboard.setPrimaryClip(token)
        }

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