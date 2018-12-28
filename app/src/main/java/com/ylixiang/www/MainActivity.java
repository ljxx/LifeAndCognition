package com.ylixiang.www;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.ylixiang.www.bottomtab.ViewPagerAdapter;
import com.ylixiang.www.utils.FragmentUtils;
import com.ylixiang.ylxcommonlib.view.YBottomNavigationView;
import com.ylixiang.ylxcommonlib.view.YViewPager;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;

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
 * 创建日期：2018/12/4 下午6:17
 * <p>
 * 描 述：
 * <p>
 * ========================================
 */
public class MainActivity extends AppCompatActivity {

    @BindView(R.id.m_main_view_pager)
    YViewPager mMainViewPager;
    @BindView(R.id.m_bottom_navigation_view)
    YBottomNavigationView mBottomNavigationView;

    private MenuItem mMenuItem;
    private final int M_TAB_LIFE = 0;
    private final int M_TAB_MARKET = 1;
    private final int M_TAB_TOOL = 2;
    private final int M_TAB_ENTERTAINMENT = 3;
    private final int M_TAB_USER = 4;

    private ViewPagerAdapter mViewPagerAdapter;
    private List<Fragment> mFragmentList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mBottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemReselectedListener);
//        BottomNavigationViewHelper.disableShifMode(mBottomNavigationView);

        mViewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        mMainViewPager.setAdapter(mViewPagerAdapter);
        mFragmentList = new ArrayList<>();
        mFragmentList.add(FragmentUtils.getLifeFragment());
        mFragmentList.add(FragmentUtils.getMarketFragment());
        mFragmentList.add(FragmentUtils.getEntertainment());
        mFragmentList.add(FragmentUtils.getToolFragment());
        mFragmentList.add(FragmentUtils.getUserFragment());
        mViewPagerAdapter.setList(mFragmentList);

        initListener();

    }

    private void initListener() {
        mMainViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (mMenuItem != null) {
                    mMenuItem.setChecked(false);
                } else {
                    mBottomNavigationView.getMenu().getItem(M_TAB_LIFE).setChecked(false);
                }
                mMenuItem = mBottomNavigationView.getMenu().getItem(position);
                mMenuItem.setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int position) {

            }
        });
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemReselectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            mMenuItem = menuItem;
            int index = 0;
            switch (menuItem.getItemId()) {
                case R.id.m_main_life_tab: //生活
                    index = M_TAB_LIFE;
                    break;
                case R.id.m_main_market_tab: //集市
                    index = M_TAB_MARKET;
                    break;
                case R.id.m_main_tool_tab: //工具
                    index = M_TAB_TOOL;
                    break;
                case R.id.m_main_entertainment_tab: //娱乐
                    index = M_TAB_ENTERTAINMENT;
                    break;
                case R.id.m_main_user_tab: //用户
                    index = M_TAB_USER;
                    break;
            }
            mMainViewPager.setCurrentItem(index);
            return true;
        }
    };
}
