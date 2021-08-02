package com.lll.kotlin_dem.moto

data class MotoBean(
    var code: Int,
    var data: List<Datam>,

    var msg: String
)


data class Datam(
    var brandId: Int,
    var brandName: String,
    var browseCount: Int,
    var discount: String,
    var goodId: Int,
    var goodName: String,
    var goodPic: String,
    var goodPrice: String,
    var grade: Int,
    var gradeCount: Int,
    var maxPrice: String,
    var minPrice: String,
    var queryPriceCount: Int,
    var rankChange: Int,
    var saleStatus: Int
)
