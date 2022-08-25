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
        CacuKeys.LeftBrackets.applyCacuPalete(false, myPalete),
        CacuKeys.RightBrackets.applyCacuPalete(false, myPalete),
        CacuKeys.Mc.applyCacuPalete(false, myPalete),
        CacuKeys.mPlus.applyCacuPalete(false, myPalete),
        CacuKeys.mReduce.applyCacuPalete(false, myPalete),
        CacuKeys.mr.applyCacuPalete(false, myPalete),
        CacuKeys.C.applyCacuPalete(false, myPalete),
        CacuKeys.PlusOrReduce.applyCacuPalete(false, myPalete),
        CacuKeys.Percent.applyCacuPalete(false, myPalete),
        CacuKeys.Div,


        CacuKeys.nd2.applyCacuPalete(false, myPalete),
        CacuKeys.x2.applyCacuPalete(false, myPalete),
        CacuKeys.x3.applyCacuPalete(false, myPalete),
        CacuKeys.xy.applyCacuPalete(false, myPalete),
        CacuKeys.yx.applyCacuPalete(false, myPalete),
        CacuKeys.`2x`.applyCacuPalete(false, myPalete),
        CacuKeys.Num(7).applyCacuPalete(false, myPalete),
        CacuKeys.Num(8).applyCacuPalete(false, myPalete),
        CacuKeys.Num(9).applyCacuPalete(false, myPalete),
        CacuKeys.Mul,


        CacuKeys.Num(1).applyCacuPalete(false, myPalete),
        CacuKeys.Num(1).applyCacuPalete(false, myPalete),
        CacuKeys.Num(1).applyCacuPalete(false, myPalete),
        CacuKeys.Num(1).applyCacuPalete(false, myPalete),
        CacuKeys.Num(1).applyCacuPalete(false, myPalete),
        CacuKeys.Num(1).applyCacuPalete(false, myPalete),
        CacuKeys.Num(1).applyCacuPalete(false, myPalete),
        CacuKeys.Num(1).applyCacuPalete(false, myPalete),
        CacuKeys.Num(1).applyCacuPalete(false, myPalete),
        CacuKeys.Reduce,

        CacuKeys.Num(1).applyCacuPalete(false, myPalete),
        CacuKeys.Num(1).applyCacuPalete(false, myPalete),
        CacuKeys.Num(1).applyCacuPalete(false, myPalete),
        CacuKeys.Num(1).applyCacuPalete(false, myPalete),
        CacuKeys.Num(1).applyCacuPalete(false, myPalete),
        CacuKeys.Num(1).applyCacuPalete(false, myPalete),
        CacuKeys.Num(1).applyCacuPalete(false, myPalete),
        CacuKeys.Num(1).applyCacuPalete(false, myPalete),
        CacuKeys.Num(1).applyCacuPalete(false, myPalete),
        CacuKeys.Plus,

        CacuKeys.Num(1).applyCacuPalete(false, myPalete),
        CacuKeys.Num(1).applyCacuPalete(false, myPalete),
        CacuKeys.Num(1).applyCacuPalete(false, myPalete),
        CacuKeys.Num(1).applyCacuPalete(false, myPalete),
        CacuKeys.Num(1).applyCacuPalete(false, myPalete),
        CacuKeys.Num(1).applyCacuPalete(false, myPalete),
        CacuKeys.Num(1).applyCacuPalete(false, myPalete),
        CacuKeys.Num(1).applyCacuPalete(false, myPalete),
        CacuKeys.Num(1).applyCacuPalete(false, myPalete),
        CacuKeys.Result,
//        CacuKeys.C.applyCacuPalete(true, myPalete),
//        CacuKeys.PlusOrReduce.applyCacuPalete(true, myPalete),
//        CacuKeys.Percent.applyCacuPalete(true, myPalete),
//        CacuKeys.Div,
//
//        CacuKeys.Num(7).applyCacuPalete(false, myPalete),
//        CacuKeys.Num(8).applyCacuPalete(false, myPalete),
//        CacuKeys.Num(9).applyCacuPalete(false, myPalete),
//        CacuKeys.Mul,
//
//        CacuKeys.Num(4).applyCacuPalete(false, myPalete),
//        CacuKeys.Num(5).applyCacuPalete(false, myPalete),
//        CacuKeys.Num(6).applyCacuPalete(false, myPalete),
//        CacuKeys.Reduce,
//
//        CacuKeys.Num(1).applyCacuPalete(false, myPalete),
//        CacuKeys.Num(2).applyCacuPalete(false, myPalete),
//        CacuKeys.Num(3).applyCacuPalete(false, myPalete),
//        CacuKeys.Plus,
//
//        CacuKeys.Num(0, 2).applyCacuPalete(false, myPalete),
//        CacuKeys.Point.applyCacuPalete(false, myPalete), CacuKeys.Result
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

    MyGrid(10,
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