package com.ylixiang.ylxcommonlib.base;

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
 * 创建日期：2018/12/18  下午2:53
 * <p>
 * 描 述：
 * <p>
 * ========================================
 */
public interface BasePressenter {

    //默认初始化
    void start();

    //Activity关闭把View对象置为空
    void detach();

    //将网络请求的每一个disposable添加进入CompositeDisposable，再退出的时候注销掉，防止内存泄漏
    void addDisposable(Disposable subscription);

    //注销所有请求
    void unDisposable();
}
