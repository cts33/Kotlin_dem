package com.lll.kotlin_dem;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainJavaActivity extends AppCompatActivity {

    private Button mClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.java_activity);
        initViews();


    }

    private static final String TAG = "MainJavaActivity";
    private void initViews() {
        mClick = findViewById(R.id.click);
        mClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                startActivity(new Intent(MainJavaActivity.this,MainActivity.class));
                User user = new  User("dd",33);



                Log.d(TAG, "onClick: "+user.component1());
            }
        });
    }
}
