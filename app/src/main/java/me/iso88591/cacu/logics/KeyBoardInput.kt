package me.iso88591.cacu.logics

import kotlin.math.roundToInt

//计算逻辑
class KeyBoardInput {

    //左边数字
    private var num1: Double = 0.0

    private var num1HasPoint = false

    //右边数字
    var num2: Double? = null
    private var num2HasPoint = false

    //根据操作id 来进行运算 后面可能会换成枚举来
    private val OP_NULL = CacuKeys.None

    //目前用 -1来标识无效操作（就是啥都没有）
    var opId: CacuKeys = OP_NULL

    private inline fun doWhenOp(
        whenNoOp: () -> Unit,
        whenHasOp: () -> Unit
    ) {
        if (opId == OP_NULL) {
            //没有操作符
            whenNoOp()
        } else {
            whenHasOp()
        }
    }

    fun emptyShow(): String {
        return "0"
    }

    fun onKeyDown(key: CacuKeys): String {
        var show = ""
        when (key) {
            is CacuKeys.Num -> {
                doWhenOp({
                    //没有操作符 新增符号到第一个数字上
                    num1 = num1 * 10 + key.number.toDouble()
                    show = num1.convertNum2DivideString()
                }, {
                    num2 = (num2 ?: 0.0) * 10 + key.number.toDouble()
                    show = (num2 ?: 0.0).convertNum2DivideString()
                })
            }
            CacuKeys.Point -> {
                doWhenOp({
                    num1HasPoint = true
                    //没有操作符 新增符号到第一个数字上
//                    num1 = num1 * 10 + key.number.toDouble()
                    show = num1.convertNum2DivideString() + "."
                }, {
                    num2HasPoint = true
//                    num2 = num2 * 10 + key.number.toDouble()
                    show = (num2 ?: 0.0).convertNum2DivideString() + "."
                })
            }

            //这个case应该把所有的计算操作符放到一起来
            CacuKeys.Plus, CacuKeys.Reduce, CacuKeys.Mul, CacuKeys.Div -> {

                //更新操作符
                if (num2 == null) {
                    opId = key//TODO("赋值操作符")
                    show = num1.convertNum2DivideString()
                } else {
                    //有数字二 那么把上次结果算出来
                    show = cacuAndClear()
                    //然后将此次op覆盖上次op
                    opId = key

                }
            }
            CacuKeys.Result -> {
                //求结果
                doWhenOp({
                    //都没有操作符 直接丢弃了
                    show = num1.convertNum2DivideString()
                }, {
                    //根据操作符来计算
                    show = cacuAndClear()

                })
            }
            CacuKeys.C -> {
                //清除所有操作
                num1 = 0.0
                num2 = null
                opId = OP_NULL
                show = emptyShow()
            }
            else -> {}
        }
        return show
    }


    private fun cacuAndClear(): String {
        var show = ""
        when (opId) {
            CacuKeys.Plus -> {
                num1 += (num2 ?: 0.0)
                num2 = null
                opId = OP_NULL
                opId = OP_NULL
                show = num1.convertNum2DivideString()
            }
            CacuKeys.Reduce -> {
                num1 -= (num2 ?: 0.0)
                num2 = null
                opId = OP_NULL
                show = num1.convertNum2DivideString()
            }
            CacuKeys.Mul -> {
                num1 *= (num2 ?: 0.0)
                num2 = null
                opId = OP_NULL
                show = num1.convertNum2DivideString()
            }
            CacuKeys.Div -> {
                num1 /= (num2 ?: 0.0)
                num2 = null
                opId = OP_NULL
                show = num1.convertNum2DivideString()
            }
            else -> {

            }
        }
        return show
    }


}


//TODO:转化当前数字 变成带 逗号隔开的数字 eg:123,456,789
private fun Double.convertNum2DivideString(divideStr: String = ","): String {
//    val valueStr = this.toString()
    val roundToInt = this.roundToInt()
    val log= if (this > roundToInt){
        "有小数点"
    }else{
        "没有小数点"
    }
//
//    val hasPoint = valueStr.contains(".")
    return toString() + log
}