package me.iso88591.cacu.ui.founction

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.MeasurePolicy
import androidx.compose.ui.unit.dp

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
@Composable
fun MyGrid(
    spanCount: Int,
    //横/竖 的间隔
    vGap: Int = 0,
    hGap: Int = 0,
    modifier: Modifier = Modifier,
    items: List<MyGridLayoutScopeModel>,
    perHeightFractions: Float = 1f,
    verticalCount: Int = -1
) {

    Layout(measurePolicy = MeasurePolicy { measurables, parentConstraints ->


        //计算宽度和高度 前记得要减去
        //计算每一份的宽度
        val perWidth =
            (parentConstraints.maxWidth - (spanCount - 1) * hGap.dp.roundToPx()) / spanCount.toFloat()
        //计算每一份的高度
        val perHeight = if (perHeightFractions == 0f && verticalCount != -1) {
           (parentConstraints.maxHeight - (verticalCount - 1) * vGap.dp.roundToPx() )/ verticalCount.toFloat()
        } else {
            perWidth * perHeightFractions
        }

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

        //垂直多少个
        val columnCount = items.sumOf { it.weight } / spanCount
        val contentHeightSum =
            (columnCount * perHeight).toInt() + (columnCount - 1) * vGap.dp.roundToPx()

        layout(parentConstraints.maxWidth, contentHeightSum) {

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
}


fun log(vararg args: Any?) {
    val content = args.joinToString(separator = ":")
    Log.e("TAG", "log: ===========${content}")
}