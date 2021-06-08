package com.visitrack.core.utils

import com.visitrack.core.data.remote.model.*
import com.visitrack.core.data.remote.model.login.LoginResponse
import com.visitrack.core.data.remote.network.ApiUrl
import com.visitrack.core.domain.model.Camera
import com.visitrack.core.domain.model.Statistics
import com.visitrack.core.domain.model.Success
import com.visitrack.core.domain.model.Violation
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

object DataMapper {
    fun mapLoginResponseToDomain(input: LoginResponse): Success {
        return Success(input.success, input.token)
    }

    fun mapSuccessResponseToDomain(input: SuccessResponse): Success {
        return Success(input.success, token = null)
    }

    fun mapStatisticsResponseToDomain(input: StatisticsResponse): Statistics {
        return Statistics(
            input.visitorCount.toInt(),
            input.cameraCount,
            input.violationCount
        )
    }

    fun mapViolationResponseToDomain(input: ViolationResponse): List<Violation> {
        val violationList = ArrayList<Violation>()
        input.violationItem.map {
            val date = getDateTime(it.timestamp, 0)
            val time = getDateTime(it.timestamp, 1)
            val violation = Violation(
                it.id.toString(),
                it.message,
                it.camera,
                date,
                time,
                it.status,
                ApiUrl.IMAGE_URL + it.image
            )
            violationList.add(violation)
        }
        return violationList
    }

    fun mapViolationItemToDomain(input: ViolationItem): Violation {
        val date = getDateTime(input.timestamp, 0)
        val time = getDateTime(input.timestamp, 1)
        return Violation(
            input.id.toString(),
            input.message,
            input.camera,
            date,
            time,
            input.status,
            ApiUrl.IMAGE_URL + input.image
        )
    }

    fun mapCameraResponseToDomain(input: CameraResponse): List<Camera> {
        val cameraList = ArrayList<Camera>()
        input.cameraItem.map {
            val camera = Camera(
                it.name,
                it.visitorCount.toInt(),
                it.violationCount,
                ApiUrl.IMAGE_URL + it.image
            )
            cameraList.add(camera)
        }
        return cameraList
    }

    private fun getDateTime(timestamp: Double, type: Int): String {
        return try {
            val format = if (type==0){
                SimpleDateFormat("EEEE, d MMMM y", Locale.ENGLISH)
            } else {
                SimpleDateFormat("HH:mm:ss", Locale.ENGLISH)
            }
            val netDate = Date(timestamp.toLong() * 1000)
            format.format(netDate)
        } catch (e: Exception) {
            e.toString()
        }
    }
}