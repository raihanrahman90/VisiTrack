package com.visitrack.list

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.visitrack.R
import com.visitrack.core.data.Resource
import com.visitrack.core.domain.model.Camera
import com.visitrack.core.domain.model.Statistics
import com.visitrack.core.ui.CameraAdapter
import com.visitrack.core.ui.NotificationAdapter
import com.visitrack.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var notificationAdapter: NotificationAdapter
    private lateinit var cameraAdapter: CameraAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpToolbarVisitrack()
        setUpToolbarTitleVisitrack(resources.getString((R.string.toolbar_visitrack)))

        viewModel = ViewModelProvider(
            this, ViewModelProvider.NewInstanceFactory()
        ).get(MainViewModel::class.java)

        notificationAdapter = NotificationAdapter()
        cameraAdapter= CameraAdapter()

        getViolation()
        getNotification()
        getCamera()

    }

    private fun getViolation(){
        viewModel.getViolationStatistic().observe(this, { statistic ->
            when(statistic){
                is Resource.Loading ->{
                    binding.progressBar.visibility= View.VISIBLE
                    binding.progressBar.visibility= View.GONE
                }
                is Resource.Success ->{
                    binding.progressBar.visibility= View.VISIBLE
                    binding.progressBar.visibility= View.GONE
                    val statistics = statistic.data
                    with(binding) {
                        binding.contentGeneral.tvVisitorCount.text = statistics?.visitorCount.toString()
                        binding.contentGeneral.tvCameraCount.text = statistics?.cameraCount.toString()
                        binding.contentGeneral.tvAnother.text = statistics?.violationCount.toString()
                    }
                }
                is Resource.Error ->{
                    binding.progressBar.visibility = View.GONE
                    binding.progressBar.visibility = View.VISIBLE
                }
            }
        })
    }

    private fun getNotification(){
        viewModel.getListNotification().observe(this, { notification ->
            when (notification){
                is Resource.Loading ->{
                    binding.progressBar.visibility = View.VISIBLE
                }

                is Resource.Success -> {
                    binding.progressBar.visibility = View.GONE
                    notification.data?.let { notificationAdapter.setData(it) }
                }
                is Resource.Error -> {
                    binding.progressBar.visibility = View.GONE
                }

            }
        })

        with(binding.contentNotification.rvNotification){
            layoutManager = LinearLayoutManager(this@MainActivity)
            setHasFixedSize(true)
            adapter = notificationAdapter
        }
    }

    private fun getCamera(){
        viewModel.getListCamera().observe(this, { camera ->
            when (camera){
                is Resource.Loading ->{
                    binding.progressBar.visibility = View.VISIBLE
                }

                is Resource.Success -> {
                    binding.progressBar.visibility = View.GONE
                    camera.data?.let { cameraAdapter.setData(it) }
                }
                is Resource.Error -> {
                    binding.progressBar.visibility = View.GONE
                }

            }
        })
        with(binding.contentCamera.rvNotification){
            layoutManager = LinearLayoutManager(this@MainActivity)
            setHasFixedSize(true)
            adapter = cameraAdapter
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