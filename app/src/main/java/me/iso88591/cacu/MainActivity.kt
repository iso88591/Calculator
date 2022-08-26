package me.iso88591.cacu

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.MeasurePolicy
import androidx.compose.ui.platform.*
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import me.iso88591.cacu.logics.CacuKeys
import me.iso88591.cacu.logics.KeyBoardInput
import me.iso88591.cacu.logics.KeyBoardUiInput
import me.iso88591.cacu.logics.Local_CacuKey_Text_Size
import me.iso88591.cacu.ui.cacu.SimpleHorizontalCacu
import me.iso88591.cacu.ui.cacu.SimpleVerticalCacu
import me.iso88591.cacu.ui.cacu.VerticalScreen
import me.iso88591.cacu.ui.theme.*

//import me.iso88591.cacu.ui.cacu.SimpleCacu

class MainActivity : ComponentActivity() {

    private fun isVertical() =
        resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        WindowCompat.setDecorFitsSystemWindows(window,true)
        val isDarkState = mutableStateOf(true)
        var isDark by isDarkState
        val keyBoardInput = KeyBoardInput()
        val input = KeyBoardUiInput(isDarkState,keyBoardInput) {
        }

        setContent {

            CacuTheme(isDark) {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier
                        .fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                    var screenText by remember {
                        mutableStateOf(keyBoardInput.emptyShow())
                    }

                    CompositionLocalProvider(

                        Local_CacuKey_Text_Size provides if (isVertical()) {
                            30.sp
                        } else {
                            15.sp
                        }

                    ) {

                        Column(
                            Modifier
                        ) {

                            if (isVertical()) {

                                Box(
                                    Modifier
                                        .fillMaxWidth()
                                        .weight(1f, true)
                                ) {

                                    VerticalScreen(text = screenText)

                                    Button(onClick = { isDark = !isDark }) {
                                        Text(text = "改变主题")
                                    }

                                }

                                SimpleVerticalCacu {
                                    screenText = input.onKeyDown(it)
                                }
                            } else {

                                Box(
                                    Modifier
                                        .fillMaxWidth()
                                        .fillMaxHeight(0.25f)
                                ) {

                                    VerticalScreen(text = screenText)

                                    Button(onClick = { isDark = !isDark }) {
                                        Text(text = "改变主题")
                                    }

                                }

                                SimpleHorizontalCacu {
                                    screenText = input.onKeyDown(it)
                                }
                            }


                        }

                    }
                }
            }
        }
    }

}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CacuTheme {
    }
}