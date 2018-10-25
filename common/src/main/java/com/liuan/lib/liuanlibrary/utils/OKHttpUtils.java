package com.liuan.lib.liuanlibrary.utils;

import java.io.IOException;
import java.io.InputStream;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by wl_pc on 17/3/11.
 */

public class OKHttpUtils {

    private static final OkHttpClient okhttpclient = new OkHttpClient();

    /**
     * 获取请求对象
     * @param url
     * @return
     */
    public static Request getRequestFromUrl(String url){
        Request request = new Request.Builder().url(url).build();
        return request;
    }

    /**
     * 获取响应对象
     * @param url
     * @return
     * @throws IOException
     */
    public static Response getResponseFromUrl(String url) throws IOException{
        Request request = getRequestFromUrl(url);
        Response response = okhttpclient.newCall(request).execute();
        return response;
    }

    /**
     * 获取responseBody对象
     * @param url
     * @return
     * @throws IOException
     */
    public static ResponseBody getResponseBodyFromUrl(String url) throws IOException{
        Response response = getResponseFromUrl(url);
        if(response.isSuccessful()){
            return response.body();
        }
        return null;
    }


    /**
     * 通过网络请求获取字符串
     * @param url
     * @return
     * @throws IOException
     */
    public static String loadStringFromUrl(String url) throws IOException{
        ResponseBody responseBody = getResponseBodyFromUrl(url);
        if(responseBody != null){
            return responseBody.string();
        }
        return null;
    }

    /**
     * 通过网络请求获取字符节
     * @param url
     * @return
     * @throws IOException
     */
    public byte[] loadbyteFromUrl(String url) throws  IOException{
        ResponseBody responseBody = getResponseBodyFromUrl(url);
        if(responseBody != null){
            return responseBody.bytes();
        }
        return null;
    }

    /**
     * 通过网络请求获取输入流
     * @param url
     * @return
     * @throws IOException
     */
    public InputStream loadStreamFromUrl(String url) throws IOException{
        ResponseBody responseBody = getResponseBodyFromUrl(url);
        if(responseBody != null){
            return responseBody.byteStream();
        }
        return null;
    }

}
