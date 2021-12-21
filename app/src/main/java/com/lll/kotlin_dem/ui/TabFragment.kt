package com.lll.kotlin_dem.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.DividerItemDecoration.VERTICAL
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lll.kotlin_dem.R
import com.lll.kotlin_dem.adapter.MotoTypeListAdapter
import com.lll.kotlin_dem.bean.MotoBean
import com.lll.kotlin_dem.moto.Api
import com.lll.kotlin_dem.utils.Constants
import com.lll.kotlin_dem.utils.Constants.typeId
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * @description
 * @mail chentaishan@noboauto.com
 * @date 2021/12/17
 */
class TabFragment : Fragment() {
    private lateinit var motoTypeListAdapter: MotoTypeListAdapter

    companion object {
        fun create(tabId: String): TabFragment {
            val tabFragment = TabFragment()
            val bundle = Bundle()
            bundle.putString(typeId, tabId)
            tabFragment.arguments = bundle
            return tabFragment
        }
    }


    private lateinit var recyclerview: RecyclerView

    private fun initViews(view: View) {
        recyclerview = view.findViewById(R.id.recyclerview)
        recyclerview.layoutManager = LinearLayoutManager(activity)

        recyclerview.addItemDecoration(DividerItemDecoration(activity, VERTICAL))
        motoTypeListAdapter = MotoTypeListAdapter(activity!!)
        recyclerview.adapter = motoTypeListAdapter
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return View.inflate(activity, R.layout.tab_fragment, null)
    }

    private var tabId = ""
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initViews(view!!)

        tabId = arguments?.getString(typeId) ?: ""

        initData()
    }

    private fun initData() {

        var motobean: Api = Retrofit.Builder()
            .baseUrl(Constants.baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Api::class.java)

        val motoList = motobean.getMotoList(tabId)
        motoList.enqueue(object : Callback<MotoBean> {
            override fun onResponse(call: Call<MotoBean>, response: Response<MotoBean>) {
                val body = response.body()
                Log.d(TAG, "onResponse: " + body)
                motoTypeListAdapter.setListData(body!!.data!!)

                (activity as MainActivity).showFragment()
            }

            override fun onFailure(call: Call<MotoBean>, t: Throwable) {
                Log.d(TAG, "onFailure: " + t.message)
            }
        }
        )

    }

    private val TAG = "TabFragment"


}