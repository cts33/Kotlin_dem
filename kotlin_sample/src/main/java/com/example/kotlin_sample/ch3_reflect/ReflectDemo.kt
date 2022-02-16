package com.example.kotlin_sample.ch3_reflect

import kotlin.reflect.KClass

class ReflectDemo(val x: Int = 0) {

    constructor() : this(0) {
    }

    fun test() {
        println(x)
    }
}

fun main() {

    //获取Class对象实例的两种方法
    val class1 = ReflectDemo().javaClass

    val class2: Class<ReflectDemo> = ReflectDemo::class.java

//    获取KClass对象实例方法
    val kClass1 = ReflectDemo::class
    val kClass2 = ReflectDemo().javaClass.kotlin

    //获取构造函数数组
//    kClass1.constructors
//    kClass2.constructors

    //成员变量
    kClass1.members
//    抽象类
    kClass1.isAbstract
//    伴生对象
    kClass1.isCompanion
//    数据类
    kClass1.isData
//    是否final修饰
    kClass1.isFinal
//    函数式接口
    kClass1.isFun
//    内部类
    kClass1.isInner
    kClass1.isOpen
    kClass1.isSealed

    kClass1.isValue
    //返回该实例对象
    kClass1.objectInstance

    for (item in kClass1.constructors) {
        println(item.call())
    }
    for (item in kClass2.constructors) {
        println(item.call())
    }
}