package com.lll.kotlin_dem.moto

import com.lll.kotlin_dem.bean.KouBeiBean
import com.lll.kotlin_dem.bean.MotoBean
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url


interface Api {


    @GET("carport/goods/rank/hot/list?rows=-1")
    fun getMotoList(@Query("goodType")  type:Int): Call<MotoBean>


    @GET("carport/goods/praise/score/list/6668?limit=6&page=1&carId=")
    fun getMotoKouBeiList( @Query("uid")  uid:Int): Call<KouBeiBean>

}