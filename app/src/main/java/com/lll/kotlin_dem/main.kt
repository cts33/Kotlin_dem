package com.lll.kotlin_dem

fun main() {


//映射map
    val numbers = setOf(1, 2, 3)
    println(numbers.map { it * 3 })
    println(numbers.mapIndexed { idx, value -> value * idx })

//关联生成map
    val numbers1 = listOf("one", "two", "three", "four")
    println(numbers1.associateWith { it.length })
}