package zed.rainxch.vocabularyflash.features.home.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AddBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel
import vocabularyflash.composeapp.generated.resources.Res
import vocabularyflash.composeapp.generated.resources.app_name
import vocabularyflash.composeapp.generated.resources.create_new_deck
import zed.rainxch.vocabularyflash.core.presentation.design_system.theme.AppTheme
import zed.rainxch.vocabularyflash.features.home.presentation.components.DeckItem

@Composable
fun HomeRoot(
    onNavigateToCreateNewDeck: () -> Unit,
    onNavigateToPracticeDeck: (deckId: Int) -> Unit,
    viewModel: HomeViewModel = koinViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    HomeScreen(
        state = state,
        onAction = { action ->
            when (action) {
                is HomeAction.OnDeckClick -> {
                    onNavigateToPracticeDeck(action.deck.id)
                }

                is HomeAction.OnCreateNewDeckClick -> {
                    onNavigateToCreateNewDeck()
                }
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    state: HomeState,
    onAction: (HomeAction) -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(Res.string.app_name),
                        style = MaterialTheme.typography.titleLarge,
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    titleContentColor = MaterialTheme.colorScheme.onSurface
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    onAction(HomeAction.OnCreateNewDeckClick)
                }
            ) {
                Icon(
                    imageVector = Icons.Outlined.AddBox,
                    contentDescription = stringResource(Res.string.create_new_deck)
                )
            }
        },
        containerColor = MaterialTheme.colorScheme.surfaceContainer,
        modifier = Modifier.safeDrawingPadding()
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(top = 12.dp)
                .padding(horizontal = 12.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(
                key = { deck -> deck.id },
                items = state.decks
            ) { deck ->
                DeckItem(
                    deck = deck,
                    onClick = {
                        onAction(HomeAction.OnDeckClick(deck))
                    },
                )
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    AppTheme {
        HomeScreen(
            state = HomeState(),
            onAction = {}
        )
    }
}