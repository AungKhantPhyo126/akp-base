package com.dev.akp_base.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavType
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink

sealed interface MyNavDestination{
    val icon: ImageVector
    val route: String

    data object HomeDestination: MyNavDestination {
        override val icon: ImageVector
            get() = Icons.Filled.Home
        override val route: String
            get() = "Home"
    }


    data object DetailDestination: MyNavDestination {
        override val icon: ImageVector
            get() = Icons.Filled.Favorite
        override val route: String
            get() = "Detail"

        const val accountTypeArg = "account_type"
        const val detailsName = "details_name"
        val routeWithArgs = "$route/{$accountTypeArg}/{$detailsName}"
        val arguments = listOf(
            navArgument(accountTypeArg) { type = NavType.StringType },
            navArgument(detailsName) { type = NavType.EnumType(
                DetailType::class.java
            ) },
        )
        val deepLinks = listOf(
            navDeepLink { uriPattern = "myapp://$route/{$accountTypeArg}" }
        )

    }

    data object ProfileDestination: MyNavDestination {
        override val icon: ImageVector
            get() = Icons.Filled.Person
        override val route: String
            get() = "Profile"
    }
}

enum class DetailType{
    AKP,ANM
}

val navBarScreens = listOf(MyNavDestination.HomeDestination, MyNavDestination.ProfileDestination)
