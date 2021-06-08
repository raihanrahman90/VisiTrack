package com.visitrack.core.data.remote.network

import com.visitrack.core.data.remote.model.*
import com.visitrack.core.data.remote.model.login.LoginBody
import com.visitrack.core.data.remote.model.login.LoginResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @POST("login")
    suspend fun postLogin(@Body loginBody: LoginBody): LoginResponse

    @POST("logout")
    suspend fun postLogout(@Body logoutBody: LogoutBody): SuccessResponse

    @POST("register")
    suspend fun postRegister(@Body registerBody: RegisterBody): SuccessResponse

    @GET("statistik")
    suspend fun getStatistics(): StatisticsResponse

    @GET("pelanggaran")
    suspend fun getNotificationList(): ViolationResponse

    @GET("kamera")
    suspend fun getCameraList(): CameraResponse

    @GET("pelanggaran/{id}")
    suspend fun getViolationDetail(@Path("id")id: String): ViolationItem

    @POST("pelanggaran/{id}")
    suspend fun updateViolation(@Path("id")id: String, @Body violationBody: ViolationBody): SuccessResponse
}