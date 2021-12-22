package com.lll.kotlin_dem.adapter

import android.content.Context
import android.util.Log
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.lll.kotlin_dem.R
import com.lll.kotlin_dem.bean.MotoImg

class ImageAdapter(private val context: Context, dataList: List<MotoImg>) :
    ImageBaseAdapter<MotoImg>(context, dataList) {


    override fun bindData(baseViewHolder: BaseViewHolder, moto: MotoImg) {

        val imageView = baseViewHolder.getViewById(R.id.image)
        Log.d("", "bindData: " + moto.imgOrgUrl)

        Glide.with(context).load(moto.imgOrgUrl).into(imageView as ImageView)
    }

    override fun getLayout(): Int {

        return R.layout.imags_list_item
    }
}