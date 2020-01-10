package com.your.mall.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.your.mall.R;
import com.your.mall.application.MallApplication;
import com.your.mall.base.BaseSwipeBackActivity;

/**
 * Created by xieqinghua.
 * 创建时间：2016/9/2
 * 类描述：账户与安全
 * 修改备注：
 */
public class SafeActivity extends BaseSwipeBackActivity implements View.OnClickListener {
    private TextView tv_title;
    private TextView tv_user_name, tv_deposit, tv_point, tv_phone_number, tv_register_time, tv_change_passwords;

    private TextView tv_test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_safe);
        init();
    }

    private void init() {
        tv_title = (TextView) findViewById(R.id.tv_title_center);
        tv_user_name = (TextView) findViewById(R.id.tv_user_name);
        tv_deposit = (TextView) findViewById(R.id.tv_deposit);
        tv_point = (TextView) findViewById(R.id.tv_point);
        tv_phone_number = (TextView) findViewById(R.id.tv_phone_number);
        tv_register_time = (TextView) findViewById(R.id.tv_register_time);
        tv_change_passwords = (TextView) findViewById(R.id.tv_change_passwords);

        tv_test = (TextView) findViewById(R.id.tv_test);

        tv_title.setText("账户与安全");

        tv_user_name.setText(getIntent().getStringExtra("username"));
        tv_deposit.setText("￥" + getIntent().getStringExtra("deposit"));
        tv_point.setText(getIntent().getStringExtra("point"));
        tv_phone_number.setText(MallApplication.getInstance().getMobileNumber(SafeActivity.this));
        tv_register_time.setText(MallApplication.getInstance().getStringTime(MallApplication.getInstance().getRegTime(SafeActivity.this)));
        tv_change_passwords.setOnClickListener(this);

        tv_test.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_change_passwords:
                startActivity(new Intent(SafeActivity.this, ChangePasswordActivity.class));
                break;
            case R.id.tv_test:
                startActivity(new Intent(SafeActivity.this, TestActivity.class));
                break;
        }
    }
}
