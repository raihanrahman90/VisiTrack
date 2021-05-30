package com.visitrack.violation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.visitrack.R
import com.visitrack.core.data.Resource
import com.visitrack.core.domain.model.Camera
import com.visitrack.core.domain.model.Violation
import com.visitrack.databinding.ActivityDetailNotificationBinding
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@ExperimentalCoroutinesApi
@FlowPreview
class DetailNotificationActivity : AppCompatActivity() {

    private lateinit var viewModel: DetailNotificationViewModel
    private lateinit var binding: ActivityDetailNotificationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailNotificationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpToolbarVisitrack()
        setUpToolbarTitleVisitrack(resources.getString((R.string.toolbar_detail_visitrack)))

        viewModel = ViewModelProvider(
            this, ViewModelProvider.NewInstanceFactory()
        ).get(DetailNotificationViewModel::class.java)


//        binding.btnFinished.setOnClickListener{
//            viewModel.getUpdateViolationNotification()
//        }
//
//        binding.btnError.setOnClickListener{
//            viewModel.getUpdateViolationNotification()
//
//        }

        //getDetail(id)
    }

    private fun getDetail (id: Int){
        viewModel.getNotificationDetail(id).observe(this, { violation ->
            when(violation){
                is Resource.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    binding.progressBar.visibility = View.GONE
                    val violation = violation.data
                    Glide.with(this@DetailNotificationActivity)
                        .load((violation?.imageUrl))
                        .into(binding.ivNotification)
                    with(binding) {
                        binding.tvId.text = violation?.idViolation.toString()
                        binding.tvType.text = violation?.typeViolation
                        binding.tvCamera.text = violation?.camera.toString()
                        binding.tvDate.text = violation?.dateViolation
                        binding.tvTime.text = violation?.timeViolation
                        binding.tvDesc.text = violation?.descViolation
                        binding.tvStatus.text = violation?.statusViolation
                    }
                }
                is Resource.Error ->{
                    binding.progressBar.visibility = View.GONE
                }
            }
        })
    }
    private fun display(violation: Violation) {
        with(binding) {


        }
    }

    private fun setUpToolbarVisitrack(){
        setSupportActionBar(binding.visitrackDetailToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun setUpToolbarTitleVisitrack(title: String){
        supportActionBar?.title = title
    }
}