package com.ylixiang.ylxcommonlib.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

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
 * 描 述：
 * <p>
 * ========================================
 */
public abstract class BaseFragment<P extends BasePresenter> extends Fragment implements BaseView {

    protected Context mContext;
    public P mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
        mPresenter = initPresenter();
    }

    @Override
    public void onDestroy() {
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
