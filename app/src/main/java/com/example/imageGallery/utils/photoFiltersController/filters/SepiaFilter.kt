package com.example.imageGallery.utils.photoFiltersController.filters

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.graphics.Paint
import com.example.imageGallery.utils.photoFiltersController.PhotoFilter

class SepiaFilter {
    companion object : PhotoFilter {
        override fun applyFilterOnPhoto(
            bitmap: Bitmap
        ): Bitmap {
            val width = bitmap.width
            val height = bitmap.height
            val sepiaBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
            val canvas = Canvas(sepiaBitmap)
            val paint = Paint()
            val colorMatrix = ColorMatrix()
            colorMatrix.set(
                floatArrayOf(
                    0.393f, 0.769f, 0.189f, 0f, 0f, // Red channel
                    0.349f, 0.686f, 0.168f, 0f, 0f, // Green channel
                    0.272f, 0.534f, 0.131f, 0f, 0f, // Blue channel
                    0f, 0f, 0f, 1f, 0f  // Alpha channel
                )
            )
            val colorFilter = ColorMatrixColorFilter(colorMatrix)
            paint.colorFilter = colorFilter
            canvas.drawBitmap(bitmap, 0f, 0f, paint)
            return sepiaBitmap
        }
    }
}