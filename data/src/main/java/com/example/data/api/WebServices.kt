package com.example.data.api

import com.example.data.model.api.response.RandomPhotosResponse
import retrofit2.http.GET
import retrofit2.http.Query


interface WebServices {

    @GET(EndPoints.RANDOM_PHOTOS)
    suspend fun getAllRandomPhotos(
        @Query("page") page: Int,
        @Query("per_page") numberPerPage: Int
    ): RandomPhotosResponse

    @GET(EndPoints.SPECIFIC_PHOTOS)
    suspend fun getPhotosByCategoryName(
        @Query("query") category: String,
        @Query("page") page: Int,
        @Query("per_page") numberPerPage: Int
    ): RandomPhotosResponse
}