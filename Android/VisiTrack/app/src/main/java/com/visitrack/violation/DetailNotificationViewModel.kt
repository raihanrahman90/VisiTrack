package com.visitrack.violation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.visitrack.core.data.Resource
import com.visitrack.core.domain.model.Camera
import com.visitrack.core.domain.model.Violation
import com.visitrack.core.domain.usecase.UseCase
import kotlinx.coroutines.flow.Flow

class DetailNotificationViewModel(private val useCase: UseCase) : ViewModel() {

    fun getNotificationDetail(id: Int): LiveData<Resource<Violation>> =
        useCase.getViolationDetail(id).asLiveData()

    fun getUpdateViolationNotification (status: String) {
        useCase.updateViolation(status)
    }
}