package com.example.domain.repos.randomPhotosRepo

import com.example.domain.model.api.reponse.PhotoDTO

interface RandomPhotosOnlineDataSource {
    suspend fun getRandomPhotos(
        page: Int, numberPerPage: Int
    ): List<PhotoDTO>?
}