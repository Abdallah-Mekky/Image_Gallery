package com.example.imageGallery.utils.photoFiltersController

import android.graphics.Bitmap

interface PhotoFilter {
    fun applyFilterOnPhoto(bitmap: Bitmap): Bitmap
}