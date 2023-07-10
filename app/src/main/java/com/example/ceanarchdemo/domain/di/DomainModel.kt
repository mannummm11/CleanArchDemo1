package com.example.ceanarchdemo.domain.di

import com.example.ceanarchdemo.domain.reository.ImageRepository
import com.example.ceanarchdemo.domain.useCase.GetSearchImageUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object DomainModel {

    @Provides
    fun provideGetImageSearchUseCase(imageRepository: ImageRepository): GetSearchImageUseCase {
        return GetSearchImageUseCase(imageRepository)
    }
}