package com.visitrack.camera


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.visitrack.R
import com.visitrack.core.domain.model.Camera
import com.visitrack.databinding.ActivityDetailCameraBinding
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@ExperimentalCoroutinesApi
@FlowPreview
class DetailCameraActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailCameraBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailCameraBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpToolbarVisitrack()
        setUpToolbarTitleVisitrack(resources.getString((R.string.toolbar_camera_visitrack)))

        val data = intent.getParcelableExtra<Camera>(EXTRA_ID)

        if (data != null) {
            getDetail(data)
        }
    }

    private fun getDetail(data: Camera) {
        binding.progressBar.visibility= View.GONE
        Glide.with(this@DetailCameraActivity)
            .load((data.imageUrl))
            .into(binding.ivCamera)
        with(binding) {
            tvName.text = data.nameCamera
            tvVisitorCount.text = data.visitorCount.toString()
            tvViolationCount.text = data.violationCount.toString()
        }
    }

    private fun setUpToolbarVisitrack(){
        setSupportActionBar(binding.visitrackCameraToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun setUpToolbarTitleVisitrack(title: String){
        supportActionBar?.title = title
    }

    companion object {
        const val EXTRA_ID = "extraId"
    }
}