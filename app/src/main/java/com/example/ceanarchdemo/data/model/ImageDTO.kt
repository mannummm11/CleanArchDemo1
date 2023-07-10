package com.example.ceanarchdemo.data.model

data class ImageDTO(
    val hits: List<HitDTO>,
    val total: Int,
    val totalHits: Int
)