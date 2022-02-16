package com.lll.kotlin_dem.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

var TAG: String? = null

abstract class BaseFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        TAG = this::class.java.simpleName
        val viewBinding = getViewBinding()
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView(view)
        initData()
    }

    abstract fun initView(view: View)

    abstract fun initData()

    abstract fun getViewBinding(): ViewBinding


}