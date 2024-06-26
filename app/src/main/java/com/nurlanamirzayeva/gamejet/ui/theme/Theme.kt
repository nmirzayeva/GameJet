package com.nurlanamirzayeva.gamejet.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.nurlanamirzayeva.gamejet.utils.ThemeMode
import com.nurlanamirzayeva.gamejet.viewmodel.SettingsViewModel

private val DarkColorScheme = darkColorScheme(
    primary = dark_grey,
    secondary = black,
    tertiary = sky_blue

)

private val LightColorScheme = lightColorScheme(
    primary = dark_grey,
    secondary = black,
    tertiary = sky_blue

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable
fun GameJetTheme(
   themeViewModel:SettingsViewModel,
    content: @Composable () -> Unit
) {

    val state=themeViewModel.currentThemeMode.collectAsState()

    val isDarkTheme=when(state.value){
        ThemeMode.DAY->false
        ThemeMode.NIGHT->true
        ThemeMode.AUTO-> isSystemInDarkTheme()


    }


    val colorScheme = when {
        state.value ==ThemeMode.AUTO && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (isDarkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        isDarkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !isDarkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}