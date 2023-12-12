package com.example.data.reposImpl.photosByCategoriesRepoImpl

import com.example.domain.model.api.reponse.PhotoDTO
import com.example.domain.repos.photosByCategoriesRepo.PhotosByCategoriesOnlineDataSource
import com.example.domain.repos.photosByCategoriesRepo.PhotosByCategoriesRepo
import javax.inject.Inject

class PhotosByCategoriesRepoImpl @Inject constructor(
    private val photosByCategoriesOnlineDataSource: PhotosByCategoriesOnlineDataSource,
) : PhotosByCategoriesRepo {

    override suspend fun getPhotosByCategory(
        categoryName: String,
        page: Int,
        numberPerPage: Int
    ): List<PhotoDTO>? {
        try {

            return photosByCategoriesOnlineDataSource.getPhotosByCategory(
                    categoryName,
                    page,
                    numberPerPage
                )


        } catch (ex: Exception) {
            ex.printStackTrace()
            throw ex
        }
    }
}