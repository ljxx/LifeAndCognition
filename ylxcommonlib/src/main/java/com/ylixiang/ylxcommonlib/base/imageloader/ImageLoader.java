package com.ylixiang.ylxcommonlib.base.imageloader;

import android.content.Context;

import com.ylixiang.ylxcommonlib.base.imageloader.base.BaseImageLoaderConfig;
import com.ylixiang.ylxcommonlib.base.imageloader.base.BaseImageLoaderStrategy;


/**
 * Created by COOTEK on 2017/7/31.
 */
public class ImageLoader {
    private BaseImageLoaderStrategy baseImageLoaderStrategy;

    public ImageLoader(BaseImageLoaderStrategy baseImageLoaderStrategy) {
        this.baseImageLoaderStrategy = baseImageLoaderStrategy;
    }

    public void setBaseImageLoaderStrategy(BaseImageLoaderStrategy baseImageLoaderStrategy) {
        this.baseImageLoaderStrategy = baseImageLoaderStrategy;
    }

    public <T extends BaseImageLoaderConfig> void loadImage(Context context, T config) {
        baseImageLoaderStrategy.loadImage(context, config);
    }

}
