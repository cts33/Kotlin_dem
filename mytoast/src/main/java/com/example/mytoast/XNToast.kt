package com.example.mytoast


import android.annotation.SuppressLint
import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.PixelFormat
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.ImageView
import android.widget.TextView
import org.json.JSONObject


/**
 * 自定义toast 样式
 * 显示文本，图片，延迟时间
 */
@SuppressLint("StaticFieldLeak")
object XNToast {

    private const val MARGIN_EDGE = 50
    private var params: WindowManager.LayoutParams? = null

    /**
     * obj:
     * title 必填
     * icon 选填
     * duration 选填
     */
    fun show(context: Context, obj: JSONObject) {
        var drawable: Drawable? = null
        var title = obj.optString("title")
        if (title.isNullOrEmpty()) return
        // TODO 目前url形式为assets路径
        val imageUrl = obj.optString("image")
        if (imageUrl.isNullOrEmpty()) {
            val icon = obj.optString("icon")
            icon?.let {
                when (it) {
                    "success" -> drawable = context.getDrawable(R.drawable.toast_success)
                    "loading" -> drawable = context.getDrawable(R.drawable.toast_loading)
                    "error" -> drawable = context.getDrawable(R.drawable.toast_error)
                }
            }
        } else {
            val imgResource = context.assets.open(imageUrl)
            drawable = BitmapDrawable(BitmapFactory.decodeStream(imgResource))
        }

        if (title.length > 10) {
            title = title.substring(0, 10).plus("...")
        }


        val duration = obj.optLong("duration")

        val mask = obj.optBoolean("mask")


        show(context, title, drawable, duration)
    }

    private var iconIv: ImageView? = null
    private var titleTv: TextView? = null

    private fun initView(context: Context): View {
        val root = LayoutInflater.from(context).inflate(R.layout.toast_layout, null)

        root.setBackgroundResource(android.R.drawable.toast_frame)
        iconIv = root.findViewById(R.id.toast_iv) as ImageView
        titleTv = root.findViewById(R.id.toast_tv) as TextView

        return root
    }

    fun show(
        context: Context,
        text: String,
        assetPath: String,
        duration: Long = 1500
    ) {
        val imgResource = context.assets.open(assetPath)
        val drawable: Drawable = BitmapDrawable(BitmapFactory.decodeStream(imgResource))
        show(context, text, drawable, duration)
    }

    var windowManager: WindowManager? = null
    var root: View? = null

    /**
     * @param text
     * @param imgResource
     * @param duration
     */

    fun show(
        context: Context,
        text: String,
        drawable: Drawable?,
        duration: Long = 15000
    ) {
        if (context == null) throw  NullPointerException("context must not be null")

        if (isShowing) {
            hide()
        }
        if (windowManager == null) {
            windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        }
        if (root == null) {
            root = initView(context)
        }

        val params = getWindowLayoutParams()
        windowManager!!.addView(root, params)

        titleTv?.text = text
        if (drawable == null) {
            iconIv?.visibility = View.GONE
        } else {
            iconIv?.setImageDrawable(drawable)
            iconIv?.visibility = View.VISIBLE
        }

        isShowing = true
        root?.postDelayed(
            {
                if (isShowing) {
                    root?.let {
                        windowManager?.removeView(root)
                    }
                    isShowing = false
                }
            },
            duration
        )
    }

    var isShowing = false

    private fun getWindowLayoutParams() = WindowManager.LayoutParams().apply {
        type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY
        flags =
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE //设置这个 window 不可点击，不会获取焦点，这样可以不干扰背后的 Activity 的交互。
        width = WindowManager.LayoutParams.WRAP_CONTENT
        height = WindowManager.LayoutParams.WRAP_CONTENT

        format = PixelFormat.TRANSLUCENT
        windowAnimations = android.R.style.Animation_Toast

        // 如果没有设置过位置，设置默认为bottom
        if (params != null) {
            gravity = params!!.gravity
            x = params!!.x
            y = params!!.y
        } else {
            setLocation(Location.BOTTOM)
            gravity = params!!.gravity
            x = params!!.x
            y = params!!.y
        }
    }

    /**
     * 设置显示位置
     */
    fun setLocation(location: Location): XNToast {
        params = WindowManager.LayoutParams()
        when (location) {
            Location.LEFT -> {

                params!!.gravity = Gravity.CENTER_VERTICAL or Gravity.LEFT
                params!!.x = MARGIN_EDGE
            }
            Location.TOP -> {

                params!!.gravity = Gravity.CENTER_HORIZONTAL or Gravity.TOP
                params!!.y = MARGIN_EDGE
            }
            Location.RIGHT -> {

                params!!.gravity = Gravity.CENTER_VERTICAL or Gravity.RIGHT
                params!!.x = MARGIN_EDGE
            }
            Location.BOTTOM -> {

                params!!.gravity = Gravity.CENTER_HORIZONTAL or Gravity.BOTTOM
                params!!.y = MARGIN_EDGE
            }
        }
        return this
    }

    fun hide() {
        if (!isShowing) {
            return
        }
        if (windowManager != null && root != null) {
            windowManager!!.removeView(root)
            isShowing = false
        }
    }

    enum class Location {
        LEFT, TOP, RIGHT, BOTTOM
    }
}