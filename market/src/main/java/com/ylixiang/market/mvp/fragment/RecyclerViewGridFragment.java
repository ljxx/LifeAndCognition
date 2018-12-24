package com.ylixiang.market.mvp.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ylixiang.market.R;
import com.ylixiang.market.bean.AndroidDataBean;
import com.ylixiang.market.mvp.fragment.contact.RecyclerViewContact;
import com.ylixiang.market.mvp.fragment.presenter.RecyclerViewPresenter;
import com.ylixiang.ylxcommonlib.base.BaseFragment;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecyclerViewGridFragment extends BaseFragment<RecyclerViewContact.Presenter> implements RecyclerViewContact.View {

    public RecyclerViewGridFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recycler_view_liner, container, false);
    }

    @Override
    public RecyclerViewContact.Presenter initPresenter() {
        return new RecyclerViewPresenter(this);
    }

    @Override
    public void setData(List<AndroidDataBean> list) {

    }

    @Override
    public void setLoadEnable() {

    }

    @Override
    public void setLoadMoreComplete() {

    }
}
