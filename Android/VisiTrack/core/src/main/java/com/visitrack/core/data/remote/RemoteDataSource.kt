package com.visitrack.core.data.remote

import android.util.Log
import com.visitrack.core.data.remote.model.LoginPost
import com.visitrack.core.data.remote.model.LoginResponse
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
}