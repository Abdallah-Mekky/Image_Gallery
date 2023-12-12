package com.example.imageGallery.di.home

import com.example.imageGallery.ui.home.RandomPhotosAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.scopes.FragmentScoped

@Module
@InstallIn(FragmentComponent::class)
object RandomPhotosAdapterModule {
    @Provides
    @FragmentScoped
    fun provideRandomPhotosAdapter() = RandomPhotosAdapter()
}