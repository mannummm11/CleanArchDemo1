package com.example.ceanarchdemo.domain.reository

import com.example.ceanarchdemo.data.model.ImageDTO
import com.example.ceanarchdemo.domain.model.ImageModel

interface ImageRepository {

    suspend fun getImage(): ImageDTO
    suspend fun getSearchImage(key: String, query: String): List<ImageModel>
}