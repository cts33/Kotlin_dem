package com.example.kotlin_sample.funlambda

import android.view.View

class A {
    fun getView(view: View, iUpdateListener: () -> Unit) {
        view.setOnClickListener {
            iUpdateListener.invoke()
        }
    }

    fun filterCountries(countires: List<Country>, isBig: (Country) -> Boolean) {
        countires.forEach {
            if (isBig(it)) {
                print(it.name)
            }
        }
    }

    fun isBig(name: Country): Boolean {
        return name.name == "beijing"
    }

//    fun test
}