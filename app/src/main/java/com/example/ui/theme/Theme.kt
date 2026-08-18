package com.example.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = CyanAccent,
    onPrimary = Color(0xFF00363D),
    primaryContainer = Color(0xFF083344),
    onPrimaryContainer = Color(0xFF67E8F9),
    secondary = BlueElectric,
    onSecondary = Color(0xFF00344F),
    secondaryContainer = Color(0xFF0369A1),
    onSecondaryContainer = Color(0xFFBAE6FD),
    tertiary = IndigoAccent,
    onTertiary = Color(0xFF1E1B4B),
    tertiaryContainer = Color(0xFF312E81),
    onTertiaryContainer = Color(0xFFE0E7FF),
    background = SophisticatedDarkBg,
    onBackground = TextPrimaryDark,
    surface = SophisticatedDarkSurface,
    onSurface = TextPrimaryDark,
    surfaceVariant = SophisticatedDarkSurfaceVariant,
    onSurfaceVariant = TextSecondaryDark,
    outline = SophisticatedBorder,
    outlineVariant = Color(0x0DFFFFFF)
)

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF0284C7),
    onPrimary = Color.White,
    primaryContainer = Color(0xFFE0F2FE),
    onPrimaryContainer = Color(0xFF0369A1),
    secondary = Color(0xFF0D9488),
    onSecondary = Color.White,
    secondaryContainer = Color(0xFFCCFBF1),
    onSecondaryContainer = Color(0xFF115E59),
    tertiary = Color(0xFF6366F1),
    onTertiary = Color.White,
    tertiaryContainer = Color(0xFFEEF2FF),
    onTertiaryContainer = Color(0xFF3730A3),
    background = LightBg,
    onBackground = TextPrimaryLight,
    surface = LightSurface,
    onSurface = TextPrimaryLight,
    surfaceVariant = LightSurfaceVariant,
    onSurfaceVariant = TextSecondaryLight,
    outline = LightBorder,
    outlineVariant = Color(0xFFCBD5E1)
)

@Composable
fun MosesPortfolioTheme(
    darkTheme: Boolean = true, // Default to dark-first design as requested
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
