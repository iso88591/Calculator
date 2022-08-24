package me.iso88591.cacu.ui.theme

import android.content.res.Configuration
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.compositionLocalOf
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
data class CacuButtonColor(
    val numBg: Color,
    val numColor: Color,
    val funcBg: Color,
    val funcColor: Color
) {

    companion object {

        val Dark = CacuButtonColor(
            numBg = Color(0xff333333),
            numColor = Color.White,
            funcBg = Color(0xffD4D4D2),
            funcColor = Color(0xff1C1C1C),

            )

        //// * 数字键: 背景 505050 字 D4D4D2
        //// *
        //// * 功能键 背景 D4D4D2  字 1C1C1C
        val Light = CacuButtonColor(
            numBg = Color(0xff505050),
            numColor = Color(0xffD4D4D2),
            funcBg = Color(0xffD4D4D2),
            funcColor = Color(0xff1C1C1C),
        )


    }

}

val Local_MyPalete = compositionLocalOf<MyPalete> {
    error("没用提供默认的调色板")
}

//调色板
data class MyPalete(
    val cacuButtonColor: CacuButtonColor
)