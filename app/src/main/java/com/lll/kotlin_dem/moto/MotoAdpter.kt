package com.lll.kotlin_dem.moto

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.Adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.lll.kotlin_dem.DataItem
import com.lll.kotlin_dem.R

class MotoAdpter(val mContext: Context) : Adapter<MotoAdpter.MyViewHolder>() {

     var mDatas:ArrayList<DataItem> = ArrayList()

   fun setListData(  list:List< DataItem>){

       if (list==null){
           return
       }
       mDatas.addAll(list)
       notifyDataSetChanged()
   }

    class MyViewHolder(view:View): RecyclerView.ViewHolder(view) {

        var title: TextView
        var img:ImageView
        init {
            title = view.findViewById(R.id.title)
            img = view.findViewById(R.id.image1)
        }
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyViewHolder {

        return MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item,p0,false))
    }

    override fun onBindViewHolder(viewHolder: MyViewHolder, pos: Int) {

        val DataItem = mDatas[pos]
        viewHolder.title.setText(DataItem.brandName+"   "+DataItem.goodName)
        Glide.with(mContext).load(DataItem.goodPic).into(viewHolder.img)
    }

    override fun getItemCount(): Int {

        return mDatas.size
    }
}