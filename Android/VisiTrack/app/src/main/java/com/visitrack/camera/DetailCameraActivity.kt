package com.visitrack.camera

import android.graphics.Camera
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.os.strictmode.Violation
import android.view.View
import com.bumptech.glide.Glide
import com.visitrack.R
import com.visitrack.core.data.Resource
import com.visitrack.databinding.ActivityDetailCameraBinding
import com.visitrack.list.MainActivity.Companion.EXTRA_ID
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

        val id = intent.getParcelableArrayExtra(EXTRA_ID)

        if (id != null) {
            getDetail(id.toString())
        }

    }

    private fun getDetail (id: String){
        viewModel.getDetailCamera(id).observe(this, { camera ->
            when(camera){
                is Resource.Loading -> {
                    binding.progressBar.visibility= View.VISIBLE
                }
                is Resource.Success -> {
                    binding.progressBar.visibility= View.GONE
                    val data = camera.data
                    Glide.with(this@DetailCameraActivity)
                        .load((data?.imageUrl))
                        .into(binding.ivCamera)
                    with(binding) {
                        tvName.text = data?.nameCamera
                        tvVisitorCount.text = data?.visitorCount.toString()
                        tvViolationCount.text = data?.violationCount.toString()
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