package com.ylixiang.life;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;

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
@Route(path = "/life/LifeMainActivity")
public class LifeMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life_main);
    }
}
