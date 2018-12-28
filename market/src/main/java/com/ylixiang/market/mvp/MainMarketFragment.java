package com.ylixiang.market.mvp;


import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.ylixiang.market.R;
import com.ylixiang.market.R2;
import com.ylixiang.market.mvp.fragment.RecyclerViewGridFragment;
import com.ylixiang.market.mvp.fragment.RecyclerViewLinerFragment;
import com.ylixiang.market.mvp.fragment.RecyclerViewStaggeredFragment;
import com.ylixiang.ylxcommonlib.adapter.MyFragmentPagerAdapter;
import com.ylixiang.ylxcommonlib.arouter.ARouterUtils;
import com.ylixiang.ylxcommonlib.view.YTabLayout;
import com.ylixiang.ylxcommonlib.view.YViewPager;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
@Route(path = ARouterUtils.MARKET_FRAGMENT_MAIN)
public class MainMarketFragment extends Fragment {

    @BindView(R2.id.m_tab_layout)
    YTabLayout mTabLayout;
    @BindView(R2.id.m_view_pager)
    YViewPager mViewPager;
    Unbinder unbinder;
    private MyFragmentPagerAdapter mMyFragmentPagerAdapter;

    private RecyclerViewLinerFragment mLinearFragment;
    private RecyclerViewGridFragment mGridFragment;
    private RecyclerViewStaggeredFragment mStaggeredFragment;

    private List<Fragment> mFragments;

    private String[] mTabTitles;

    public MainMarketFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main_market, container, false);
        unbinder = ButterKnife.bind(this, view);

        mTabTitles = getActivity().getResources().getStringArray(R.array.market_recycler_type);

        mFragments = new ArrayList<>();
        mLinearFragment = new RecyclerViewLinerFragment();
        mGridFragment = new RecyclerViewGridFragment();
        mStaggeredFragment = new RecyclerViewStaggeredFragment();
        mFragments.add(mLinearFragment);
        mFragments.add(mGridFragment);
        mFragments.add(mStaggeredFragment);
        mMyFragmentPagerAdapter = new MyFragmentPagerAdapter(getChildFragmentManager(), mFragments, mTabTitles);
        mViewPager.setAdapter(mMyFragmentPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        //去除点击背景色
        mTabLayout.setTabRippleColor(ColorStateList.valueOf(getContext().getResources().getColor(R.color.white)));

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
