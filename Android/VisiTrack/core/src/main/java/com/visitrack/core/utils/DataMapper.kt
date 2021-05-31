package com.visitrack.core.utils

import com.visitrack.core.data.remote.model.LoginResponse
import com.visitrack.core.domain.model.User

object DataMapper {
    fun mapLoginResponseToUser(input: LoginResponse): User {
        return User(input.success, input.token)
    }
}