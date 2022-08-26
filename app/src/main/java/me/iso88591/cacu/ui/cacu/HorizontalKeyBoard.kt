package me.iso88591.cacu.ui.cacu

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import me.iso88591.cacu.logics.CacuKeys
import me.iso88591.cacu.logics.applyCacuPalete
import me.iso88591.cacu.ui.founction.MyGrid
import me.iso88591.cacu.ui.founction.buildGridItems
import me.iso88591.cacu.ui.theme.Local_MyPalete
import me.iso88591.cacu.ui.theme.MyPalete

//10 x 5
private fun SimpleHorizontalKeys(
    myPalete: MyPalete,
    onKeyDown: (CacuKeys) -> Unit = {}
) = buildGridItems {
    listOf<CacuKeys>(
        //=========================1
        CacuKeys.x2.applyCacuPalete(false, myPalete),
        CacuKeys.xy.applyCacuPalete(false, myPalete),
        CacuKeys.ex.applyCacuPalete(false, myPalete),
        CacuKeys.`10x`.applyCacuPalete(false, myPalete),

        CacuKeys.C.applyCacuPalete(true, myPalete),
        CacuKeys.PlusOrReduce.applyCacuPalete(true, myPalete),
        CacuKeys.Percent.applyCacuPalete(true, myPalete),
        CacuKeys.Div,

        //=========================2
        CacuKeys.`1Perx`.applyCacuPalete(false, myPalete),
        CacuKeys.root2.applyCacuPalete(false, myPalete),
        CacuKeys.root3.applyCacuPalete(false, myPalete),
        CacuKeys.rooty.applyCacuPalete(false, myPalete),

        CacuKeys.Num(7).applyCacuPalete(false, myPalete),
        CacuKeys.Num(8).applyCacuPalete(false, myPalete),
        CacuKeys.Num(9).applyCacuPalete(false, myPalete),
        CacuKeys.Mul,

        //=========================3
        CacuKeys.ln.applyCacuPalete(false, myPalete),
        CacuKeys.log10.applyCacuPalete(false, myPalete),
        CacuKeys.factorial.applyCacuPalete(false, myPalete),
        CacuKeys.e.applyCacuPalete(false, myPalete),

        CacuKeys.Num(4).applyCacuPalete(false, myPalete),
        CacuKeys.Num(5).applyCacuPalete(false, myPalete),
        CacuKeys.Num(6).applyCacuPalete(false, myPalete),
        CacuKeys.Reduce,

        //=============================4
        CacuKeys.sin.applyCacuPalete(false, myPalete),
        CacuKeys.cos.applyCacuPalete(false, myPalete),
        CacuKeys.tan.applyCacuPalete(false, myPalete),
        CacuKeys.pi.applyCacuPalete(false, myPalete),

        CacuKeys.Num(1).applyCacuPalete(false, myPalete),
        CacuKeys.Num(2).applyCacuPalete(false, myPalete),
        CacuKeys.Num(3).applyCacuPalete(false, myPalete),
        CacuKeys.Plus,

        //=======================================5
        CacuKeys.sinh.applyCacuPalete(false, myPalete),
        CacuKeys.cosh.applyCacuPalete(false, myPalete),
        CacuKeys.tanh.applyCacuPalete(false, myPalete),
        CacuKeys.rand.applyCacuPalete(false, myPalete),

        CacuKeys.Num(0).also { it.spanCount = 2 }.applyCacuPalete(false, myPalete),
        CacuKeys.Point.applyCacuPalete(false, myPalete),
        CacuKeys.Result,
    ).forEach {
        item(it.spanCount) {
            it.Show(modifier = Modifier.clickable {
                onKeyDown(it)
            })
        }
    }

}

@Composable
fun SimpleHorizontalCacu(
    onKeyDown: (CacuKeys) -> Unit = {}
) {

    val myPalete = Local_MyPalete.current

    MyGrid(8,
        8,
        8,
        Modifier
            .wrapContentHeight()
            .padding(horizontal = 10.dp)
            .padding(bottom = 20.dp),
        remember(myPalete) { SimpleHorizontalKeys(myPalete, onKeyDown) },
        0f,
        5
    )

}