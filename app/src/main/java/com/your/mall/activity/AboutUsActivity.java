package com.your.mall.activity;

import android.os.Bundle;
import android.widget.TextView;
import com.your.mall.R;
import com.your.mall.base.BaseSwipeBackActivity;

/**
 * Created by xieqinghua.
 * 创建时间：2016/9/2
 * 类描述：关于我们
 * 修改备注：
 */
public class AboutUsActivity extends BaseSwipeBackActivity {
    private TextView tv_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        tv_title = (TextView) findViewById(R.id.tv_title_center);
        tv_title.setText("关于我们");
    }
}
