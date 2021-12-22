package com.lll.kotlin_dem.adapter

import android.content.Context
import android.util.Log
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter

/**
 * decription:
 * author:chents
 * time:2021/12/22 09:34
 */
abstract class ImageBaseAdapter<T>(private val context: Context, private val dataList: List<T>) :
    BaseAdapter() {
    override fun getCount(): Int {
        return dataList.size
    }

    override fun getItem(position: Int): T {
        return dataList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    fun clearAll() {
        dataList?.let {
            it as MutableList
            it.clear()
        }
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {


        var baseViewHolder: BaseViewHolder? = BaseViewHolder()
        if (baseViewHolder?.convertView == null) {

            baseViewHolder?.convertView =
                LayoutInflater.from(context).inflate(getLayout(), null, false)
        }

        bindData(baseViewHolder!!, dataList[position])

        Log.d(TAG, "getView: pos="+position+"  converView="+baseViewHolder.convertView)

        return baseViewHolder?.convertView!!
    }

    private val TAG = "ImageBaseAdapter"
    abstract fun bindData(baseViewHolder: BaseViewHolder, t: T)

    abstract fun getLayout(): Int


    class BaseViewHolder() {

        private val viewList = SparseArray<View>()
        var convertView: View? = null

        fun getViewById(id: Int): View {
            var view = viewList[id]
            if (view == null) {
                view = convertView?.findViewById(id)
                viewList.append(id, view)
            }
            return view
        }
    }

}