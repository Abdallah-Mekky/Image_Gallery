package com.example.domain.repos.randomPhotosRepo

import com.example.domain.model.api.reponse.PhotoDTO

interface RandomPhotosRepo {
    suspend fun getRandomPhotos(
        page: Int, numberPerPage: Int
    ): List<PhotoDTO>?
}