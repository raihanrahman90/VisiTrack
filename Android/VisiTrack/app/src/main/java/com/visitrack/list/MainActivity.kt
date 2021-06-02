package com.visitrack.list

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import com.bumptech.glide.Glide
import com.visitrack.R
import com.visitrack.core.data.Resource
import com.visitrack.core.domain.model.Camera
import com.visitrack.core.domain.model.Statistics
import com.visitrack.core.ui.CameraAdapter
import com.visitrack.core.ui.NotificationAdapter
import com.visitrack.core.utils.TokenPreference
import com.visitrack.databinding.ActivityMainBinding
import com.visitrack.start.ui.LoginActivity
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModel()
    private lateinit var notificationAdapter: NotificationAdapter
    private lateinit var cameraAdapter: CameraAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpToolbarVisitrack()
        setUpToolbarTitleVisitrack(resources.getString((R.string.toolbar_visitrack)))

        notificationAdapter = NotificationAdapter()
        cameraAdapter= CameraAdapter()

        getViolation()
        getNotification()
        getCamera()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_logout -> {
                val tokenPreferences = TokenPreference(this)
                viewModel.logout(tokenPreferences.getToken()!!).observe(this, { token ->
                    if (token.data?.success == true){
                        tokenPreferences.setToken("")
                        startActivity(Intent(this, LoginActivity::class.java))
                        finish()
                    }
                })
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
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
                        contentGeneral.tvVisitorCount.text = statistics?.visitorCount.toString()
                        contentGeneral.tvCameraCount.text = statistics?.cameraCount.toString()
                        contentGeneral.tvAnother.text = statistics?.violationCount.toString()
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
            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
            OrientationHelper.HORIZONTAL
            setHasFixedSize(true)
            adapter = cameraAdapter
        }
    }

    private fun setUpToolbarVisitrack(){
        setSupportActionBar(binding.visitrackToolbar)
//        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun setUpToolbarTitleVisitrack(title: String){
        supportActionBar?.title = title
    }
}