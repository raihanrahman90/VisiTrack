package com.visitrack.core.data.remote.model.login

import com.google.gson.annotations.SerializedName

data class LoginBody(

	@field:SerializedName("username")
	val username: String? = null,

	@field:SerializedName("password")
	val password: String? = null,

	@field:SerializedName("token")
	val token: String? = null
)
