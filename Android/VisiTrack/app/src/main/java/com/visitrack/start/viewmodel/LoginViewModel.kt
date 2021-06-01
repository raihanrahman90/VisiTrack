package com.visitrack.start.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.visitrack.core.data.Resource
import com.visitrack.core.domain.model.User
import com.visitrack.core.domain.usecase.UseCase
import com.visitrack.start.ui.LoginActivity

class LoginViewModel(private val useCase: UseCase) : ViewModel() {

    fun getLogin(username: String, password: String, token: String) : LiveData<Resource<User>> {
        return useCase.login(username, password, token).asLiveData()
    }
}