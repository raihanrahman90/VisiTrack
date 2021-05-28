package com.visitrack.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val email: String,
    val username: String,
    val fullName: String,
    val company: String,
    val token: String
): Parcelable
