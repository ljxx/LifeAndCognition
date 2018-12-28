package com.ylixiang.market;

import android.os.Bundle;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.ylixiang.ylxcommonlib.arouter.ConstantUtils;

import androidx.appcompat.app.AppCompatActivity;

/**
 * ========================================
 * 
 * 版 权：仅供学习使用
 * 
 * 作 者：杨理想
 * 
 * 微 信：lanjixingxun
 * 
 * Q  Q：1099740455
 * 
 * 创建日期：2018/12/4 下午6:16
 * 
 * 描 述：
 * 
 * ========================================
 */
@Route(path = "/market/MarketMainActivity")
public class MarketMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market_main);

        String mHello = getIntent().getStringExtra(ConstantUtils.MARKET_ACTIVITY_SEND_HELLO);

        Toast.makeText(this, mHello, Toast.LENGTH_LONG).show();

    }
}
