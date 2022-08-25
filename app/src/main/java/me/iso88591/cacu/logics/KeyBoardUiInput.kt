package me.iso88591.cacu.logics

import me.iso88591.cacu.ui.theme.CacuKeyColorState

class KeyBoardUiInput(
    private val keyBoardInput: KeyBoardInput,
    private val onStateClear: () -> Unit
) {

    private fun CacuKeys.isOP(): Boolean {
        val it = this
        return it is CacuKeys.Mul || it is CacuKeys.Div || it is CacuKeys.Plus || it is CacuKeys.Reduce
    }

    fun onKeyDown(key: CacuKeys): String {

        val lastOp = keyBoardInput.opId

        val onKeyDown = keyBoardInput.onKeyDown(key)

        //clear lastop state
        if (lastOp.isOP()) {
            lastOp.colorState = CacuKeyColorState.Op
        }

        if (keyBoardInput.num2 == null) {
            keyBoardInput.opId.colorState = CacuKeyColorState.OpCheck
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