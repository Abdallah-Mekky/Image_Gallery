package com.example.imageGallery.ui.home

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
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.domain.model.api.reponse.PhotoDTO
import com.example.imageGallery.databinding.FragmentHomeBinding
import com.example.imageGallery.model.CategoryItem
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()

    @Inject
    lateinit var categoriesAdapter: CategoriesAdapter

    @Inject
    lateinit var randomPhotosAdapter: RandomPhotosAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        init()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        subscribeToLiveData()
        onCategoryClick()
        onRandomPhotoClick()
    }

    private fun init() {
        viewModel.checkInternConnection()
        setupForCategoriesRV()
        binding.randomPhotosRV.adapter = randomPhotosAdapter
    }

    private fun setupForCategoriesRV() {

        binding.categoriesRV.apply {
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.HORIZONTAL,
                false
            )

            adapter = categoriesAdapter
        }
    }

    private fun onCategoryClick() {
        categoriesAdapter.onItemClickListener = object : CategoriesAdapter.OnItemClickListener {
            override fun onItemClick(position: Int, currentCategory: CategoryItem) {
                val action = HomeFragmentDirections.actionHomeFragmentToPhotosFragment(
                    headerTitle = currentCategory.categoryTitle,
                    selectedCategory = currentCategory.categoryTitle.lowercase(Locale.ROOT)
                )

                findNavController().navigate(action)
            }

        }
    }

    private fun subscribeToLiveData() {
        viewModel.categoriesList.observe(viewLifecycleOwner) {
            categoriesAdapter.refreshAdapter(it)
        }

        viewModel.isInternetConnected.observe(viewLifecycleOwner) {
            if (it) {
                showViews()
            } else {
                hideViews()
            }
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.randomPhotosList.collect { pagingData ->
                    randomPhotosAdapter.submitData(pagingData)
                }
            }
        }

    }

    private fun showViews() {
        binding.noInternet.visibility = View.GONE
        binding.randomPhotosTV.visibility = View.VISIBLE
        binding.randomPhotosRV.visibility = View.VISIBLE
    }

    private fun hideViews() {
        binding.noInternet.visibility = View.VISIBLE
        binding.randomPhotosTV.visibility = View.GONE
        binding.randomPhotosTV.visibility = View.GONE
    }

    private fun onRandomPhotoClick() {
        randomPhotosAdapter.onItemClickListener = object : RandomPhotosAdapter.OnItemClickListener {
            override fun onItemClick(position: Int, currentPhoto: PhotoDTO) {

                val action = HomeFragmentDirections.actionHomeFragmentToDetailedViewFragment(
                    currentPhotoDetails = currentPhoto
                )

                findNavController().navigate(action)
            }

        }
    }

    override fun onResume() {
        super.onResume()

        if (!(randomPhotosAdapter.isEmpty())){
            binding.noInternet.visibility = View.GONE
        }
    }
}