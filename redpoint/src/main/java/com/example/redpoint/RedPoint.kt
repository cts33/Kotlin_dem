package com.example.redpoint

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.util.Log
import android.util.TypedValue
import android.util.TypedValue.COMPLEX_UNIT_SP
import android.view.View

class RedPoint constructor(
    context: Context,
    attrs: AttributeSet,
    defStyleAttr: Int = 0,
    defStyleRes: Int
) :
    View(context, attrs, defStyleAttr, defStyleRes) {

    constructor(context: Context, attrs: AttributeSet) : this(context, attrs, 0, 0)

    private val paintBg = Paint()
    private val paintText = Paint()

    private val textSize =
        TypedValue.applyDimension(COMPLEX_UNIT_SP, 14f, context.resources.displayMetrics)
    private var numberStr: String? = ""

    private var radius: Float = 0f

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        val width = MeasureSpec.getSize(widthMeasureSpec)
        val height = MeasureSpec.getSize(heightMeasureSpec)

        //获取圆点的半径
        radius = width.coerceAtMost(height).toFloat() / 2
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        paintBg.color = Color.RED
        canvas?.drawCircle(radius, radius, radius, paintBg)

        paintText.color = Color.WHITE
        paintText.style = Paint.Style.FILL

        paintText.textSize = textSize

        Log.d(TAG, "textsize:" + paintText.textSize);

        val rect = Rect()
        //检索文本边界框，保存宽高
        paintText.getTextBounds(numberStr, 0, numberStr!!.length, rect)

        //绘制文字是以左下角为中心，所以文字要居中，计算偏移量
        val x = radius - rect.width() / 2
        val y = radius + rect.height() / 2

        canvas?.drawText(numberStr!!, x, y, paintText)


    }

    fun setText(number: String) {
        this.numberStr = number

        invalidate()
    }

    private val TAG = "RedPoint"

}