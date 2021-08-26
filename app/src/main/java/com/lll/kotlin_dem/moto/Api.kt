package com.lll.kotlin_dem.moto

import com.lll.kotlin_dem.MotoBean
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface Api {


    @GET("carport/goods/rank/hot/list?rows=-1")
    fun getMotoList(@Query("goodType")  type:Int): Call<MotoBean>

}