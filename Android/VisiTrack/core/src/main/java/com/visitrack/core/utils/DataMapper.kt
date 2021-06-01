package com.visitrack.core.utils

import com.visitrack.core.data.remote.model.login.LoginResponse
import com.visitrack.core.data.remote.model.logout.LogoutResponse
import com.visitrack.core.data.remote.model.register.RegisterResponse
import com.visitrack.core.domain.model.User

object DataMapper {
    fun mapLoginResponseToUser(input: LoginResponse): User {
        return User(input.success, input.token)
    }

    fun mapLogoutResponseToUser(input: LogoutResponse): User {
        return User(input.success, token = null)
    }

    fun mapRegisterResponseToUser(input: RegisterResponse): User {
        return User(input.success, token = null)
    }
}