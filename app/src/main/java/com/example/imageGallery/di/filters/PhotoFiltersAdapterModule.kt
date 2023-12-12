package com.example.imageGallery.di.filters

import com.example.imageGallery.ui.filters.PhotoFiltersAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.scopes.FragmentScoped

@Module
@InstallIn(FragmentComponent::class)
object PhotoFiltersAdapterModule {
    @Provides
    @FragmentScoped
    fun providePhotoFiltersAdapter() = PhotoFiltersAdapter()
}