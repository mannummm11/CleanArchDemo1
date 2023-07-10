package com.example.ceanarchdemo.domain.useCase

import com.example.ceanarchdemo.common.Resource
import com.example.ceanarchdemo.domain.model.ImageModel
import com.example.ceanarchdemo.domain.reository.ImageRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetSearchImageUseCase(private val imageRepository: ImageRepository) {

    operator fun invoke(query: String): Flow<Resource<List<ImageModel>>> {
        return flow {
            emit(Resource.Loading())
            try {
                emit(Resource.Success(
                   imageRepository.getSearchImage(key = "", query = query)
                ))
            } catch (e: Exception) {
                emit(Resource.Error(e.message.toString()))
            }
        }
    }
}