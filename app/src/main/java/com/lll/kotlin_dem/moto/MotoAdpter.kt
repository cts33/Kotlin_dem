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
import com.lll.kotlin_dem.R

class MotoAdpter(val mContext: Context) : Adapter<MotoAdpter.MyViewHolder>() {

     var mDatas:ArrayList< Datam> = ArrayList()

   fun setListData(  list:List< Datam>){

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

        val datam = mDatas[pos]
        viewHolder.title.setText(datam.brandName+"   "+datam.goodName)
        Glide.with(mContext).load(datam.goodPic).into(viewHolder.img)
    }

    override fun getItemCount(): Int {

        return mDatas.size
    }
}