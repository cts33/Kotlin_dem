package com.example.test.chapter2


class Book(val name: String)

fun main() {
    //使用构造函数的引用来调用属性
    val getBook = ::Book
    println(getBook("this is book").name)
}
