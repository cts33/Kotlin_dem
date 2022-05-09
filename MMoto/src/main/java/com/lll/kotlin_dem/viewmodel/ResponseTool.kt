package com.lll.kotlin_dem.viewmodel

import com.example.library.http.ResponseResult
import com.example.library.http.ResponseSuspend
import com.example.library.http.RetrofitManager
import com.lll.kotlin_dem.bean.DataItem
import com.lll.kotlin_dem.moto.Api

object ResponseTool {
    suspend fun getMotoList(tabId: String): ResponseResult<DataItem> {
        return ResponseSuspend.handle { RetrofitManager.getApi(Api::class.java).getMotoList(tabId) }
    }

}