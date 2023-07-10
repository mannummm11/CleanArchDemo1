package com.example.ceanarchdemo.common

import com.example.ceanarchdemo.data.model.HitDTO
import com.example.ceanarchdemo.domain.model.ImageModel

fun HitDTO.toDomainModel(): ImageModel {
    return ImageModel(
        this.previewURL
    )
}