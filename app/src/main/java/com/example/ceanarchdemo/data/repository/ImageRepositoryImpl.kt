package com.example.ceanarchdemo.data.repository

import com.example.ceanarchdemo.common.toDomainModel
import com.example.ceanarchdemo.data.model.ImageDTO
import com.example.ceanarchdemo.data.network.ApiService
import com.example.ceanarchdemo.domain.model.ImageModel
import com.example.ceanarchdemo.domain.reository.ImageRepository
import java.lang.Exception

class ImageRepositoryImpl(private val apiService: ApiService) : ImageRepository {
    override suspend fun getImage(): ImageDTO {
        try {
            return apiService.getImages("38155337-9e4909ae1de8948a68ac71621")
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun getSearchImage(key: String, query: String): List<ImageModel> {
        try {
            return apiService.getImageSearch("38155337-9e4909ae1de8948a68ac71621", query).hits.map { it.toDomainModel() }
        } catch (e: Exception) {
            throw e
        }
    }
}