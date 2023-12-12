package com.example.data.reposImpl.photosByCategoriesRepoImpl

import com.example.data.api.WebServices
import com.example.data.mappers.toRandomPhotoResponseDTO
import com.example.domain.model.api.reponse.PhotoDTO
import com.example.domain.repos.photosByCategoriesRepo.PhotosByCategoriesOnlineDataSource
import javax.inject.Inject

class PhotosByCategoriesOnlineDataSourceImpl @Inject constructor(
    private val webServices: WebServices
) : PhotosByCategoriesOnlineDataSource {

    override suspend fun getPhotosByCategory(
        categoryName: String,
        page: Int,
        numberPerPage: Int
    ): List<PhotoDTO>? {

        try {
            return webServices.getPhotosByCategoryName(
                categoryName, page, numberPerPage).toRandomPhotoResponseDTO().photos
        } catch (ex: Exception) {
            ex.printStackTrace()
            throw ex
        }
    }
}