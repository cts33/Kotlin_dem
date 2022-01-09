package com.example.kotlin_sample.ch1


class Kotlin1 {

    fun isRight(b: Int): Boolean {
        return b > 0
    }


    fun test1(a: Int, test: ((b: Int) -> Boolean)) {
        if (test(a)) {
            print("test 函数传 函数")
        }
    }


    /**
     * 函数嵌套函数使用，对于不要求复用，不
     */
    fun test2(a: Int) {
        fun test3(b: Int): Boolean {
            return b > 0
        }

        if (test3(3)) {
            print("函数嵌套函数使用")
        }
    }

    fun main() {

        val kotlin1 = Kotlin1()
        //使用引用函数的形式调用高阶函数
        test1(2, kotlin1::isRight)

        //匿名函数形式
        test1(2, fun(a): Boolean {
            return a > 0
        })
//        另一种形式 lambda
        test1(2, { b -> b > 0 })
    }
}