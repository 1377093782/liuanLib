package com.lib.liuan;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.liuan.lib.liuanlibrary.utils.ToastUtil;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.tv_test).setOnClickListener(new View.OnClickListener() {
             int i=0;

            @Override
            public void onClick(View view) {
                ToastUtil.showToast(MainActivity.this,""+(--i));
            }
        });
    }
}
