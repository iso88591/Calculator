package me.iso88591.cacu.logics

interface IScreen {

    fun onShow(str: String)

    fun onNextLine()

    //显示屏
    //结果
    //是否结束
    //是否是错误
    fun onResult(result: String, error: Throwable?)

}