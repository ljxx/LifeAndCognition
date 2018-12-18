package com.ylixiang.ylxcommonlib.base;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

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
 * 创建日期：2018/12/18  下午3:34
 * <p>
 * 描 述：
 * <p>
 * ========================================
 */
public class BasePresenterImpl<V extends BaseView> implements BasePressenter {

    private V mView; //给子类使用的View

    public BasePresenterImpl(V view) {
        this.mView = view;
        start();
    }

    @Override
    public void start() {

    }

    @Override
    public void detach() {
        this.mView = null;
        unDisposable();
    }

    /****************/

    private CompositeDisposable mCompositeDisposable;

    @Override
    public void addDisposable(Disposable subscription) {
        //如果解绑，需要添加绑定
        if(mCompositeDisposable == null || mCompositeDisposable.isDisposed()) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(subscription);
    }

    /**
     * 页面退出时，解绑观察者；防止Rx造成内存泄漏
     */
    @Override
    public void unDisposable() {
        if(mCompositeDisposable != null) {
            mCompositeDisposable.dispose();
        }
    }




}
