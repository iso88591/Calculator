package me.iso88591.cacu.ui.cacu

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import me.iso88591.cacu.logics.CacuKeys


class VerticalKeyBoard(
    val spanCount: Int,
    override var onKeyDown: (CacuKeys) -> Unit,
    override val keys: List<CacuKeys>
) : IKeyBoard {

    @Composable
    override fun Display() {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom,
            modifier = Modifier
                .background(Color(0xff010101))
                .padding(bottom = 20.dp)
        ) {

            //屏幕
//            SimpleCacuScreen(
//                text = showText,
//                modifier = Modifier
//                    .fillMaxWidth(1f)
//                    .weight(1f, true)
//            )

//            repeat(lines) { column ->
//                Row {
//
//                    repeat(spanCount) { row ->
//                        val key = keys[column * spanCount + row]
//                        OneCacuButton(
//                            key = key,
//                            androidx.compose.ui.Modifier
//                                .padding(itemDivider.dp)
//                                .clickable {
//                                    onKeyDown(key)
//                                }
//                                .width(width.dp * key.spanCount)
//                                .height(width.dp)
//                        )
//                    }
//                }
//            }
        }

    }

}

//@Composable
//fun VerticalKeyBoard(){
//
//}