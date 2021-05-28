package com.visitrack.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Violation(
    val idViolation: Int,
    val typeViolation: String,
    val camera: Camera,
    val dateViolation: String,
    val timeViolation: String,
    val descViolation: String,
    val statusViolation: String,
    val imageUrl: String
): Parcelable
