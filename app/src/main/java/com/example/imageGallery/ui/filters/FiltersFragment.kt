package com.example.imageGallery.ui.filters

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.imageGallery.R
import com.example.imageGallery.databinding.FragmentFiltersBinding
import com.example.imageGallery.enums.PhotoTypesEnum
import com.example.imageGallery.model.PhotoFilterItem
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@AndroidEntryPoint
class FiltersFragment : Fragment() {

    private lateinit var binding: FragmentFiltersBinding

    @Inject
    lateinit var photoFiltersAdapter: PhotoFiltersAdapter
    private lateinit var currentBitmap: Bitmap
    private val viewModel: FiltersViewModel by viewModels()
    private val args: FiltersFragmentArgs by navArgs()
    private val currentRandomPhoto by lazy { args.currentPhotoDetails }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentFiltersBinding.inflate(inflater, container, false)
        init()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        subscribeToLiveData()
        onPhotoFilterItemClick()
        onCloseClick()
    }

    private fun init() {
        setupPhotoFiltersRV()
        getBitmapFromUrlWithGlide(
            url = currentRandomPhoto.src?.portrait ?: "",
            type = PhotoTypesEnum.BIG_PHOTO
        )
        getBitmapFromUrlWithGlide(
            url = currentRandomPhoto.src?.tiny ?: "",
            type = PhotoTypesEnum.SMALL_PHOTO
        )
    }

    private fun setupPhotoFiltersRV() {
        binding.photoFiltersRV.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.HORIZONTAL,
            false
        )

        binding.photoFiltersRV.adapter = photoFiltersAdapter
    }

    private fun subscribeToLiveData() {
        viewModel.photoFiltersList.observe(viewLifecycleOwner) {
            photoFiltersAdapter.refreshAdapter(it)
        }
    }

    private fun onCloseClick() {
        binding.icClose.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun getBitmapFromUrlWithGlide(url: String, type: PhotoTypesEnum) {
        Glide.with(this)
            .asBitmap()
            .load(url)
            .placeholder(R.drawable.ic_loading)
            .fitCenter()
            .into(object : CustomTarget<Bitmap>() {
                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {

                    when (type) {
                        PhotoTypesEnum.BIG_PHOTO -> {
                            currentBitmap = resource
                            binding.currentPhotoIV.setImageBitmap(resource)
                        }

                        PhotoTypesEnum.SMALL_PHOTO -> {
                            lifecycleScope.launch(Dispatchers.Default) {
                                viewModel.getPhotoFiltersList(resource)
                            }
                        }
                    }
                }

                override fun onLoadCleared(placeholder: Drawable?) {
                    // this is called when imageView is cleared on lifecycle call or for
                    // some other reason.
                    // if you are referencing the bitmap somewhere else too other than this imageView
                    // clear it here as you can no longer have the bitmap
                }
            })
    }

    private fun onPhotoFilterItemClick() {

        photoFiltersAdapter.onItemClickListener = object : PhotoFiltersAdapter.OnItemClickListener {
            override fun onItemClick(position: Int, currentPhotoFilter: PhotoFilterItem) {

                lifecycleScope.launch(Dispatchers.Main) {
                    val result = withContext(Dispatchers.Default) {
                        viewModel.getPhotoWithFilterApplied(
                            filter = currentPhotoFilter.filter,
                            currentBitmap = currentBitmap
                        )
                    }
                    binding.currentPhotoIV.setImageBitmap(result)
                }
            }

        }
    }
}