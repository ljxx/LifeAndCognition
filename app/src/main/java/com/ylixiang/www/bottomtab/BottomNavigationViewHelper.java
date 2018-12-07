package com.ylixiang.www.bottomtab;

import android.annotation.SuppressLint;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;

import java.lang.reflect.Field;

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
 * 创建日期：2018/12/7  上午11:22
 * <p>
 * 描 述：
 * <p>
 * ========================================
 */
public class BottomNavigationViewHelper {

    private static final int INDEX_ONE = 0;
    private static final String M_SHIFTING_MODE = "mShiftingMode";

    @SuppressLint("RestrictedApi")
    public static void disableShifMode(BottomNavigationView view) {
        //获取视图
        BottomNavigationMenuView mMenuView = (BottomNavigationMenuView) view.getChildAt(INDEX_ONE);
        //去掉BottomNavigationMenuView的默认动画效果ShiftingMode
        try {
            Field mField = mMenuView.getClass().getDeclaredField(M_SHIFTING_MODE);
            //反射时可以访问私有变量
            mField.setAccessible(true);
            mField.setBoolean(mMenuView, false);
            mField.setAccessible(false);
            //循环子元素个数
            for (int i = 0; i < mMenuView.getChildCount(); i++) {
                BottomNavigationItemView mItemView = (BottomNavigationItemView) mMenuView.getChildAt(i);
                //关闭ShiftingMode
//                mItemView.setShiftingMode(false);
                //重新检查，关闭掉ShiftingMode
                mItemView.setChecked(mItemView.getItemData().isChecked());
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
