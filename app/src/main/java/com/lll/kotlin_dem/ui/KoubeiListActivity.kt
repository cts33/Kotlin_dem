package com.lll.kotlin_dem.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.library.LoadingLayout
import com.lll.kotlin_dem.R
import com.lll.kotlin_dem.adapter.KoubeiListAdpter
import com.lll.kotlin_dem.bean.KouBeiDataItem
import com.lll.kotlin_dem.bean.ResponseResult
import com.lll.kotlin_dem.utils.Constants.uid
import com.lll.kotlin_dem.moto.NetMangager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class KoubeiListActivity : AppCompatActivity() {

    private lateinit var koubeiListAdapter: KoubeiListAdpter
    private lateinit var recycler: RecyclerView
    private lateinit var loadingLayout: LoadingLayout

    lateinit var context: Context
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_koubei)
        context = this;
        recycler = findViewById(R.id.koubei_recycler)
        loadingLayout = findViewById(R.id.loadingLayout)

        recycler.apply {
            layoutManager = LinearLayoutManager(context)
            koubeiListAdapter = KoubeiListAdpter(context)
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            adapter = koubeiListAdapter
        }
        loadingLayout.showLoading()
        initData()
    }

    val TAG = "KoubeiActivity"
    private fun initData() {

        val uid = intent.getIntExtra(uid, -1)

        val motoList = NetMangager.apiService.getMotoKouBeiList(uid, 1)
        motoList.enqueue(object : Callback<ResponseResult<KouBeiDataItem>> {
            override fun onResponse(
                call: Call<ResponseResult<KouBeiDataItem>>,
                response: Response<ResponseResult<KouBeiDataItem>>
            ) {
                val body = response.body()

                Log.d(TAG, "onResponse: $body")
                koubeiListAdapter.setListData(body?.data)
                loadingLayout.showLoadSuccess()
            }

            override fun onFailure(call: Call<ResponseResult<KouBeiDataItem>>, t: Throwable) {

                Log.d(TAG, "onFailure: " + t.message)
                loadingLayout.showLoadFailed()

            }
        }
        )

    }
}