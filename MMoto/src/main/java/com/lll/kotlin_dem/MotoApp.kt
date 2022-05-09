package com.lll.kotlin_dem

import android.app.Application
import com.example.library.http.RetrofitManager

/**
 * @description
 * @mail chentaishan@aliyun.com
 * @date 2022/5/9
 */
class MotoApp : Application() {
    override fun onCreate() {
        super.onCreate()
        RetrofitManager.init(this)
    }
}