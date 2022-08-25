package me.iso88591.cacu.ui.theme

import android.content.res.Configuration
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration


// * 浅色:
// *
// * 数字键: 背景 505050 字 D4D4D2
// *
// * 功能键 背景 D4D4D2  字 1C1C1C
// *

// * 深色:
// *
// *  * 数字键 背景
// *  val textColor: Color = Color.White,
//val bgColor: Color = Color(0xff333333),
//
// * 功能键:
// *  textColor = Color.Black,
//bgColor = Color(0xffA5A5A5)
data class CacuKeyColorState(
    val textColor: Color = Color.White,
    val bgColor: Color = Color.White,
) {


    object Dark {

        val Num = CacuKeyColorState(
            Color.White,
            Color(0xff333333),
        )

        val Func = CacuKeyColorState(
            Color(0xff1C1C1C),
            Color(0xffD4D4D2),
        )

        val FuncCheck = CacuKeyColorState(
            Color(0xffD4D4D2),
            Color(0xff1C1C1C),
        )

    }

    object Light {

        val Num = CacuKeyColorState(
            Color(0xffD4D4D2),
            Color(0xff505050),
        )

        val Func = CacuKeyColorState(
            Color(0xff1C1C1C),
            Color(0xffD4D4D2),
        )

        val FuncCheck = CacuKeyColorState(
            Color(0xffD4D4D2),
            Color(0xff1C1C1C),
        )

    }

    companion object{
        //op键 +-*/
        val Op = CacuKeyColorState(
            textColor = Color.White,
            bgColor = Color(0xffFEA00A)
        )

        val OpCheck = CacuKeyColorState(
            bgColor = Color.White,
            textColor = Color(0xffFEA00A)
        )
    }

}

val Local_MyPalete = compositionLocalOf<MyPalete> {
    error("没用提供默认的调色板")
}

//调色板
@Stable
class MyPalete {

    var cacuNumButtonColor by mutableStateOf(CacuKeyColorState())

    var cacuFuncButtonColor by mutableStateOf(CacuKeyColorState())
}