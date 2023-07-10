package com.example.ceanarchdemo.data.di

import com.example.ceanarchdemo.data.network.ApiService
import com.example.ceanarchdemo.data.repository.ImageRepositoryImpl
import com.example.ceanarchdemo.domain.reository.ImageRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@InstallIn(SingletonComponent::class)
@Module
object DataModule {

    @Provides
    fun provideApiService(): ApiService {
        return Retrofit.Builder().baseUrl("https://pixabay.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    @Provides
    fun provideImageRepository(apiService: ApiService): ImageRepository {
        return ImageRepositoryImpl(apiService)
    }

}