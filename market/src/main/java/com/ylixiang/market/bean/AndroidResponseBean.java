package com.ylixiang.market.bean;

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
 * 创建日期：2018/12/21  下午2:21
 * <p>
 * 描 述：
 * <p>
 * ========================================
 */
public class AndroidResponseBean {
    private boolean error;
    private List<AndroidDataBean> results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<AndroidDataBean> getResults() {
        return results;
    }

    public void setResults(List<AndroidDataBean> results) {
        this.results = results;
    }
}
