package com.visitrack.core.data.remote

import android.util.Log
import com.visitrack.core.data.remote.model.login.LoginPost
import com.visitrack.core.data.remote.model.login.LoginResponse
import com.visitrack.core.data.remote.model.register.RegisterPost
import com.visitrack.core.data.remote.model.register.RegisterResponse
import com.visitrack.core.data.remote.network.ApiResponse
import com.visitrack.core.data.remote.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.lang.Exception

class RemoteDataSource(private val apiService: ApiService) {
    suspend fun login(loginPost: LoginPost): Flow<ApiResponse<LoginResponse>> {
        return flow {
            try {
                val response = apiService.login(loginPost)
                if (response.success == "true"){
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Error(response.success))
                }
            } catch (e: Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e(RemoteDataSource::class.java.simpleName, e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun register(registerPost: RegisterPost): Flow<ApiResponse<RegisterResponse>> {
        return flow {
            try {
                val response = apiService.register(registerPost)
                if (response.success == "true"){
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Error(response.success))
                }
            } catch (e: Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e(RemoteDataSource::class.java.simpleName, e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}