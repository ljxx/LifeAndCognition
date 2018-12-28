package com.ylixiang.market.mvp.fragment;


import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ylixiang.market.R;
import com.ylixiang.market.R2;
import com.ylixiang.market.bean.AndroidDataBean;
import com.ylixiang.market.mvp.fragment.adapter.RecyclerLinerAdapter;
import com.ylixiang.market.mvp.fragment.contact.RecyclerViewContact;
import com.ylixiang.market.mvp.fragment.presenter.RecyclerViewPresenter;
import com.ylixiang.ylxcommonlib.base.BaseFragment;
import com.ylixiang.ylxcommonlib.utils.decoration.SpacesItemDecoration;
import com.ylixiang.ylxcommonlib.view.YRecyclerView;
import com.ylixiang.ylxcommonlib.view.YSwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecyclerViewStaggeredFragment extends BaseFragment<RecyclerViewContact.Presenter> implements RecyclerViewContact.View {

    @BindView(R2.id.m_recycler_view)
    YRecyclerView mRecyclerView;
    @BindView(R2.id.m_swipe_refresh_layout)
    YSwipeRefreshLayout mSwipeRefreshLayout;

    private StaggeredGridLayoutManager mStaggeredGridLayoutManager;
    private RecyclerLinerAdapter mAdater;
    private List<AndroidDataBean> mDataList;

    public RecyclerViewStaggeredFragment() {
        // Required empty public constructor
    }

    @Override
    public RecyclerViewContact.Presenter initPresenter() {
        return new RecyclerViewPresenter(this);
    }

    @Override
    protected int setContentView() {
        return R.layout.fragment_recycler_view;
    }

    @Override
    protected void initView() {
        //设置刷新样式
        mSwipeRefreshLayout.setColorSchemeColors(Color.RED, Color.BLUE, Color.GREEN);

        mDataList = new ArrayList<>();
        mAdater = new RecyclerLinerAdapter(R.layout.recycler_grid_item_layout, mDataList);
        mAdater.setType(2);

        mStaggeredGridLayoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mStaggeredGridLayoutManager);
        int mSpacingInPixels = getResources().getDimensionPixelSize(R.dimen.qb_px_3);
        mRecyclerView.addItemDecoration(new SpacesItemDecoration(mSpacingInPixels));
        mRecyclerView.setAdapter(mAdater);

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
                mPresenter.getGrilData();
            }
        });

//        默认第一次加载会回调onLoadMoreRequested()加载更多这个方法，如果不需要可以配置如下：
//        mAdater.setEnableLoadMore(false);
//        mAdater.disableLoadMoreIfNotFullPage();
//        加载完成（注意不是加载结束，而是本次数据加载结束并且还有下页数据）
//        mAdater.loadMoreComplete();
//        加载失败
//        mAdater.loadMoreFail();
//        加载结束
//        mAdater.loadMoreEnd();

//        mAdater.setAutoLoadMoreSize(5);

        mAdater.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {

                mPresenter.getGrilData();

            }
        }, mRecyclerView);
    }

    @Override
    protected void lazyLoad() {
        Log.i("=======","****RecyclerViewStaggeredFragment*****");
        /**
         * 初始化自动加载的时候显示加载
         */
        mRecyclerView.postDelayed(new Runnable() {
            @Override
            public void run() {
                //这个方法是让一进入页面的时候实现网络请求，有个缓冲的效果
                mSwipeRefreshLayout.setRefreshing(true);
                //模拟一下网络请求
                mPresenter.getGrilData();
            }
        }, 1000);
    }


    @Override
    public void setData(List<AndroidDataBean> list) {
        mSwipeRefreshLayout.setRefreshing(false);
        if(mAdater == null) {
            mAdater = new RecyclerLinerAdapter(R.layout.recycler_grid_item_layout, mDataList);
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

}
