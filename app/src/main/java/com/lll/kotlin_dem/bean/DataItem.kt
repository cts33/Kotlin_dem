package com.lll.kotlin_dem.bean

import com.google.gson.annotations.SerializedName

data class DataItem(@SerializedName("brandName")
                    val brandName: String = "",
                    @SerializedName("queryPriceCount")
                    val queryPriceCount: Int = 0,
                    @SerializedName("browseCount")
                    val browseCount: Int = 0,
                    @SerializedName("rankChange")
                    val rankChange: Int = 0,
                    @SerializedName("discount")
                    val discount: String = "",
                    @SerializedName("goodId")
                    val goodId: Int = 0,
                    @SerializedName("saleStatus")
                    val saleStatus: Int = 0,
                    @SerializedName("goodPrice")
                    val goodPrice: String = "",
                    @SerializedName("goodName")
                    val goodName: String = "",
                    @SerializedName("brandId")
                    val brandId: Int = 0,
                    @SerializedName("grade")
                    val grade: Int = 0,
                    @SerializedName("minPrice")
                    val minPrice: String = "",
                    @SerializedName("goodPic")
                    val goodPic: String = "",
                    @SerializedName("maxPrice")
                    val maxPrice: String = "",
                    @SerializedName("gradeCount")
                    val gradeCount: Int = 0)