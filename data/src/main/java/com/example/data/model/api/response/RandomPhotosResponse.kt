package com.example.data.model.api.response


import com.google.gson.annotations.SerializedName

data class RandomPhotosResponse(
    @SerializedName("photos")
    val photos: List<Photo>?
)