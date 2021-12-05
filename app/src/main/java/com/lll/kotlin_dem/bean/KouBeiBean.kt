package com.lll.kotlin_dem.bean

import com.google.gson.annotations.SerializedName

/**
 * @description
 * @mail chentaishan@noboauto.com
 * @date 2021/12/5
 */
data class KouBeiBean(
    val msg: String,

    val code: Int,

    val data: List<KouBeiDataItem>?


) {
    inner class KouBeiDataItem(

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


}