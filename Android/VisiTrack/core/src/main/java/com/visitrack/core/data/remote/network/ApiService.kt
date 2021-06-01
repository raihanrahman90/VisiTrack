package com.visitrack.core.data.remote.network

import com.visitrack.core.data.remote.model.login.LoginPost
import com.visitrack.core.data.remote.model.login.LoginResponse
import com.visitrack.core.data.remote.model.register.RegisterPost
import com.visitrack.core.data.remote.model.register.RegisterResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @POST("login")
    suspend fun login(@Body loginPost: LoginPost): LoginResponse

    @POST("register")
    suspend fun register(@Body registerPost: RegisterPost): RegisterResponse

    //@GET("statistik")
    //suspend fun getStatistics():
}