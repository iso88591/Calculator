package me.iso88591.cacu.logics

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
sealed class CacuKeys(
    val keyCode: Int = keyCodeGenerate++,
//    val style: CacuButtonStyle = CacuButtonStyle(),
) {

    companion object{

        private var keyCodeGenerate = 0

    }

    @Composable
    protected fun Bg(
        color: Color = Color(0xffA5A5A5),
        modifier: Modifier,
        content: @Composable () -> Unit
    ) {
        Box(
            modifier = modifier
                .background(color),
            contentAlignment = Alignment.Center
        ) {
            content()
        }
    }

    @Composable
    protected fun SimpleText(
        text: String,
        fontSize:TextUnit = 30.sp,
        color: Color = Color.Black
    ) {
        Text(
            text = text,
            fontSize = fontSize,
            color = color,
        )
    }

    @Composable
    open fun Show(modifier: Modifier) {
        Bg(modifier = modifier) {
            SimpleText(text = "1",)
        }
    }

    //text
//    class Text(str: String) : CacuKeys(str)


    //归零键
    object C : CacuKeys(
//        "C",
//        CacuButtonStyle(
//            bgColor = Color(0xffA5A5A5),
//            textColor = Color(0xff030303),
//        ),
        0
    )

    //括号
    object Brackets : CacuKeys(
//        "( )",
//        CacuButtonStyle(
//            bgColor = Color(0xffA5A5A5),
//            textColor = Color(0xff030303),
//        ),
        1
    )

    //delete
    object Delete : CacuKeys(
//        "Del",
//        CacuButtonStyle(
//            bgColor = Color(0xffA5A5A5),
//            textColor = Color(0xff030303),
//        ),
        2
    )

    //+
    object Plus : CacuKeys(
//        "+",
//        CacuButtonStyle(
//            bgColor = Color(0xffFEA00A),
//            textColor = Color.White,
//        ),
        3
    )

    //-
    object Reduce : CacuKeys(
//        "-",
//        CacuButtonStyle(
//            bgColor = Color(0xffFEA00A),
//            textColor = Color.White,
//        ),
        4
    )

    //乘法
    object Mul : CacuKeys(
//        "x",
//        CacuButtonStyle(
//            bgColor = Color(0xffFEA00A),
//            textColor = Color.White,
//        ),
        5
    )

    //除法
    object Div : CacuKeys(
//        "/",
//        CacuButtonStyle(
//            bgColor = Color(0xffFEA00A),
//            textColor = Color.White,
//        ),
        6
    )

    //求结果
    object Result : CacuKeys(
//        "=", CacuButtonStyle(
//            bgColor = Color(0xffFEA00A),
//            textColor = Color.White,
//        ),
        7
    )

    //百分号
    object Percent : CacuKeys(
//        "%",
//        CacuButtonStyle(
//            bgColor = Color(0xffA5A5A5),
//            textColor = Color(0xff030303),
//        ),
        8
    )

    object Point : CacuKeys(
//        ".",
//        CacuButtonStyle(
//            bgColor = Color(0xff333333),
//            textColor = Color.White,
//        ),
        9
    )

    //数字
    class Num(val number: Number) : CacuKeys(
//        "${number}",
//        CacuButtonStyle(
//            bgColor = Color(0xff333333),
//            textColor = Color.White,
//        )
    )

}