package com.example.kotlin_sample.Collection

class Demo1 {

    val list = emptyList<String>()
    //空集合list
    val list1 = listOf<String>()
    //固定大小集合list
    val list2 = listOf<String>("1","2")
    //可变大小集合list
    val list3 = mutableListOf<String>("1","2")

    //空set
    val set1 = setOf<String>()
    //固定set
    val set2 = setOf<String>("1","2")
    //可变大小set
    val set3 = mutableSetOf<String>("1","2")

    //空set
    val map1 = mapOf<String,String>()
    val map2 = mapOf<String,String>("1" to "A")
    //可变map
    val map3 = mutableMapOf<String,String>()

}