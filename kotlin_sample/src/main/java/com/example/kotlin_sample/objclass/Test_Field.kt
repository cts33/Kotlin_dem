package com.example.kotlin_sample.objclass

data class Test_Field(val name: String) {
    var age: Int = 18
        get() {
            //field 隐藏字段 其实代表age
            return field
        }
        set(value) {
            field = value
        }
}

fun main() {
    val li = Test_Field("lili")
    li.age = 33
    println(li.toString())
    println(li.age)
}