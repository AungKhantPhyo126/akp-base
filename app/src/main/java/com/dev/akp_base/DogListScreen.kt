package com.dev.akp_base

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
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

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.DogListScreen(
    modifier: Modifier = Modifier,
    animatedVisibilityScope: AnimatedVisibilityScope,
    onClickDog: (Dog,BreedSize)->Unit,
) {
    LazyColumn (
        modifier = Modifier.fillMaxSize()
    ){
        itemsIndexed(sampleDogList){index,dog->
            Row(
                modifier = Modifier.clickable {
                    onClickDog(dog,BreedSize.MEDIUM)
                },
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Image(
                    modifier = Modifier.aspectRatio(16/9f)
                        .weight(1f)
                        .sharedElement(
                            state = rememberSharedContentState(key = "image/${dog.id}"),
                            animatedVisibilityScope = animatedVisibilityScope,
                            boundsTransform = {initial,target->
                                tween(durationMillis = 1000)
                            }
                        ),
                    contentDescription = "dog",
                    contentScale = ContentScale.Crop,
                    painter = painterResource(R.drawable.dog1)
                )
                Text(
                    modifier = Modifier
                        .sharedElement(
                            state = rememberSharedContentState(key = "text/${dog.name}"),
                            animatedVisibilityScope = animatedVisibilityScope,
                            boundsTransform = {initial,target->
                                tween(durationMillis = 1000)
                            }
                        ),
                    text = "This dog name is ${dog.name}"
                )


            }

        }
    }
}
