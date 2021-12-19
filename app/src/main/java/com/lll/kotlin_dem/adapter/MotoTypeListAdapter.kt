package com.lll.kotlin_dem.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lll.kotlin_dem.bean.DataItem
import com.lll.kotlin_dem.R
import com.lll.kotlin_dem.moto.Constants
import com.lll.kotlin_dem.ui.KoubeiListActivity

class MotoTypeListAdapter(private val mContext: Context) : RecyclerView.Adapter<MotoTypeListAdapter.MyViewHolder>() {

    var mDatas: ArrayList<DataItem> = ArrayList()

    fun setListData(list: List<DataItem>) {


        mDatas.addAll(list)
        notifyDataSetChanged()
    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var title: TextView = view.findViewById(R.id.title)
        var img: ImageView = view.findViewById(R.id.image1)

    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyViewHolder {

        return MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item, p0, false))
    }

    override fun onBindViewHolder(viewHolder: MyViewHolder, pos: Int) {


        with(mDatas[pos]) {
            viewHolder.title.text = this.brandName + "   " + this.goodName

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
}