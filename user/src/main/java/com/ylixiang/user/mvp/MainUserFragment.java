package com.ylixiang.user.mvp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ylixiang.user.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainUserFragment extends Fragment {


    public MainUserFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main_user, container, false);
    }

    public static MainUserFragment newInstance() {
        return new MainUserFragment();
    }

}
