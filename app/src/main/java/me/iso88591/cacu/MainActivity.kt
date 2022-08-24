package me.iso88591.cacu

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.MeasurePolicy
import androidx.compose.ui.platform.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.core.view.WindowCompat
import me.iso88591.cacu.logics.CacuKeys
//import me.iso88591.cacu.ui.cacu.SimpleCacu
import me.iso88591.cacu.ui.theme.CacuTheme

class MainActivity : ComponentActivity() {

    private fun SimpleVerticalKeys() = buildGridItems {
        listOf<CacuKeys>(
            CacuKeys.C,
            CacuKeys.PlusOrReduce,
            CacuKeys.Percent,
            CacuKeys.Div,

            CacuKeys.Num(7),
            CacuKeys.Num(8),
            CacuKeys.Num(9),
            CacuKeys.Mul,

            CacuKeys.Num(4),
            CacuKeys.Num(5),
            CacuKeys.Num(6),
            CacuKeys.Reduce,

            CacuKeys.Num(1),
            CacuKeys.Num(2),
            CacuKeys.Num(3),
            CacuKeys.Plus,

            CacuKeys.Num(0,2),CacuKeys.Point, CacuKeys.Result
        ).forEach {
            item(it.spanCount) {
                it.Show(modifier = Modifier)
            }
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        WindowCompat.setDecorFitsSystemWindows(window,true)

        setContent {
            CacuTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

//                    var screenText by remember {
//                        mutableStateOf("")
//                    }
//                    SimpleCacu(screenText){
////                        screenText += it.key
//                    }

//                    UseGrid()

                    UseLayout(4,
                        10,
                        10,
                        Modifier
                            .background(Color(0xff010101))
                            .padding(horizontal = 10.dp),
                        remember { SimpleVerticalKeys() }
                    )

                }
            }
        }
    }

    private fun log(vararg args: Any?) {
        val content = args.joinToString(separator = ":")
        Log.e("TAG", "log: ===========${content}")
    }

    data class MyGridLayoutScopeModel(
        val weight: Int,
        val content: @Composable () -> Unit
    )

    fun interface GridLayoutScope {

        fun item(weight: Int, content: @Composable () -> Unit)

        fun item(content: @Composable () -> Unit) = item(1, content)

    }

    fun buildGridItems(
        scope: GridLayoutScope.() -> Unit
    ): List<MyGridLayoutScopeModel> {
        return ArrayList<MyGridLayoutScopeModel>().also {
            scope.invoke(GridLayoutScope { weight, content ->
                it.add(MyGridLayoutScopeModel(weight, content))
            })
        }
    }

    /**
     * create by iso88591 at 2022/8/24
     *
     */
//    @OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3WindowSizeClassApi::class)
    @Composable
    private fun UseLayout(
        spanCount: Int,
        //横/竖 的间隔
        vGap: Int = 0,
        hGap: Int = 0,
        modifier: Modifier = Modifier,
        items: List<MyGridLayoutScopeModel>
    ) {

        Layout(measurePolicy = MeasurePolicy { measurables, parentConstraints ->


            //计算宽度和高度 前记得要减去
            //计算每一份的宽度
            val perWidth =
                (parentConstraints.maxWidth - (spanCount - 1) * hGap.dp.roundToPx()) / spanCount.toFloat()
            //计算每一份的高度
            val perHeight = perWidth

            log("=======${perWidth}")

            val places = measurables.mapIndexed { index, measurable ->

                val scopeModel = items[index]
                val thisWidth =
                    (perWidth * scopeModel.weight + hGap.dp.roundToPx() * (scopeModel.weight - 1)).toInt()
                val thisHeight = perHeight.toInt()
                measurable.measure(
                    parentConstraints.copy(
                        minWidth = thisWidth,
                        maxWidth = thisWidth,
                        minHeight = thisHeight,
                        maxHeight = thisHeight,
                    )
                )

            }

            layout(parentConstraints.maxWidth, parentConstraints.maxHeight) {

                var start = 0
                places.forEachIndexed { index, placeable ->
                    val current = items[index]
                    val hIndex = start % spanCount
                    val vIndex = start / spanCount
                    placeable.place(
                        (perWidth * hIndex + hIndex * hGap.dp.roundToPx()).toInt(),
                        (perHeight * vIndex + vIndex * vGap.dp.roundToPx()).toInt()
                    )
                    start += current.weight
                }

            }

        }, content = {
            items.forEach {
                it.content()
            }
        }, modifier = modifier)


//        LazyVerticalGrid(cells = GridCells.Adaptive(1000.dp), content = {
//            itemsIndexed(items) { index, item ->
//                Box(
//                    modifier = Modifier
//                        .width(perWidth * item.weight)
//                        .height(perHeight * item.weight)
//                ) {
//                    item.content()
//                }
//            }
//
//        })
    }


    @OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3WindowSizeClassApi::class)
    @Composable
    private fun UseGrid() {

//        LazyVerticalGrid(cells = GridCells.Fixed(5), content = {
//
//            items(99){
//
//                Text(text = "text${it}")
//            }
//
//        })

//        val calculateWindowSizeClass =
//            calculateWindowSizeClass(activity = LocalContext.current as Activity)


        println("============${LocalConfiguration.current.screenWidthDp} ==== ${LocalConfiguration.current.screenHeightDp}")

        println("============${LocalView.current.measuredHeight}")

        println("============${(LocalView.current.parent.parent.parent as LinearLayout).measuredHeight}")

        //2340
        //2244
        //  96


        val size = (LocalConfiguration.current.screenWidthDp / 4).dp

        LazyVerticalGrid(cells = GridCells.Adaptive(size), content = {
            items(99) {
                Box(modifier = Modifier.height(size), contentAlignment = Alignment.Center) {
                    Text(text = "text${it}", textAlign = TextAlign.Center)
                }
            }

        })

    }

}

@Composable
fun Greeting(name: String) {
    //计算器
    //基本的加减乘除 四则运算
    //支持横竖屏 额外加分
    //独特设计 或者拓展功能额外功能


    //
//    1 + 2

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CacuTheme {
        Greeting("Android")
    }
}


//Layout(measurePolicy = MeasurePolicy { measurables, outConstraints ->
//
////            outConstraints.copy()
//            log("约束", outConstraints.toString())
//
//            //1. 把所有子的可测量的（Measurable） 测量(传入约束)得到PlaceAble
//            val places = measurables.mapIndexed { index, measurable ->
//                measurable.measure(outConstraints.copy()).apply {
//
//                    log("遍历子并且测量", this.toString())
//
//                }
//            }
//
//            layout(
//                outConstraints.maxWidth,
//                outConstraints.maxHeight
//            ) {
//
//                //摆放子item的逻辑
//                places.forEachIndexed { index, placeable ->
//                    placeable.place(0, 0, 0f)
//                    log("遍历子并摆放", placeable.toString())
//                }
//
//            }
//
//        }, content = {
//            remember {
//                MyGridLayoutScopeImpl()
//            }.also {
//                it.content()
////                    it.items.forEachIndexed { index, myGridLayoutScopeModel ->
////
////                    }
//            }
//        })