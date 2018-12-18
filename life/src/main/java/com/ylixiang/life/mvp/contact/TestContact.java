package com.ylixiang.life.mvp.contact;

import com.ylixiang.ylxcommonlib.base.BasePressenter;
import com.ylixiang.ylxcommonlib.base.BaseView;

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
 * 创建日期：2018/12/18  下午3:45
 * <p>
 * 描 述：首先先思考view需要设置数据所有view中需要一个setData方法
 * presenter需要去访问网络所以需要一个getData方法。
 * <p>
 * ========================================
 */
public interface TestContact {

    interface View extends BaseView {

        /**
         * 设置数据
         * @param str
         */
        void setData(String str);
    }

    interface Presenter extends BasePressenter {
        /**
         * 获取数据
         */
        void getData();
    }
}
