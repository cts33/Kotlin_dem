package com.lll.kotlin_dem.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import com.lll.kotlin_dem.R
import com.lll.kotlin_dem.adapter.KoubeiListAdpter
import com.lll.kotlin_dem.adapter.MotoAdpter
import com.lll.kotlin_dem.bean.KouBeiBean
import com.lll.kotlin_dem.bean.MotoBean
import com.lll.kotlin_dem.moto.Api
import com.lll.kotlin_dem.moto.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class KoubeiActivity : AppCompatActivity() {

    private lateinit var koubeiListAdapter: KoubeiListAdpter
    private lateinit var recycler: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_koubei)

        recycler = findViewById(R.id.koubei_recycler)

        recycler.layoutManager = LinearLayoutManager(this)
        koubeiListAdapter = KoubeiListAdpter(this)
        recycler.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        recycler.adapter = koubeiListAdapter


        initData()
    }

    val TAG = "KoubeiActivity"
    private fun initData() {

        val uid = intent.getIntExtra("uid", -1)

        Log.d(TAG, "initData: $uid")
        var motobean: Api = Retrofit.Builder()
            .baseUrl(Constants.baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Api::class.java)

        val motoList = motobean.getMotoKouBeiList(  uid)
        motoList.enqueue(object : Callback<KouBeiBean> {
            override fun onResponse(call: Call<KouBeiBean>, response: Response<KouBeiBean>) {
                val body = response.body()

                koubeiListAdapter.setListData(body!!.data!!)

            }

            override fun onFailure(call: Call<KouBeiBean>, t: Throwable) {


            }
        }

        )

    }
}