package me.iso88591.cacu.logics

import me.iso88591.cacu.ui.cacu.CacuButtonStyle

sealed class CacuKeys(
    val key: String,
    val style: CacuButtonStyle = CacuButtonStyle()
) {

    //归零键
    object C : CacuKeys("C")

    //括号
    object Brackets : CacuKeys("( )")

    //delete
    object Delete : CacuKeys("Del")

    //+
    object Plus : CacuKeys("+")

    //-
    object Reduce : CacuKeys("-")

    //乘法
    object Mul : CacuKeys("x")

    //除法
    object Div : CacuKeys("/")

    //求结果
    object Result : CacuKeys("=")

    //百分号
    object Percent : CacuKeys("%")

    object Point : CacuKeys(".")

    //数字
    class Num(val number: Number) : CacuKeys("${number}")

}