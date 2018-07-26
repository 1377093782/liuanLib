package com.lib.liuan;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.liuan.lib.liuanlibrary.utils.LiuAnUtils;
import com.liuan.lib.liuanlibrary.utils.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.iv)
    ImageView iv;
    @BindView(R.id.iv2)
    ImageView iv2;
    @BindView(R.id.mian)
    LinearLayout mian;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        LiuAnUtils.init(this, "586e250999f0c70cdb000ef8");


    }


    private void initClick() {
//        GlideUtils.setImage(this, "http://img5.imgtn.bdimg.com/it/u=2984724874,1496605649&fm=27&gp=0.jpg", iv, false);
    }

    @OnClick({R.id.iv, R.id.iv2, R.id.mian})
    public void onViewClicked(View view) {
        initClick();
        ToastUtil.showToast(this,"12345");
        switch (view.getId()) {
            case R.id.iv:
                break;
            case R.id.iv2:
                break;
            case R.id.mian:
                break;
        }
    }
}
