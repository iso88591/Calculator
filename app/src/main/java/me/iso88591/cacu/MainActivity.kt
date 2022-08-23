package me.iso88591.cacu

import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import android.widget.LinearLayout
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.core.view.WindowCompat
//import me.iso88591.cacu.ui.cacu.SimpleCacu
import me.iso88591.cacu.ui.theme.CacuTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        WindowCompat.setDecorFitsSystemWindows(window,true)

        setContent {
            CacuTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

//                    var screenText by remember {
//                        mutableStateOf("")
//                    }
//                    SimpleCacu(screenText){
////                        screenText += it.key
//                    }

                    UseGrid()

                }
            }
        }
    }

    @OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3WindowSizeClassApi::class)
    @Composable
    private fun UseGrid() {

//        LazyVerticalGrid(cells = GridCells.Fixed(5), content = {
//
//            items(99){
//
//                Text(text = "text${it}")
//            }
//
//        })

//        val calculateWindowSizeClass =
//            calculateWindowSizeClass(activity = LocalContext.current as Activity)


        println("============${LocalConfiguration.current.screenWidthDp} ==== ${LocalConfiguration.current.screenHeightDp}")

        println("============${LocalView.current.measuredHeight}")

        println("============${(LocalView.current.parent.parent.parent as LinearLayout).measuredHeight}")

        //2340
        //2244
        //  96


        val size = (LocalConfiguration.current.screenWidthDp / 4).dp

        LazyVerticalGrid(cells = GridCells.Adaptive(size), content = {
            items(99) {
                Box(modifier = Modifier.height(size), contentAlignment = Alignment.Center) {
                    Text(text = "text${it}", textAlign = TextAlign.Center)
                }
            }

        })

    }

}

@Composable
fun Greeting(name: String) {
    //计算器
    //基本的加减乘除 四则运算
    //支持横竖屏 额外加分
    //独特设计 或者拓展功能额外功能


    //
//    1 + 2

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CacuTheme {
        Greeting("Android")
    }
}