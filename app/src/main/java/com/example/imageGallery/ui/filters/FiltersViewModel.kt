package com.example.imageGallery.ui.filters

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.imageGallery.enums.PhotoFiltersEnum
import com.example.imageGallery.model.PhotoFilterItem
import com.example.imageGallery.utils.photoFiltersController.filters.GrayscaleFilter
import com.example.imageGallery.utils.photoFiltersController.filters.HighContrastFilter
import com.example.imageGallery.utils.photoFiltersController.filters.PortraitFilter
import com.example.imageGallery.utils.photoFiltersController.filters.SepiaFilter
import com.example.imageGallery.utils.photoFiltersController.filters.VintageFilter
import com.example.imageGallery.utils.photoFiltersController.filters.YellowishFilter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class FiltersViewModel : ViewModel() {

    private val _photoFiltersList: MutableLiveData<List<PhotoFilterItem>> = MutableLiveData()
    val photoFiltersList: LiveData<List<PhotoFilterItem>> = _photoFiltersList

    fun getPhotoWithFilterApplied(filter: PhotoFiltersEnum, currentBitmap: Bitmap): Bitmap {
        return when (filter) {
            PhotoFiltersEnum.ORIGINAL -> currentBitmap

            PhotoFiltersEnum.GRAYSCALE -> GrayscaleFilter.applyFilterOnPhoto(currentBitmap)

            PhotoFiltersEnum.HIGH_CONTRAST -> HighContrastFilter.applyFilterOnPhoto(currentBitmap)

            PhotoFiltersEnum.PORTRAIT -> PortraitFilter.applyFilterOnPhoto(currentBitmap)

            PhotoFiltersEnum.SEPIA -> SepiaFilter.applyFilterOnPhoto(currentBitmap)

            PhotoFiltersEnum.VINTAGE -> VintageFilter.applyFilterOnPhoto(currentBitmap)

            PhotoFiltersEnum.YELLOWISH -> YellowishFilter.applyFilterOnPhoto(currentBitmap)
        }
    }

    fun getPhotoFiltersList(currentPhoto: Bitmap) {

        viewModelScope.launch(Dispatchers.Default) {

            _photoFiltersList.postValue(
                listOf(
                    PhotoFilterItem(
                        filterName = PhotoFiltersEnum.ORIGINAL.photoFilterName,
                        photoFilter = currentPhoto,
                        filter = PhotoFiltersEnum.ORIGINAL
                    ),
                    PhotoFilterItem(
                        filterName = PhotoFiltersEnum.GRAYSCALE.photoFilterName,
                        photoFilter = GrayscaleFilter.applyFilterOnPhoto(currentPhoto),
                        filter = PhotoFiltersEnum.GRAYSCALE

                    ),
                    PhotoFilterItem(
                        filterName = PhotoFiltersEnum.HIGH_CONTRAST.photoFilterName,
                        photoFilter = HighContrastFilter.applyFilterOnPhoto(currentPhoto),
                        filter = PhotoFiltersEnum.HIGH_CONTRAST

                    ),
                    PhotoFilterItem(
                        filterName = PhotoFiltersEnum.PORTRAIT.photoFilterName,
                        photoFilter = PortraitFilter.applyFilterOnPhoto(currentPhoto),
                        filter = PhotoFiltersEnum.PORTRAIT

                    ),
                    PhotoFilterItem(
                        filterName = PhotoFiltersEnum.SEPIA.photoFilterName,
                        photoFilter = SepiaFilter.applyFilterOnPhoto(currentPhoto),
                        filter = PhotoFiltersEnum.SEPIA

                    ),
                    PhotoFilterItem(
                        filterName = PhotoFiltersEnum.VINTAGE.photoFilterName,
                        photoFilter = VintageFilter.applyFilterOnPhoto(currentPhoto),
                        filter = PhotoFiltersEnum.VINTAGE

                    ),
                    PhotoFilterItem(
                        filterName = PhotoFiltersEnum.YELLOWISH.photoFilterName,
                        photoFilter = YellowishFilter.applyFilterOnPhoto(currentPhoto),
                        filter = PhotoFiltersEnum.YELLOWISH
                    )
                )
            )
        }
    }
}