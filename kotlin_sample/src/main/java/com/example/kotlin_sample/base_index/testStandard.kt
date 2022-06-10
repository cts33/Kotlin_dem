package com.example.kotlin_sample.base_index;


class A {
    val x = 11
    fun test1(): String {
        return "this is A.test1()"
    }
    fun test2() {}
}

fun testRun() {
    val len = A().run {
        test1()
    }.length
    println("A的run 方法返回字符串长度为$len")
}

fun testLet() {
    val result = A().let { it ->
        it.test1()
    }.length
    println("A的let 方法返回字符串为$result")
}

fun testAlso(){
    A().also {
        it.x ==44
    }.test1()
}

fun main() {
    testRun()
    testLet()
    testAlso()
    testWith()
}

fun testWith() {

    with(A()){
        this.test1()
    }
}
