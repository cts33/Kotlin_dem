package com.lll.kotlin_dem.adapter

import android.content.Context
import android.content.Intent
import android.text.SpannableString
import android.text.Spanned
import android.text.style.RelativeSizeSpan
import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lll.kotlin_dem.R
import com.lll.kotlin_dem.bean.DataItem
import com.lll.kotlin_dem.databinding.ItemBinding
import com.lll.kotlin_dem.ui.KoubeiListActivity
import com.lll.kotlin_dem.utils.Constants

private const val TAG = "MotoTypeListAdapter"

class MotoTypeListAdapter(private val mContext: Context) :
    RecyclerView.Adapter<MotoTypeListAdapter.MyViewHolder>() {
    private val mDatas = mutableListOf<DataItem>()

    fun setListData(list: List<DataItem>) {
        mDatas.clear()
        mDatas.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyViewHolder {

        val viewBinding = ItemBinding.inflate(LayoutInflater.from(mContext))
        return MyViewHolder(viewBinding)
    }

    override fun onBindViewHolder(viewHolder: MyViewHolder, pos: Int) {


        with(mDatas[pos]) {

            viewHolder.number.text = " ${(pos + 1)} "

            viewHolder.title.text =  this.brandName + "   " + this.goodName + "\n" + this.grade

            Glide.with(mContext).asBitmap().load(this.goodPic).into(viewHolder.imageView)

            viewHolder.viewBinding.root.setOnClickListener {

                val intent = Intent(mContext, KoubeiListActivity::class.java)
                intent.putExtra(Constants.uid, this.goodId)
                mContext.startActivity(intent)
            }
        }
    }


    override fun getItemCount(): Int {
        return mDatas.size
    }


    class MyViewHolder(val viewBinding: ItemBinding) : RecyclerView.ViewHolder(viewBinding.root) {
        val imageView = viewBinding.image1
        val number = viewBinding.number
        val title = viewBinding.title
    }

}