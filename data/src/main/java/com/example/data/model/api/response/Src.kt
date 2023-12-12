package com.example.data.model.api.response


import com.google.gson.annotations.SerializedName

data class Src(
    @SerializedName("medium")
    val medium: String?,
    @SerializedName("portrait")
    val portrait: String?,
    @SerializedName("tiny")
    val tiny: String?
)