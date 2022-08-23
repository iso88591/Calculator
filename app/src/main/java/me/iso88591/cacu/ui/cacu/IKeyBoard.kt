package me.iso88591.cacu.ui.cacu

import androidx.compose.runtime.Composable
import me.iso88591.cacu.logics.CacuKeys

interface IKeyBoard {

    @Composable
    fun Display()

    //按键点击
    var onKeyDown: (CacuKeys) -> Unit

    //按键
    val keys: List<CacuKeys>

}