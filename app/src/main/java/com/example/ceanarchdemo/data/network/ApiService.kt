package com.example.ceanarchdemo.data.network

import com.example.ceanarchdemo.data.model.ImageDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("api")
    suspend fun getImages(
        @Query("key") key: String
    ): ImageDTO

    @GET("api/")
    suspend fun getImageSearch(
        @Query("key") key: String,
        @Query("q") query: String
    ): ImageDTO
}