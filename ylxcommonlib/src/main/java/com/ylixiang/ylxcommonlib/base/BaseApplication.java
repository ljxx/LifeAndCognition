package com.ylixiang.ylxcommonlib;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;

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
 * 创建日期：2018/12/7  下午3:13
 * <p>
 * 描 述：
 * <p>
 * ========================================
 */
public class BaseApplication extends Application {

    private boolean isDebut = true;

    @Override
    public void onCreate() {
        super.onCreate();

        //ARouter init
        initRouter(this);
    }

    private void initRouter(BaseApplication mApplication) {
        //这两行必须写在init之前，否则这些配置在init过程中将无效
        if (isDebut) {
            ARouter.openLog();
            ARouter.openDebug();
        }
        ARouter.init(this);
    }
}
