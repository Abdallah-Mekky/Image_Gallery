package com.example.imageGallery.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.domain.model.api.reponse.PhotoDTO
import com.example.domain.repos.randomPhotosRepo.RandomPhotosRepo
import com.example.imageGallery.Constants.NUMBER_OF_PHOTOS_PRE_PAGE

class PhotoPagingSource(private val randomPhotosRepo: RandomPhotosRepo) :
    PagingSource<Int, PhotoDTO>() {
    override fun getRefreshKey(state: PagingState<Int, PhotoDTO>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PhotoDTO> {
        return try {
            val currentPage = params.key ?: 1
            val response = randomPhotosRepo.getRandomPhotos(
                page = currentPage,
                numberPerPage = NUMBER_OF_PHOTOS_PRE_PAGE
            )
            val responseData = mutableListOf<PhotoDTO>()
            val data = response ?: emptyList()
            responseData.addAll(data)

            LoadResult.Page(
                data = responseData,
                prevKey = if (currentPage == 1) null else -1,
                nextKey = currentPage.plus(1)
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}