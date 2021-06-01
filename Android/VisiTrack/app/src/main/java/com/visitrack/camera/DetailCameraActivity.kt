package com.visitrack.camera

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.visitrack.R
import com.visitrack.core.data.Resource
import com.visitrack.databinding.ActivityDetailCameraBinding
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import org.koin.android.viewmodel.ext.android.viewModel

@ExperimentalCoroutinesApi
@FlowPreview
class DetailCameraActivity : AppCompatActivity() {

    private val viewModel: DetailCameraViewModel by viewModel()
    private lateinit var binding: ActivityDetailCameraBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailCameraBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpToolbarVisitrack()
        setUpToolbarTitleVisitrack(resources.getString((R.string.toolbar_camera_visitrack)))

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