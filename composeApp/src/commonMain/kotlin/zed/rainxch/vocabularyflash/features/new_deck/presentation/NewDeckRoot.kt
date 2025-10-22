package zed.rainxch.vocabularyflash.features.new_deck.presentation

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel
import vocabularyflash.composeapp.generated.resources.Res
import vocabularyflash.composeapp.generated.resources.create_new_deck
import vocabularyflash.composeapp.generated.resources.navigate_back
import zed.rainxch.vocabularyflash.core.presentation.design_system.theme.AppTheme
import zed.rainxch.vocabularyflash.core.presentation.utils.ObserveAsEvents
import zed.rainxch.vocabularyflash.features.new_deck.presentation.components.ChooseNameStep
import zed.rainxch.vocabularyflash.features.new_deck.presentation.components.CompleteStep
import zed.rainxch.vocabularyflash.features.new_deck.presentation.components.CreateWordsStep
import zed.rainxch.vocabularyflash.features.new_deck.presentation.components.NewDeckNavigationButtons
import zed.rainxch.vocabularyflash.features.new_deck.presentation.components.NewDeckStepIndicator
import zed.rainxch.vocabularyflash.features.new_deck.presentation.model.NewDeckStep

@Composable
fun NewDeckRoot(
    onNavigateBack: () -> Unit,
    viewModel: NewDeckViewModel = koinViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    ObserveAsEvents(viewModel.events) { event ->
        when (event) {
            NewDeckEvents.OnDeckCreatedSuccessfully -> {
                onNavigateBack()
            }

            is NewDeckEvents.OnDeckCreateFailure -> {
                scope.launch {
                    snackbarHostState.showSnackbar(event.message)
                }
            }
        }
    }

    NewDeckScreen(
        state = state,
        onAction = { action ->
            when (action) {
                NewDeckAction.OnBackClick -> onNavigateBack()

                else -> viewModel.onAction(action)
            }
        },
        snackbarHostState = snackbarHostState
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewDeckScreen(
    state: NewDeckState,
    onAction: (NewDeckAction) -> Unit,
    snackbarHostState: SnackbarHostState,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(Res.string.create_new_deck),
                        style = MaterialTheme.typography.titleLarge
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = { onAction(NewDeckAction.OnBackClick) }
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                            contentDescription = stringResource(Res.string.navigate_back)
                        )
                    }
                }
            )
        },
        snackbarHost = {
            SnackbarHost(snackbarHostState)
        },
        modifier = Modifier.safeDrawingPadding()
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            NewDeckStepIndicator(
                currentStep = state.currentStep
            )

            Spacer(Modifier.height(32.dp))

            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) {
                AnimatedContent(
                    targetState = state.currentStep,
                    transitionSpec = {
                        if (state.isNavigatingForward) {
                            (slideInHorizontally(
                                initialOffsetX = { it },
                                animationSpec = tween(300, easing = FastOutSlowInEasing)
                            ) + fadeIn(animationSpec = tween(300)))
                                .togetherWith(
                                    slideOutHorizontally(
                                        targetOffsetX = { -it },
                                        animationSpec = tween(300, easing = FastOutSlowInEasing)
                                    ) + fadeOut(animationSpec = tween(300))
                                )
                        } else {
                            (slideInHorizontally(
                                initialOffsetX = { -it },
                                animationSpec = tween(300, easing = FastOutSlowInEasing)
                            ) + fadeIn(animationSpec = tween(300)))
                                .togetherWith(
                                    slideOutHorizontally(
                                        targetOffsetX = { it },
                                        animationSpec = tween(300, easing = FastOutSlowInEasing)
                                    ) + fadeOut(animationSpec = tween(300))
                                )
                        }
                    },
                    label = "step_animation"
                ) { currentStep ->
                    when (currentStep) {
                        NewDeckStep.ChooseName -> {
                            ChooseNameStep(
                                state = state,
                                onAction = onAction
                            )
                        }

                        NewDeckStep.CreateWords -> {
                            CreateWordsStep(
                                state = state,
                                onAction = onAction
                            )
                        }

                        NewDeckStep.Complete -> {
                            CompleteStep(
                                state = state,
                            )
                        }
                    }
                }
            }

            NewDeckNavigationButtons(
                currentStep = state.currentStep,
                onPreviousClick = {
                    onAction(NewDeckAction.OnPreviousStepClick)
                },
                onNextClick = {
                    onAction(NewDeckAction.OnNextStepClick)
                }
            )
        }
    }
}

@Preview
@Composable
private fun Preview() {
    AppTheme {
        NewDeckScreen(
            state = NewDeckState(),
            onAction = {},
            snackbarHostState = SnackbarHostState()
        )
    }
}