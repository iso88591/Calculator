package me.iso88591.cacu.logics

import java.util.*

class CacuKeyInputDecoder(
    private val iScreen: IScreen
) {


    //操作
    class Op(
        val type: Int,
        var value: String
    ) {
        companion object {
            const val TYPE_OP_NUM = 0
            const val TYPE_OP_OP = 1
        }
    }

    private val cacuStack = Stack<Op>()

    private var lastOp: Op? = null

    fun onInput(key: CacuKeys) {
        when (key) {
            is CacuKeys.Num,CacuKeys.Point -> {
                if (lastOp == null) {
                    lastOp = Op(Op.TYPE_OP_NUM, key.key)
                } else if (lastOp!!.type == Op.TYPE_OP_NUM) {
                    lastOp!!.value += key.key
                } else {
                    //之前不是数字 应该抛出异常

                }

                //检测 多个点号

                //显示此行内容
                iScreen.onShow(lastOp?.value ?: "")

            }
            //加减乘除
            is CacuKeys.Plus, CacuKeys.Reduce, CacuKeys.Mul, CacuKeys.Div -> {
                //之前应该有数字 否则无法操作
                if (lastOp?.type == Op.TYPE_OP_NUM){
                    iScreen.onNextLine()
                    cacuStack.push(lastOp)
                    iScreen.onShow(key.key)
                    iScreen.onNextLine()
                    lastOp = Op(Op.TYPE_OP_OP,"${key.keyCode}")
                    cacuStack.push(lastOp)

                }else{
                    //如果之前不是数字忽略此次操作
                    /* no-op */
                }
            }
            //求结果
            is CacuKeys.Result -> {
                iScreen.onNextLine()
                iScreen.onResult(makeResult, null)
            }
            //括号
            is CacuKeys.Brackets -> {

            }
            is CacuKeys.Delete -> {

            }
            else -> {

            }
        }
    }


}