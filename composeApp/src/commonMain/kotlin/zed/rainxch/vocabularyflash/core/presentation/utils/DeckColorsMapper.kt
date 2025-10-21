package zed.rainxch.vocabularyflash.core.presentation.utils

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import zed.rainxch.vocabularyflash.core.domain.utils.DeckColorsConstants


object DeckColorsMapper {
    val deckColors = mapOf(
        DeckColorsConstants.RAINBOW_BLUE_ID to rainbowBlueBrush,
        DeckColorsConstants.ORANGE_CORAL_ID to orangeCoralBrush,
        DeckColorsConstants.CRYSTAL_CLEAR_ID to crystalClearBrush,
    )

    val rainbowBlueBrush
        get() = Brush.linearGradient(
            listOf(Color(0xff00F260), Color(0xff0575E6))
        )

    val orangeCoralBrush
        get() = Brush.linearGradient(
            listOf(Color(0xffff9966), Color(0xffff5e62))
        )

    val crystalClearBrush
        get() = Brush.linearGradient(
            listOf(Color(0xff159957), Color(0xff155799))
        )
}

fun String.toDeckColor(): Brush {
    return DeckColorsMapper.deckColors[this] ?: DeckColorsMapper.rainbowBlueBrush
}