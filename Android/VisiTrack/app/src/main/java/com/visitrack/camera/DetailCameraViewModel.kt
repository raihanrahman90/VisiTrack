package com.visitrack.camera

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.visitrack.core.data.Resource
import com.visitrack.core.domain.model.Camera
import com.visitrack.core.domain.usecase.UseCase

class DetailCameraViewModel(private val useCase: UseCase) : ViewModel() {

    fun getDetailCamera(id: Int): LiveData<Resource<Camera>> =
        useCase.getCameraDetail(id).asLiveData()

}