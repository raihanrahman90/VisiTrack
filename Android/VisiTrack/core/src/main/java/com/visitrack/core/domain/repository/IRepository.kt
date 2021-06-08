package com.visitrack.core.domain.repository

import com.visitrack.core.data.Resource
import com.visitrack.core.domain.model.Camera
import com.visitrack.core.domain.model.Statistics
import com.visitrack.core.domain.model.Success
import com.visitrack.core.domain.model.Violation
import kotlinx.coroutines.flow.Flow

interface IRepository {
    fun login(username: String, password: String, token: String): Flow<Resource<Success>>
    fun logout(token: String): Flow<Resource<Success>>
    fun register(username: String, password: String): Flow<Resource<Success>>
    fun getStatistics(): Flow<Resource<Statistics>>
    fun getNotificationList(): Flow<Resource<List<Violation>>>
    fun getCameraList(): Flow<Resource<List<Camera>>>
    fun getViolationDetail(id: String): Flow<Resource<Violation>>
    fun updateViolation(id: String, status: Int): Flow<Resource<Success>>
}