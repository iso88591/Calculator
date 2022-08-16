package me.iso88591.cacu

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalViewConfiguration
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat
import androidx.core.view.WindowCompat
import me.iso88591.cacu.ui.cacu.SimpleCacu
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

                    var screenText by remember {
                        mutableStateOf("")
                    }
                    SimpleCacu(screenText){
                        screenText += it.key
                    }

                }
            }
        }
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