package com.visitrack.core.data.remote.model.logout

import com.google.gson.annotations.SerializedName

data class LogoutResponse(

    @field:SerializedName("success")
    val success: String
)
