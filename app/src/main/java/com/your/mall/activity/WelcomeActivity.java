package com.your.mall.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.your.mall.R;
import com.your.mall.application.MallApplication;
import com.your.mall.base.BaseActivity;
import com.your.mall.utils.MD5Utils;
import com.your.mall.utils.SharedPreferencesUtil;


/**
 * Created by xieqinghua.
 * 创建时间：2016/8/30
 * 类描述：程序的欢迎界面，此Acticity用于判断用户是否第一次登录程序
 * 修改备注：
 */
public class WelcomeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        boolean b = (boolean) SharedPreferencesUtil.getData(MallApplication.getInstance().getContext(), "is_first", true);
        Intent intent;
        if (b) {
            // 第一次进入应用，进入引导页面
            intent = new Intent(this, GuideActivity.class);
            startActivity(intent);
            SharedPreferencesUtil.saveData(MallApplication.getInstance().getContext(), "is_first", false);
            finish();
        } else {
            Log.e("MD5", MD5Utils.code32(this.getPackageName(), "UTF-8"));//获取应用的MD5签名
            // 不是第一次进入，进入应用程序的开屏页面
            intent = new Intent(this, SplashActivity.class);
            startActivity(intent);
            finish();
        }
    }
}