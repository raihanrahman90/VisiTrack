package com.visitrack.core.data.remote.network

import com.visitrack.core.data.remote.model.logout.LogoutBody
import com.visitrack.core.data.remote.model.login.LoginBody
import com.visitrack.core.data.remote.model.login.LoginResponse
import com.visitrack.core.data.remote.model.logout.LogoutResponse
import com.visitrack.core.data.remote.model.register.RegisterBody
import com.visitrack.core.data.remote.model.register.RegisterResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("login")
    suspend fun login(@Body loginBody: LoginBody): LoginResponse

    @POST("logout")
    suspend fun logout(@Body logoutBody: LogoutBody): LogoutResponse

    @POST("register")
    suspend fun register(@Body registerBody: RegisterBody): RegisterResponse

    //@GET("statistik")
    //suspend fun getStatistics():
}