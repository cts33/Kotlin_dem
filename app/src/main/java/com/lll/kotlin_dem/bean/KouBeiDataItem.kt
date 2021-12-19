package com.lll.kotlin_dem.bean

/**
 * @description
 * @mail chentaishan@noboauto.com
 * @date 2021/12/5
 */
data class KouBeiDataItem(

    val carName: String,
    val carPrice: String,
    val content: String,
    val createTime: String,
    val duration: Int,
    val id: Int,
    val score: String,
    val satisfaction: String,
    val purchaseDate: String,
    val purchaseCity: String,
    val images: Array<MotoImg>
)
