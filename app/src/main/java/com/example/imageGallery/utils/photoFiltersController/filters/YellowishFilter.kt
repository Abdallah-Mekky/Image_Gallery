package com.example.imageGallery.utils.photoFiltersController.filters

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.graphics.Paint
import com.example.imageGallery.utils.photoFiltersController.PhotoFilter

class YellowishFilter {
    companion object : PhotoFilter {
        override fun applyFilterOnPhoto(
            bitmap: Bitmap
        ): Bitmap {
            val width = bitmap.width
            val height = bitmap.height
            val yellowishBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
            val canvas = Canvas(yellowishBitmap)
            val paint = Paint()
            val colorMatrix = ColorMatrix()
            colorMatrix.set(
                floatArrayOf(
                    1f, 0f, 0f, 0f, 0f, // Red channel
                    0f, 1f, 0f, 0f, 0f, // Green channel
                    0f, 0f, 1f, 0f, 0f, // Blue channel
                    0f, 0f, 0f, 1f, 0f  // Alpha channel
                )
            )
            colorMatrix.postConcat(
                ColorMatrix(
                    floatArrayOf(
                        1f, 0f, 0f, 0f, 0f, // Red channel
                        0f, 1f, 0f, 0f, 0f, // Green channel
                        0f, 0f, 0.5f, 0f, 0f, // Blue channel
                        0f, 0f, 0f, 1f, 0f  // Alpha channel
                    )
                )
            )
            val colorFilter = ColorMatrixColorFilter(colorMatrix)
            paint.colorFilter = colorFilter
            canvas.drawBitmap(bitmap, 0f, 0f, paint)
            return yellowishBitmap
        }
    }
}