package com.lll.kotlin_dem.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.DividerItemDecoration.VERTICAL
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lll.kotlin_dem.adapter.MotoTypeListAdapter
import com.lll.kotlin_dem.base.BaseFragment
import com.lll.kotlin_dem.databinding.TabFragmentBinding
import com.lll.kotlin_dem.moto.Api
import com.lll.kotlin_dem.utils.Constants
import com.lll.kotlin_dem.utils.Constants.typeId
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val TAG = "TabFragment"

/**
 * @description
 * @mail chentaishan@noboauto.com
 * @date 2021/12/17
 */
class TabFragment : BaseFragment() {

    private lateinit var motoTypeListAdapter: MotoTypeListAdapter
    private lateinit var recyclerview: RecyclerView
    private var tabId = ""

    override fun initView() {

        motoTypeListAdapter = MotoTypeListAdapter(activity as Context)

        with(getViewBinding().recyclerview) {

            this.layoutManager = LinearLayoutManager(activity)
            this.addItemDecoration(DividerItemDecoration(activity, VERTICAL))

            this.adapter = motoTypeListAdapter
        }

    }

    override fun initData() {
        tabId = arguments?.getString(typeId) ?: ""

        GlobalScope.launch {
            var motobean: Api = Retrofit.Builder()
                .baseUrl(Constants.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(Api::class.java)
            val motoList = motobean.getMotoList(tabId)
            Log.d(TAG, "initData: $motoList")
            withContext(Dispatchers.Main) {
                if (motoList.code == 0) {
                    motoTypeListAdapter.setListData(motoList!!.data!!)
                    (activity as MainActivity).showFragment()
                } else {
                    (activity as MainActivity).showFailed()
                }
            }
        }
    }

    override fun getViewBinding(): TabFragmentBinding = TabFragmentBinding.inflate(layoutInflater)

    companion object {
        fun newInstance(tabId: String): TabFragment {
            val tabFragment = TabFragment()
            val bundle = Bundle()
            bundle.putString(typeId, tabId)
            tabFragment.arguments = bundle
            return tabFragment
        }
    }

}



