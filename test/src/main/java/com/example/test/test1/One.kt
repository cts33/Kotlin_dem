package com.example.test.test1


object Uitl{

    @JvmStatic
    fun sayMsg(msg:String?){
        println("$msg")
    }
}

fun one(name:String){
    println("$name")
}

fun two(name:String ="lisi"){
    println("$name")
}

fun three (name:String) = println("$name")