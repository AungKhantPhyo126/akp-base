package com.dev.akp_base

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import kotlinx.serialization.Serializable

@Serializable
data class Dog(
    val id:String,
    val name:String
)

enum class BreedSize{
    SMALL,MEDIUM,LARGE
}
val sampleDogList = listOf(
    Dog("1","dogOne"),
    Dog("2","dogTwo"),
    Dog("3","dogThree"),
    Dog("4","dogFour"),
    Dog("5","dogFive"),
)

@Composable
fun DogListScreen(
    modifier: Modifier = Modifier,
    onClickDog: (Dog,BreedSize)->Unit
) {
    LazyColumn (
        modifier = Modifier.fillMaxSize()
    ){
        items(sampleDogList){
            Text(
                modifier = Modifier.clickable {
                    onClickDog(it,BreedSize.MEDIUM)
                },
                text = "This dog name is ${it.name}"
            )
        }
    }
}
