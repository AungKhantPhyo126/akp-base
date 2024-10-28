package com.dev.akp_base.navigation

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.dev.akp_base.BreedSize
import com.dev.akp_base.CustomNavType
import com.dev.akp_base.Dog
import com.dev.akp_base.DogDetailScreen
import com.dev.akp_base.DogListScreen
import kotlinx.serialization.Serializable
import kotlin.reflect.typeOf

@Serializable
data object DogListRoute

@Serializable
data class DogDetailRoute(val dog: Dog, val breedSize: BreedSize)



@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun AkpBaseNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {

    SharedTransitionLayout {
        NavHost(
            navController = navController,
            startDestination = DogListRoute,
            modifier = modifier
        ) {
            composable<DogListRoute>{
                DogListScreen(
                    onClickDog = {dog, breedSize ->
                        navController.navigate(
                            DogDetailRoute(
                                dog = dog,
                                breedSize = breedSize
                            )
                        )
                    },
                    animatedVisibilityScope = this
                )
            }
            composable<DogDetailRoute>(
                typeMap = mapOf(
                    typeOf<Dog>() to CustomNavType.DogType,
                    typeOf<BreedSize>() to NavType.EnumType(BreedSize::class.java)
                )
            ){
                val arguments = it.toRoute<DogDetailRoute>()
                DogDetailScreen(
                    dog= arguments.dog,
                    breedSize = arguments.breedSize,
                    animatedVisibilityScope = this
                )
            }
        }
    }

}

//fun NavHostController.navigateSingleTopTo(route: String) =
//    this.navigate(route) {
//        // Pop up to the start destination of the graph to
//        // avoid building up a large stack of destinations
//        // on the back stack as users select items
//        popUpTo(
//            this@navigateSingleTopTo.graph.findStartDestination().id
//        ) {
//            saveState = true
//        }
//        // Avoid multiple copies of the same destination when
//        // reselecting the same item
//        launchSingleTop = true
//        // Restore state when reselecting a previously selected item
//        restoreState = true
//    }
//
//private fun NavHostController.navigateToDetailScreen(accountType: String) {
//    this.navigateSingleTopTo("${MyNavDestination.DetailDestination.route}/$accountType")
//}