package com.example.kotlin_sample.funlambda

import android.widget.TextView

class B {
    fun testLambda() {
        var a = A()
//        a.getView(TextView(context), {
//            //TODO 此处接收A类的调用
//
//        })
        a.filterCountries(listOf(Country()), {
            it.name == "beijing"
        })
        a.filterCountries(arrayListOf(Country()), a::isBig)
    }

    fun testReturnValue(x:Int): (Int) -> Unit {
        return { y: Int -> x + y }
    }
    fun sum(x:Int)={ y:Int ->
        { z:Int ->
            x+y+z
        }
    }
    sum(1)(2)(3)
}

