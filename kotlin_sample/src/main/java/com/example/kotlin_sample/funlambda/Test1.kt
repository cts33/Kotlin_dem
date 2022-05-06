package com.example.kotlin_sample.funlambda

import android.widget.Button

/**
 * @description
 * @mail chentaishan@noboauto.com
 * @date 2021/10/19
 */

fun main() {
    /**
     * 方法内部定义局部方法
     */
    fun test1(){
        fun testSub(value:Int):Int{
           return value*3
        }
        print("this is subMethod value=${testSub(4)}")
    }


//    val repeatFun: String.(Int) -> String = { times -> this.repeat(times) }
//    val twoParameters: (String, Int) -> String = repeatFun // OK
//
//    fun runTransformation(f: (String, Int) -> String): String {
//        return f("hello", 3)
//    }
//    val result = runTransformation(repeatFun) // OK
//
//    println("result = $result")

//    val sourceList = mutableListOf(1, 2, 3)
//    val copySet = sourceList.toMutableSet()
//    copySet.add(3)
//    copySet.add(4)
//    println(copySet)

    val testinline = Testinline()
    testinline.xxx(33,
        fun(a: Int): Boolean {
            return a > 0
        }
    )
}

