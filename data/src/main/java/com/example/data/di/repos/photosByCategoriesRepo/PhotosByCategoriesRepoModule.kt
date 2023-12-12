package com.example.data.di.repos.photosByCategoriesRepo

import com.example.data.api.WebServices
import com.example.data.reposImpl.photosByCategoriesRepoImpl.PhotosByCategoriesOnlineDataSourceImpl
import com.example.data.reposImpl.photosByCategoriesRepoImpl.PhotosByCategoriesRepoImpl
import com.example.domain.repos.photosByCategoriesRepo.PhotosByCategoriesOnlineDataSource
import com.example.domain.repos.photosByCategoriesRepo.PhotosByCategoriesRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PhotosByCategoriesRepoModule {

    @Provides
    @Singleton
    fun providePhotosByCategoriesOnlineDataSource(
        webServices: WebServices
    ): PhotosByCategoriesOnlineDataSource {
        return PhotosByCategoriesOnlineDataSourceImpl(webServices)
    }

    @Provides
    @Singleton
    fun providePhotosByCategoriesRepo(
        photosByCategoriesOnlineDataSource: PhotosByCategoriesOnlineDataSource,
    ): PhotosByCategoriesRepo {
        return PhotosByCategoriesRepoImpl(photosByCategoriesOnlineDataSource)
    }
}