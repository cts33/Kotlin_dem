package com.lll.kotlin_dem.view

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.widget.GridView
import java.lang.Integer.MAX_VALUE

class MyGridView(
    context: Context, attributeSet: AttributeSet

) : GridView(context, attributeSet) {

//    @SuppressLint("Range")
//    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
//        val expandSpec = MeasureSpec.makeMeasureSpec(MAX_VALUE, MeasureSpec.AT_MOST);
//        super.onMeasure(widthMeasureSpec, expandSpec);
//
//    }
}