package com.visitrack.core.data.remote.network

import com.visitrack.core.data.remote.model.LoginPost
import com.visitrack.core.data.remote.model.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("login")
    suspend fun login(@Body loginPost: LoginPost): LoginResponse
}