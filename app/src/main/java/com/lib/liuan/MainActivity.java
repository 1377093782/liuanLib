package com.lib.liuan;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.LinearLayout;

import com.liuan.lib.liuanlibrary.init.LiuAnUtils;
import com.liuan.lib.liuanlibrary.utils.LogUtils;
import com.liuan.lib.liuanlibrary.utils.OKHttpUtils;
import com.liuan.lib.liuanlibrary.utils.ToastUtil;

import org.json.JSONObject;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.am_user_ok)
    Button amUserOk;
    @BindView(R.id.mian)
    LinearLayout mian;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        LiuAnUtils.init(this);


    }


    @OnClick(R.id.am_user_ok)
    public void onViewClicked() {
//        {
//            "isShowCloseAd": true,
//                "clickNextTime": 40
//        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                showNetWork();
            }
        }).start();
    }

    private void showNetWork() {
        String httpUrl = "http://www.wanandroid.com/tools/mockapi/11904/freereader";

        try {
            String res = OKHttpUtils.loadStringFromUrl(httpUrl);
            if (TextUtils.isEmpty(res)) {
                return;
            }
            JSONObject jsonObj = new JSONObject(res);
            if (jsonObj.has("isShowCloseAd")) {
                boolean isShowCloseAd = jsonObj.getBoolean("isShowCloseAd");

                LogUtils.e(isShowCloseAd+"");
            }

            if (jsonObj.has("clickNextTime")) {
                int clickNextTime = jsonObj.getInt("clickNextTime");
                setTitle(clickNextTime+"");
                LogUtils.e(clickNextTime+"");
            }

        } catch (Exception e) {
            e.printStackTrace();
            LogUtils.e(e.toString());
        }

    }
}
