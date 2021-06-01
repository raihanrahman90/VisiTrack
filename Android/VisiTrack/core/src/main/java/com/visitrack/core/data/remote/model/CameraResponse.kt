package com.visitrack.core.data.remote.model

import com.google.gson.annotations.SerializedName

data class CameraResponse(
    @field:SerializedName("kamera")
    val cameraItem: List<CameraItem>
)

data class CameraItem(
    @field:SerializedName("gambar")
    val image: String,

    @field:SerializedName("id")
    val id: String? = null,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("desc")
    val desc: String,

    @field:SerializedName("jumlah_orang")
    val visitorCount: Int,

    @field:SerializedName("jumlah_pelanggaran")
    val violationCount: Int
)
