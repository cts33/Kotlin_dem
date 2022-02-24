package com.example.kotlin_sample.ch3_reflect

import org.jetbrains.annotations.Nullable
import kotlin.reflect.KClass
import kotlin.reflect.KProperty
import kotlin.reflect.full.*

class Person(val name: String, val age: Int, var address: String) {

    fun find(name:String): List<String> {
        return listOf("hebei", "beijing")
    }

    fun <A,B> get(a:A):A{
        return a
    }
}



fun <A : Any> toMap(a: A): Map<String, Any?> {
    //遍历a的所有属性，name to value
    return a::class.memberProperties.map { m ->
        m.name to m.call(a)
    }.toMap()
}

fun main() {

    val person = Person("lisi", 44, "北京")

    for (c in Person::class.members) {
        println("${c.name}:${  c.typeParameters}")
    }

//    //获取Class对象实例的两种方法
//    val class1 = ReflectDemo().javaClass
//
//    val class2: Class<ReflectDemo> = ReflectDemo::class.java
//
//    val method = class1.declaredMethods
//    if (method[0].name == "test") {
//        method[0].invoke(class1)
//    }
//    val instance = class1.newInstance()
//    instance.x
//    instance.test()
//
//    val instance2 = class2.newInstance()
//    instance2.x
//    instance2.test()
//
//
//    testKClass()

}

fun testKClass() {
    //    获取KClass对象实例方法
    val kClass1 = Person::class
//    val kClass2 = ReflectDemo().javaClass.kotlin

    kClass1.objectInstance

    //成员变量

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
    //是否open
    kClass1.isOpen
    //是否为密封类
    kClass1.isSealed

    kClass1.isValue
    //返回该实例对象
    kClass1.objectInstance
    kClass1.companionObjectInstance
//    扩展属性
    kClass1.declaredMemberExtensionProperties
    //扩展函数 返回这个类声明的扩展函数，不是其他位置的声明的本类扩展函数
    kClass1.declaredMemberExtensionFunctions
//    本类及超类扩展属性
    kClass1.memberExtensionProperties
    //本类及超类扩展函数
    kClass1.memberExtensionFunctions
    //泛型通配类型
    kClass1.starProjectedType

    kClass1.members


    for (item in kClass1.constructors) {
        println(item.call())
    }

}