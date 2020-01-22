package com.anguomob.lib.utils;

import android.app.Activity;
import android.content.Context;
import android.media.Image;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.signature.ObjectKey;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

public class GlideUtils {


    public static void setImage(Context context,Object o, ImageView imageView, boolean isDisCach) {
        RequestOptions options = new RequestOptions();
        options.centerCrop();


        if (isDisCach) {
            options.diskCacheStrategy(DiskCacheStrategy.ALL);
            options.skipMemoryCache(false);

        } else {
            options.diskCacheStrategy(DiskCacheStrategy.NONE);
            options.skipMemoryCache(true);
        }


            Glide.with(context)
                    .load(o)
                    //交叉淡入
                    .transition(withCrossFade())
                    .apply(options)
                    .into(imageView);

    }

    public static void setImage(Context context, Object o, ImageView imageView, boolean isDisCach, int width, int height) {
        RequestOptions options = new RequestOptions();
        options.centerCrop()
        .override(width,height);


        if (isDisCach) {
            options.diskCacheStrategy(DiskCacheStrategy.ALL);
            options.skipMemoryCache(false);

        } else {
            options.diskCacheStrategy(DiskCacheStrategy.NONE);
            options.skipMemoryCache(true);
        }


        Glide.with(context)
                .load(o)
                //交叉淡入
                .transition(withCrossFade())
                .apply(options)
                .into(imageView);

    }

    public static void loadCirPic(Activity activity, String url, ImageView imageView){
        RequestOptions userAvatarOptions = new RequestOptions()
//            .placeholder(R.drawable.def_avatar)
//            .error(R.drawable.def_avatar)
                .signature(new ObjectKey(System.currentTimeMillis()));
        Glide.with(activity).applyDefaultRequestOptions(userAvatarOptions).load(url).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(imageView);

    }
    public static void loadCirPic(Activity activity, Object url, ImageView imageView){
        RequestOptions userAvatarOptions = new RequestOptions()
//            .placeholder(R.drawable.def_avatar)
//            .error(R.drawable.def_avatar)
                .signature(new ObjectKey(System.currentTimeMillis()));
        Glide.with(activity).applyDefaultRequestOptions(userAvatarOptions).load(url).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(imageView);

    }

}
