package com.example.testoverlaypermission

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)


        findViewById<Button>(R.id.click).setOnClickListener {
            val intent = Intent()
            intent.putExtra("data", "this is second Activity")

            setResult(44, intent)
            finish()
        }
    }
}