package com.example.testoverlaypermission

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    private val PermissionRequestCode = 11111
    private var activityResultLauncher: ActivityResultLauncher<Intent>? = null
    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        activityResultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult(), object :
                ActivityResultCallback<ActivityResult> {
                override fun onActivityResult(result: ActivityResult?) {
                    //接收回调，更新状态
                    updateStatus()
                }
            })
        findViewById<Button>(R.id.click).setOnClickListener {

            val intent = Intent()
            intent.action = Settings.ACTION_MANAGE_OVERLAY_PERMISSION
            intent.data = Uri.parse("package:$packageName");
            activityResultLauncher?.launch(intent)
        }
    }

    private fun updateStatus() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val canDrawOverlays = Settings.canDrawOverlays(this)
            Log.d(TAG, "onCreate:canDrawOverlays=$canDrawOverlays ")
            findViewById<TextView>(R.id.status).text = "是否可悬浮在其他app上：${canDrawOverlays.toString()}"
        }
    }

}