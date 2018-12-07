package com.ylixiang.www.bottomtab;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

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
 * 创建日期：2018/12/6  下午4:56
 * <p>
 * 描 述：
 * <p>
 * ========================================
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> mList;

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public void setList(List<Fragment> list) {
        this.mList = list;
        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int position) {
        return mList != null ? mList.get(position) : null;
    }

    @Override
    public int getCount() {
        return mList != null ? mList.size() : 0;
    }
}
