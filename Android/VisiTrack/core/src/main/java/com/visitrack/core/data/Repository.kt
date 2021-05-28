package com.visitrack.core.data

import com.visitrack.core.data.remote.RemoteDataSource
import com.visitrack.core.domain.model.Camera
import com.visitrack.core.domain.model.Statistics
import com.visitrack.core.domain.model.User
import com.visitrack.core.domain.model.Violation
import com.visitrack.core.domain.repository.IRepository
import kotlinx.coroutines.flow.Flow

class Repository(private val remoteDataSource: RemoteDataSource): IRepository {
    override fun login(): Flow<Resource<User>> {
        TODO("Not yet implemented")
    }

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