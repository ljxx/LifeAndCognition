package com.ylixiang.market.mvp.fragment.presenter;

import android.util.Log;

import com.ylixiang.market.bean.AndroidDataBean;
import com.ylixiang.market.bean.AndroidResponseBean;
import com.ylixiang.market.http.RetrofitFactory;
import com.ylixiang.market.mvp.fragment.contact.RecyclerViewContact;
import com.ylixiang.ylxcommonlib.RxHelper;
import com.ylixiang.ylxcommonlib.base.BasePresenterImpl;

import java.util.List;

import io.reactivex.functions.Consumer;

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
 * 创建日期：2018/12/21  下午2:28
 * <p>
 * 描 述：
 * <p>
 * ========================================
 */
public class RecyclerViewPresenter extends BasePresenterImpl<RecyclerViewContact.View> implements RecyclerViewContact.Presenter {

    private RecyclerViewContact.View mView;

    private int mPageSize = 10;
    private int mPageNum = 1;
    private boolean isLoading = true;

    public RecyclerViewPresenter(RecyclerViewContact.View view) {
        super(view);
        this.mView = view;
    }

    @Override
    public void setRefreshing() {
        this.mPageNum = 1;
    }

    @Override
    public void getData() {
        if(isLoading) {
            isLoading = false;
            mView.showLoadingDialog("数据载入中。。。");
            RetrofitFactory.getInstance()
                    .getAndroidData(mPageSize, mPageNum)
                    .compose(RxHelper.<AndroidResponseBean>rxSchedulerHelper())
                    .subscribe(new Consumer<AndroidResponseBean>() {
                        @Override
                        public void accept(AndroidResponseBean androidResponseBean) throws Exception {
                            mView.dismissLoadingDialog();
                            List<AndroidDataBean> mDataList = androidResponseBean.getResults();
                            if(mDataList != null) {
                                mView.setData(mDataList);
                                if(mDataList.size() < 10) {
                                    mView.setLoadEnable();
                                    isLoading = false;
                                } else {
                                    isLoading = true;
                                    mView.setLoadMoreComplete();
                                }
                                mPageNum ++;
                            } else {
                                //没有数据

                                //判断是不是第一页
                            }
                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Exception {
                            mView.dismissLoadingDialog();
                            Log.i("====", "" + throwable.getMessage());
                        }
                    });
        }
    }
}
