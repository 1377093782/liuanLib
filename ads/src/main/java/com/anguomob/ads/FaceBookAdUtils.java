package com.anguomob.ads;

import android.content.Context;

import com.facebook.ads.AudienceNetworkAds;
import com.facebook.ads.InterstitialAd;

public class FaceBookAdUtils {

    private static FaceBookAdUtils adUtils;
    private static InterstitialAd mInterstitialAd;
    private static Context mContent;
    private static boolean isInit;

    public static FaceBookAdUtils getInstance(Context context) {


        if (mContent == null) {
            mContent = context;
        }
        if (adUtils == null) {
            adUtils = new FaceBookAdUtils();
        }
        if (!isInit) {
            AudienceNetworkAds.initialize(mContent);
            isInit=true;
        }

        return adUtils;
    }

    private static final String TAG = "FaceBookAdUtils";

    public InterstitialAd showInsertAd(String id) {

        if (mInterstitialAd == null) {
            mInterstitialAd = new InterstitialAd(mContent, id);
        }
        mInterstitialAd.show();
        return mInterstitialAd;
    }






}
