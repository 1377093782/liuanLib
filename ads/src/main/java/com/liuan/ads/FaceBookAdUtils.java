package com.liuan.ads;

import android.content.Context;
import android.os.Handler;
import android.util.Log;

import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AudienceNetworkAds;
import com.facebook.ads.InterstitialAd;
import com.facebook.ads.InterstitialAdListener;

public class FaceBookAdUtils {

    private static FaceBookAdUtils adUtils;
    private static InterstitialAd mInterstitialAd;
    private static Context mContent;

    public static FaceBookAdUtils getInstance(Context context) {
        if (mContent == null) {
            mContent = context;
        }
        if (adUtils == null) {
            adUtils = new FaceBookAdUtils();
        }
        return adUtils;
    }


    public void init() {
        AudienceNetworkAds.initialize(mContent);
    }

    public InterstitialAd initInterstitialAd(String insertId) {


        if (mInterstitialAd == null) {
            mInterstitialAd = new InterstitialAd(mContent, insertId);
        }
        return mInterstitialAd;


    }
    public void destoryInterstitialad(InterstitialAd ad) {
        if (ad != null) {
            ad.destroy();
        }
    }
}
