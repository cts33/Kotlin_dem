package com.example.kotlin_sample.objclass

/**
 * @description
 * @mail chentaishan@noboauto.com
 * @date 2021/10/25
 */

interface Base {
    fun print()
}

class BaseImpl(val x: Int) : Base {

    override fun print() {

        println(x)
    }
}

class Derived(b: Base) : Base by b

fun main() {
//    var b = BaseImpl(22)
//
//    Derived(b).print()

    val clazz = MyClass(11,ClassWithDelegate(33))
    println(clazz.delegatedToAnotherClass)
}

var topLevelInt: Int = 0

class ClassWithDelegate(val anotherClassInt: Int)

class MyClass(var memberInt: Int, val anotherClassInstance: ClassWithDelegate) {
    //委托本类成员加  this：：
    var delegatedToMember: Int by this::memberInt
    //委托文件内成员加：：
    var delegatedToTopLevel: Int by ::topLevelInt
    //委托其他类成员加  类：：成员
    val delegatedToAnotherClass: Int by anotherClassInstance::anotherClassInt
}
var MyClass.extDelegated: Int by ::topLevelInt