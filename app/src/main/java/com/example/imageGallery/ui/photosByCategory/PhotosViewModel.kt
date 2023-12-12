package com.example.imageGallery.ui.photosByCategory

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.domain.model.api.reponse.PhotoDTO
import com.example.domain.repos.networkHandler.NetworkHandler
import com.example.domain.repos.photosByCategoriesRepo.PhotosByCategoriesRepo
import com.example.imageGallery.paging.PhotoByCategoryPagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class PhotosViewModel @Inject constructor(
    private val photosByCategoriesRepo:
    PhotosByCategoriesRepo,
    private val networkHandler: NetworkHandler
) : ViewModel() {


    private val _isInternetConnected: MutableLiveData<Boolean> = MutableLiveData()
    val isInternetConnected = _isInternetConnected

    fun checkInternConnection() {
        _isInternetConnected.value = networkHandler.isOnline()
    }


    fun getPhotosByCategory(categoryName: String): Flow<PagingData<PhotoDTO>> {
        return Pager(PagingConfig(pageSize = 1)) {
            PhotoByCategoryPagingSource(photosByCategoriesRepo, categoryName)
        }.flow.cachedIn(viewModelScope)
    }
}