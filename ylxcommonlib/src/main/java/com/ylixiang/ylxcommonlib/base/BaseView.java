package com.ylixiang.ylxcommonlib.base;

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
 * 创建日期：2018/12/18  下午2:51
 * <p>
 * 描 述：
 * <p>
 * ========================================
 */
public interface BaseView {

    //显示dialog
    void showLoadingDialog(String msg);

    //取消dialog
    void dismissLoadingDialog();
}
