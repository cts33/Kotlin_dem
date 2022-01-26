package com.lll.kotlin_dem.base

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    @SuppressLint("MissingSuperCall")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(bindingView())
        initView()
        initData()
    }

    abstract fun bindingView(): View

    override fun onDestroy() {
        super.onDestroy()
    }

    fun initData() {}

    open fun initView() {}


}