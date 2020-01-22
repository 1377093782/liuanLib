package com.lib.liuan;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.anguomob.lib.init.LiuAnUtils;
import com.anguomob.lib.utils.LogUtils;
import com.anguomob.lib.utils.OKHttpUtils;
import com.anguomob.lib.utils.SettingUtils;

import org.json.JSONObject;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    /**
     * 网络请求使用
     */
    private Button mAmUserOk;
    /**
     * 网络请求使用
     */
    private Button mAmFiveStar;
    private LinearLayout mMian;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        ButterKnife.bind(this);
        LiuAnUtils.init(this);

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

                LogUtils.e(isShowCloseAd + "");
            }

            if (jsonObj.has("clickNextTime")) {
                int clickNextTime = jsonObj.getInt("clickNextTime");
                setTitle(clickNextTime + "");
                LogUtils.e(clickNextTime + "");
            }

        } catch (Exception e) {
            e.printStackTrace();
            LogUtils.e(e.toString());
        }

    }

    private void initView() {
        mAmUserOk = (Button) findViewById(R.id.am_user_ok);
        mAmUserOk.setOnClickListener(this);
        mAmFiveStar = (Button) findViewById(R.id.am_five_star);
        mAmFiveStar.setOnClickListener(this);
        mMian = (LinearLayout) findViewById(R.id.mian);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.am_user_ok:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        showNetWork();
                    }
                }).start();                break;
            case R.id.am_five_star:
                SettingUtils.startGooglePlayByMarketUrl(this,"com.liuan.freereader");
                break;
        }
    }
}
