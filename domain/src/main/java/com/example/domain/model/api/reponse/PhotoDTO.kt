package com.example.domain.model.api.reponse


import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PhotoDTO(
    val alt: String?,
    val id: Int?,
    val photographer: String?,
    val photographerUrl: String?,
    val src: SrcDTO?,
    val url: String?
) : Parcelable