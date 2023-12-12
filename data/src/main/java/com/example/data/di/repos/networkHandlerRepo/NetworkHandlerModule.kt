package com.example.data.di.repos.networkHandlerRepo

import android.content.Context
import com.example.data.reposImpl.networkHandlerImpl.NetworkHandlerImpl
import com.example.domain.repos.networkHandler.NetworkHandler
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkHandlerModule {

    @Provides
    @Singleton
    fun provideNetworkHandler(@ApplicationContext context: Context): NetworkHandler {
        return NetworkHandlerImpl(context)
    }
}