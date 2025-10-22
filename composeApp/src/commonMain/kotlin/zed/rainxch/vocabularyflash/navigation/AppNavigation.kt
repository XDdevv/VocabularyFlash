package zed.rainxch.vocabularyflash.navigation

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import zed.rainxch.vocabularyflash.features.home.presentation.HomeRoot
import zed.rainxch.vocabularyflash.features.new_deck.presentation.NewDeckRoot
import zed.rainxch.vocabularyflash.features.practice_deck.presentation.PracticeDeckRoot

@Composable
fun AppNavigation(
    navHostController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navHostController,
        startDestination = VocabularyFlashNavGraph.HomeScreen,
        enterTransition = {
            slideInHorizontally(
                initialOffsetX = { it },
                animationSpec = spring(Spring.DampingRatioMediumBouncy)
            ) + fadeIn(animationSpec = tween(300))
        },
        exitTransition = {
            slideOutHorizontally(
                targetOffsetX = { -it / 3 },
                animationSpec = spring(Spring.DampingRatioMediumBouncy)
            ) + fadeOut(animationSpec = tween(300))
        },
        popEnterTransition = {
            slideInHorizontally(
                initialOffsetX = { -it / 3 },
                animationSpec = spring(Spring.DampingRatioMediumBouncy)
            ) + fadeIn(animationSpec = tween(300))
        },
        popExitTransition = {
            slideOutHorizontally(
                targetOffsetX = { it },
                animationSpec = spring(Spring.DampingRatioMediumBouncy)
            ) + fadeOut(animationSpec = tween(300))
        },
        modifier = Modifier.background(MaterialTheme.colorScheme.background)
    ) {
        composable<VocabularyFlashNavGraph.HomeScreen> {
            HomeRoot(
                onNavigateToPracticeDeck = { deckId ->
                    navHostController.navigate(VocabularyFlashNavGraph.PracticeDeckScreen(deckId))
                },
                onNavigateToCreateNewDeck = {
                    navHostController.navigate(VocabularyFlashNavGraph.NewDeckScreen)
                }
            )
        }

        composable<VocabularyFlashNavGraph.PracticeDeckScreen> {
            PracticeDeckRoot()
        }

        composable<VocabularyFlashNavGraph.NewDeckScreen> {
            NewDeckRoot(
                onNavigateBack = {
                    navHostController.navigateUp()
                }
            )
        }
    }
}
