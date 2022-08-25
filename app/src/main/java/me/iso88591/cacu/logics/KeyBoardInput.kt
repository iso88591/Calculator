package me.iso88591.cacu.logics

import java.text.DecimalFormat
import kotlin.math.pow
import kotlin.math.roundToInt

//计算逻辑
class KeyBoardInput {

    private var num1Str: String = ""
    private var num2Str: String = ""

    //左边数字
    private var num1: Double = 0.0

    //右边数字
    var num2: Double? = null

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
                    num1Str += (key.number.toInt())
                    num1 = num1Str.toDoubleOrNull() ?: 0.0
//                    num1 = if (num1HasPoint) {
//                        //小数位数分之一   第0位 则乘 1/10 第1位 则乘 1/100
//                        num1 + (key.number.toDouble() / (10.0.pow(num1PointAfterCount + 1)))
//                    } else {
//                        num1 * 10 + key.number.toDouble()
//                    }
                    show = num1Str
                }, {
                    num2Str += (key.number.toInt())
                    num2 = num2Str.toDoubleOrNull()
//                    num2 = if (num2HasPoint) {
//                        (num2 ?: 0.0) + key.number.toDouble() / (10.0.pow(num2PointAfterCount + 1))
//                    } else {
//                        (num2 ?: 0.0) * 10 + key.number.toDouble()
//                    }
                    show = num2Str
                })
            }
            CacuKeys.Point -> {
                doWhenOp({
                    //没有操作符 新增符号到第一个数字上
//                    num1 = num1 * 10 + key.number.toDouble()
                    num1Str += "."
                    show = num1Str
                }, {
                    num2Str += "."
//                    num2 = num2 * 10 + key.number.toDouble()
                    show = num2Str
                })
            }

            //这个case应该把所有的计算操作符放到一起来
            CacuKeys.Plus, CacuKeys.Reduce, CacuKeys.Mul, CacuKeys.Div -> {

                //更新操作符
                if (num2 == null) {
                    opId = key
                    show = num1Str
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
                    show = num1.trimEnd0()
                }, {
                    //根据操作符来计算
                    show = cacuAndClear()

                })
            }
            CacuKeys.C -> {
                //清除所有操作
                num1 = 0.0
                num1Str = ""
                num2 = null
                num2Str = ""
                opId = OP_NULL
                show = emptyShow()
            }
            CacuKeys.Percent -> {
                doWhenOp({
                    num1 /= 100
                    num1Str = num1.trimEnd0()
                    show = num1Str
                }, {
                    num2 = (num2 ?: 0.0) / 100
                    num2Str = (num2 ?: 0.0).trimEnd0()
                    show = num2Str
                })
            }
            CacuKeys.PlusOrReduce->{
                //正负号
                doWhenOp({
                    num1 *= -1
                    num1Str = num1.trimEnd0()
                    show = num1Str
                }, {
                    num2 = (num2 ?: 0.0) * -1
                    num2Str = (num2 ?: 0.0).trimEnd0()
                    show = num2Str
                })
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
                num1Str = num1.trimEnd0()
                num2 = null
                num2Str = ""
                opId = OP_NULL
                opId = OP_NULL
                show = num1Str
            }
            CacuKeys.Reduce -> {
                num1 -= (num2 ?: 0.0)
                num1Str = num1.trimEnd0()
                num2 = null
                num2Str = ""
                opId = OP_NULL
                show = num1Str
            }
            CacuKeys.Mul -> {
                num1 *= (num2 ?: 0.0)
                num1Str = num1.trimEnd0()
                num2 = null
                num2Str = ""
                opId = OP_NULL
                show = num1Str
            }
            CacuKeys.Div -> {
                num1 /= (num2 ?: 0.0)
                num1Str = num1.trimEnd0()
                num2 = null
                num2Str = ""
                opId = OP_NULL
                show = num1Str
            }
            else -> {

            }
        }
        return show
    }

    private val decimalFormat = DecimalFormat("###################.###########")
    private fun Double.trimEnd0() = decimalFormat.format(this)

}