package me.iso88591.cacu.logics

import androidx.compose.runtime.State
import me.iso88591.cacu.ui.theme.CacuKeyColorState

//需要两个数值参与的
fun CacuKeys.isOP(): Boolean {
    val it = this
    return it is CacuKeys.Mul
            || it is CacuKeys.Div
            || it is CacuKeys.Plus
            || it is CacuKeys.Reduce
            || isOtherOp()
}

fun CacuKeys.isOtherOp():Boolean{
    return this === CacuKeys.xy
            || this === CacuKeys.rooty

}

fun CacuKeys.isSingleOp():Boolean{
    return this === CacuKeys.x2
            || this === CacuKeys.ex
            || this === CacuKeys.`10x`
            || this === CacuKeys.`1Perx`
            || this === CacuKeys.root2
            || this === CacuKeys.root3
            || this === CacuKeys.ln
            || this === CacuKeys.log10
            || this === CacuKeys.factorial
            || this === CacuKeys.e
            || this === CacuKeys.sin
            || this === CacuKeys.cos
            || this === CacuKeys.tan
            || this === CacuKeys.pi
            || this === CacuKeys.sinh
            || this === CacuKeys.cosh
            || this === CacuKeys.tanh
            || this === CacuKeys.rand
}

class KeyBoardUiInput(
    var darkLightState:Boolean,
    private val keyBoardInput: KeyBoardInput,
    private val onStateClear: () -> Unit
) {


    fun onKeyDown(key: CacuKeys): String {

        val lastOp = keyBoardInput.opId

        val onKeyDown = keyBoardInput.onKeyDown(key)

        //clear lastop state
        if (lastOp.isOP()) {
            //如果是除了 +-*/以外的op 就用otherOp
            if (lastOp.isOtherOp()){
                lastOp.colorState = CacuKeyColorState.Num.get(darkLightState)
            }else{
                lastOp.colorState = CacuKeyColorState.Op
            }
        }

        if (keyBoardInput.num2 == null) {
            //如果是除了 +-*/以外的op 就用otherOp
            if (keyBoardInput.opId.isOtherOp()){
                keyBoardInput.opId.colorState = CacuKeyColorState.Num.get(darkLightState,true)
            }else{
                keyBoardInput.opId.colorState = CacuKeyColorState.OpCheck
            }
        }

        //这里处理一下那些key的显示ui
        val it = key
        if (it == CacuKeys.C) {
            //把所有的操作 op 状态变成初始状态
            //这里采用的方案是 直接重新调用一下主题
            onStateClear()
        }


        return onKeyDown
    }

}