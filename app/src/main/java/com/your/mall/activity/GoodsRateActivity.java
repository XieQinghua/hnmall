package com.your.mall.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.your.mall.R;
import com.your.mall.base.BaseSwipeBackActivity;

/**
 * Created by xieqinghua.
 * 创建时间：2016/10/16
 * 类描述：产品评价
 * 修改备注：
 */
public class GoodsRateActivity extends BaseSwipeBackActivity {
    private TextView tv_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_rate);
        tv_title = (TextView) findViewById(R.id.tv_title_center);
        tv_title.setText("产品评价");
    }
}