package com.visitrack.core.data.remote.model

import com.google.gson.annotations.SerializedName

data class StatisticsResponse(
    @field:SerializedName("jumlah_kamera")
    val cameraCount: Int,

    @field:SerializedName("jumlah_orang")
    val visitorCount: String,

    @field:SerializedName("jumlah_pelanggaran")
    val violationCount: Int,

    @field:SerializedName("started")
    val started: Boolean
)
