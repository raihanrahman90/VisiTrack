package com.visitrack.core.data.remote.model

import com.google.gson.annotations.SerializedName
import java.sql.Timestamp

data class ViolationResponse(
    @field:SerializedName("pelanggaran")
    val violationItem: List<ViolationItem>,
)

data class ViolationItem(
    @field:SerializedName("gambar")
    val image: String,

    @field:SerializedName("id")
    val id: String? = null,

    @field:SerializedName("kamera")
    val camera: String,

    @field:SerializedName("pesan")
    val message: String,

    @field:SerializedName("status")
    val status: Int,

    @field:SerializedName("tanggal")
    val timestamp: Double
)
