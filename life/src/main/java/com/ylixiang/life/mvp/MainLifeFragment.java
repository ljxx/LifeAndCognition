package com.ylixiang.life.mvp;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.ylixiang.life.R;
import com.ylixiang.life.R2;
import com.ylixiang.life.bean.GanHuoBean;
import com.ylixiang.life.http.RetrofitFactory;
import com.ylixiang.ylxcommonlib.arouter.ARouterUtils;

import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
@Route(path = ARouterUtils.LIFE_FRAGMENT_MAIN)
public class MainLifeFragment extends Fragment {


    @BindView(R2.id.m_open_btn)
    TextView mOpenBtn;
    @BindView(R2.id.m_test_iv)
    ImageView mTestIv;

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
//                ARouter.getInstance().build(ARouterUtils.MARK_MAIN_ACTIVITY).withString(ConstantUtils.MARKET_ACTIVITY_SEND_HELLO, "嘿嘿嘿，我来了。。。").navigation();
                ARouter.getInstance().build(ARouterUtils.LIFE_TEST_ACTIVITY).navigation();
            }
        });

        fillData();

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void fillData() {
        RetrofitFactory.getInstance()
                .test() //接口
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        //1、将这个请求的Disposable添加进入CompositeDisposable同一管理（在封装的presenter中）


                        //2、访问网络显示Dialog
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<GanHuoBean>() {
                    @Override
                    public void accept(GanHuoBean testBean) throws Exception {
                        //1、消失Dialogs
                        String mImgUrl = testBean.getResults().getAndroid().get(0).getImages().get(0);
                        mOpenBtn.setText(mImgUrl);

                        //2、设置数据
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        //1、隐藏Dialog

                        //2、打印错误信息
                        Log.e("TAG", "exception:");
                    }
                });
    }
}
