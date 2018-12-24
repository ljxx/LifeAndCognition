package com.ylixiang.market.mvp.fragment.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ylixiang.market.R;
import com.ylixiang.market.bean.AndroidDataBean;
import com.ylixiang.ylxcommonlib.base.GlideApp;

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
 * 创建日期：2018/12/21  下午3:17
 * <p>
 * 描 述：
 * <p>
 * ========================================
 */
public class RecyclerLinerAdapter extends BaseQuickAdapter<AndroidDataBean, BaseViewHolder> {

    public RecyclerLinerAdapter(int layoutResId, @Nullable List<AndroidDataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, AndroidDataBean item) {

        ImageView mImageView = helper.getView(R.id.m_img_iv);

        String mUrl = item.getUrl();
        mUrl = mUrl != null ? mUrl : "";

        List<String> mImgs = item.getImages();
        String mImgUrl = mImgs != null && mImgs.size() > 0 ? mImgs.get(0) : mUrl;
//        ImageLoader imageLoader = new ImageLoader(new GlideImageLoaderStrategy());
//        imageLoader.loadImage(mImageView.getContext(), new GlideImageLoaderConfig.Builder().url(mImgUrl).imageView(mImageView).build());
        GlideApp.with(mImageView.getContext()).load(mImgUrl).into(mImageView);

        String mTitle = item.getDesc();
        helper.setText(R.id.m_title_txt, mTitle);

    }
}
