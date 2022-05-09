package com.lll.kotlin_dem.viewmodel

import android.os.Looper
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lll.kotlin_dem.bean.DataItem
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

            Log.d(TAG, "initData1: main thread =${Looper.getMainLooper() == Looper.myLooper()}")
            val motoList = NetMangager.apiService.getMotoList(tabId)

            withContext(Dispatchers.Main) {
                Log.d(TAG, "initData2: main thread =${Looper.getMainLooper() == Looper.myLooper()}")
                if (motoList.code == 0) {
                    mutableMotoList.postValue(motoList.data)
                } else {
                    Log.d(TAG, "getMutableMotoList: failed")
                }
            }
        }

    }
}