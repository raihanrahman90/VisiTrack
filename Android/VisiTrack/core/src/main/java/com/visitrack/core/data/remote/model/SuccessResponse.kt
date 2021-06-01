package com.visitrack.core.data.remote.model

import com.google.gson.annotations.SerializedName

data class SuccessResponse(
    @field:SerializedName("success")
    val success: Boolean
)
