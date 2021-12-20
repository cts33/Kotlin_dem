package com.lll.kotlin_dem.utils

import android.content.Context
import android.view.WindowManager

/**
 * @description
 * @mail chentaishan@noboauto.com
 * @date 2021/12/20
 */
object ScreenUtil {

    fun getScreenWidth(context: Context): Int {
        val wm1: WindowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        return wm1.getDefaultDisplay().getWidth()
    }
}