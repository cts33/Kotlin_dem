package com.lll.kotlin_dem.ui


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.library.LoadingLayout
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.lll.kotlin_dem.R
import com.lll.kotlin_dem.TabFragment
import com.lll.kotlin_dem.adapter.MotoTypeListAdapter
import com.lll.kotlin_dem.moto.Constants

class MainActivity : AppCompatActivity() {
    lateinit var recycler: RecyclerView
    lateinit var motoTypeListAdapter: MotoTypeListAdapter;
    private lateinit var viewpager2: ViewPager2
    private lateinit var tablayout: TabLayout
    private lateinit var loadingLayout: LoadingLayout

    private fun initViews() {
        tablayout = findViewById(R.id.tablayout)
        viewpager2 = findViewById(R.id.viewpager2)
        loadingLayout = findViewById(R.id.loadingLayout)

        viewpager2.adapter = object : FragmentStateAdapter(this) {
            override fun createFragment(position: Int): Fragment {
                var tabId = Constants.tabs[position].tabId
                return TabFragment.create(tabId!!)
            }

            override fun getItemCount(): Int {
                return Constants.tabs.size
            }
        }

        TabLayoutMediator(tablayout, viewpager2) { tab, position ->
            tab.text = Constants.tabs[position].name
        }.attach()


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        loadingLayout.showLoading()

    }


    fun showFragment() {

        loadingLayout.showLoadSuccess()
    }
}