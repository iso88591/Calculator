package me.iso88591.cacu.logics

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import me.iso88591.cacu.ui.theme.CacuKeyColorState
import me.iso88591.cacu.ui.theme.MyPalete

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
        modifier = Modifier
            .background(color, RoundedCornerShape(1000.dp))
            .clip(RoundedCornerShape(1000.dp))
            .then(modifier),
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

val Local_CacuKey_Text_Size = compositionLocalOf<TextUnit> { 10.sp }

sealed class CacuKeys(
    val simpleText: String = "",
    var spanCount: Int = 1,
    textColor: Color = Color.White,
    bgColor: Color = Color.White,

    //这玩意好像是多余的
    val keyCode: Int = 0,
) {

    var colorState by mutableStateOf<CacuKeyColorState>(CacuKeyColorState())

    init {

        colorState = CacuKeyColorState(textColor, bgColor)

    }

    @Composable
    open fun Show(modifier: Modifier) {
        Bg(
            color = colorState.bgColor,
            modifier = modifier
        ) {
            SimpleText(
                text = simpleText,
                color = colorState.textColor,
                fontSize = Local_CacuKey_Text_Size.current
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

    object None : CacuKeys("None")

    class Text(str: String) : CacuKeys(str)

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
    object PlusOrReduce : CacuKeys(
        "±",
        textColor = Color.Black,
        bgColor = Color(0xffA5A5A5)
    )

    //乘法
    object Mul : CacuKeys(
        "×",
        textColor = Color.White,
        bgColor = Color(0xffFEA00A)
    )

    //除法
    object Div : CacuKeys(
        "÷",
        textColor = Color.White,
        bgColor = Color(0xffFEA00A)
    )

    //求结果
    object Result : CacuKeys(
        "＝",
        textColor = Color.White,
        bgColor = Color(0xffFEA00A)
    )

    //百分号
    object Percent : CacuKeys(
        "％",
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


    companion object{
        /**
         *
         * 为横屏拓展的
         *
         */
        //左括号
        val LeftBrackets = Text("(")
        val RightBrackets = Text(")")

        val Mc = Text("mc")

        val mPlus = Text("m+")
        val mReduce = Text("m-")
        val mr = Text("mr")

        // 2 nd
        val nd2 = Text("2nd")

        val x2  = Text("x²")
        val x3 = Text("x³")

        val xy = Text("x^y")
        val ex = Text("e^x")
        val `10x` = Text("10^x")
        val `2x` = Text("2^x")

        //x分之1
        val `1Perx` = Text("1/x")
        //x开2次根 根号
        val root2 = Text("2√文")
        //x开3次根 根号
        val root3 = Text("3√文")
        //x开y次根 根号
        val rooty = Text("y√文")
        //对数 ln
        val ln = Text("ln")
        //log10
        val log10 = Text("log10")
        //阶乘
        val factorial = Text("x!")
        //sin
        val sin = Text("sin")
        //cos
        val cos = Text("cos")
        //tan
        val tan = Text("tan")
        //sinh
        val sinh = Text("sinh")
        //cosh
        val cosh = Text("cosh")
        //tanh
        val tanh = Text("tanh")
        //派 3.14.......
        val pi = Text("π")
        //随机数
        val rand = Text("Rand")
        //常数e
        val e = Text("e")




        //x! -> 表示阶乘 eg:  5! ==>  1*2*3*4*5

        //次方:
        //开方
        //三角函数
        //反三角函数 常数 派(3.14),随机数 阶乘

    }



}

fun <T : CacuKeys> T.applyCacuPalete(
    isFuncBtn: Boolean,
    palete: MyPalete
): T {
    this.colorState = if (isFuncBtn) {
        palete.cacuFuncButtonColor
    } else {
        palete.cacuNumButtonColor
    }
    return this
}