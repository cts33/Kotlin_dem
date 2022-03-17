package com.example.kotlin_sample

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

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


    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)

        val iterator = map3.iterator()
        while (iterator.hasNext()){
            val next = iterator.next()

        }

    }


}