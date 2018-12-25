package com.ylixiang.ylxcommonlib.utils;

import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

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
 * 创建日期：2018/12/24  下午6:13
 * <p>
 * 描 述：
 * <p>
 * ========================================
 */
public class SpacesItemDecoration extends RecyclerView.ItemDecoration {

    private int mSpace;

    public SpacesItemDecoration(int space) {
        this.mSpace = space;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
//        super.getItemOffsets(outRect, view, parent, state);
        outRect.left = mSpace;
        outRect.top = mSpace;
        outRect.right = mSpace;
        outRect.bottom = mSpace;

//        if(parent.getChildLayoutPosition(view) == 0) {
//            outRect.top = mSpace;
//        } else {
//            outRect.top = 0;
//        }
    }
}
