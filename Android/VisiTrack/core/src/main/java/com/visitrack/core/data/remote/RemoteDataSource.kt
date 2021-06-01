package com.visitrack.core.data.remote

import android.util.Log
import com.visitrack.core.data.remote.model.login.LoginBody
import com.visitrack.core.data.remote.model.login.LoginResponse
import com.visitrack.core.data.remote.model.logout.LogoutBody
import com.visitrack.core.data.remote.model.logout.LogoutResponse
import com.visitrack.core.data.remote.model.register.RegisterBody
import com.visitrack.core.data.remote.model.register.RegisterResponse
import com.visitrack.core.data.remote.network.ApiResponse
import com.visitrack.core.data.remote.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.lang.Exception

class RemoteDataSource(private val apiService: ApiService) {
    suspend fun login(loginBody: LoginBody): Flow<ApiResponse<LoginResponse>> {
        return flow {
            try {
                val response = apiService.login(loginBody)
                emit(ApiResponse.Success(response))
            } catch (e: Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e(RemoteDataSource::class.java.simpleName, e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun logout(logoutBody: LogoutBody): Flow<ApiResponse<LogoutResponse>> {
        return flow {
            try {
                val response = apiService.logout(logoutBody)
                emit(ApiResponse.Success(response))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e(RemoteDataSource::class.java.simpleName, e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun register(registerBody: RegisterBody): Flow<ApiResponse<RegisterResponse>> {
        return flow {
            try {
                val response = apiService.register(registerBody)
                emit(ApiResponse.Success(response))
            } catch (e: Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e(RemoteDataSource::class.java.simpleName, e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}