package com.lll.kotlin_dem.ui


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.lll.kotlin_dem.bean.MotoBean
import com.lll.kotlin_dem.R
import com.lll.kotlin_dem.moto.Api
import com.lll.kotlin_dem.moto.Constants
import com.lll.kotlin_dem.adapter.MotoAdpter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    lateinit var recycler: RecyclerView
    lateinit var motoAdpter: MotoAdpter;
    private lateinit var viewpager2: ViewPager2
    private lateinit var tabs: TabLayout

    private fun initViews() {
        tabs = findViewById(R.id.tabs)
        viewpager2 = findViewById(R.id.viewpager2)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()

//        recycler = findViewById<RecyclerView>(R.id.recycler)
//
//
//        recycler.layoutManager = LinearLayoutManager(this)
//        motoAdpter = MotoAdpter(this)
//        recycler.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
//        recycler.adapter = motoAdpter
//
//
//        initData()
    }

    private fun initData() {

        var motobean: Api = Retrofit.Builder()
            .baseUrl(Constants.baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Api::class.java)

        val motoList = motobean.getMotoList("")
        motoList.enqueue(object : Callback<MotoBean> {
            override fun onResponse(call: Call<MotoBean>, response: Response<MotoBean>) {
                val body = response.body()

                motoAdpter.setListData(body!!.data!!)

            }

            override fun onFailure(call: Call<MotoBean>, t: Throwable) {


            }
        }

        )

    }
}