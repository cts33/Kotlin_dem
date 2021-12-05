package com.lll.kotlin_dem.bean

import com.google.gson.annotations.SerializedName

data class MotoBean(
                @SerializedName("msg")
                val msg: String = "",
                @SerializedName("code")
                val code: Int = 0,
                @SerializedName("data")
                val data: List<DataItem>?)