package com.lll.kotlin_dem.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import com.example.library.GridViewLayout
import com.lll.kotlin_dem.R
import com.lll.kotlin_dem.bean.KouBeiDataItem
import com.lll.kotlin_dem.bean.MotoImg
import com.lll.kotlin_dem.utils.DateUtil
import com.lll.kotlin_dem.utils.ScreenUtil
import com.lll.kotlin_dem.view.MyGridView

class KoubeiListAdpter(private val mContext: Context) :
    RecyclerView.Adapter<KoubeiListAdpter.MyViewHolder>() {

    var mDatas: ArrayList<KouBeiDataItem> = ArrayList()

    val heightMap = HashMap<Int, Int>()
    fun setListData(list: List<KouBeiDataItem>?) {
        list?.let {
            mDatas.addAll(list)
            notifyDataSetChanged()
        }
    }

    class MyViewHolder(
        val itemView: View
    ) : RecyclerView.ViewHolder(itemView) {
        var userImg: ImageView = itemView.findViewById(R.id.user_img)
        var name: TextView = itemView.findViewById(R.id.name)
        var advantage: TextView = itemView.findViewById(R.id.advantage)
        var shortcoming: TextView = itemView.findViewById(R.id.shortcoming)
        var myGridView: GridViewLayout<MotoImg> = itemView.findViewById(R.id.gridview)


    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): MyViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.koubei_list_item, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(
        viewHolder: MyViewHolder,
        @SuppressLint("RecyclerView") pos: Int
    ) {

        val listItem = mDatas[pos]

        Glide.with(mContext).load(listItem.userInfo.autherimg)
            .apply(RequestOptions.bitmapTransform(CircleCrop()))
            .into(viewHolder.userImg)
        viewHolder.advantage.text = "优点：" + listItem.satisfaction
        viewHolder.shortcoming.text = "缺点：" + listItem.notSatisfied
        val sex = if (listItem.userInfo.gender == 1) {
            "男"
        } else {
            "女"
        }

        viewHolder.name.text =
            listItem.userInfo.auther + "  " + sex + "\n" + "创建时间：" + DateUtil.formatDate(listItem.createTime)


//        var layoutParams: RelativeLayout.LayoutParams? = null
//        val height = heightMap[pos]
//        if (height == null) {
//            heightMap[pos] = getGridHeight(listItem.images.size, mContext)
//        }
//        layoutParams = getRelativeLayoutParams(heightMap[pos]!!)
//        viewHolder.myGridView.layoutParams = layoutParams

        viewHolder.myGridView.setDataList(listItem.images){ it,image ->

            Glide.with(mContext).load(it.imgOrgUrl).into(image)
        }

    }


    private fun getRelativeLayoutParams(height: Int): RelativeLayout.LayoutParams? {

        return RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.MATCH_PARENT,
            height

        ).apply {
            addRule(RelativeLayout.BELOW, R.id.shortcoming)
        }
    }

    override fun getItemCount(): Int {
        return mDatas.size
    }
}


private const val TAG = "KoubeiListAdpter"

private fun getGridHeight(size: Int, mContext: Context): Int {
    val itemHeight = ScreenUtil.getScreenWidth(mContext) / 3
    var height = 0
    if (size % 3 == 0) {
        height = itemHeight * (size / 3)
    } else if (size > 3) {
        height = (size / 3 + 1) * itemHeight
    } else {
        height = itemHeight
    }

    Log.d(TAG, "getGridHeight:height=${height} size=${size}")
    return height
}

