package com.example.domain.model.api.reponse


import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SrcDTO(
    val medium: String?,
    val portrait: String?,
    val tiny: String?
) : Parcelable