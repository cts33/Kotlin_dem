package com.lll.kotlin_dem.moto

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.lll.kotlin_dem.MotoBean
import com.lll.kotlin_dem.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    lateinit var recycler: RecyclerView
    lateinit var motoAdpter: MotoAdpter;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycler = findViewById<RecyclerView>(R.id.recycler)

        recycler.layoutManager = LinearLayoutManager(this)
        motoAdpter = MotoAdpter(this)
        recycler.addItemDecoration(DividerItemDecoration(this,DividerItemDecoration.VERTICAL))
        recycler.adapter = motoAdpter


        initData()
    }

    private fun initData() {

       var motobean:Api =   Retrofit.Builder()
            .baseUrl(Constants.koubeiUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Api::class.java)

        val motoList = motobean.getMotoList(9)
        motoList.enqueue(object: Callback<MotoBean>{
            override fun onResponse(call: Call<MotoBean>, response: Response<MotoBean>) {
                val body = response.body()

                motoAdpter.setListData(body!!.data!!)

            }

            override fun onFailure(call: Call<MotoBean>, t: Throwable) {
                TODO("Not yet implemented")

            }
        }

        )

    }
}