package com.ylixiang.market.mvp.fragment.contact;

import com.ylixiang.market.bean.AndroidDataBean;
import com.ylixiang.ylxcommonlib.base.BasePresenter;
import com.ylixiang.ylxcommonlib.base.BaseView;

import java.util.List;

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
 * 创建日期：2018/12/21  下午2:28
 * <p>
 * 描 述：
 * <p>
 * ========================================
 */
public interface RecyclerViewContact {
    interface View extends BaseView {

        /**
         * 设置数据
         * @param list
         */
        void setData(List<AndroidDataBean> list);

        /**
         * 数据加载完成
         */
        void setLoadEnable();

        /**
         * 继续加载更多
         */
        void setLoadMoreComplete();
    }

    interface Presenter extends BasePresenter {
        /**
         * 获取数据
         */
        void getData();

        /**
         * 获取福利数据
         */
        void getGrilData();

        void setRefreshing();
    }
}
