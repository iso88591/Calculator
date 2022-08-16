package me.iso88591.cacu.logics

import androidx.compose.ui.graphics.Color
import me.iso88591.cacu.ui.cacu.CacuButtonStyle

sealed class CacuKeys(
    val key: String,
    val style: CacuButtonStyle = CacuButtonStyle(),
    val keyCode: Int = -1,
) {


    //text
    class Text(str: String) : CacuKeys(str)


    //归零键
    object C : CacuKeys(
        "C",
        CacuButtonStyle(
            bgColor = Color(0xffA5A5A5),
            textColor = Color(0xff030303),
        ),
        0
    )

    //括号
    object Brackets : CacuKeys(
        "( )",
        CacuButtonStyle(
            bgColor = Color(0xffA5A5A5),
            textColor = Color(0xff030303),
        ),
        1
    )

    //delete
    object Delete : CacuKeys(
        "Del",
        CacuButtonStyle(
            bgColor = Color(0xffA5A5A5),
            textColor = Color(0xff030303),
        ),
        2
    )

    //+
    object Plus : CacuKeys(
        "+", CacuButtonStyle(
            bgColor = Color(0xffFEA00A),
            textColor = Color.White,
        ),
        3
    )

    //-
    object Reduce : CacuKeys(
        "-", CacuButtonStyle(
            bgColor = Color(0xffFEA00A),
            textColor = Color.White,
        ),
        4
    )

    //乘法
    object Mul : CacuKeys(
        "x", CacuButtonStyle(
            bgColor = Color(0xffFEA00A),
            textColor = Color.White,
        ),5
    )

    //除法
    object Div : CacuKeys(
        "/", CacuButtonStyle(
            bgColor = Color(0xffFEA00A),
            textColor = Color.White,
        ),
        6
    )

    //求结果
    object Result : CacuKeys(
        "=", CacuButtonStyle(
            bgColor = Color(0xffFEA00A),
            textColor = Color.White,
        ),
        7
    )

    //百分号
    object Percent : CacuKeys(
        "%",
        CacuButtonStyle(
            bgColor = Color(0xffA5A5A5),
            textColor = Color(0xff030303),
        ),
        8
    )

    object Point : CacuKeys(
        ".",
        CacuButtonStyle(
            bgColor = Color(0xff333333),
            textColor = Color.White,
        ),
        9
    )

    //数字
    class Num(val number: Number) : CacuKeys(
        "${number}",
        CacuButtonStyle(
            bgColor = Color(0xff333333),
            textColor = Color.White,
        )
    )

}