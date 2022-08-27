package me.iso88591.cacu.logics

import java.text.DecimalFormat
import kotlin.math.*
import kotlin.random.Random

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
        if (num1 != 0.0) {
            return num1.trimEnd0()
        }
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
            CacuKeys.PlusOrReduce -> {
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

        //这个case应该把所有的计算操作符放到一起来
        if (key.isOP()) {
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
        if (key.isSingleOp()) {
            //可以通过一个数字和一个符号得出的 eg:     x的平方  当x值确定了 值就能确定
            show = cacuSimpleOp(key)
        }
        return show
    }


    private fun clear() {
        num1Str = num1.trimEnd0()
        num2 = null
        num2Str = ""
        opId = OP_NULL
        opId = OP_NULL
    }

    private fun cacuAndClear(): String {
        var show = ""
        when (opId) {
            CacuKeys.Plus -> {
                num1 += (num2 ?: 0.0)
                clear()
                show = num1Str
            }
            CacuKeys.Reduce -> {
                num1 -= (num2 ?: 0.0)
                clear()
                show = num1Str
            }
            CacuKeys.Mul -> {
                num1 *= (num2 ?: 0.0)
                clear()
                show = num1Str
            }
            CacuKeys.Div -> {
                num1 /= (num2 ?: 0.0)
                clear()
                show = num1Str
            }

            /**
             *
             * 需要两个数字参与的 符号
             *
             */

            CacuKeys.xy -> {
                //x的y次方
                num1 = num1.pow(num2 ?: 0.0)
                clear()
                show = num1Str
            }
            CacuKeys.rooty -> {
                num1 = num1.pow(1 / (num2 ?: 0.0))
                clear()
                show = num1Str
            }
            else -> {

            }
        }
        return show
    }


    private fun cacuSimpleOp(key: CacuKeys): String {
        var show = ""
        when (key) {
            /**
             *
             * 只需要一个数字参与的符号
             *
             */
            CacuKeys.x2 -> {
                doWhenOp({
                    //将结果附加到第一个数字上
                    num1 = num1.pow(2.0)
                    clear()
                    show = num1Str
                }, {
                    //将结果附加到第1个数字上
                    num1 = (num2 ?: 0.0).pow(2.0)
                    clear()
                    show = num1Str
                })
            }
            CacuKeys.ex -> {

                doWhenOp({
                    //将结果附加到第一个数字上
                    num1 = Math.E.pow(num1)
                    clear()
                    show = num1Str
                }, {
                    //将结果附加到第1个数字上
                    num1 = Math.E.pow((num2 ?: 0.0))
                    clear()
                    show = num1Str
                })

            }
            CacuKeys.`10x` -> {
                doWhenOp({
                    //将结果附加到第一个数字上
                    num1 = 10.0.pow(num1)
                    clear()
                    show = num1Str
                }, {
                    //将结果附加到第1个数字上
                    num1 = 10.0.pow((num2 ?: 0.0))
                    clear()
                    show = num1Str
                })

            }
            CacuKeys.`1Perx` -> {
                doWhenOp({
                    //将结果附加到第一个数字上
                    num1 = 1f / num1
                    clear()
                    show = num1Str
                }, {
                    //将结果附加到第1个数字上
                    num1 = 1f / (num2 ?: 0.0)
                    clear()
                    show = num1Str
                })
            }
            //  || this === CacuKeys.root2
            //            || this === CacuKeys.root3
            CacuKeys.root2 -> {
                doWhenOp({
                    num1 = sqrt(num1)
                    clear()
                    show = num1Str
                }, {
                    num2 = sqrt(num2 ?: 0.0)
                    clear()
                    show = num1Str
                })
            }
            CacuKeys.root3 -> {
                doWhenOp({
                    num1 = num1.pow(1 / 3.0)
                    clear()
                    show = num1Str
                }, {
                    num1 = (num2 ?: 0.0).pow(1 / 3.0)
                    clear()
                    show = num1Str
                })
            }
            CacuKeys.ln -> {
                doWhenOp({
                    num1 = ln(num1)
                    clear()
                    show = num1Str
                }, {
                    num1 = ln(num2 ?: 0.0)
                    clear()
                    show = num1Str
                })
            }
            CacuKeys.log10 -> {
                doWhenOp({
                    num1 = log10(num1)
                    clear()
                    show = num1Str
                }, {
                    num1 = log10(num2 ?: 0.0)
                    clear()
                    show = num1Str
                })
            }
            CacuKeys.factorial -> {
                doWhenOp({
                    if (num1.toInt().toDouble() == num1) {
                        num1 = num1.toInt().cacuFactorial().toDouble()
                        clear()
                    }
                    show = num1Str
                }, {
                    if (num2?.toInt()?.toDouble() == num2) {
                        num1 = (num2 ?: 0.0).toInt().cacuFactorial().toDouble()
                        clear()
                    }
                    show = num1Str
                })
            }
            CacuKeys.e -> {
                doWhenOp({
                    num1 = Math.E
                    num1Str = num1.trimEnd0()
                    show = num1Str
                }, {
                    num2 = Math.E
                    num2Str = (num2 ?: 0.0).trimEnd0()
                    show = num2Str
                })
            }
            CacuKeys.sin -> {
                doWhenOp({
                    num1 = sin2(num1)
                    clear()
                    show = num1Str
                },{
                    num1 = sin2(num2?:0.0)
                    clear()
                    show = num1Str
                })
            }
            CacuKeys.cos -> {
                try {
                    doWhenOp({
                        num1 = cos2(num1)
                        clear()
                        show = num1Str
                    },{
                        num1 = cos2(num2?:0.0)
                        clear()
                        show = num1Str
                    })
                } catch (e: Exception) {
                    clear()
                    show = "Error"
                }
            }
            CacuKeys.tan -> {
                doWhenOp({
                    num1 = tan2(num1)
                    clear()
                    show = num1Str
                },{
                    num1 = tan2(num2?:0.0)
                    clear()
                    show = num1Str
                })
            }
            CacuKeys.pi ->{
                doWhenOp({
                    num1 = Math.PI
                    num1Str = num1.trimEnd0()
                    show = num1Str
                }, {
                    num2 = Math.PI
                    num2Str = (num2 ?: 0.0).trimEnd0()
                    show = num2Str
                })
            }


            CacuKeys.sinh -> {
                doWhenOp({
                    num1 = sinh2(num1)
                    clear()
                    show = num1Str
                },{
                    num1 = sinh2(num2?:0.0)
                    clear()
                    show = num1Str
                })
            }
            CacuKeys.cosh -> {
                try {
                    doWhenOp({
                        num1 = cosh2(num1)
                        clear()
                        show = num1Str
                    },{
                        num1 = cosh2(num2?:0.0)
                        clear()
                        show = num1Str
                    })
                } catch (e: Exception) {
                    clear()
                    show = "Error"
                }
            }
            CacuKeys.tanh -> {
                doWhenOp({
                    num1 = tanh2(num1)
                    clear()
                    show = num1Str
                },{
                    num1 = tanh2(num2?:0.0)
                    clear()
                    show = num1Str
                })
            }

            CacuKeys.rand ->{
                doWhenOp({
                    num1 = Random.nextDouble()
                    num1Str = num1.trimEnd0()
                    show = num1Str
                }, {
                    num2 = Random.nextDouble()
                    num2Str = (num2 ?: 0.0).trimEnd0()
                    show = num2Str
                })
            }
            else -> error("")
        }
        return show
    }

    private val decimalFormat = DecimalFormat("###################.###########")
    private fun Double.trimEnd0() = decimalFormat.format(this)


    private fun sin2(d:Double) = sin(Math.toRadians(d))
    private fun cos2(d:Double) = cos(Math.toRadians(d))
    private fun tan2(d:Double) = tan(Math.toRadians(d))

    private fun sinh2(d:Double) = sinh(Math.toRadians(d))
    private fun cosh2(d:Double) = cosh(Math.toRadians(d))
    private fun tanh2(d:Double) = tanh(Math.toRadians(d))

}

//计算阶乘
private fun Int.cacuFactorial(): Int {
    if (this > 1) {
        return this * (this - 1).cacuFactorial()
    } else {
        return 1
    }
}