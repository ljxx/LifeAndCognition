package com.ylixiang.market.mvp.fragment;


import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ylixiang.market.R;
import com.ylixiang.market.R2;
import com.ylixiang.market.bean.AndroidDataBean;
import com.ylixiang.market.mvp.fragment.adapter.RecyclerLinerAdapter;
import com.ylixiang.market.mvp.fragment.contact.RecyclerViewContact;
import com.ylixiang.market.mvp.fragment.presenter.RecyclerViewPresenter;
import com.ylixiang.ylxcommonlib.base.BaseFragment;
import com.ylixiang.ylxcommonlib.view.SuperSwipeRefreshLayout;
import com.ylixiang.ylxcommonlib.view.YRecyclerView;

import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecyclerViewLinerFragment extends BaseFragment<RecyclerViewContact.Presenter> implements RecyclerViewContact.View {

    @BindView(R2.id.m_recycler_view)
    YRecyclerView mRecyclerView;
    @BindView(R2.id.m_swipe_refresh_layout)
    SuperSwipeRefreshLayout mSwipeRefreshLayout;

    private LinearLayoutManager mLinearLayoutManager;
    private RecyclerLinerAdapter mAdater;
    private List<AndroidDataBean> mDataList;

    public RecyclerViewLinerFragment() {
        // Required empty public constructor
    }

    @Override
    protected int setContentView() {
        return R.layout.fragment_recycler_view_liner;
    }

    @Override
    protected void initView() {

        /**
         * 手动下拉刷新
         */
//        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                mSwipeRefreshLayout.setRefreshing(false);
//                mPresenter.setRefreshing();
//                if(mAdater != null) {
//                    mAdater.getData().clear();
//                }
//                mPresenter.getData();
//            }
//        });

//        默认第一次加载会回调onLoadMoreRequested()加载更多这个方法，如果不需要可以配置如下：
//        mAdater.disableLoadMoreIfNotFullPage();
//        加载完成（注意不是加载结束，而是本次数据加载结束并且还有下页数据）
//        mAdater.loadMoreComplete();
//        加载失败
//        mAdater.loadMoreFail();
//        加载结束
//        mAdater.loadMoreEnd();

        mSwipeRefreshLayout.setHeaderViewBackgroundColor(0xff888888);
        mSwipeRefreshLayout.setHeaderView(createHeaderView());// add headerView
        mSwipeRefreshLayout.setTargetScrollWithLayout(true);
        mSwipeRefreshLayout.setOnPullRefreshListener(new SuperSwipeRefreshLayout.OnPullRefreshListener() {

            @Override
            public void onRefresh() {
                textView.setText("正在刷新");
                imageView.setVisibility(View.GONE);
                progressBar.setVisibility(View.VISIBLE);
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        mSwipeRefreshLayout.setRefreshing(false);
                        progressBar.setVisibility(View.GONE);
                    }
                }, 2000);


                mPresenter.setRefreshing();
                if(mAdater != null) {
                    mAdater.getData().clear();
                }
                mPresenter.getData();
            }

            @Override
            public void onPullDistance(int distance) {
                // pull distance
            }

            @Override
            public void onPullEnable(boolean enable) {
                textView.setText(enable ? "松开刷新" : "下拉刷新");
                imageView.setVisibility(View.VISIBLE);
                imageView.setRotation(enable ? 180 : 0);
                progressBar.setVisibility(View.GONE);
            }
        });

        //设置刷新样式
//        mSwipeRefreshLayout.setColorSchemeColors(Color.RED, Color.BLUE, Color.GREEN);

        mAdater = new RecyclerLinerAdapter(R.layout.recycler_liner_item_layout, mDataList);
        mLinearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        int mSpacingInPixels = getResources().getDimensionPixelSize(R.dimen.qb_px_1);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        mRecyclerView.setAdapter(mAdater);

        mAdater.setAutoLoadMoreSize(5);

        mAdater.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {

                mPresenter.getData();

            }
        }, mRecyclerView);
    }

    @Override
    public RecyclerViewContact.Presenter initPresenter() {
        return new RecyclerViewPresenter(this);
    }

    @Override
    protected void lazyLoad() {
        Log.i("=======","****RecyclerViewLinerFragment*****");
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
    }

    @Override
    public void setData(List<AndroidDataBean> list) {
        mSwipeRefreshLayout.setRefreshing(false);
        if(mAdater == null) {
            mAdater = new RecyclerLinerAdapter(R.layout.recycler_liner_item_layout, mDataList);
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


    // Header View
    private ProgressBar progressBar;
    private TextView textView;
    private ImageView imageView;

    private View createHeaderView() {
        View headerView = LayoutInflater.from(mSwipeRefreshLayout.getContext())
                .inflate(R.layout.layout_head, null);
        progressBar = (ProgressBar) headerView.findViewById(R.id.pb_view);
        textView = (TextView) headerView.findViewById(R.id.text_view);
        textView.setText("下拉刷新");
        imageView = (ImageView) headerView.findViewById(R.id.image_view);
        imageView.setVisibility(View.VISIBLE);
        imageView.setImageResource(R.drawable.down_arrow);
        progressBar.setVisibility(View.GONE);
        return headerView;
    }

}