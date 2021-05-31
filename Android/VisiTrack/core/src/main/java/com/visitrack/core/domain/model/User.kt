package com.visitrack.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val success: String,
    val token: String?
): Parcelable
