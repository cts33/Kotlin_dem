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
import com.lll.kotlin_dem.R
import com.lll.kotlin_dem.bean.KouBeiDataItem
import com.lll.kotlin_dem.utils.DateUtil
import com.lll.kotlin_dem.utils.ScreenUtil

class KoubeiListAdpter(private val mContext: Context) :
    RecyclerView.Adapter<KoubeiListAdpter.MyViewHolder>() {

    var mDatas: ArrayList<KouBeiDataItem> = ArrayList()

    fun setListData(list: List<KouBeiDataItem>?) {
        list?.let {
            mDatas.addAll(list)
            notifyDataSetChanged()
        }
    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var user_img: ImageView = view.findViewById(R.id.user_img)
        var name: TextView = view.findViewById(R.id.name)

        //        var create_time: TextView = view.findViewById(R.id.create_time)
        var advantage: TextView = view.findViewById(R.id.advantage)
        var shortcoming: TextView = view.findViewById(R.id.shortcoming)
        var gridview: GridView = view.findViewById(R.id.gridview)

    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): MyViewHolder {

        return MyViewHolder(
            LayoutInflater.from(mContext).inflate(R.layout.koubei_list_item, parent, false)
        )
    }

    override fun onBindViewHolder(
        viewHolder: MyViewHolder,
        @SuppressLint("RecyclerView") pos: Int
    ) {

        val listItem = mDatas[pos]

        Glide.with(mContext).load(listItem.userInfo.autherimg)
            .apply(RequestOptions.bitmapTransform(CircleCrop()))
            .into(viewHolder.user_img)
        viewHolder.advantage.text = "优点：" + listItem.satisfaction
        viewHolder.shortcoming.text = "缺点：" + listItem.notSatisfied
        val sex = if (listItem.userInfo.gender == 1) {
            "男"
        } else {
            "女"
        }

        viewHolder.name.text =
            listItem.userInfo.auther + "  " + sex + "\n" + "创建时间：" + DateUtil.formatDate(listItem.createTime)


        var layoutParams = viewHolder.gridview.layoutParams
        layoutParams = getRelativeLayoutParams(listItem.images.size)
        viewHolder.gridview.layoutParams = layoutParams




        viewHolder.gridview.adapter = object : BaseAdapter() {
            override fun getCount(): Int {
                return listItem.images.size
            }

            override fun getItem(position: Int): Any {

                return listItem.images[pos]
            }

            override fun getItemId(position: Int): Long {
                return position.toLong()
            }

            override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {

                val convertView =
                    LayoutInflater.from(mContext).inflate(R.layout.imags_list_item, null)

                val img = convertView as ImageView
                val imgUrl = listItem.images[position].imgOrgUrl
                Log.d(TAG, "getView: $imgUrl")
                Glide.with(mContext).load(imgUrl).into(img)
                return convertView
            }

        }

        fun getItemCount(): Int {
            return mDatas.size
        }
    }

    private fun getRelativeLayoutParams(size: Int): ViewGroup.LayoutParams? {

        return RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.MATCH_PARENT,
            getGridHeight(size, mContext)

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

