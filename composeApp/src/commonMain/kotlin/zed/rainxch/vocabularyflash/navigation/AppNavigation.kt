package zed.rainxch.vocabularyflash.navigation

import androidx.compose.runtime.Composable
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
        startDestination = VocabularyFlashNavGraph.HomeScreen
    ) {
        composable<VocabularyFlashNavGraph.HomeScreen> {
            HomeRoot()
        }

        composable<VocabularyFlashNavGraph.PracticeDeckScreen> {
            PracticeDeckRoot()
        }

        composable<VocabularyFlashNavGraph.NewDeckScreen> {
            NewDeckRoot()
        }
    }
}
