package com.visitrack.core.domain.usecase

import com.visitrack.core.data.Resource
import com.visitrack.core.domain.model.Camera
import com.visitrack.core.domain.model.Statistics
import com.visitrack.core.domain.model.User
import com.visitrack.core.domain.model.Violation
import kotlinx.coroutines.flow.Flow

interface UseCase {
    fun login(): Flow<Resource<User>>
    fun register(user: User)
    fun getStatistics(): Flow<Resource<Statistics>>
    fun getNotificationList(): Flow<Resource<List<Violation>>>
    fun getCameraList(): Flow<Resource<List<Camera>>>
    fun getViolationDetail(id: Int): Flow<Resource<Violation>>
    fun getCameraDetail(id: Int): Flow<Resource<Camera>>
    fun updateViolation(status: String)
}