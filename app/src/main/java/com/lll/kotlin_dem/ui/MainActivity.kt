package com.lll.kotlin_dem.ui


import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.lll.kotlin_dem.base.BaseActivity
import com.lll.kotlin_dem.databinding.ActivityMainBinding
import com.lll.kotlin_dem.utils.Constants

class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun bindingView(): View {
        binding = ActivityMainBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun initView() {

        binding.viewpager2.adapter = object : FragmentStateAdapter(this) {
            override fun createFragment(position: Int): Fragment {
                var tabId = Constants.tabs[position].tabId
                return TabFragment.create(tabId!!)
            }

            override fun getItemCount(): Int {
                return Constants.tabs.size
            }
        }

        TabLayoutMediator(binding.tablayout, binding.viewpager2) { tab, position ->
            tab.text = Constants.tabs[position].name
        }.attach()

        binding.loadingLayout.showLoading()
    }
    fun showFragment() {
        binding.loadingLayout.showLoadSuccess()
    }
    fun showFailed() {
        binding.loadingLayout.showLoadFailed()
    }

}