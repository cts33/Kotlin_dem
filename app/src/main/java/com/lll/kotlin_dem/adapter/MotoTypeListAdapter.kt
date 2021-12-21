package com.lll.kotlin_dem.adapter

import android.content.Context
import android.content.Intent
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.style.RelativeSizeSpan
import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lll.kotlin_dem.bean.DataItem
import com.lll.kotlin_dem.R
import com.lll.kotlin_dem.utils.Constants
import com.lll.kotlin_dem.ui.KoubeiListActivity

class MotoTypeListAdapter(private val mContext: Context) :
    RecyclerView.Adapter<MotoTypeListAdapter.MyViewHolder>() {
    var mDatas: ArrayList<DataItem> = ArrayList()
    fun setListData(list: List<DataItem>) {
        mDatas.addAll(list)
        notifyDataSetChanged()
    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var number: TextView = view.findViewById(R.id.number)
        var title: TextView = view.findViewById(R.id.title)
        var img: ImageView = view.findViewById(R.id.image1)

    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyViewHolder {

        return MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item, p0, false))
    }

    override fun onBindViewHolder(viewHolder: MyViewHolder, pos: Int) {


        with(mDatas[pos]) {
//            viewHolder.number.text = (pos+1).toString()

            setTextAutoSize(viewHolder.number, (pos + 1).toString())

            viewHolder.title.text = this.brandName + "   " + this.goodName + "\n" + this.grade

            Log.d(TAG, "onBindViewHolder:goodPic: " + this.goodPic)

            Glide.with(mContext).load(this.goodPic).into(viewHolder.img)

            viewHolder.itemView.setOnClickListener {

                val intent = Intent(mContext, KoubeiListActivity::class.java)
                intent.putExtra(Constants.uid, this.goodId)
                mContext.startActivity(intent)
            }
        }
    }

    val TAG = "MotoAdpter"

    override fun getItemCount(): Int {
        return mDatas.size
    }

    /**
     * @param textView 根据 固定宽度 设置字体大小
     * @param text 显示文本
     */
    private fun setTextAutoSize(textView: TextView, text: String) {
        //设置textView固定宽度
        val layoutParams = textView.layoutParams
        layoutParams.width = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            20f,
            textView.context.resources.displayMetrics
        ).toInt() //必须明确 TextView的宽度（注意：实际宽度跟所在布局有关系）
        textView.layoutParams = layoutParams

        //获取控件的可绘制的宽度，加上由于精度的偏差，取控件的比例宽度
        val drawWidth = (layoutParams.width - textView.paddingLeft - textView.paddingRight) * 0.98f
        //获取文本内容需要的宽度
        val defTextWidth: Float = textView.paint.measureText(text)

        val proportion = drawWidth / defTextWidth //计算 字体大小比例
        val spannableString = SpannableString(text)
        spannableString.setSpan(
            RelativeSizeSpan(proportion),
            0, text.length, Spanned.SPAN_INCLUSIVE_INCLUSIVE
        )
        textView.text = spannableString
    }

}