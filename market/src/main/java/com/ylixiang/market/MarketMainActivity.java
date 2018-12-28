package com.ylixiang.market;

import android.os.Bundle;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.ylixiang.market.mvp.MainMarketFragment;
import com.ylixiang.ylxcommonlib.arouter.ConstantUtils;

import androidx.appcompat.app.AppCompatActivity;

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
 * 创建日期：2018/12/4 下午6:16
 * <p>
 * 描 述：
 * <p>
 * ========================================
 */
@Route(path = "/market/MarketMainActivity")
public class MarketMainActivity extends AppCompatActivity {

    private MainMarketFragment mMainMarketFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market_main);

        String mHello = getIntent().getStringExtra(ConstantUtils.MARKET_ACTIVITY_SEND_HELLO);

        Toast.makeText(this, mHello, Toast.LENGTH_LONG).show();

        mMainMarketFragment = new MainMarketFragment();

        getSupportFragmentManager().beginTransaction().add(R.id.m_main_frame_layout, mMainMarketFragment).commit();

    }
}
