package com.example.library.views

import android.content.Context
import android.graphics.Color
import android.graphics.PorterDuff
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import android.widget.ImageView.ScaleType
import android.widget.LinearLayout
import com.example.library.R

class GridViewLayout<T> : LinearLayout {
    private var mContext: Context
    private var mImageViewcache: ImageView? = null


    /**
     * 长度 单位为Pixel
     * 单张图显示时：宽高是最大宽度的2/3
     */
    private var pxOneMaxDH = 0// 单张图最大允许宽高
    private var pxMoreDH = 0 // 多张图显示时的单个图片宽高，图片都是正方形显示
    private val pxImagePadding = dip2px(context, 3f) // 图片间的间距
    private var MAX_PER_COLUMN_COUNT = 3 // 最大纵列数量
    private var onePicPara: LayoutParams = LayoutParams(pxOneMaxDH, LayoutParams.WRAP_CONTENT)
    private var rowPara: LayoutParams?  = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
    private var mOnItemClickListener: OnItemClickListener? = null

    private var moreParaColumnFirst: LayoutParams = LayoutParams(pxMoreDH, pxMoreDH)
    private var morePara: LayoutParams = LayoutParams(pxMoreDH, pxMoreDH)

    init{
        morePara!!.setMargins(pxImagePadding, 0, 0, 0)
    }
    fun setOnItemClickListener(onItemClickListener: OnItemClickListener?) {
        mOnItemClickListener = onItemClickListener
    }

    constructor(context: Context) : super(context) {
        mContext = context
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        mContext = context

        this.orientation = VERTICAL
    }

    lateinit var imageLoader: ((T, ImageView) -> Unit)

    // 照片的Url列表
    private val dataList = arrayListOf<T>()

    fun setDataList(
        dataList: List<T>,
        imageLoader: ((T, ImageView) -> Unit)
    ) {

        this.dataList.clear()
        this.dataList.addAll(dataList)


        this.imageLoader = imageLoader

        if (MAX_WIDTH > 0) {
            pxMoreDH = (MAX_WIDTH - pxImagePadding * 2) / 3 //解决右侧图片和内容对不齐问题
            pxOneMaxDH = MAX_WIDTH * 2 / 3
            initImageLayoutParams()
        }
        initView()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        if (MAX_WIDTH == 0) {
            MAX_WIDTH = measureWidth(widthMeasureSpec)
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    /**
     * Determines the width of this view
     *
     * @param measureSpec A measureSpec packed into an int
     * @return The width of the view, honoring constraints from measureSpec
     */
    private fun measureWidth(measureSpec: Int): Int {
        var result = 0
        val specMode = MeasureSpec.getMode(measureSpec)
        val specSize = MeasureSpec.getSize(measureSpec)
        if (specMode == MeasureSpec.EXACTLY) {
            // We were told how big to be
            result = specSize
        } else {
            // Measure the text
            // result = (int) mTextPaint.measureText(mText) + getPaddingLeft()
            // + getPaddingRight();
            if (specMode == MeasureSpec.AT_MOST) {
                // Respect AT_MOST value if that was what is called for by
                // measureSpec
                result = Math.min(result, specSize)
            }
        }
        return result
    }

    private fun initImageLayoutParams() {
        onePicPara = LayoutParams(pxOneMaxDH, LayoutParams.WRAP_CONTENT)
        moreParaColumnFirst = LayoutParams(pxMoreDH, pxMoreDH)
        morePara = LayoutParams(pxMoreDH, pxMoreDH)
        morePara!!.setMargins(pxImagePadding, 0, 0, 0)
        rowPara = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
    }

    // 根据imageView的数量初始化不同的View布局,还要为每一个View作点击效果
    private fun initView() {

        removeAllViews()

        if (dataList.isNullOrEmpty()) {
            return
        }
        if (dataList!!.size == 1) {
            addView(getImageViewFromCache(0, false))
        } else {
            val allCount = dataList!!.size
            MAX_PER_COLUMN_COUNT = if (allCount == 4) {
                2
            } else {
                3
            }
            val rowCount =
                allCount / MAX_PER_COLUMN_COUNT + if (allCount % MAX_PER_COLUMN_COUNT > 0) 1 else 0 // 行数
            for (rowCursor in 0 until rowCount) {
                val rowLayout = LinearLayout(context)
                rowLayout.orientation = HORIZONTAL
                rowLayout.setLayoutParams(rowPara)
//                rowLayout.layoutParams = rowPara
                if (rowCursor != 0) {
                    rowLayout.setPadding(0, pxImagePadding, 0, 0)
                }
                var columnCount =
                    if (allCount % MAX_PER_COLUMN_COUNT == 0) MAX_PER_COLUMN_COUNT else allCount % MAX_PER_COLUMN_COUNT //每行的列数
                if (rowCursor != rowCount - 1) {
                    columnCount = MAX_PER_COLUMN_COUNT
                }
                addView(rowLayout)
                val rowOffset = rowCursor * MAX_PER_COLUMN_COUNT // 行偏移
                for (columnCursor in 0 until columnCount) {
                    val position = columnCursor + rowOffset
                    val imageView = getImageViewFromCache(position, true)
                    rowLayout.addView(imageView)
                }
            }
        }
    }

    private val TAG = "GridViewLayout"

    private fun getImageViewFromCache(
        position: Int,
        isMultiImage: Boolean
    ): ImageView? {
        var mImageView: ImageView? = null
        if (!isMultiImage) {
            if (mImageViewcache == null) {
                mImageViewcache = ImageView(context)
            }
            mImageViewcache!!.adjustViewBounds = true
            mImageViewcache!!.scaleType = ScaleType.FIT_START
            mImageViewcache!!.maxHeight = pxOneMaxDH
            mImageViewcache!!.setLayoutParams(onePicPara)
            mImageView = mImageViewcache
        } else {

            if (mImageView == null) {
                mImageView = ImageView(context)
                mImageView.scaleType = ScaleType.CENTER_CROP
                mImageView.layoutParams =
                    if (position % MAX_PER_COLUMN_COUNT == 0) moreParaColumnFirst else morePara
            }
        }
        val PressX = FloatArray(1)
        val PressY = FloatArray(1)
        val url = dataList!![position]
        mImageView!!.setOnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    //                        ((ImageView) v).setColorFilter(Color.GRAY, PorterDuff.Mode.MULTIPLY);
                    PressX[0] = event.x
                    PressY[0] = event.y
                }
                MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> (v as ImageView).setColorFilter(
                    Color.TRANSPARENT
                )
                else -> {
                }
            }
            false
        }
        mImageView.setOnClickListener { v ->
            if (mOnItemClickListener != null) {
                mOnItemClickListener!!.onItemClick(
                    v,
                    v.getTag(R.id.FriendLife_Position) as Int,
                    PressX[0],
                    PressY[0]
                )
            }
        }
        mImageView.setOnLongClickListener { v ->
            if (mOnItemClickListener != null) {
                (v as ImageView).setColorFilter(Color.GRAY, PorterDuff.Mode.MULTIPLY)
                mOnItemClickListener!!.onItemLongClick(
                    v,
                    v.getTag(R.id.FriendLife_Position) as Int,
                    PressX[0],
                    PressY[0]
                )
            }
            true
        }
        mImageView.id = url.hashCode()
        mImageView.setTag(R.id.FriendLife_Position, position)

        this.imageLoader(dataList[position], mImageView)
        return mImageView
    }

    interface OnItemClickListener {
        fun onItemClick(view: View?, PressImagePosition: Int, PressX: Float, PressY: Float)
        fun onItemLongClick(view: View?, PressImagePosition: Int, PressX: Float, PressY: Float)
    }

    private fun dip2px(context: Context, dpValue: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (dpValue * scale + 0.5f).toInt()
    }


    companion object {
        var MAX_WIDTH = 0
    }
}

