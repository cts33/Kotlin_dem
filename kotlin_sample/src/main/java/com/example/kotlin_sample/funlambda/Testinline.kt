package com.example.kotlin_sample.funlambda

import java.util.concurrent.locks.Lock

/**
 * @description
 * @mail chentaishan@noboauto.com
 * @date 2021/10/26
 */
class Testinline {

    fun xxx(x: Int, test: ((Int) -> Boolean)) {
        if (test(x)) {

            print("this is test")
        }
    }


}
//inline fun <T> lock(lock: Lock, body: () -> T): T {
//
//    return body
//}
//fun main() {
//
//    lock(l) { foo() }
//
//}
//val body = {
//    l.lock()
//    try {
//        foo()
//    }
//    finally {
//        l.unlock()
//    }
//}
