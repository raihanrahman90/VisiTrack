package com.visitrack.list

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.visitrack.R
import com.visitrack.core.domain.model.Camera
import com.visitrack.core.domain.model.Statistics
import com.visitrack.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpToolbarVisitrack()
        setUpToolbarTitleVisitrack(resources.getString((R.string.toolbar_visitrack)))

        viewModel = ViewModelProvider(
            this, ViewModelProvider.NewInstanceFactory()
        ).get(MainViewModel::class.java)
    }

    private fun display(statistics: Statistics) {
        with(binding) {
            binding.contentGeneral.tvVisitorCount.text = statistics.visitorCount.toString()
            binding.contentGeneral.tvCameraCount.text = statistics.cameraCount.toString()
            binding.contentGeneral.tvAnother.text = statistics.violationCount.toString()
        }
    }
    private fun setUpToolbarVisitrack(){
        setSupportActionBar(binding.visitrackToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun setUpToolbarTitleVisitrack(title: String){
        supportActionBar?.title = title
    }
}