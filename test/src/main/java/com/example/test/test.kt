package com.example.test

fun main() {
    val user1 = User("Jane", "Doe")
    val user2  = User("Jane", "Doe")
    val structurallyEqual = user1 === user2 // true

    val referentiallyEqual  = user2==user1 // false

    println(structurallyEqual)
    print(referentiallyEqual)
}