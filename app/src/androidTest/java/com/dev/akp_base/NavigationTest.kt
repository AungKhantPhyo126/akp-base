/*
 * Copyright 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.dev.akp_base

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.dev.akp_base.navigation.AkpBaseNavHost
import com.dev.akp_base.navigation.MyNavDestination
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class NavigationTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    private lateinit var navController: TestNavHostController

    @Before
    fun setupAkpBaseNavHost() {
        composeTestRule.setContent {
            // Creates a TestNavHostController
            navController = TestNavHostController(LocalContext.current)
            // Sets a ComposeNavigator to the navController so it can navigate through composables
            navController.navigatorProvider.addNavigator(ComposeNavigator())
            AkpBaseNavHost(navController = navController)
        }
    }

    @Test
    fun akpBaseNavHost_HomeScreenDestination() {
        composeTestRule
            .onNodeWithContentDescription("Home Screen")
            .assertIsDisplayed()
    }

    @Test
    fun akpBaseNavHost_clickGoToDetails_navigatesToDetailsScreen() {
        composeTestRule
            .onNodeWithText("Go to Details")
            .performClick()

        composeTestRule
            .onNodeWithContentDescription("Details Screen")
            .assertIsDisplayed()
    }

    @Test
    fun akpBaseNavHost_ProfileScreenDestination() {
        composeTestRule.onNodeWithContentDescription("Profile")
            .performClick()

        val route = navController.currentBackStackEntry?.destination?.route
        assertEquals(route, MyNavDestination.ProfileDestination.route)
    }
}
