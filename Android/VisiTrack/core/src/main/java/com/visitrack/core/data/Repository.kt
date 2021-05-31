package com.visitrack.core.data

import com.visitrack.core.data.remote.RemoteDataSource
import com.visitrack.core.data.remote.model.LoginPost
import com.visitrack.core.data.remote.model.LoginResponse
import com.visitrack.core.data.remote.network.ApiResponse
import com.visitrack.core.domain.model.Camera
import com.visitrack.core.domain.model.Statistics
import com.visitrack.core.domain.model.User
import com.visitrack.core.domain.model.Violation
import com.visitrack.core.domain.repository.IRepository
import com.visitrack.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow

class Repository(private val remoteDataSource: RemoteDataSource): IRepository {
    override fun login(username: String, password: String, token: String): Flow<Resource<User>> =
        object : NetworkBoundResource<User, LoginResponse>() {
            override suspend fun load(data: LoginResponse): Flow<User> {
                return listOf(DataMapper.mapLoginResponseToUser(data)).asFlow()
            }

            override suspend fun createCall(): Flow<ApiResponse<LoginResponse>> {
                val data = LoginPost(username, password, token)
                return remoteDataSource.login(data)
            }

        }.asFlow()

    override fun register(user: User) {
        TODO("Not yet implemented")
    }

    override fun getStatistics(): Flow<Resource<Statistics>> {
        TODO("Not yet implemented")
    }

    override fun getNotificationList(): Flow<Resource<List<Violation>>> {
        TODO("Not yet implemented")
    }

    override fun getCameraList(): Flow<Resource<List<Camera>>> {
        TODO("Not yet implemented")
    }

    override fun getViolationDetail(id: Int): Flow<Resource<Violation>> {
        TODO("Not yet implemented")
    }

    override fun getCameraDetail(id: Int): Flow<Resource<Camera>> {
        TODO("Not yet implemented")
    }

    override fun updateViolation(status: String) {
        TODO("Not yet implemented")
    }

}