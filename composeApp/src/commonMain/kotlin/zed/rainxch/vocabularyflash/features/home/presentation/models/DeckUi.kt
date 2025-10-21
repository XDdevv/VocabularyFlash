package zed.rainxch.vocabularyflash.features.home.presentation.models

import androidx.compose.ui.graphics.Brush

data class DeckUi(
    val id: Int,
    val title: String,
    val description: String?,
    val totalWords: Int,
    val color: Brush,
)
