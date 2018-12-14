package com.ylixiang.entertainment.mvp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.ylixiang.entertainment.R;
import com.ylixiang.ylxcommonlib.arouter.ARouterUtils;

/**
 * A simple {@link Fragment} subclass.
 */
@Route(path = ARouterUtils.ENTERTAINMENT_FRAGMENT_MAIN)
public class MainEntertainmentFragment extends Fragment {


    public MainEntertainmentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main_entertainment, container, false);
    }

}
