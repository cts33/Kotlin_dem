package com.example.kotlin_sample



//fun main() {
//    val reflectDemo = ReflectDemo(33)
//
//    val kclass = ReflectDemo::class
//
//    val obj = kclass.objectInstance
//
//
//    println("obj=${obj?.x}")
//
//    //kclass.members 当前类和父类的所有属性
//    //kclass.memberProperties打印当前类的所有属性
//    for (item in kclass.memberProperties) {
//        val obj = item as KProperty<*>
//        println("${obj.name}:${obj.call(reflectDemo)}")
//    }
//    //获取所有方法包含父类
//    kclass.memberFunctions
//    //获取当前类的所有方法,不包括外部文件的扩展方法
//    kclass.declaredMemberFunctions
//
//    for (item in kclass.declaredMemberFunctions) {
//
//        println("${item.name}")
//        //调用方法
////        item.call(reflectDemo)
//    }
//
//
//    for (item in kclass.annotations) {
//
//        println("${item.javaClass}")
//    }
//
//
//
//}