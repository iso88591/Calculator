package me.iso88591.cacu.logics

import androidx.compose.ui.graphics.Color
import me.iso88591.cacu.ui.cacu.CacuButtonStyle

sealed class CacuKeys(
    val key: String,
    val style: CacuButtonStyle = CacuButtonStyle()
) {

    //归零键
    object C : CacuKeys("C",
        CacuButtonStyle(
            bgColor = Color(0xffA5A5A5),
            textColor = Color(0xff030303),
        ))

    //括号
    object Brackets : CacuKeys("( )",
        CacuButtonStyle(
            bgColor = Color(0xffA5A5A5),
            textColor = Color(0xff030303),
        ))

    //delete
    object Delete : CacuKeys("Del",
        CacuButtonStyle(
            bgColor = Color(0xffA5A5A5),
            textColor = Color(0xff030303),
        ))

    //+
    object Plus : CacuKeys("+",CacuButtonStyle(
        bgColor = Color(0xffFEA00A),
        textColor = Color.White,
    ))

    //-
    object Reduce : CacuKeys("-",CacuButtonStyle(
        bgColor = Color(0xffFEA00A),
        textColor = Color.White,
    ))

    //乘法
    object Mul : CacuKeys("x",CacuButtonStyle(
        bgColor = Color(0xffFEA00A),
        textColor = Color.White,
    ))

    //除法
    object Div : CacuKeys("/",CacuButtonStyle(
        bgColor = Color(0xffFEA00A),
        textColor = Color.White,
    ))

    //求结果
    object Result : CacuKeys("=",CacuButtonStyle(
        bgColor = Color(0xffFEA00A),
        textColor = Color.White,
    ))

    //百分号
    object Percent : CacuKeys("%",
        CacuButtonStyle(
            bgColor = Color(0xffA5A5A5),
            textColor = Color(0xff030303),
        ))

    object Point : CacuKeys(".",
        CacuButtonStyle(
            bgColor = Color(0xff333333),
            textColor = Color.White,
        ))

    //数字
    class Num(val number: Number) : CacuKeys(
        "${number}",
        CacuButtonStyle(
            bgColor = Color(0xff333333),
            textColor = Color.White,
            )
    )

}