package com.example.kotlin_sample.objclass

import com.example.kotlin_sample.objclass.Color.RED

/**
 * @description
 * @mail chentaishan@noboauto.com
 * @date 2021/10/25
 */

sealed class Color{

    class RED(val value:Int) : Color()

    class Blue(val value:Int) : Color()
}

fun main() {
val color = RED(1)
    when(color){
        is RED -> print("red")
    }
}
