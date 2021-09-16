package com.example.kotlin_sample.ch2

val sum: (Int, Int) -> Int = { x: Int, y: Int -> x + y }

val sum1 = { x: Int, y: Int -> x + y }

val sum2: (Int, Int) -> Int = { x, y -> x + y }

val sum3 = { x: Int ->
    val y = x + 1
    y//如果想要返回值，必须写y,返回没有返回值
}

fun main() {
    val s3 = sum3(4)
    println(s3)
}
