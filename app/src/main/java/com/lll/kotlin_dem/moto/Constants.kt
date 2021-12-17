package com.lll.kotlin_dem.moto

import com.lll.kotlin_dem.bean.TabTag

object Constants {

    val baseUrl: String = " https://api.58moto.com/"
    val tabs = listOf<TabTag>(TabTag("全部",""),TabTag("街车","8"),TabTag("复古","13"),
        TabTag("街车","11"),TabTag("街车","4"))
}