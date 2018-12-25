package com.ylixiang.ylxcommonlib.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

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
 * 创建日期：2018/12/7  下午3:14
 * <p>
 * 描 述：坑来了
 * ViewPager用PagerAdapter，setUserVisibleHint()是不执行的，
 * 需要用FragmentPagerAdapter显式调用setUserVisibleHint()。
 * <p>
 * ========================================
 */
public abstract class BaseFragment<P extends BasePresenter> extends Fragment implements BaseView {

    private View mRootView;
    protected Context mContext;
    public P mPresenter;

    Unbinder unbinder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
        mPresenter = initPresenter();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(setContentView(), container, false);
        unbinder = ButterKnife.bind(this, mRootView);
        initView();
        isViewInitiated = true;
        prepareFetchData();
        return mRootView;
    }

    /**
     * 让子类初始化相应的Presenter
     * @return
     */
    public abstract P initPresenter();

    @Override
    public void showLoadingDialog(String msg) {

    }

    @Override
    public void dismissLoadingDialog() {

    }

   /******************懒加载*****************/

    protected boolean isViewInitiated;
    protected boolean isVisibleToUser;
    protected boolean isDataInitiated;

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        this.isVisibleToUser = isVisibleToUser;
        prepareFetchData();
    }

    public boolean prepareFetchData() {
        return prepareFetchData(false);
    }

    public boolean prepareFetchData(boolean forceUpdate) {
        if (isVisibleToUser && isViewInitiated && (!isDataInitiated || forceUpdate)) {
            lazyLoad();
            isDataInitiated = true;
            return true;
        }
        return false;
    }


    /**
     * 加载页面布局文件
     * @return
     */
    protected abstract int setContentView();

    /**
     * 初始化布局变量
     */
    protected abstract void initView();

    /**
     * 加载要显示的数据
     */
    protected abstract void lazyLoad();

    @Override
    public void onDestroyView() {
        unbinder.unbind();
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        if(mPresenter != null) {
            //在Presenter中解绑View
            mPresenter.detach();
            mPresenter = null;
        }
        super.onDestroy();
    }
}
