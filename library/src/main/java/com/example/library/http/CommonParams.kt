package com.example.library.http

import okhttp3.Request
import java.util.HashMap

class CommonParams : ParamsInterceptor() {
    private val commonParams: HashMap<String, String> = HashMap()
    private val headers: HashMap<String, String> = HashMap()

    fun addHeaders(headers: HashMap<String, String>): CommonParams {
        this.headers.putAll(headers)
        return this
    }

    fun addCommonParams(commonParams: HashMap<String, String>): CommonParams {
        this.commonParams.putAll(commonParams)
        return this
    }

    override fun applyCommonParams(params: HashMap<String, String>) {
        if (commonParams.size > 0) {
            params.putAll(commonParams)
        } else {
            super.applyCommonParams(params)
        }
    }

    override fun applyHeader(oldRequest: Request, params: HashMap<String, String>): Request {
        return if (headers.size > 0) {
            val builder = oldRequest.newBuilder()
            for ((key, value) in headers) {
                builder.addHeader(key, value)
            }
            builder.build()
        } else {
            super.applyHeader(oldRequest, params)
        }
    }
}