package com.example.kotlin_sample.objclass

import kotlin.reflect.KProperty

/**
 * @description
 * @mail chentaishan@noboauto.com
 * @date 2021/10/25
 */

class xx{

    var prop: MyDelegate by MyDelegate()
}
class MyDelegate{
    operator fun getValue(xx: xx, property: KProperty<*>): MyDelegate {
        return MyDelegate()
    }
    operator fun setValue(xx: xx, property: KProperty<*>, myDelegate: MyDelegate) {
    }
}

fun main() {

}