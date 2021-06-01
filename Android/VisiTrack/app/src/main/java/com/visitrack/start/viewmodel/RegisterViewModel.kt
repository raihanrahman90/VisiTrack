package com.visitrack.start.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.visitrack.core.data.Resource
import com.visitrack.core.domain.model.Success
import com.visitrack.core.domain.usecase.UseCase

class RegisterViewModel (private val useCase: UseCase) : ViewModel() {

    fun getRegister(username: String, password: String) : LiveData<Resource<Success>> =
        useCase.register(username, password).asLiveData()

}