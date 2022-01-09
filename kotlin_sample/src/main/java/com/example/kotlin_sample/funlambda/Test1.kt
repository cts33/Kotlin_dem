package com.example.kotlin_sample.funlambda

/**
 * @description
 * @mail chentaishan@noboauto.com
 * @date 2021/10/19
 */

fun main() {
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

