package com.example.imageGallery.ui.detailedView

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.imageGallery.Constants.FILE_NAME
import com.example.imageGallery.Constants.FOLDER_NAME
import com.example.imageGallery.Constants.TITLE_OF_CHOOSER
import com.example.imageGallery.R
import com.example.imageGallery.databinding.FragmentDetailedViewBinding
import com.example.imageGallery.enums.IntentTypesEnum
import java.io.File
import java.io.FileOutputStream
import java.io.IOException


class DetailedViewFragment : Fragment() {

    private lateinit var binding: FragmentDetailedViewBinding
    private val args: DetailedViewFragmentArgs by navArgs()
    private val currentPhoto by lazy { args.currentPhotoDetails }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentDetailedViewBinding.inflate(inflater, container, false)
        init()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        onBackClick()
        onShareClick()
        onEditClick()
        onPhotographerLinkClick()
    }

    private fun init() {
        binding.photographerNameTV.text = currentPhoto.photographer
        binding.photographerLinkTV.text = currentPhoto.photographerUrl
        checkPhotographerDescription()
        loadPortraitImageWithGlide()
    }

    private fun checkPhotographerDescription() {
        if (!(currentPhoto.alt.isNullOrBlank())) {
            binding.description.text = currentPhoto.alt
        } else {
            binding.descriptionLinearLayout.visibility = View.GONE
        }
    }

    private fun loadPortraitImageWithGlide() {
        Glide.with(this)
            .load(currentPhoto.src?.portrait)
            .placeholder(R.drawable.ic_loading)
            .fitCenter()
            .into(binding.currentPhotoIV)
    }

    private fun onBackClick() {
        binding.icBack.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun onShareClick() {
        binding.icShare.setOnClickListener {
            shareImage()
        }
    }

    private fun onEditClick() {
        binding.icEdit.setOnClickListener {

            val action =
                DetailedViewFragmentDirections.actionDetailedViewFragmentToFiltersFragment2(
                    currentPhotoDetails = currentPhoto
                )

            findNavController().navigate(action)
        }
    }

    private fun shareImage() {
        // save bitmap to cache directory And Share it on others apps
        try {
            val cachePath = File(requireContext().cacheDir, FOLDER_NAME)

            // check directory existence
            if (!cachePath.exists()) {
                cachePath.mkdirs()
            }
            // overwrites this image every time
            val stream = FileOutputStream(File(cachePath, FILE_NAME))
            val bitmap: Bitmap = (binding.currentPhotoIV.drawable as BitmapDrawable).bitmap
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
            stream.close()

            // Share the image on others apps
            val imagePath = File(requireContext().cacheDir, FOLDER_NAME)
            val newFile = File(imagePath, FILE_NAME)
            val contentUri = FileProvider.getUriForFile(
                requireContext(), requireContext().packageName +
                        ".provider", newFile
            )

            if (contentUri != null) {
                val shareIntent = Intent()
                shareIntent.action = Intent.ACTION_SEND
                shareIntent.type = IntentTypesEnum.ALL_IMAGES.intentType
                // temp permission for receiving app to read this file
                shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                shareIntent.setDataAndType(
                    contentUri,
                    requireContext().contentResolver.getType(contentUri)
                )
                shareIntent.putExtra(Intent.EXTRA_STREAM, contentUri)
                startActivity(Intent.createChooser(shareIntent, TITLE_OF_CHOOSER))
            }
        } catch (e: IOException) {
            e.printStackTrace()
            Log.e("TAG", "shareWallpaper: " + e.localizedMessage)
        }
    }

    private fun onPhotographerLinkClick() {
        binding.photographerLinkTV.setOnClickListener {
            openLink(binding.photographerLinkTV.text.trim().toString())
        }
    }

    private fun openLink(url: String) {
        val webpage: Uri = Uri.parse(url)
        val intent = Intent(Intent.ACTION_VIEW, webpage)
        if (intent.resolveActivity(requireContext().packageManager) != null) {
            startActivity(intent)
        }
    }
}