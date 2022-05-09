package com.lll.kotlin_dem.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lll.kotlin_dem.bean.DataItem
import com.lll.kotlin_dem.bean.KouBeiDataItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class KoubeiListVM: ViewModel() {

    private val TAG = "KoubeiListVM"
    val mutableMotoList = MutableLiveData<List<KouBeiDataItem>>()

    fun getMotoKouBeiList(uid:Int,page:Int) {
        GlobalScope.launch(Dispatchers.IO) {

            val koubeiList = ResponseTool.getMotoKouBeiList(uid, page)

            withContext(Dispatchers.Main) {

                if (koubeiList == null || koubeiList.code != 0) {
                    Log.d(TAG, "getMutableMotoList: error")
                } else {
                    mutableMotoList.postValue(koubeiList.data)
                }
            }
        }

    }
}