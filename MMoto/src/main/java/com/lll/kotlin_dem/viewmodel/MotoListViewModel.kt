package com.lll.kotlin_dem.viewmodel

import android.os.Looper
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.library.http.RetrofitManager
import com.lll.kotlin_dem.bean.DataItem
import com.lll.kotlin_dem.moto.Api
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * get moto列表数据
 * @description
 * @mail chentaishan@aliyun.com
 * @date 2022/5/7
 */
class MotoListViewModel : ViewModel() {
    private val TAG = "MotoListViewModel"
    val mutableMotoList = MutableLiveData<List<DataItem>>()

    fun getMutableMotoList(tabId: String) {
        GlobalScope.launch(Dispatchers.IO) {

            val motoList = ResponseTool.getMotoList(tabId)

            withContext(Dispatchers.Main) {

                if (motoList == null || motoList.code != 0) {
                    Log.d(TAG, "getMutableMotoList: error")
                } else {
                    mutableMotoList.postValue(motoList.data)
                }
            }
        }

    }
}