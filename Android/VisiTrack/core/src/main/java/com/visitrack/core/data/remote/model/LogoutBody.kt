package com.visitrack.core.data.remote.model

import com.google.gson.annotations.SerializedName

data class LogoutBody(

	@field:SerializedName("token")
	val token: String? = null
)
