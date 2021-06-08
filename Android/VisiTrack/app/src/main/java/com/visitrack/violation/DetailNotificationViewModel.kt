package com.visitrack.violation

import androidx.lifecycle.ViewModel
import com.visitrack.core.domain.usecase.UseCase

class DetailNotificationViewModel(private val useCase: UseCase) : ViewModel() {

    fun getUpdateViolationNotification (id: String, status: Int) {
        useCase.updateViolation(id, status)
    }
}