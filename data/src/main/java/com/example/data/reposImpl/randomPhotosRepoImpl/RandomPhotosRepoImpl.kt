package com.example.data.reposImpl.randomPhotosRepoImpl

import com.example.domain.model.api.reponse.PhotoDTO
import com.example.domain.repos.randomPhotosRepo.RandomPhotosOnlineDataSource
import com.example.domain.repos.randomPhotosRepo.RandomPhotosRepo
import javax.inject.Inject

class RandomPhotosRepoImpl @Inject constructor(
    private val randomPhotosOnlineDataSource: RandomPhotosOnlineDataSource
) : RandomPhotosRepo {

    override suspend fun getRandomPhotos(
        page: Int,
        numberPerPage: Int
    ): List<PhotoDTO>? {
        try {
            return randomPhotosOnlineDataSource.getRandomPhotos(
                page, numberPerPage
            )
        } catch (ex: Exception) {
            ex.printStackTrace()
            throw ex
        }
    }
}