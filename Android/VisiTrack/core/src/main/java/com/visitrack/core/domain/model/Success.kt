package com.visitrack.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Success(
    val success: Boolean,
    val token: String?
): Parcelable
