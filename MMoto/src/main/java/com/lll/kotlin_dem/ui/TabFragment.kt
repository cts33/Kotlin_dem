package com.lll.kotlin_dem.ui

import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.DividerItemDecoration.VERTICAL
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lll.kotlin_dem.adapter.MotoTypeListAdapter
import com.lll.kotlin_dem.base.BaseFragment
import com.lll.kotlin_dem.databinding.TabFragmentBinding
import com.lll.kotlin_dem.moto.NetMangager
import com.lll.kotlin_dem.utils.Constants.typeId
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private const val TAG = "TabFragment"

/**
 * @description
 * @mail chentaishan@noboauto.com
 * @date 2021/12/17
 */
class TabFragment : BaseFragment() {

    private lateinit var motoTypeListAdapter: MotoTypeListAdapter
    private var tabId = ""

    override fun getViewBinding(): TabFragmentBinding = TabFragmentBinding.inflate(layoutInflater)

    override fun initView(view: View) {

        view as RecyclerView
        motoTypeListAdapter = MotoTypeListAdapter(requireActivity())

        view.layoutManager = LinearLayoutManager(activity)
        view.addItemDecoration(DividerItemDecoration(activity, VERTICAL))

        view.adapter = motoTypeListAdapter
    }

    override fun initData() {
        tabId = arguments?.getString(typeId) ?: ""

        GlobalScope.launch(Dispatchers.IO) {

            Log.d(TAG, "initData1: main thread =${Looper.getMainLooper() == Looper.myLooper()}")
            val motoList = NetMangager.apiService.getMotoList(tabId)
            Log.d(TAG, "initData: $motoList")
            withContext(Dispatchers.Main) {
                Log.d(TAG, "initData2: main thread =${Looper.getMainLooper() == Looper.myLooper()}")
                if (motoList.code == 0) {

                    motoTypeListAdapter.setListData(motoList!!.data!!)
                    (activity as MainActivity).showFragment()
                } else {
                    (activity as MainActivity).showFailed()
                }
            }
        }
    }

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



