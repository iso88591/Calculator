package me.iso88591.cacu

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
import me.iso88591.cacu.logics.CacuKeys
import me.iso88591.cacu.ui.cacu.SimpleVerticalCacu
import me.iso88591.cacu.ui.cacu.VerticalScreen
import me.iso88591.cacu.ui.theme.CacuButtonColor
//import me.iso88591.cacu.ui.cacu.SimpleCacu
import me.iso88591.cacu.ui.theme.CacuTheme
import me.iso88591.cacu.ui.theme.Local_MyPalete
import me.iso88591.cacu.ui.theme.MyPalete

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        WindowCompat.setDecorFitsSystemWindows(window,true)

        setContent {
            var isDark by remember {
                mutableStateOf(true)
            }
            CacuTheme(isDark) {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier
                        .fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                    var screenText by remember {
                        mutableStateOf("")
                    }

                    Column(
                        Modifier
                    ) {

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

                        SimpleVerticalCacu{
                            screenText += it.simpleText
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