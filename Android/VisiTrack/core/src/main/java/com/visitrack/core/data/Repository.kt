package com.visitrack.core.data

import com.visitrack.core.data.remote.RemoteDataSource
import com.visitrack.core.data.remote.model.*
import com.visitrack.core.data.remote.model.login.LoginBody
import com.visitrack.core.data.remote.model.login.LoginResponse
import com.visitrack.core.data.remote.network.ApiResponse
import com.visitrack.core.domain.model.Camera
import com.visitrack.core.domain.model.Statistics
import com.visitrack.core.domain.model.Success
import com.visitrack.core.domain.model.Violation
import com.visitrack.core.domain.repository.IRepository
import com.visitrack.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow

class Repository(private val remoteDataSource: RemoteDataSource): IRepository {
    override fun login(username: String, password: String, token: String): Flow<Resource<Success>> =
        object : NetworkBoundResource<Success, LoginResponse>() {
            override suspend fun load(data: LoginResponse): Flow<Success> {
                return listOf(DataMapper.mapLoginResponseToDomain(data)).asFlow()
            }

            override suspend fun createCall(): Flow<ApiResponse<LoginResponse>> {
                val data = LoginBody(username, password, token)
                return remoteDataSource.login(data)
            }

        }.asFlow()

    override fun logout(token: String): Flow<Resource<Success>> =
        object : NetworkBoundResource<Success, SuccessResponse>() {
            override suspend fun load(data: SuccessResponse): Flow<Success> {
                return listOf(DataMapper.mapSuccessResponseToDomain(data)).asFlow()
            }

            override suspend fun createCall(): Flow<ApiResponse<SuccessResponse>> {
                val data = LogoutBody(token)
                return remoteDataSource.logout(data)
            }
        }.asFlow()


    override fun register(username: String, password: String): Flow<Resource<Success>> =
        object : NetworkBoundResource<Success, SuccessResponse>() {
            override suspend fun load(data: SuccessResponse): Flow<Success> {
                return listOf(DataMapper.mapSuccessResponseToDomain(data)).asFlow()
            }

            override suspend fun createCall(): Flow<ApiResponse<SuccessResponse>> {
                val data = RegisterBody(username, password)
                return remoteDataSource.register(data)
            }

        }.asFlow()

    override fun getStatistics(): Flow<Resource<Statistics>> =
        object : NetworkBoundResource<Statistics, StatisticsResponse>() {
            override suspend fun load(data: StatisticsResponse): Flow<Statistics> {
                return listOf(DataMapper.mapStatisticsResponseToDomain(data)).asFlow()
            }

            override suspend fun createCall(): Flow<ApiResponse<StatisticsResponse>> {
                return remoteDataSource.getStatistics()
            }

        }.asFlow()

    override fun getNotificationList(): Flow<Resource<List<Violation>>> =
        object : NetworkBoundResource<List<Violation>, ViolationResponse>() {
            override suspend fun load(data: ViolationResponse): Flow<List<Violation>> {
                return listOf(DataMapper.mapViolationResponseToDomain(data)).asFlow()
            }

            override suspend fun createCall(): Flow<ApiResponse<ViolationResponse>> {
                return remoteDataSource.getNotificationList()
            }

        }.asFlow()

    override fun getCameraList(): Flow<Resource<List<Camera>>> =
        object : NetworkBoundResource<List<Camera>, CameraResponse>() {
            override suspend fun load(data: CameraResponse): Flow<List<Camera>> {
                return listOf(DataMapper.mapCameraResponseToDomain(data)).asFlow()
            }

            override suspend fun createCall(): Flow<ApiResponse<CameraResponse>> {
                return remoteDataSource.getCameraList()
            }

        }.asFlow()

    override fun getViolationDetail(id: String): Flow<Resource<Violation>> =
        object : NetworkBoundResource<Violation, ViolationItem>() {
            override suspend fun load(data: ViolationItem): Flow<Violation> {
                return listOf(DataMapper.mapViolationItemToDomain(data)).asFlow()
            }

            override suspend fun createCall(): Flow<ApiResponse<ViolationItem>> {
                return remoteDataSource.getViolationDetail(id)
            }

        }.asFlow()

    override fun getCameraDetail(id: String): Flow<Resource<Camera>> =
        object : NetworkBoundResource<Camera, CameraItem>() {
            override suspend fun load(data: CameraItem): Flow<Camera> {
                return listOf(DataMapper.mapCameraItemToDomain(data)).asFlow()
            }

            override suspend fun createCall(): Flow<ApiResponse<CameraItem>> {
                return remoteDataSource.getCameraDetail(id)
            }

        }.asFlow()

    override fun updateViolation(id: String, status: Int): Flow<Resource<Success>> =
        object : NetworkBoundResource<Success, SuccessResponse>() {
            override suspend fun load(data: SuccessResponse): Flow<Success> {
                return listOf(DataMapper.mapSuccessResponseToDomain(data)).asFlow()
            }

            override suspend fun createCall(): Flow<ApiResponse<SuccessResponse>> {
                val violationBody = ViolationBody(status)
                return remoteDataSource.updateViolation(id, violationBody)
            }

        }.asFlow()

}