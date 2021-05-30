package com.visitrack.camera

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.visitrack.R
import com.visitrack.core.data.Resource
import com.visitrack.databinding.ActivityDetailCameraBinding
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@ExperimentalCoroutinesApi
@FlowPreview
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

        //getDetail(id)

    }

    private fun getDetail (id: Int){
        viewModel.getDetailCamera(id).observe(this, { camera ->
            when(camera){
                is Resource.Loading -> {
                    binding.progressBar.visibility= View.VISIBLE
                }
                is Resource.Success -> {
                    binding.progressBar.visibility= View.GONE
                    val camera = camera.data
                    Glide.with(this@DetailCameraActivity)
                        .load((camera?.imageUrl))
                        .into(binding.ivCamera)
                    with(binding) {
                        binding.tvId.text = camera?.idCamera.toString()
                        binding.tvName.text = camera?.nameCamera
                        binding.tvDesc.text = camera?.descCamera
                        binding.tvVisitorCount.text = camera?.visitorCount.toString()
                        binding.tvViolationCount.text = camera?.violationCount.toString()
                    }
                }
                is Resource.Error ->{
                    binding.progressBar.visibility = View.GONE
                }
            }
        })
    }

    private fun setUpToolbarVisitrack(){
        setSupportActionBar(binding.visitrackCameraToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun setUpToolbarTitleVisitrack(title: String){
        supportActionBar?.title = title
    }
}