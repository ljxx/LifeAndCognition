package com.ylixiang.www.utils;

import android.support.v4.app.Fragment;

import com.alibaba.android.arouter.launcher.ARouter;
import com.ylixiang.entertainment.mvp.MainEntertainmentFragment;
import com.ylixiang.life.mvp.MainLifeFragment;
import com.ylixiang.market.mvp.MainMarketFragment;
import com.ylixiang.tool.mvp.MainToolFragment;
import com.ylixiang.user.mvp.MainUserFragment;
import com.ylixiang.ylxcommonlib.arouter.ARouterUtils;

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
 * 创建日期：2018/12/14  下午3:36
 * <p>
 * 描 述：
 * <p>
 * ========================================
 */
public class FragmentUtils {

    /**
     * 生活首页Fragment
     * @return
     */
    public static Fragment getLifeFragment() {
        MainLifeFragment mLifeFragment = (MainLifeFragment) ARouter.getInstance().build(ARouterUtils.LIFE_FRAGMENT_MAIN).navigation();
        return mLifeFragment;
    }

    /**
     * 集市首页Fragment
     * @return
     */
    public static Fragment getMarketFragment() {
        MainMarketFragment mMarketFragment = (MainMarketFragment) ARouter.getInstance().build(ARouterUtils.MARKET_FRAGMENT_MAIN).navigation();
        return mMarketFragment;
    }

    /**
     * 工具首页Fragment
     * @return
     */
    public static Fragment getToolFragment() {
        MainToolFragment mToolFragment = (MainToolFragment) ARouter.getInstance().build(ARouterUtils.TOOL_FRAGMENT_MAIN).navigation();
        return mToolFragment;
    }

    /**
     * 娱乐首页Fragment
     * @return
     */
    public static Fragment getEntertainment() {
        MainEntertainmentFragment mEntertainmentFragment = (MainEntertainmentFragment) ARouter.getInstance().build(ARouterUtils.ENTERTAINMENT_FRAGMENT_MAIN).navigation();
        return mEntertainmentFragment;
    }

    /**
     * 用户首页Fragment
     * @return
     */
    public static Fragment getUserFragment() {
        MainUserFragment mUserFragment = (MainUserFragment) ARouter.getInstance().build(ARouterUtils.USER_FRAGMENT_MAIN).navigation();
        return mUserFragment;
    }

}
