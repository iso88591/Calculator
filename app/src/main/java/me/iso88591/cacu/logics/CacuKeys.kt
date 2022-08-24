package me.iso88591.cacu.logics

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 *
 * 描述一个按钮的显示(可能显示文字，可能显示图片哦) 字体颜色 字体大小 这里用一个ComposeAble来描述，
 * 功能，code键(此值用于区分) ,
 * 在ui显示中一行占几个位置
 *
 */

@Composable
private fun Bg(
    color: Color,
    modifier: Modifier,
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(1000.dp))
            .background(color),
        contentAlignment = Alignment.Center
    ) {
        content()
    }
}

@Composable
private fun SimpleText(
    text: String,
    fontSize: TextUnit = 30.sp,
    color: Color = Color.White
) {
    Text(
        text = text,
        fontSize = fontSize,
        color = color,
        textAlign = TextAlign.Center,
        modifier = Modifier.fillMaxWidth()
    )
}

sealed class CacuKeys(
    val simpleText: String = "",
    var spanCount: Int = 1,
    var textColor: Color = Color.White,
    var bgColor: Color = Color.White,
    val keyCode: Int = keyCodeGenerate++,
) {

    companion object {
        private var keyCodeGenerate = 0
    }

    @Composable
    open fun Show(modifier: Modifier) {
        Bg(
            color = bgColor,
            modifier = modifier
        ) {
            SimpleText(
                text = simpleText,
                color = textColor
            )
        }
    }

    open class ImageCacuKeys(
        @DrawableRes private val imageResId: Int,
        spanCount: Int = 1
    ) : CacuKeys(
        "",
        spanCount
    ) {

        @Composable
        override fun Show(modifier: Modifier) {
            Box(
                modifier = modifier,
                contentAlignment = Alignment.Center
            ) {
                Image(painter = painterResource(id = imageResId), contentDescription = "")
            }
        }

    }

    //归零键
    object C : CacuKeys(
        "C",
        textColor = Color.Black,
        bgColor = Color(0xffA5A5A5)
    )

    //括号
//    object Brackets : CacuKeys(
////        "( )",
////        CacuButtonStyle(
////            bgColor = Color(0xffA5A5A5),
////            textColor = Color(0xff030303),
////        ),
//        1
//    )

    //delete
    object Delete : CacuKeys(
        "Del",
        textColor = Color.White,
        bgColor = Color(0xffFEA00A)
    )

    //+
    object Plus : CacuKeys(
        "+",
        textColor = Color.White,
        bgColor = Color(0xffFEA00A)
    )

    //-
    object Reduce : CacuKeys(
        "-",
        textColor = Color.White,
        bgColor = Color(0xffFEA00A)
    )

    //+或者-
    object PlusOrReduce:CacuKeys(
        "+/-",
        textColor = Color.Black,
        bgColor = Color(0xffA5A5A5)
    )

    //乘法
    object Mul : CacuKeys(
        "x",
        textColor = Color.White,
        bgColor = Color(0xffFEA00A)
    )

    //除法
    object Div : CacuKeys(
        "/",
        textColor = Color.White,
        bgColor = Color(0xffFEA00A)
    )

    //求结果
    object Result : CacuKeys(
        "=",
        textColor = Color.White,
        bgColor = Color(0xffFEA00A)
    )

    //百分号
    object Percent : CacuKeys(
        "%",
        textColor = Color.Black,
        bgColor = Color(0xffA5A5A5)
    )

    object Point : CacuKeys(
        ".",
    )

    //数字 01234567879
    class Num(val number: Number, weight: Int = 1) : CacuKeys(
        "${number}", weight
    )

}