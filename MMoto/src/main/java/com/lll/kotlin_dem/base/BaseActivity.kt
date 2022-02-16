package com.lll.kotlin_dem.base

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(bindingView())
        initView()
        initData()
    }

    abstract fun bindingView(): View

    open fun initData() {}

    open fun initView() {}

    override fun onDestroy() {
        super.onDestroy()
    }


}