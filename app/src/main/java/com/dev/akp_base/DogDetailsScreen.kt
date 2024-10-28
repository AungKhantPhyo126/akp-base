package com.dev.akp_base

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.DogDetailScreen(
    modifier: Modifier = Modifier,
    dog: Dog,
    breedSize: BreedSize,
    animatedVisibilityScope: AnimatedVisibilityScope
) {
    Column (
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ){
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
            text = "This dog name is $dog and breedSize is ${breedSize.name}"
        )

    }

}