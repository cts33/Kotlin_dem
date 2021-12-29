package com.example.mytoast

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.mytoast.R
import com.example.mytoast.XNToast

class MainActivity : AppCompatActivity() {
    private var mClick: Button? = null
    private var mClick1: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }

    private fun initViews() {
        mClick = findViewById(R.id.click)
        mClick!!.setOnClickListener(View.OnClickListener { //                XNToast.INSTANCE.show(MainActivity.this,"HHHH",getResources().getDrawable(R.drawable.toast_error));
            XNToast.show(this@MainActivity, "HHHH", resources.getDrawable(R.drawable.toast_error))
        })

        mClick1 = findViewById(R.id.click1)
        mClick1!!.setOnClickListener(View.OnClickListener { //                XNToast.INSTANCE.show(MainActivity.this,"HHHH",getResources().getDrawable(R.drawable.toast_error));
            XNToast.hide()
        })
    }
}