package com.example.data.di.repos.randomPhotosRepo

import com.example.data.api.WebServices
import com.example.data.reposImpl.randomPhotosRepoImpl.RandomPhotosOnlineDataSourceImpl
import com.example.data.reposImpl.randomPhotosRepoImpl.RandomPhotosRepoImpl
import com.example.domain.repos.randomPhotosRepo.RandomPhotosOnlineDataSource
import com.example.domain.repos.randomPhotosRepo.RandomPhotosRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RandomPhotosRepoModule {

    @Provides
    @Singleton
    fun provideRandomPhotosOnlineDataSource(
        webServices: WebServices
    ): RandomPhotosOnlineDataSource {
        return RandomPhotosOnlineDataSourceImpl(webServices)
    }

    @Provides
    @Singleton
    fun provideRandomPhotosRepo(
        randomPhotosOnlineDataSource: RandomPhotosOnlineDataSource,
    ): RandomPhotosRepo {
        return RandomPhotosRepoImpl(randomPhotosOnlineDataSource)
    }
}