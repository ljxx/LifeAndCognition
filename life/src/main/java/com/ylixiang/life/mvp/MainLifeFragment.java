package com.ylixiang.life.mvp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.ylixiang.life.R;
import com.ylixiang.life.R2;
import com.ylixiang.ylxcommonlib.arouter.ARouterUtils;
import com.ylixiang.ylxcommonlib.arouter.ConstantUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
@Route(path = ARouterUtils.LIFE_FRAGMENT_MAIN)
public class MainLifeFragment extends Fragment {


    @BindView(R2.id.m_open_btn)
    TextView mOpenBtn;
    Unbinder unbinder;

    public MainLifeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main_life, container, false);
        unbinder = ButterKnife.bind(this, view);
        mOpenBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build(ARouterUtils.MARK_MAIN_ACTIVITY).withString(ConstantUtils.MARKET_ACTIVITY_SEND_HELLO, "嘿嘿嘿，我来了。。。").navigation();
            }
        });
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
