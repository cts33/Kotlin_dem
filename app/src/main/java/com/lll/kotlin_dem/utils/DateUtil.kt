package com.lll.kotlin_dem.utils

import java.text.SimpleDateFormat

/**
 * @description
 * @mail chentaishan@noboauto.com
 * @date 2021/12/20
 */
object DateUtil {

    fun formatDate(longStr: String): String {

        val dd = longStr.toLong()
        // Date 格式化输出

        val f1 = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

        return f1.format(dd)
    }
}