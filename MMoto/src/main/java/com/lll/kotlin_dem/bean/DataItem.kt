package com.lll.kotlin_dem.bean

/**
 * Updated by chents on 2022-01-24
 */
data class DataItem(
    var brandId: Int,
    var brandName: String,
    var browseCount: Int,
    var discount: String,
    var energyType: String,
    var goodId: Int,
    var goodName: String,
    var goodPic: String,
    var goodPrice: String,
    var grade: Double,
    var gradeCount: Int,
    var maxPrice: String,
    var minPrice: String,
    var queryPriceCount: Int,
    var rankChange: Int,
    var saleStatus: Int
)