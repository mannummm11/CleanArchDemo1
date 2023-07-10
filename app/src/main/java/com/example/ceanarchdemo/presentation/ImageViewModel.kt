package com.example.ceanarchdemo.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ceanarchdemo.common.Resource
import com.example.ceanarchdemo.domain.useCase.GetSearchImageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ImageViewModel @Inject constructor(
    private val getSearchImageUseCase: GetSearchImageUseCase
) : ViewModel() {

    private val _imageList = mutableStateOf(ImageState())
    val imageList: State<ImageState> get() = _imageList

    private val _query = MutableStateFlow("")

    init {
        getSearchImage("")
        viewModelScope.launch {
            _query.debounce(1000).collectLatest {
                getSearchImage(it)
            }
        }
    }

    fun updateQuery(query: String) {
        _query.value = query
    }

    private fun getSearchImage(query: String) {
        getSearchImageUseCase(query).onEach {
            when(it) {
                is Resource.Success -> {
                    _imageList.value = ImageState(data = it.data)
                }
                is Resource.Error -> {
                    _imageList.value = ImageState(error = it.error.toString())
                }
                is Resource.Loading -> {
                    _imageList.value = ImageState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}