package com.visitrack.core.data.remote.model

import com.google.gson.annotations.SerializedName

data class CameraResponse(
    @field:SerializedName("kamera")
    val cameraItem: List<CameraItem>
)

data class CameraItem(
    @field:SerializedName("gambar")
    val image: String,

    @field:SerializedName("kamera")
    val name: String,

    @field:SerializedName("jumlah_orang")
    val visitorCount: String,

    @field:SerializedName("jumlah_pelanggaran")
    val violationCount: Int
)
