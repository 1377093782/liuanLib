package com.liuan.lib.liuanlibrary.utils;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;

import com.google.firebase.analytics.FirebaseAnalytics;

/**
 * Created by
 * LScorpio on 2017/3/7.
 */
public class Firebase {

    private static Firebase instance;
    private Context context;
    private FirebaseAnalytics mFireInstance;

    public Firebase(Context context) {
        this.context = context;
    }

    public static Firebase getInstance(Context context) {
        if (instance == null) {
            instance = new Firebase(context);
        }
        return instance;
    }



    public FirebaseAnalytics getFirebaseAnalytics() {
        if (mFireInstance == null) {
            try {
                mFireInstance = FirebaseAnalytics.getInstance(context);
            } catch (Exception e) {

            }
        }
        return mFireInstance;
    }

    public void logEvent(String category, String action) {
        FirebaseAnalytics firebaseAnalytics = instance.getFirebaseAnalytics();
        Bundle bundle = new Bundle();
        bundle.putString("action", cutStringIfNecessary(action));
        firebaseAnalytics.logEvent(validateKey(category), bundle);
    }

    public void logEvent(String category, String action, String label) {
        FirebaseAnalytics firebaseAnalytics = instance.getFirebaseAnalytics();
        Bundle bundle = new Bundle();
        bundle.putString(validateKey(action), cutStringIfNecessary(label));
        firebaseAnalytics.logEvent(validateKey(category), bundle);
    }

    public void logEvent(String category, String action, long value) {
        FirebaseAnalytics firebaseAnalytics = instance.getFirebaseAnalytics();
        Bundle bundle = new Bundle();
        bundle.putLong(validateKey(action), value);
        firebaseAnalytics.logEvent(validateKey(category), bundle);
    }

    public void logEvent(String category, String action, double value) {
        FirebaseAnalytics firebaseAnalytics = instance.getFirebaseAnalytics();
        Bundle bundle = new Bundle();
        bundle.putDouble(validateKey(action), value);
        firebaseAnalytics.logEvent(validateKey(category), bundle);
    }

    public void logEvent(String category, long value) {
        FirebaseAnalytics firebaseAnalytics = instance.getFirebaseAnalytics();
        Bundle bundle = new Bundle();
        bundle.putLong("value", value);
        firebaseAnalytics.logEvent(validateKey(category), bundle);
    }

    public void logEvent(String category, double value) {
        FirebaseAnalytics firebaseAnalytics = instance.getFirebaseAnalytics();
        Bundle bundle = new Bundle();
        bundle.putDouble("value", value);
        firebaseAnalytics.logEvent(validateKey(category), bundle);
    }

    public void logEvent(String category, Bundle values) {
        FirebaseAnalytics firebaseAnalytics = instance.getFirebaseAnalytics();
        firebaseAnalytics.logEvent(validateKey(category), values);
    }

    private String cutStringIfNecessary(String v) {
        if (!TextUtils.isEmpty(v) && v.length() > 100) {
            return v.substring(0, 100);
        }
        return v;
    }

    private String validateKey(String key) {
        if (!TextUtils.isEmpty(key)) {
            if (!Character.isLetter(key.charAt(0))) {
                key = "K" + key;
            }
            if (key.length() > 40) {
                key = key.substring(0, 40);
            }
            for (int i = 0; i < key.length(); i++) {
                if (!Character.isLetterOrDigit(key.charAt(i)) && key.charAt(i) != '_') {
                    key = key.replace(key.charAt(i), '_');
                }
            }
        }
        return key;
    }

    //设置是否开启数据收集功能
    public void setAnalyticsCollectionEnabled(boolean enabled) {
        instance.getFirebaseAnalytics().setAnalyticsCollectionEnabled(enabled);
    }

    public void setMinimumSessionDuration(long milliseconds) {
        instance.getFirebaseAnalytics().setMinimumSessionDuration(milliseconds);
    }

    public void setUserId(String userId) {
        instance.getFirebaseAnalytics().setUserId(userId);
    }

    public void setSessionTimeoutDuration(long milliseconds) {
        instance.getFirebaseAnalytics().setSessionTimeoutDuration(milliseconds);
    }

    public void setUserProperty(String name, String value) {
        instance.getFirebaseAnalytics().setUserProperty(name, value);
    }

}