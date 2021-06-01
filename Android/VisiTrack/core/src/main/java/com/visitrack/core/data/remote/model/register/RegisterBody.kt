package com.visitrack.core.data.remote.model.register

import com.google.gson.annotations.SerializedName

data class RegisterBody(
    @field:SerializedName("username")
    val username: String? = null,

    @field:SerializedName("password")
    val password: String? = null,
)
