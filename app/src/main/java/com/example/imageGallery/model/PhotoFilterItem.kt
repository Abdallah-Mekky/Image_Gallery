package com.example.imageGallery.model

import android.graphics.Bitmap
import com.example.imageGallery.enums.PhotoFiltersEnum

data class PhotoFilterItem(
    val filterName: String,
    val photoFilter: Bitmap,
    val filter:PhotoFiltersEnum
)
