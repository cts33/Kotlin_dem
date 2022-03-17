package com.example.kotlin_sample.coroutline

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 *
 *
 *  implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.0-RC'
 */
fun main(){

//    CoroutineScope()

    GlobalScope.launch {
        delay(1000)
        println("world")
    }
    Thread.sleep(2000L)
    println("hello")
}