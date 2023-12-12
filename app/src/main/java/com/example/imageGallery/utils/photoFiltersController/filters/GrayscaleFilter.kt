package com.example.imageGallery.utils.photoFiltersController.filters

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.graphics.Paint
import com.example.imageGallery.utils.photoFiltersController.PhotoFilter

class GrayscaleFilter {
    companion object : PhotoFilter {
        override fun applyFilterOnPhoto(
            bitmap: Bitmap
        ): Bitmap {
            val width = bitmap.width
            val height = bitmap.height
            val grayscaleBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
            val canvas = Canvas(grayscaleBitmap)
            val paint = Paint()
            val colorMatrix = ColorMatrix()
            colorMatrix.setSaturation(0f) // Set saturation to 0 for grayscale
            val colorFilter = ColorMatrixColorFilter(colorMatrix)
            paint.colorFilter = colorFilter
            canvas.drawBitmap(bitmap, 0f, 0f, paint)
            return grayscaleBitmap
        }
    }
}