package com.example.kotlin_sample.coroutline

import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis


val TAG = "testCoroutline"
fun main() = runBlocking<Unit> {

    val time = measureTimeMillis {

        val one = doSomethingUsefulone()
        val two = doSomethingUsefulTwo()

        println("The answer is ${one + two}")
    }
    println("Completed in $time ms")

}


suspend fun doSomethingUsefulone(): Int {
    delay(1000)
    return 11
}

suspend fun doSomethingUsefulTwo(): Int {
    delay(1000L) // 假设我们在这里也做了一些有用的事
    return 29
}

