package com.ylixiang.life.mvp.ui;

import android.os.Bundle;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.ylixiang.life.R;
import com.ylixiang.life.mvp.contact.TestContact;
import com.ylixiang.life.mvp.presenter.TestPresenter;
import com.ylixiang.ylxcommonlib.arouter.ARouterUtils;
import com.ylixiang.ylxcommonlib.base.BaseActivity;

@Route(path = ARouterUtils.LIFE_TEST_ACTIVITY)
public class TestActivity extends BaseActivity<TestContact.Presenter> implements TestContact.View {

    private TextView m_test_txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        m_test_txt = findViewById(R.id.m_test_txt);

        mPresenter.getData();
    }

    @Override
    public TestContact.Presenter initPresenter() {
        return new TestPresenter(this);
    }

    @Override
    public void setData(String str) {
        m_test_txt.setText(str);
    }
}
