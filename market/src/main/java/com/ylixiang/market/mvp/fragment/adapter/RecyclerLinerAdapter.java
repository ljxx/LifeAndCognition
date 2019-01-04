package com.ylixiang.market.mvp.fragment.adapter;

import android.app.Activity;
import androidx.annotation.Nullable;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ylixiang.market.R;
import com.ylixiang.market.bean.AndroidDataBean;
import com.ylixiang.ylxcommonlib.base.GlideApp;

import java.util.ArrayList;
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

    private List<Integer> mHeights;

    private int mType = 0;

    public RecyclerLinerAdapter(int layoutResId, @Nullable List<AndroidDataBean> data) {
        super(layoutResId, data);
        mHeights = new ArrayList<>();
    }

    public void setType(int type) {
        this.mType = type;
    }

    @Override
    protected void convert(BaseViewHolder helper, AndroidDataBean item) {

        ImageView mImageView = helper.getView(R.id.m_img_iv);

        switch (mType) {
            case 0:
            case 1:
                break;
            case 2:
                /**************为瀑布流设置高度****且，防止刷新后瀑布流变更位置，这里将第一次加载的位置记录下来，复用*********/
                int width = ((Activity) mImageView.getContext()).getWindowManager().getDefaultDisplay().getWidth();
                ViewGroup.LayoutParams params = mImageView.getLayoutParams();
                //设置图片的相对于屏幕的宽高比
                params.width = width/3;
                if(mHeights.size() <= helper.getLayoutPosition()) {
                    int mHeight = (int) (200 + Math.random() * 400);
                    mHeights.add(mHeight);
                } else {
                }
//        params.height = mHeight;
                params.height = mHeights.get(helper.getAdapterPosition());
                mImageView.setLayoutParams(params);

                /**************为瀑布流设置高度****结束*********/
                break;
        }


        String mUrl = item.getUrl();
        mUrl = mUrl != null ? mUrl : "";

        List<String> mImgs = item.getImages();
//        String mImgUrl = mImgs != null && mImgs.size() > 0 ? mImgs.get(0) : mUrl;
//        ImageLoader imageLoader = new ImageLoader(new GlideImageLoaderStrategy());
//        imageLoader.loadImage(mImageView.getContext(), new GlideImageLoaderConfig.Builder().url(mImgUrl).imageView(mImageView).build());
//        GlideApp.with(mImageView.getContext()).load(mImgUrl).into(mImageView);

        String mTitle = item.getDesc();
        helper.setText(R.id.m_title_txt, mTitle);

    }
}
