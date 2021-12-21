package com.lll.kotlin_dem.utils

import com.lll.kotlin_dem.bean.TabTag

object Constants {

    val typeId: String = "typeId"
    val uid: String = "uid"
    val koubeiId: String = ""
    val baseUrl: String = " https://api.58moto.com/"
    val tabs = listOf(
        TabTag("全部", ""),
        TabTag("巡航", "4"),
        TabTag("踏板", "3"),
        TabTag("街车", "8"),
        TabTag("跑车", "9"),
        TabTag("复古", "13"),
        TabTag("拉力", "11")

    )

}