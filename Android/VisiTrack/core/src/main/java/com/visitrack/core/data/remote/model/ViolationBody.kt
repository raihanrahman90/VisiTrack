package com.visitrack.core.data.remote.model

import com.google.gson.annotations.SerializedName

data class ViolationBody(
    @field:SerializedName("status")
    val status: Int,
)
