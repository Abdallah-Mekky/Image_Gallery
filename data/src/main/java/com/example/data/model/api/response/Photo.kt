package com.example.data.model.api.response


import com.google.gson.annotations.SerializedName

data class Photo(
    @SerializedName("alt")
    val alt: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("photographer")
    val photographer: String?,
    @SerializedName("photographer_url")
    val photographerUrl: String?,
    @SerializedName("src")
    val src: Src?,
    @SerializedName("url")
    val url: String?
)