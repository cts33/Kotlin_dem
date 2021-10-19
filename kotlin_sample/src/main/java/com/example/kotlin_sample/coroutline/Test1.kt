package com.example.kotlin_sample.coroutline

/**
 * @description
 * @mail chentaishan@noboauto.com
 * @date 2021/10/19
 */
import kotlinx.coroutines.*
fun main() = runBlocking {
    GlobalScope.launch {
        repeat(1000) { i ->
            println("I'm sleeping $i ...")
            delay(500L)
        }
    }
    delay(1300L) // 在延迟后退出
}