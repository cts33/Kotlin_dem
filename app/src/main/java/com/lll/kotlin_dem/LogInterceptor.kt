package com.lll.kotlin_dem

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

/**
 * @description
 * @mail chentaishan@noboauto.com
 * @date 2021/12/19
 */
class LogInterceptor : Interceptor {
    private val TAG = "LogInterceptor"
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        Log.d(TAG, "intercept: " + request.url)
        return chain.proceed(request)
    }
}