package com.visitrack.core.domain.usecase

import com.visitrack.core.data.Resource
import com.visitrack.core.domain.model.Camera
import com.visitrack.core.domain.model.Statistics
import com.visitrack.core.domain.model.User
import com.visitrack.core.domain.model.Violation
import com.visitrack.core.domain.repository.IRepository
import kotlinx.coroutines.flow.Flow

class Interactor(private val repository: IRepository): UseCase {
    override fun login(): Flow<Resource<User>> {
        return repository.login()
    }

    override fun register(user: User) {
        repository.register(user)
    }

    override fun getStatistics(): Flow<Resource<Statistics>> {
        return repository.getStatistics()
    }

    override fun getNotificationList(): Flow<Resource<List<Violation>>> {
        return repository.getNotificationList()
    }

    override fun getCameraList(): Flow<Resource<List<Camera>>> {
        return repository.getCameraList()
    }

    override fun getViolationDetail(id: Int): Flow<Resource<Violation>> {
        return repository.getViolationDetail(id)
    }

    override fun getCameraDetail(id: Int): Flow<Resource<Camera>> {
        return repository.getCameraDetail(id)
    }

    override fun updateViolation(status: String) {
        repository.updateViolation(status)
    }

}