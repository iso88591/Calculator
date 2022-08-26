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

interface DarkLightAble<T> {

    val dark: T

    val light: T

    fun get(darkOrLight: Boolean): T {
        return if (darkOrLight) {
            dark
        } else {
            light
        }
    }

}

abstract class CheckSelector<T> : DarkLightAble<T> {

    abstract val selected: DarkLightAble<T>

    abstract val unSelected: DarkLightAble<T>

    fun get(darkOrLight: Boolean, isCheck: Boolean = false): T {
        return if (isCheck) {
            selected.get(darkOrLight)
        } else {
            unSelected.get(darkOrLight)
        }
    }

    override fun get(darkOrLight: Boolean): T {
        return get(darkOrLight,false)
    }

    override val dark: T
        get() = TODO("Not yet implemented")
    override val light: T
        get() = TODO("Not yet implemented")

}

data class CacuKeyColorState(
    val textColor: Color = Color.White,
    val bgColor: Color = Color.White,
) {

//    object Num : DarkLightAble<CacuKeyColorState> {
//        override val dark: CacuKeyColorState = CacuKeyColorState(
//            Color.White,
//            Color(0xff333333),
//        )
//        override val light: CacuKeyColorState = CacuKeyColorState(
//            Color(0xffD4D4D2),
//            Color(0xff505050),
//        )
//    }

    object Num : CheckSelector<CacuKeyColorState>() {
        override val selected: DarkLightAble<CacuKeyColorState> = object :DarkLightAble<CacuKeyColorState>{
            override val dark: CacuKeyColorState = CacuKeyColorState(
                Color.Black,
                Color(0xffD9D9D9),
            )
            override val light: CacuKeyColorState = CacuKeyColorState(
                Color.White,
                Color.Black,
            )
        }

        override val unSelected: DarkLightAble<CacuKeyColorState> = object :DarkLightAble<CacuKeyColorState>{
            override val dark: CacuKeyColorState = CacuKeyColorState(
                Color.White,
                Color(0xff333333),
            )
            override val light: CacuKeyColorState = CacuKeyColorState(
                Color(0xffD4D4D2),
                Color(0xff505050),
            )
        }

    }

    object Func : DarkLightAble<CacuKeyColorState> {
        override val dark: CacuKeyColorState = CacuKeyColorState(
            Color(0xff1C1C1C),
            Color(0xffD4D4D2),
        )
        override val light: CacuKeyColorState = CacuKeyColorState(
            Color(0xff1C1C1C),
            Color(0xffD4D4D2),
        )

    }

    companion object {
        //op键 +-*/
        val Op = CacuKeyColorState(
            textColor = Color.White,
            bgColor = Color(0xffFEA00A)
        )

        val OpCheck = CacuKeyColorState(
            bgColor = Color.White,
            textColor = Color(0xffFEA00A)
        )


        //other op
        val OtherOpCheck = CacuKeyColorState(
            textColor = Color.Black,
            bgColor = Color(0xffD9D9D9)
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