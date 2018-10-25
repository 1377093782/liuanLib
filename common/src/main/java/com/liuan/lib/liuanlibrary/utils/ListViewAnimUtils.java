package com.liuan.lib.liuanlibrary.utils;

import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LayoutAnimationController;
import android.view.animation.TranslateAnimation;

/**
 * Created by liuan on 2017/5/17
 */

public class ListViewAnimUtils {
    //listview内存条目从右飞入动画
    public static LayoutAnimationController getListInFromRightAnim() {
        AnimationSet set = new AnimationSet(true);
        Animation animation = new AlphaAnimation(0.0f, 1.0f);
        animation.setDuration(300);
        set.addAnimation(animation);

        animation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 1.0f, Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f);
        animation.setDuration(500);
        set.addAnimation(animation);
        LayoutAnimationController controller = new LayoutAnimationController(set, 0.5f);
        return controller;
    }

    //清理内存时listview从左边飞出的动画
    public static LayoutAnimationController getListOutToLeftAnim() {
        AnimationSet set = new AnimationSet(true);
        Animation animation = new AlphaAnimation(1.0f, 0.0f);
        animation.setDuration(700);
//        animation.setDuration(Constant.TIME_CPU_CIR*65/100);
        set.addAnimation(animation);

        animation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, -7.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f);
        animation.setDuration(2000);
//        animation.setDuration(Constant.TIME_CPU_CIR*65/100*2);
        set.addAnimation(animation);

        set.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                //rl_button_boost.setVisibility(View.GONE);


            }
        });
        LayoutAnimationController controller = new LayoutAnimationController(set, 0.17f);
        controller.setOrder(LayoutAnimationController.ORDER_REVERSE);
        return controller;
    }

}
