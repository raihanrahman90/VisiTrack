package com.visitrack

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.visitrack.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpTollbarVisitrack()
        setUpTollbarTitleVisitrack(resources.getString((R.string.toolbar_visitrack)))
    }

    private fun setUpTollbarVisitrack(){
        setSupportActionBar(binding.visitrackToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun setUpTollbarTitleVisitrack(title: String){
        supportActionBar?.title = title
    }
}