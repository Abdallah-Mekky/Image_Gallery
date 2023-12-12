package com.example.imageGallery.utils.photoFiltersController.filters

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.graphics.Paint
import com.example.imageGallery.utils.photoFiltersController.PhotoFilter

class PortraitFilter {
    companion object : PhotoFilter {
        override fun applyFilterOnPhoto(
            bitmap: Bitmap
        ): Bitmap {
            val width = bitmap.width
            val height = bitmap.height
            val portraitBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
            val canvas = Canvas(portraitBitmap)
            val paint = Paint()
            val colorMatrix = ColorMatrix()
            colorMatrix.setScale(
                1.2f,
                0.95f,
                0.85f,
                1f
            ) // Adjust color matrix values for portrait effect
            val colorFilter = ColorMatrixColorFilter(colorMatrix)
            paint.colorFilter = colorFilter
            canvas.drawBitmap(bitmap, 0f, 0f, paint)
            return portraitBitmap
        }
    }
}