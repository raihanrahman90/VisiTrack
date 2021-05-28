package com.visitrack.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Statistics(
    val visitorCount: Int,
    val cameraCount: Int,
    val violationCount: Int
): Parcelable
