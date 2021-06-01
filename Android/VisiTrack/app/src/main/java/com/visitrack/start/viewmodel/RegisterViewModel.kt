package com.visitrack.start.viewmodel

import androidx.lifecycle.ViewModel
import com.visitrack.core.domain.model.User
import com.visitrack.core.domain.usecase.UseCase

class RegisterViewModel (private val useCase: UseCase) : ViewModel() {

    fun getRegister(user: User) {
        //useCase.register(user)
    }
}