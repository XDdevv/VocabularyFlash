package zed.rainxch.vocabularyflash.core.presentation.design_system.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import org.jetbrains.compose.resources.Font
import vocabularyflash.composeapp.generated.resources.Montserrat_Black
import vocabularyflash.composeapp.generated.resources.Montserrat_Bold
import vocabularyflash.composeapp.generated.resources.Montserrat_Light
import vocabularyflash.composeapp.generated.resources.Montserrat_Medium
import vocabularyflash.composeapp.generated.resources.Montserrat_Regular
import vocabularyflash.composeapp.generated.resources.Montserrat_SemiBold
import vocabularyflash.composeapp.generated.resources.Poppins_Black
import vocabularyflash.composeapp.generated.resources.Poppins_Bold
import vocabularyflash.composeapp.generated.resources.Poppins_Light
import vocabularyflash.composeapp.generated.resources.Poppins_Medium
import vocabularyflash.composeapp.generated.resources.Poppins_Regular
import vocabularyflash.composeapp.generated.resources.Poppins_SemiBold
import vocabularyflash.composeapp.generated.resources.Res


@Composable
fun bodyFontFamily(): FontFamily = FontFamily(
    Font(Res.font.Poppins_Light, FontWeight.Light),
    Font(Res.font.Poppins_Regular, FontWeight.Normal),
    Font(Res.font.Poppins_Medium, FontWeight.Medium),
    Font(Res.font.Poppins_SemiBold, FontWeight.SemiBold),
    Font(Res.font.Poppins_Bold, FontWeight.Bold),
    Font(Res.font.Poppins_Black, FontWeight.Black),
)

@Composable
fun displayFontFamily(): FontFamily = FontFamily(
    Font(Res.font.Montserrat_Light, FontWeight.Light),
    Font(Res.font.Montserrat_Regular, FontWeight.Normal),
    Font(Res.font.Montserrat_Medium, FontWeight.Medium),
    Font(Res.font.Montserrat_SemiBold, FontWeight.SemiBold),
    Font(Res.font.Montserrat_Bold, FontWeight.Bold),
    Font(Res.font.Montserrat_Black, FontWeight.Black),
)


// Default Material 3 typography values
val baseline = Typography()

@Composable
fun appTypography(): Typography {
    return Typography(
        displayLarge = baseline.displayLarge.copy(fontFamily = displayFontFamily()),
        displayMedium = baseline.displayMedium.copy(fontFamily = displayFontFamily()),
        displaySmall = baseline.displaySmall.copy(fontFamily = displayFontFamily()),
        headlineLarge = baseline.headlineLarge.copy(fontFamily = displayFontFamily()),
        headlineMedium = baseline.headlineMedium.copy(fontFamily = displayFontFamily()),
        headlineSmall = baseline.headlineSmall.copy(fontFamily = displayFontFamily()),
        titleLarge = baseline.titleLarge.copy(fontFamily = displayFontFamily()),
        titleMedium = baseline.titleMedium.copy(fontFamily = displayFontFamily()),
        titleSmall = baseline.titleSmall.copy(fontFamily = displayFontFamily()),
        bodyLarge = baseline.bodyLarge.copy(fontFamily = bodyFontFamily()),
        bodyMedium = baseline.bodyMedium.copy(fontFamily = bodyFontFamily()),
        bodySmall = baseline.bodySmall.copy(fontFamily = bodyFontFamily()),
        labelLarge = baseline.labelLarge.copy(fontFamily = bodyFontFamily()),
        labelMedium = baseline.labelMedium.copy(fontFamily = bodyFontFamily()),
        labelSmall = baseline.labelSmall.copy(fontFamily = bodyFontFamily()),
    )
}

