package com.visitrack.core.data.remote.model

import com.google.gson.annotations.SerializedName

data class LoginPost(

	@field:SerializedName("username")
	val username: String? = null,

	@field:SerializedName("password")
	val password: String? = null,

	@field:SerializedName("token")
	val token: String? = null
)
