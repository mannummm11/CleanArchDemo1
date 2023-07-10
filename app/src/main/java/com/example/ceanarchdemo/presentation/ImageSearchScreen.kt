package com.example.ceanarchdemo.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(imageViewModel: ImageViewModel = hiltViewModel()) {

    val result = imageViewModel.imageList.value
    val query = remember { mutableStateOf("") }

    if (result.isLoading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    }

    if (result.error.isNotBlank()) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(text = result.error)
        }
    }

    Box(modifier = Modifier.fillMaxWidth()) {
        Column {
            TextField(
                value = query.value,
                modifier = Modifier.padding(4.dp).fillMaxWidth(),
                onValueChange = {
                    query.value = it
                    imageViewModel.updateQuery(query = query.value)
                },
                colors = TextFieldDefaults.textFieldColors(
                    textColor = Color.Black,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )
            )
            result.data?.let { listData ->
                LazyColumn {
                    items(listData.size) {
                        AsyncImage(
                            model = listData[it].imageURL,
                            contentDescription = null,
                            modifier = Modifier.fillMaxWidth()
                                .height(200.dp)
                                .padding(vertical = 4.dp)
                        )
                    }
                }
            }
        }
    }
}