package com.lll.kotlin_dem.bean

/**
 * @description
 * @mail chentaishan@noboauto.com
 * @date 2021/12/18
 */
data class ResponseResult<T>(val code:Int,val msg:String,val data:List<T>)