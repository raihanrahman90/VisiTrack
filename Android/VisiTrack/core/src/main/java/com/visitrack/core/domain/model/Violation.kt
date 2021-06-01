package com.visitrack.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Violation(
    val idViolation: String?,
    val typeViolation: String,
    val camera: String,
    val dateViolation: String,
    val timeViolation: String,
    val statusViolation: Int,
    val imageUrl: String
): Parcelable
