package com.example.imageGallery.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.domain.repos.networkHandler.NetworkHandler
import com.example.domain.repos.randomPhotosRepo.RandomPhotosRepo
import com.example.imageGallery.R
import com.example.imageGallery.enums.CategoriesEnum
import com.example.imageGallery.model.CategoryItem
import com.example.imageGallery.paging.PhotoPagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val randomPhotosRepo: RandomPhotosRepo,
    private val networkHandler: NetworkHandler
) : ViewModel() {

    private val _categoriesList: MutableLiveData<List<CategoryItem>> = MutableLiveData()
    val categoriesList: LiveData<List<CategoryItem>> = _categoriesList

    private val _isInternetConnected: MutableLiveData<Boolean> = MutableLiveData()
    val isInternetConnected = _isInternetConnected

    val randomPhotosList = Pager(PagingConfig(pageSize = 1)) {
        PhotoPagingSource(randomPhotosRepo)
    }.flow.cachedIn(viewModelScope)

    init {
        viewModelScope.launch {
            _categoriesList.value = getCategoriesList()
        }
    }

    fun checkInternConnection() {
        _isInternetConnected.value = networkHandler.isOnline()
    }

    private fun getCategoriesList(): List<CategoryItem> {
        return listOf(
            CategoryItem(
                categoryImage = R.drawable.nature,
                categoryTitle = CategoriesEnum.NATURE.categoryName
            ),
            CategoryItem(
                categoryImage = R.drawable.ocean,
                categoryTitle = CategoriesEnum.OCEAN.categoryName
            ),
            CategoryItem(
                categoryImage = R.drawable.animals,
                categoryTitle = CategoriesEnum.ANIMALS.categoryName
            ),
            CategoryItem(
                categoryImage = R.drawable.food,
                categoryTitle = CategoriesEnum.FOOD.categoryName
            ),
            CategoryItem(
                categoryImage = R.drawable.architecture,
                categoryTitle = CategoriesEnum.ARCHITECTURE.categoryName
            ),
            CategoryItem(
                categoryImage = R.drawable.space,
                categoryTitle = CategoriesEnum.SPACE.categoryName
            ),
        )
    }
}