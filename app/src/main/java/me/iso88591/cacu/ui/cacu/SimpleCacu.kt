package me.iso88591.cacu.ui.cacu

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import me.iso88591.cacu.logics.CacuKeys
import kotlin.math.min


@Preview
@Composable
private fun PreViewSimpleCacu1() {
    SimpleCacu()
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SimpleCacu() {

    // val screenHeight = configuration.screenHeightDp.dp
    //  val screenWidth = configuration.screenWidthDp.dp
    val configuration = LocalConfiguration.current
    val orientation = if (configuration.screenWidthDp > configuration.screenHeightDp) {
        //横屏
        Orientation.Horizontal
    } else {
        //竖屏
        Orientation.Vertical
    }
    val spanCount = if (orientation == Orientation.Horizontal) {
        //横屏
        5
    } else {
        //竖屏
        4
    }

    val keys = remember{
        //竖屏
       if (orientation == Orientation.Vertical){
           mutableStateListOf(
               CacuKeys.C, CacuKeys.Brackets, CacuKeys.Delete, CacuKeys.Div,
               CacuKeys.Num(7), CacuKeys.Num(8), CacuKeys.Num(9), CacuKeys.Mul,
               CacuKeys.Num(4), CacuKeys.Num(5), CacuKeys.Num(6), CacuKeys.Reduce,
               CacuKeys.Num(1), CacuKeys.Num(2), CacuKeys.Num(3), CacuKeys.Plus,
               CacuKeys.Percent, CacuKeys.Num(0), CacuKeys.Point, CacuKeys.Result
           )
       }else{
           mutableStateListOf(
               CacuKeys.C, CacuKeys.Brackets, CacuKeys.Delete,CacuKeys.Mul, CacuKeys.Div,
               CacuKeys.Num(7), CacuKeys.Num(8), CacuKeys.Num(9),CacuKeys.Plus,CacuKeys.Reduce,
               CacuKeys.Num(4), CacuKeys.Num(5), CacuKeys.Num(6),CacuKeys.Percent, CacuKeys.Num(0),
               CacuKeys.Num(1), CacuKeys.Num(2), CacuKeys.Num(3),CacuKeys.Point, CacuKeys.Result,
           )
       }
    }

    //屏幕宽度 - 每个块的左右 - 最外层的左右
    val minSide = min(configuration.screenWidthDp, configuration.screenHeightDp)
    val itemDivider = 5
    val width = (minSide - itemDivider * 2 * spanCount - 10 * 2) / spanCount

    Column(horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.background(Color(0xff010101))) {
        val lines = keys.size / spanCount
        repeat(lines) { column ->
            Row {
                repeat(spanCount) { row ->
                    val key = keys[column * spanCount + row]
                    OneCacuButton(
                        key = key,
                        Modifier
                            .padding(itemDivider.dp)
                            .width(width.dp)
                            .height(width.dp)
                    )
                }
            }
        }
    }
//
//    LazyVerticalGrid(
//        cells = GridCells.Fixed(spanCount),
//        contentPadding = PaddingValues(horizontal = 10.dp),
//        content = {
//            items(keys.size) {
//                val key = keys[it]
//                OneCacuButton(
//                    key = key,
//                    Modifier
//                        .padding(3.dp)
//                        .width(width.dp)
//                        .height(width.dp)
//                )
//            }
//        },
//    )

}

data class CacuButtonStyle(
    val bgColor: Color = Color.Black,
    val textSize: TextUnit = 30.sp,
    val textColor: Color = Color.White
) {

}

@Composable
private fun OneCacuButton(
    key: CacuKeys,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .background(
                key.style.bgColor, RoundedCornerShape(1000.dp)
            ),
        contentAlignment = Alignment.Center
    ) {

        Text(
            text = key.key,
            fontSize = key.style.textSize,
            color = key.style.textColor,
        )

    }
}