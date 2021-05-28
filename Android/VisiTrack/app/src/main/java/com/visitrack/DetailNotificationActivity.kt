package com.visitrack

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.visitrack.databinding.ActivityDetailNotificationBinding

class DetailNotificationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailNotificationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailNotificationBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}