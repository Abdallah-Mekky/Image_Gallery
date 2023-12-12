package com.example.domain.repos.photosByCategoriesRepo

import com.example.domain.model.api.reponse.PhotoDTO

interface PhotosByCategoriesRepo {
    suspend fun getPhotosByCategory(
        categoryName: String,
        page: Int,
        numberPerPage: Int
    ): List<PhotoDTO>?
}