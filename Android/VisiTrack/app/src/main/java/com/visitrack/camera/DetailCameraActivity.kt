package com.visitrack.camera

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.visitrack.R
import com.visitrack.core.data.Resource
import com.visitrack.core.domain.model.Camera
import com.visitrack.databinding.ActivityDetailCameraBinding

class DetailCameraActivity : AppCompatActivity() {

    private lateinit var viewModel: DetailCameraViewModel
    private lateinit var binding: ActivityDetailCameraBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailCameraBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpToolbarVisitrack()
        setUpToolbarTitleVisitrack(resources.getString((R.string.toolbar_camera_visitrack)))

        viewModel = ViewModelProvider(
            this,ViewModelProvider.NewInstanceFactory()
        ).get(DetailCameraViewModel::class.java)


    }
    private fun display(camera:Camera) {
        with(binding) {
            Glide.with(this@DetailCameraActivity)
                .load((camera.imageUrl))
                .into(binding.ivCamera)
            binding.tvId.text = camera.idCamera.toString()
            binding.tvName.text = camera.nameCamera
            binding.tvDesc.text = camera.descCamera
            binding.tvVisitorCount.text = camera.visitorCount.toString()
            binding.tvViolationCount.text = camera.violationCount.toString()
        }
    }

    private fun setUpToolbarVisitrack(){
        setSupportActionBar(binding.visitrackCameraToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun setUpToolbarTitleVisitrack(title: String){
        supportActionBar?.title = title
    }
}