package com.ylixiang.ylxcommonlib.view;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

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
 * 创建日期：2018/12/21  下午2:55
 * <p>
 * 描 述：
 * <p>
 * ========================================
 */
public class YSwipeRefreshLayout extends SwipeRefreshLayout {
    public YSwipeRefreshLayout(@NonNull Context context) {
        super(context);
    }

    public YSwipeRefreshLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
}
