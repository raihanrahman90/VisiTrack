package com.visitrack.core.data.remote.model.login

import com.google.gson.annotations.SerializedName

data class LoginResponse(

	@field:SerializedName("success")
	val success: Boolean,

	@field:SerializedName("token")
	val token: String? = null
)
