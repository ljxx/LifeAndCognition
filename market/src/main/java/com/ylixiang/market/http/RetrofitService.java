package com.ylixiang.market.http;

import com.ylixiang.market.bean.AndroidResponseBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

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
 * 创建日期：2018/12/14  下午5:39
 * <p>
 * 描 述：
 * <p>
 * ========================================
 */
public interface RetrofitService {
    String BASE_URL = "https://gank.io/";

    @GET("api/data/Android/{pageSize}/{pageNum}")
    Observable<AndroidResponseBean> getAndroidData(@Path("pageSize") int pageSize, @Path("pageNum") int pageNum);
}