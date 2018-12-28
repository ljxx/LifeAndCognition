package com.ylixiang.ylxcommonlib.base;

import android.content.Context;
import android.os.Bundle;

import com.ylixiang.ylxcommonlib.utils.AppActivityManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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
 * 创建日期：2018/12/7  下午3:14
 * <p>
 * 描 述：由于每一个view都对应不同的presenter。当然对应的每个presenter也同样对应一个view。所有我们使用接口和泛型来封装了。
 * <p>
 * ========================================
 */
public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements BaseView {

    protected P mPresenter;
    public Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;

        //将当前Activity添加进入管理栈
        AppActivityManager.getInstance().addActivity(this);

        mPresenter = initPresenter();
    }

    @Override
    protected void onDestroy() {

        //将当前Activity移除管理栈
        AppActivityManager.getInstance().finishActivity();

        if(mPresenter != null) {
            //在Presenter中解绑View
            mPresenter.detach();
            mPresenter = null;
        }

        super.onDestroy();
    }

    /**
     * 让子类初始化相应的Presenter
     * @return
     */
    public abstract P initPresenter();

    @Override
    public void showLoadingDialog(String msg) {

    }

    @Override
    public void dismissLoadingDialog() {

    }
}
