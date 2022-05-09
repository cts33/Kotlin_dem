package com.lll.kotlin_dem.ui

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.DividerItemDecoration.VERTICAL
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lll.kotlin_dem.adapter.MotoTypeListAdapter
import com.lll.kotlin_dem.base.BaseFragment
import com.lll.kotlin_dem.databinding.TabFragmentBinding
import com.lll.kotlin_dem.utils.Constants.typeId
import com.lll.kotlin_dem.viewmodel.MotoListViewModel

private const val TAG = "TabFragment"

/**
 * @description
 * @mail chentaishan@noboauto.com
 * @date 2021/12/17
 */
class TabFragment : BaseFragment() {

    private lateinit var motoTypeListAdapter: MotoTypeListAdapter
    private var tabId = ""
    private lateinit var motoListViewModel: MotoListViewModel
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

        motoListViewModel = ViewModelProviders.of(this).get(MotoListViewModel::class.java)
        motoListViewModel.mutableMotoList.observe(this.viewLifecycleOwner) {
            motoTypeListAdapter.setListData(it)
            (activity as MainActivity).showFragment()
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



