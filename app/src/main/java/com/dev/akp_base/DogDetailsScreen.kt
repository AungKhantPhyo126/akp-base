package com.dev.akp_base

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun DogDetailScreen(
    modifier: Modifier = Modifier,
    dog: Dog,
    breedSize: BreedSize,
) {
    Text(
        modifier = Modifier.fillMaxSize(),
        text = "This dog name is $dog and breedSize is ${breedSize.name}"
    )

}