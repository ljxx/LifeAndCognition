package com.ylixiang.life.mvp.presenter;

import android.util.Log;

import com.ylixiang.life.mvp.contact.TestContact;
import com.ylixiang.ylxcommonlib.base.BasePresenterImpl;
import com.ylixiang.ylxcommonlib.bean.GanHuoBean;
import com.ylixiang.ylxcommonlib.http.RetrofitFactory;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

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
 * 创建日期：2018/12/18  下午3:53
 * <p>
 * 描 述：
 * <p>
 * ========================================
 */
public class TestPresenter extends BasePresenterImpl<TestContact.View> implements TestContact.Presenter {

    private TestContact.View mView;

    public TestPresenter(TestContact.View view) {
        super(view);
        this.mView = view;
    }

    /**
     * 获取数据
     */
    @Override
    public void getData() {
        RetrofitFactory.getInstance()
                .test()
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        addDisposable(disposable); //请求加入管理
                        mView.showLoadingDialog("加载中。。。");

                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<GanHuoBean>() {
                    @Override
                    public void accept(GanHuoBean testBean) throws Exception {
                        //1、消失Dialogs
                        String mImgUrl = testBean.getResults().getAndroid().get(0).getImages().get(0);

                        mView.dismissLoadingDialog();
                        mView.setData(mImgUrl);

                        //2、设置数据
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        //1、隐藏Dialog
                        mView.dismissLoadingDialog();

                        //2、打印错误信息
                        Log.e("TAG", "exception:");
                    }
                });
    }
}
