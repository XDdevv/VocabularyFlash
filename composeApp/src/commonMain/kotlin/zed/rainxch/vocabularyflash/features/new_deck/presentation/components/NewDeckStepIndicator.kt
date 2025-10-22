package zed.rainxch.vocabularyflash.features.new_deck.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import zed.rainxch.vocabularyflash.features.new_deck.presentation.model.NewDeckStep

@Composable
fun NewDeckStepIndicator(
    currentStep: NewDeckStep,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterHorizontally)
    ) {
        NewDeckStep.entries.forEach { step ->
            if (step != NewDeckStep.Complete) {
                Box(
                    Modifier
                        .size(16.dp)
                        .clip(CircleShape)
                        .background(
                            color = if (step == currentStep) {
                                MaterialTheme.colorScheme.primary
                            } else MaterialTheme.colorScheme.outline
                        )
                        .padding(8.dp)
                )
            } else {
                Icon(
                    imageVector = Icons.Default.CheckCircle,
                    contentDescription = null,
                    modifier = Modifier
                        .size(20.dp),
                    tint = if (step == currentStep) {
                        MaterialTheme.colorScheme.primary
                    } else MaterialTheme.colorScheme.outline
                )
            }
        }
    }
}