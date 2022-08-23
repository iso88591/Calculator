package me.iso88591.cacu.ui.cacu

//import androidx.compose.foundation.*
//import androidx.compose.foundation.gestures.Orientation
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material.Text
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.platform.LocalConfiguration
//import androidx.compose.ui.text.font.FontStyle
//import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import me.iso88591.cacu.logics.CacuKeys
//import kotlin.math.min
//
//
//@Preview
//@Composable
//private fun PreViewSimpleCacu1() {
//    SimpleCacu()
//}
//
//
///**
// * create by iso88591 at 2022/8/16
// * 简易计算器的屏幕
// */
//@Composable
//private fun SimpleCacuScreen(text: String, modifier: Modifier = Modifier) {
//    LazyColumn(
//        modifier = modifier
////            .background(Color.Blue)
//        ,
//        reverseLayout = true
//    ) {
//        item {
//            Text(
//                modifier = Modifier.fillMaxWidth(),
//                text = text,
//                textAlign = TextAlign.End,
//                color = Color.White,
//                fontSize = 75.sp,
//                fontStyle = FontStyle.Normal,
//            )
//        }
//
//    }
//}
//
//
////横屏的计算器
//@Composable
//private fun SimpleHorizontalCacu(
//    width: Int,
//    spanCount: Int,
//    keys: List<CacuKeys>,
//    itemDivider: Int,
//    showText: String,
//    onKeyDown: (CacuKeys) -> Unit
//) {
//    Row(
//        horizontalArrangement = Arrangement.End,
//        verticalAlignment = Alignment.CenterVertically,
//        modifier = Modifier
//            .background(Color(0xff010101))
//            .padding(bottom = 20.dp)
//    ) {
//        //屏幕
//        SimpleCacuScreen(
//            text = showText,
//            modifier = Modifier
//                .fillMaxHeight(1f)
//                .weight(1f, true)
//        )
//
//        val lines = keys.size / spanCount
//        repeat(spanCount) { column ->
//            Column {
//                repeat(lines) { row ->
//                    val key = keys.getOrNull(column * lines + row)//keys[column * spanCount + row]
//                    if (key != null) {
//                        OneCacuButton(
//                            key = key,
//                            Modifier
//                                .padding(itemDivider.dp)
//                                .clickable {
//                                    onKeyDown(key)
//                                }
//                                .width(width.dp)
//                                .height(width.dp)
//                        )
//                    }
//                }
//            }
//        }
//    }
//}
//
//
////竖屏的计算器
//@Composable
//private fun SimpleVerticalCacu(
//    width: Int,
//    spanCount: Int,
//    keys: List<CacuKeys>,
//    itemDivider: Int,
//    showText: String,
//    onKeyDown: (CacuKeys) -> Unit
//) {
//    Column(
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Bottom,
//        modifier = Modifier
//            .background(Color(0xff010101))
//            .padding(bottom = 20.dp)
//    ) {
//        //屏幕
//        SimpleCacuScreen(
//            text = showText,
//            modifier = Modifier
//                .fillMaxWidth(1f)
//                .weight(1f, true)
//        )
//
//        val lines = keys.size / spanCount
//        repeat(lines) { column ->
//            Row {
//
//
//
//                repeat(spanCount) { row ->
//                    val key = keys[column * spanCount + row]
//                    OneCacuButton(
//                        key = key,
//                        Modifier
//                            .padding(itemDivider.dp)
//                            .clickable {
//                                onKeyDown(key)
//                            }
//                            .width(width.dp * key.spanCount)
//                            .height(width.dp)
//                    )
//                }
//            }
//        }
//    }
//}
//
//
//@Composable
//fun SimpleCacu(
//    showText: String = "===============",
//    onKeyDown: (CacuKeys) -> Unit = {}
//) {
//
//    // val screenHeight = configuration.screenHeightDp.dp
//    //  val screenWidth = configuration.screenWidthDp.dp
//    val configuration = LocalConfiguration.current
//    val orientation = if (configuration.screenWidthDp > configuration.screenHeightDp) {
//        //横屏
//        Orientation.Horizontal
//    } else {
//        //竖屏
//        Orientation.Vertical
//    }
//    val spanCount = if (orientation == Orientation.Horizontal) {
//        //横屏
//        5
//    } else {
//        //竖屏
//        4
//    }
//
//    val keys = remember {
//        //竖屏
//        if (orientation == Orientation.Vertical) {
//            mutableStateListOf(
//                CacuKeys.C, CacuKeys.Brackets, CacuKeys.Delete, CacuKeys.Div,
//                CacuKeys.Num(7), CacuKeys.Num(8), CacuKeys.Num(9), CacuKeys.Mul,
//                CacuKeys.Num(4), CacuKeys.Num(5), CacuKeys.Num(6), CacuKeys.Reduce,
//                CacuKeys.Num(1), CacuKeys.Num(2), CacuKeys.Num(3), CacuKeys.Plus,
//                CacuKeys.Percent, CacuKeys.Num(0), CacuKeys.Point, CacuKeys.Result
//            )
//        } else {
//            mutableStateListOf(
//                CacuKeys.Num(7),
//                CacuKeys.Num(8),
//                CacuKeys.Num(9),
//                CacuKeys.C,
//
//                CacuKeys.Delete,
//                CacuKeys.Num(4),
//                CacuKeys.Num(5),
//                CacuKeys.Num(6),
//
//                CacuKeys.Num(0),
//                CacuKeys.Percent,
//                CacuKeys.Num(1),
//                CacuKeys.Num(2),
//
//                CacuKeys.Num(3),
//                CacuKeys.Point,
//                CacuKeys.Brackets,
//                CacuKeys.Plus,
//
//                CacuKeys.Reduce,
//                CacuKeys.Mul,
//                CacuKeys.Div,
//                CacuKeys.Result,
//            )
//        }
//    }
//
//    //屏幕宽度 - 每个块的左右 - 最外层的左右
//    val minSide = min(configuration.screenWidthDp, configuration.screenHeightDp)
//    val itemDivider = 5
//    val sides = if (orientation == Orientation.Horizontal) {
//        keys.size / spanCount
//    } else {
//        spanCount
//    }
//    val width = (minSide - itemDivider * 2 * sides - 10 * 2) / sides
//
////    if (orientation == Orientation.Vertical) {
//        SimpleVerticalCacu(
//            width = width,
//            spanCount = spanCount,
//            keys = keys,
//            itemDivider = itemDivider,
//            showText = showText,
//            onKeyDown = onKeyDown
//        )
//
////    } else {
////        SimpleHorizontalCacu(
////            width = width,
////            spanCount = spanCount,
////            keys = keys,
////            itemDivider = itemDivider,
////            showText = showText,
////            onKeyDown = onKeyDown
////        )
////
////    }
//
//}
//
////data class CacuButtonStyle(
////    val bgColor: Color = Color.Black,
////    val textSize: TextUnit = 30.sp,
////    val textColor: Color = Color.White
////) {
////
////}
//
//@Composable
//fun OneCacuButton(
//    key: CacuKeys,
//    modifier: Modifier = Modifier
//) {
//    key.Show(
//        modifier = modifier
//            .clip(RoundedCornerShape(1000.dp))
//    )
//}