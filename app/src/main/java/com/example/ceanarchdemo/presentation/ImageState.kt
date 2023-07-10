package com.example.ceanarchdemo.presentation

import com.example.ceanarchdemo.domain.model.ImageModel

data class ImageState(
    val isLoading: Boolean = false,
    val data: List<ImageModel>? = null,
    val error: String = ""
)
