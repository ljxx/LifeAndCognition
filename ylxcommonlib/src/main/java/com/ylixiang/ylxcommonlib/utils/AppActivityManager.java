package com.ylixiang.ylxcommonlib.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;

import java.util.Stack;

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
 * 创建日期：2018/12/18  下午3:22
 * <p>
 * 描 述：
 * <p>
 * ========================================
 */
public class AppActivityManager {

    // Activity栈
    private static Stack<Activity> mActivityStack;
    // 单例模式
    private static AppActivityManager mInstance;

    private AppActivityManager() {
    }

    /**
     * 单一实例
     */
    public static AppActivityManager getInstance() {
        if (mInstance == null) {
            mInstance = new AppActivityManager();
        }
        return mInstance;
    }

    /**
     * 添加Activity到堆栈
     */
    public void addActivity(Activity activity) {
        if (mActivityStack == null) {
            mActivityStack = new Stack<Activity>();
        }
        mActivityStack.add(activity);
    }

    /**
     * 获取当前Activity（堆栈中最后一个压入的）
     */
    public Activity currentActivity() {
        Activity activity = mActivityStack.lastElement();
        return activity;
    }

    /**
     * 结束当前Activity（堆栈中最后一个压入的）
     */
    public void finishActivity() {
        Activity activity = mActivityStack.lastElement();
        finishActivity(activity);
    }

    /**
     * 结束指定的Activity
     */
    public void finishActivity(Activity activity) {
        if (activity != null) {
            mActivityStack.remove(activity);
            activity.finish();
            activity = null;
        }
    }

    /**
     * 结束指定类名的Activity
     */
    public void finishActivity(Class<?> cls) {
        for (Activity activity : mActivityStack) {
            if (activity.getClass().equals(cls)) {
                finishActivity(activity);
            }
        }
    }

    /**
     * 结束所有Activity
     */
    public void finishAllActivity() {
        for (int i = 0; i < mActivityStack.size(); i++) {
            if (null != mActivityStack.get(i)) {
                mActivityStack.get(i).finish();
            }
        }
        mActivityStack.clear();
    }

    /**
     * 退出应用程序
     */
    public void AppExit(Context context) {
        try {
            finishAllActivity();
            ActivityManager activityMgr = (ActivityManager) context
                    .getSystemService(Context.ACTIVITY_SERVICE);
            activityMgr.killBackgroundProcesses(context.getPackageName());
            System.exit(0);
        } catch (Exception e) {
        }
    }
}
