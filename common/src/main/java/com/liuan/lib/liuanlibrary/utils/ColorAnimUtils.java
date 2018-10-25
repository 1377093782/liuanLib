package com.liuan.lib.liuanlibrary.utils;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.view.View;

import com.liuan.lib.liuanlibrary.R;


/**
 * Created by liuan on 2017/5/18.
 */

public class ClolorAnimUtils {
    public static ObjectAnimator changeColor(final View v, final int Time,int colorOneId,int colorTwoId) {
//
        int drawingCacheBackgroundColor = v.getDrawingCacheBackgroundColor();
        if (v == null) {
            return null;
        }
        ObjectAnimator
                animator = ObjectAnimator.ofInt(v, "BackgroundColor", v.getContext().getResources().getColor(colorOneId), v.getContext().getResources().getColor(colorTwoId));
        animator.setDuration(Time * 65 * 2);

        animator.setEvaluator(new ArgbEvaluator());

        animator.start();
        return animator;

    }
}
