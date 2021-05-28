package com.visitrack

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.visitrack.databinding.ActivityDetailCameraBinding

class DetailCameraActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailCameraBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailCameraBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}