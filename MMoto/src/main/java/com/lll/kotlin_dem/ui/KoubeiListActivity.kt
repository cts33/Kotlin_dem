package com.lll.kotlin_dem.ui

import android.content.Context
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import byc.imagewatcher.ImageWatcher
import byc.imagewatcher.ImageWatcher.OnStateChangedListener
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.example.library.http.ResponseResult
import com.example.library.http.RetrofitManager
import com.example.library.views.LoadingLayout
import com.lll.kotlin_dem.R
import com.lll.kotlin_dem.adapter.KoubeiListAdpter
import com.lll.kotlin_dem.bean.KouBeiDataItem
import com.lll.kotlin_dem.moto.Api
import com.lll.kotlin_dem.utils.Constants
import com.lll.kotlin_dem.utils.Constants.uid
import com.lll.kotlin_dem.viewmodel.KoubeiListVM
import com.lll.kotlin_dem.viewmodel.MotoListViewModel
import com.lll.kotlin_dem.viewmodel.ResponseTool
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


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
        // ??????????????????????????????????????????????????????????????????????????????????????????
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
//                    Toast.makeText(applicationContext, "??????????????? [$position]$uri", Toast.LENGTH_SHORT)
//                        .show()
//                } else if (actionTag == ImageWatcher.STATE_EXIT_HIDING) {
//                    Toast.makeText(applicationContext, "?????????????????????", Toast.LENGTH_SHORT).show()
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
    private   fun initData() {

        val uid = intent.getIntExtra(uid, -1)

        val koubeiList = ViewModelProvider(this).get(KoubeiListVM::class.java)
        koubeiList.mutableMotoList.observe(this){
            if (it == null) {
                Log.d(TAG, "onFailure: ")
                loadingLayout.showLoadFailed()
            } else {
                koubeiListAdapter.setListData(it)

                loadingLayout.showLoadSuccess()
            }
        }

        koubeiList.getMotoKouBeiList(uid,1)

    }
}