package com.example.data.reposImpl.randomPhotosRepoImpl

import com.example.data.api.WebServices
import com.example.data.mappers.toRandomPhotoResponseDTO
import com.example.domain.model.api.reponse.PhotoDTO
import com.example.domain.repos.randomPhotosRepo.RandomPhotosOnlineDataSource
import javax.inject.Inject

class RandomPhotosOnlineDataSourceImpl @Inject constructor(
    private val webServices: WebServices
) : RandomPhotosOnlineDataSource {

    override suspend fun getRandomPhotos(
        page: Int,
        numberPerPage: Int
    ): List<PhotoDTO>? {
        try {
            return webServices.getAllRandomPhotos(
                page, numberPerPage
            ).toRandomPhotoResponseDTO().photos
        } catch (ex: Exception) {
            ex.printStackTrace()
            throw ex
        }
    }
}