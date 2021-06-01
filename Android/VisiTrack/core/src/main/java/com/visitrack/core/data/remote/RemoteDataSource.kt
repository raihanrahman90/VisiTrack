package com.visitrack.core.data.remote

import android.util.Log
import com.visitrack.core.data.remote.model.*
import com.visitrack.core.data.remote.model.login.LoginBody
import com.visitrack.core.data.remote.model.login.LoginResponse
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
                val response = apiService.postLogin(loginBody)
                emit(ApiResponse.Success(response))
            } catch (e: Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e(TAG, e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun logout(logoutBody: LogoutBody): Flow<ApiResponse<SuccessResponse>> {
        return flow {
            try {
                val response = apiService.postLogout(logoutBody)
                emit(ApiResponse.Success(response))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e(TAG, e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun register(registerBody: RegisterBody): Flow<ApiResponse<SuccessResponse>> {
        return flow {
            try {
                val response = apiService.postRegister(registerBody)
                emit(ApiResponse.Success(response))
            } catch (e: Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e(TAG, e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
    
    suspend fun getStatistics(): Flow<ApiResponse<StatisticsResponse>> {
        return flow { 
            try {
                val response = apiService.getStatistics()
                emit(ApiResponse.Success(response))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e(TAG, e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getNotificationList(): Flow<ApiResponse<ViolationResponse>> {
        return flow {
            try {
                val response = apiService.getNotificationList()
                if (response.violationItem.isEmpty()) {
                    emit(ApiResponse.Empty)
                } else {
                    emit(ApiResponse.Success(response))
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e(TAG, e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getViolationDetail(id: String): Flow<ApiResponse<ViolationItem>> {
        return flow {
            try {
                val response = apiService.getViolationDetail(id)
                emit(ApiResponse.Success(response))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e(TAG, e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getCameraList(): Flow<ApiResponse<CameraResponse>> {
        return flow {
            try {
                val response = apiService.getCameraList()
                if (response.cameraItem.isEmpty()) {
                    emit(ApiResponse.Empty)
                } else {
                    emit(ApiResponse.Success(response))
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e(TAG, e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getCameraDetail(id: String): Flow<ApiResponse<CameraItem>> {
        return flow {
            try {
                val response = apiService.getCameraDetail(id)
                emit(ApiResponse.Success(response))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e(TAG, e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun updateViolation(id: String, violationBody: ViolationBody): Flow<ApiResponse<SuccessResponse>> {
        return flow {
            try {
                val response = apiService.updateViolation(id, violationBody)
                emit(ApiResponse.Success(response))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e(TAG, e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    companion object {
        private val TAG: String = RemoteDataSource::class.java.simpleName
    }
}