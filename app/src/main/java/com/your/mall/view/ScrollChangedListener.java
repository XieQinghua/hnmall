package com.your.mall.view;


/**
 * Created by xieqinghua.
 * 创建时间：2016/9/01
 * 类描述：监听ScrollView滑动距离接口
 * 修改备注：
 */
public interface ScrollChangedListener {
    void onScrollChanged(CustomScrollView scrollView, int l, int t, int oldl, int oldt);
}
