package com.lll.kotlin_dem.moto

import com.lll.kotlin_dem.LogInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * @description
 * @mail chentaishan@noboauto.com
 * @date 2021/12/19
 */
class NetMangager {

    companion object {

        private val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(LogInterceptor())
            .build()
        var apiService: Api = Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(Constants.baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Api::class.java)

    }
}