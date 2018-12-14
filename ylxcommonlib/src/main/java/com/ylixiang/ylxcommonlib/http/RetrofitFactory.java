package com.ylixiang.ylxcommonlib.http;

import android.util.Log;

import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * ========================================
 * <p>
 * 版 权：仅供学习使用
 * <p>
 * 作 者：杨理想
 * <p>
 * 微 信：lanjixingxun
 * <p>
 * Q  Q：1099740455
 * <p>
 * 创建日期：2018/12/14  下午5:41
 * <p>
 * 描 述：
 * <p>
 * ========================================
 */
public class RetrofitFactory {

    //访问超时
    private static final long TIMEOUT = 30; //30秒

    //Retrofit是基于OKHttpClient的，可以创建一个OkHttpClient进行配置
    private static OkHttpClient mHttpClient = new OkHttpClient.Builder()
            //打印接口返回信息
            .addInterceptor(new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                @Override
                public void log(String message) {
                    Log.e("TAG", "log:" + message);
                }
            }).setLevel(HttpLoggingInterceptor.Level.BASIC)) //是否打印，打印哪一部分信息
            .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT, TimeUnit.SECONDS)
            .build();

    private static RetrofitService mRetrofitService = new Retrofit.Builder()
            .baseUrl(RetrofitService.BASE_URL)
            //添加Gson数据转换器
            .addConverterFactory(GsonConverterFactory.create(new GsonBuilder()
                    .setLenient()
                    .create()
            ))
            //添加Retrofit到RxJava的转换器
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(mHttpClient)
            .build()
            .create(RetrofitService.class);

    public static RetrofitService getInstance() {
        return mRetrofitService;
    }

}
