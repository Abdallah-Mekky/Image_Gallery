package com.example.imageGallery.ui.photosByCategory

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.paging.PagingData
import com.example.domain.model.api.reponse.PhotoDTO
import com.example.imageGallery.databinding.FragmentPhotosBinding
import com.example.imageGallery.ui.home.RandomPhotosAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class PhotosFragment : Fragment() {

    private lateinit var binding: FragmentPhotosBinding
    private val args: PhotosFragmentArgs by navArgs()
    private val viewModel: PhotosViewModel by viewModels()

    @Inject
    lateinit var photosAdapter: RandomPhotosAdapter
    private val titleOfHeader by lazy { args.headerTitle }
    private val selectedCategory by lazy { args.selectedCategory }
    private lateinit var photosList: Flow<PagingData<PhotoDTO>>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentPhotosBinding.inflate(inflater, container, false)
        init()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        subscribeToLiveData()
        onPhotoClick()
    }

    private fun init() {
        viewModel.checkInternConnection()
        lifecycleScope.launch {
            photosList = viewModel.getPhotosByCategory(selectedCategory)
        }

        binding.headerTitle.text = titleOfHeader
        binding.photosRV.adapter = photosAdapter
    }

    private fun subscribeToLiveData() {

        viewModel.isInternetConnected.observe(viewLifecycleOwner) {

            if (it) {
                binding.noInternet.visibility = View.GONE
                binding.photosRV.visibility = View.VISIBLE
                binding.headerTitle.visibility = View.VISIBLE
            } else {
                binding.noInternet.visibility = View.VISIBLE
                binding.photosRV.visibility = View.GONE
                binding.headerTitle.visibility = View.GONE
            }
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                photosList.collect { pagingData ->
                    photosAdapter.submitData(pagingData)
                }
            }
        }
    }

    private fun onPhotoClick() {
        photosAdapter.onItemClickListener = object : RandomPhotosAdapter.OnItemClickListener {
            override fun onItemClick(position: Int, currentPhoto: PhotoDTO) {
                val action = PhotosFragmentDirections.actionPhotosFragmentToDetailedViewFragment(
                    currentPhotoDetails = currentPhoto
                )
                findNavController().navigate(action)
            }

        }
    }

    override fun onResume() {
        super.onResume()

        if (!(photosAdapter.isEmpty())){
            binding.noInternet.visibility = View.GONE
        }
    }
}