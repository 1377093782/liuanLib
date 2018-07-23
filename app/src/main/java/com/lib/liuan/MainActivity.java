package com.lib.liuan;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.liuan.lib.liuanlibrary.utils.AesUtil;
import com.liuan.lib.liuanlibrary.utils.LiuAnUtils;
import com.liuan.lib.liuanlibrary.utils.LogUtils;
import com.liuan.lib.liuanlibrary.utils.OSUtils;
import com.liuan.lib.liuanlibrary.utils.ToastUtil;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LiuAnUtils.init(this,"");

        findViewById(R.id.tv_test).setOnClickListener(new View.OnClickListener() {
            int i = 0;

            @Override
            public void onClick(View view) {
                ToastUtil.showToast(MainActivity.this, "" + (--i));
            }
        });
//获取手机是不是MIUI  MIUI版本  目前兼容华为  小米 魅族 可能新手机检测的不是很准确

        String type = OSUtils.getRomType().toString();
        //ROM 版本号
        String systemProperty = OSUtils.getSystemProperty();


    }
}
