package com.lll.kotlin_dem.ui

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.library.LoadingLayout
import com.lll.kotlin_dem.adapter.KoubeiListAdpter
import com.lll.kotlin_dem.bean.KouBeiDataItem
import com.lll.kotlin_dem.bean.ResponseResult
import com.lll.kotlin_dem.utils.Constants.uid
import com.lll.kotlin_dem.moto.NetMangager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.widget.Toast

import byc.imagewatcher.ImageWatcher

import android.view.View
import android.widget.ImageView
import byc.imagewatcher.ImageWatcher.OnStateChangedListener
import byc.imagewatcher.ImageWatcher.generateViewId
import com.bumptech.glide.Glide
import com.lll.kotlin_dem.R
import android.graphics.drawable.Drawable

import androidx.annotation.NonNull
import androidx.annotation.Nullable

import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition


class KoubeiListActivity : AppCompatActivity() {

    private lateinit var koubeiListAdapter: KoubeiListAdpter
    private lateinit var recycler: RecyclerView
    private lateinit var loadingLayout: LoadingLayout
    private var vImageWatcher: ImageWatcher? = null
    lateinit var context: Context
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_koubei)
        context = this;
        recycler = findViewById(R.id.koubei_recycler)
        loadingLayout = findViewById(R.id.loadingLayout)
        vImageWatcher = findViewById<ImageWatcher>(R.id.image_watcher)


        recycler.apply {
            layoutManager = LinearLayoutManager(context)
            koubeiListAdapter = KoubeiListAdpter(context, vImageWatcher!!)
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            adapter = koubeiListAdapter
        }

        vImageWatcher!!.setErrorImageRes(R.mipmap.error_picture)
        // 长按图片的回调，你可以显示一个框继续提供一些复制，发送等功能
//        vImageWatcher!!.setOnPictureLongPressListener(this)
        vImageWatcher!!.setLoader(GlideSimpleLoader())
        vImageWatcher!!.setOnStateChangedListener(object : OnStateChangedListener {
            override fun onStateChangeUpdate(
                imageWatcher: ImageWatcher?,
                clicked: ImageView?,
                position: Int,
                uri: Uri,
                animatedValue: Float,
                actionTag: Int
            ) {
                Log.e("IW", "onStateChangeUpdate [$position][$uri][$animatedValue][$actionTag]")
            }

            override fun onStateChanged(
                imageWatcher: ImageWatcher?,
                position: Int,
                uri: Uri,
                actionTag: Int
            ) {
//                if (actionTag == ImageWatcher.STATE_ENTER_DISPLAYING) {
//                    Toast.makeText(applicationContext, "点击了图片 [$position]$uri", Toast.LENGTH_SHORT)
//                        .show()
//                } else if (actionTag == ImageWatcher.STATE_EXIT_HIDING) {
//                    Toast.makeText(applicationContext, "退出了查看大图", Toast.LENGTH_SHORT).show()
//                }
            }
        })


        loadingLayout.showLoading()
        initData()
    }

    class GlideSimpleLoader : ImageWatcher.Loader {
        override fun load(context: Context, uri: Uri, lc: ImageWatcher.LoadCallback?) {
            Glide.with(context).load(uri)
                .into(object : SimpleTarget<Drawable?>() {
                    override fun onResourceReady(
                        resource: Drawable,
                        @Nullable transition: Transition<in Drawable?>?
                    ) {
                        lc!!.onResourceReady(resource)
                    }

                    override fun onLoadFailed(@Nullable errorDrawable: Drawable?) {
                        lc!!.onLoadFailed(errorDrawable)
                    }

                    override fun onLoadStarted(@Nullable placeholder: Drawable?) {
                        lc!!.onLoadStarted(placeholder)
                    }
                })
        }

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