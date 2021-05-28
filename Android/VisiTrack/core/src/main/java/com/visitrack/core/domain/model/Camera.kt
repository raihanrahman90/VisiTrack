package com.visitrack.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Camera(
    val idCamera: Int,
    val nameCamera: String,
    val descCamera: String,
    val visitorCount: Int,
    val violationCount: Int,
    val imageUrl: String
): Parcelable
