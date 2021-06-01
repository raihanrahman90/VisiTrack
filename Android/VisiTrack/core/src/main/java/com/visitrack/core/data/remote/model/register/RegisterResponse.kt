package com.visitrack.core.data.remote.model.register

import com.google.gson.annotations.SerializedName

data class RegisterResponse(
    @field:SerializedName("success")
    val success: String,
)
