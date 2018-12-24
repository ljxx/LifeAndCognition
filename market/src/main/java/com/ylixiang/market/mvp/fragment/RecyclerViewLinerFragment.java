package com.ylixiang.market.mvp.fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ylixiang.market.R;
import com.ylixiang.market.R2;
import com.ylixiang.market.bean.AndroidDataBean;
import com.ylixiang.market.mvp.fragment.adapter.RecyclerLinerAdapter;
import com.ylixiang.market.mvp.fragment.contact.RecyclerViewContact;
import com.ylixiang.market.mvp.fragment.presenter.RecyclerViewPresenter;
import com.ylixiang.ylxcommonlib.base.BaseFragment;
import com.ylixiang.ylxcommonlib.view.YRecyclerView;
import com.ylixiang.ylxcommonlib.view.YSwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecyclerViewLinerFragment extends BaseFragment<RecyclerViewContact.Presenter> implements RecyclerViewContact.View {


    @BindView(R2.id.m_recycler_view)
    YRecyclerView mRecyclerView;
    @BindView(R2.id.m_swipe_refresh_layout)
    YSwipeRefreshLayout mSwipeRefreshLayout;
    Unbinder unbinder;

    private LinearLayoutManager mLinearLayoutManager;
    private RecyclerLinerAdapter mAdater;
    private List<AndroidDataBean> mDataList;

    public RecyclerViewLinerFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recycler_view_liner, container, false);
        unbinder = ButterKnife.bind(this, view);

        //设置刷新样式
        mSwipeRefreshLayout.setColorSchemeColors(Color.RED, Color.BLUE, Color.GREEN);

        mDataList = new ArrayList<>();
        mAdater = new RecyclerLinerAdapter(R.layout.recycler_liner_item_layout, mDataList);

        mLinearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.setAdapter(mAdater);

//        mPresenter.getData(10, 1);

        /**
         * 手动下拉刷新
         */
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mSwipeRefreshLayout.setRefreshing(false);
                mPresenter.setRefreshing();
                if(mAdater != null) {
                    mAdater.getData().clear();
                }
                mPresenter.getData();
            }
        });

        /**
         * 初始化自动加载的时候显示加载
         */
        mRecyclerView.postDelayed(new Runnable() {
            @Override
            public void run() {
                //这个方法是让一进入页面的时候实现网络请求，有个缓冲的效果
                mSwipeRefreshLayout.setRefreshing(true);
                //模拟一下网络请求
                mPresenter.getData();
            }
        }, 1000);

//        默认第一次加载会回调onLoadMoreRequested()加载更多这个方法，如果不需要可以配置如下：
//        mAdater.disableLoadMoreIfNotFullPage();
//        加载完成（注意不是加载结束，而是本次数据加载结束并且还有下页数据）
//        mAdater.loadMoreComplete();
//        加载失败
//        mAdater.loadMoreFail();
//        加载结束
//        mAdater.loadMoreEnd();

        mAdater.setAutoLoadMoreSize(5);

        mAdater.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {

                mPresenter.getData();

            }
        }, mRecyclerView);

        return view;
    }

    @Override
    public RecyclerViewContact.Presenter initPresenter() {
        return new RecyclerViewPresenter(this);
    }

    @Override
    public void setData(List<AndroidDataBean> list) {
        mSwipeRefreshLayout.setRefreshing(false);
        if(mAdater == null) {
            mAdater = new RecyclerLinerAdapter(R.layout.recycler_liner_item_layout, mDataList);
            mLinearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
            mRecyclerView.setLayoutManager(mLinearLayoutManager);
            mRecyclerView.setAdapter(mAdater);
        } else {
            mAdater.addData(list);
        }
    }

    @Override
    public void setLoadEnable() {
        mAdater.loadMoreEnd();
    }

    @Override
    public void setLoadMoreComplete() {
        mAdater.loadMoreComplete();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}