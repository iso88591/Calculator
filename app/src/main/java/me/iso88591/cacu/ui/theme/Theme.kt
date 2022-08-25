package me.iso88591.cacu.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val DarkColorPalette = darkColors(
    primary = Purple200,
    primaryVariant = Purple700,
    secondary = Teal200,
    //.background(Color(0xff010101))
    background = Color(0xff010101),
//MaterialTheme.colors.background
)

private val LightColorPalette = lightColors(
    primary = Purple500,
    primaryVariant = Purple700,
    secondary = Teal200,

    background = Color(0xffF7F8FB),


    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun CacuTheme(darkTheme: Boolean = isSystemInDarkTheme(),
              content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = {
            val systemUiController = rememberSystemUiController()
            val darkIcons = MaterialTheme.colors.isLight
            SideEffect {
                systemUiController.setSystemBarsColor(colors.background, darkIcons = darkIcons)
            }

            CompositionLocalProvider(
                Local_MyPalete provides if (darkTheme) {
                    MyPalete().also {
                        it.cacuNumButtonColor = CacuKeyColorState.Dark.Num
                        it.cacuFuncButtonColor = CacuKeyColorState.Dark.Func
                    }
                } else {
                    MyPalete().also {
                        it.cacuNumButtonColor = CacuKeyColorState.Light.Num
                        it.cacuFuncButtonColor = CacuKeyColorState.Light.Func
                    }
                }
            ) {
                content()
            }

        }
    )
}