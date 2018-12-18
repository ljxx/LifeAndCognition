package com.ylixiang.ylxcommonlib.base.imageloader.glide;

import android.content.Context;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.ylixiang.ylxcommonlib.base.GlideApp;
import com.ylixiang.ylxcommonlib.base.GlideRequest;
import com.ylixiang.ylxcommonlib.base.imageloader.base.BaseImageLoaderStrategy;

/**
 * ========================================
 * 
 * 版 权：仅供学习使用
 * 
 * 作 者：杨理想
 * 
 * 微 信：lanjixingxun
 * 
 * Q  Q：1099740455
 * 
 * 创建日期：2018/12/18 下午5:33
 * 
 * 描 述：
 * 
 * ========================================
 */
public class GlideImageLoaderStrategy implements BaseImageLoaderStrategy<GlideImageLoaderConfig> {
    @Override
    public void loadImage(Context context, GlideImageLoaderConfig config) {
        if (config == null || context == null) {
            return;
        }
        GlideRequest mGlideRequest = GlideApp.with(context).load(config.getUrl()).centerCrop();

        switch (config.getCacheStrategy()) {
            case GlideImageLoaderConfig.CACHE_ALL:
                mGlideRequest.diskCacheStrategy(DiskCacheStrategy.ALL);
                break;
            case GlideImageLoaderConfig.CACHE_NONE:
                mGlideRequest.diskCacheStrategy(DiskCacheStrategy.NONE);
                break;
            case GlideImageLoaderConfig.CACHE_AUTOMATIC:
                mGlideRequest.diskCacheStrategy(DiskCacheStrategy.AUTOMATIC);
                break;
            case GlideImageLoaderConfig.CACHE_DATA:
                mGlideRequest.diskCacheStrategy(DiskCacheStrategy.DATA);
                break;
            case GlideImageLoaderConfig.CACHE_RESOURCE:
                mGlideRequest.diskCacheStrategy(DiskCacheStrategy.RESOURCE);
                break;
                default:
                    break;
        }
        if (config.isCenterInside()) {
            mGlideRequest.fitCenter();
        }else {
            mGlideRequest.centerCrop();
        }
//        if (config.getBitmapTransformation() != null) {
//            mGlideRequest.bitmapTransform(config.getBitmapTransformation());
//        }
        if (config.getErrorResId() != 0) {
            mGlideRequest.error(config.getErrorResId());
        }
        if (config.getPlaceHolderResId() != 0) {
            mGlideRequest.placeholder(config.getPlaceHolderResId());
        }
        mGlideRequest.into(config.getImageView());
    }
}
