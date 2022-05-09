package com.lll.kotlin_dem.moto

import com.example.library.http.ResponseResult
import com.lll.kotlin_dem.bean.DataItem
import com.lll.kotlin_dem.bean.KouBeiDataItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface Api {


    @GET("carport/goods/rank/grade/list?rows=-1")
    suspend fun getMotoList(@Query("goodType")  type:String): ResponseResult<DataItem>

    @GET("carport/goods/praise/score/list/{uid}?limit=20&carId=")
    suspend fun getMotoKouBeiList( @Path("uid")  uid:Int,@Query("page") page:Int): ResponseResult<KouBeiDataItem>

    @GET("forum/public/businessEssayController.do?action=22004&id=5737777")
    fun getKouBeiDetail( @Path("autherid")  autherid:Int): Call<ResponseResult<KouBeiDataItem>>

}