package com.ylixiang.www;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.ylixiang.entertainment.mvp.MainEntertainmentFragment;
import com.ylixiang.life.mvp.MainLifeFragment;
import com.ylixiang.market.mvp.MainMarketFragment;
import com.ylixiang.tool.mvp.MainToolFragment;
import com.ylixiang.user.mvp.MainUserFragment;
import com.ylixiang.www.bottomtab.ViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

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
    ViewPager mMainViewPager;
    @BindView(R.id.m_bottom_navigation_view)
    BottomNavigationView mBottomNavigationView;

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

        mViewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        mMainViewPager.setAdapter(mViewPagerAdapter);
        mFragmentList = new ArrayList<>();
        mFragmentList.add(MainLifeFragment.newInstance());
        mFragmentList.add(MainMarketFragment.newInstance());
        mFragmentList.add(MainToolFragment.newInstance());
        mFragmentList.add(MainEntertainmentFragment.newInstance());
        mFragmentList.add(MainUserFragment.newInstance());
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
