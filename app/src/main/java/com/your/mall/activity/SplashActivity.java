package com.your.mall.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.your.mall.R;
import com.your.mall.application.MallApplication;
import com.your.mall.base.BaseActivity;
import com.your.mall.bean.LoginTepinBean;
import com.your.mall.http.MallApi;
import com.your.mall.utils.JsonValidator;
import com.your.mall.utils.MyCallBack;
import com.your.mall.utils.NetUtils;
import com.your.mall.utils.ParseUtils;
import com.your.mall.utils.XUtils;

import org.xutils.http.cookie.DbCookieStore;

import java.net.HttpCookie;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by xieqinghua.
 * 创建时间：2016/8/30
 * 类描述：开屏页
 * 修改备注：
 */
public class SplashActivity extends BaseActivity {
    private static final String TAG = "SplashActivity";
    private String cookieString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

//        Timer timer = new Timer();
//        TimerTask task = new TimerTask() {
//            public void run() {
        //自动跳转首页
        if (MallApplication.getInstance().getAutoLogin(SplashActivity.this)) {//已登录状态下，自动登陆获取
            if (!NetUtils.isNetworkConnected(SplashActivity.this)) {
                //判断网络是否可用
                Toast.makeText(SplashActivity.this, R.string.network_is_not_good, Toast.LENGTH_SHORT).show();
            }
            loginTepin();
        } else {
            Log.e(TAG, "主程序没有登陆");
            startActivity(new Intent(SplashActivity.this, MainActivity.class));
            finish();
        }
    }
//        };
//        timer.schedule(task, 500);session
//    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    private void loginTepin() {
        Map<String, String> map = new HashMap<>();
        map.put("mobileNumber", MallApplication.getInstance().getMobileNumber(SplashActivity.this));
        map.put("openId", MallApplication.getInstance().getOpenId(SplashActivity.this));
        //sso登录成功，登录服务平台
//        showDialog(LOADING_DIALOG);
        XUtils.Post(MallApi.APP_LOGIN_TEPIN, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                Log.e("result", result);
                if (JsonValidator.validate(result)) {
                    LoginTepinBean loginTepinBean = ParseUtils.parseLoginTepinBean(result);
                    if (loginTepinBean.getCode().equals("0")) {
//                        Toast.makeText(SplashActivity.this, "登陆web成功", Toast.LENGTH_SHORT).show();
                        Log.e(TAG, "登陆web成功");

                        DbCookieStore instance = DbCookieStore.INSTANCE;
                        List<HttpCookie> cookies = instance.getCookies();
//                        String str = "";
//                        for (int i = 0; i < cookies.size(); i++) {
//                            str += cookies.get(i).getName() + "=" + cookies.get(i).getValue() + ";";
//                        }
//                        cookieString = str.substring(0, str.length() - 1);
//                        Log.e(TAG, cookieString);
                        for (HttpCookie cookie : cookies) {
                            String name = cookie.getName();
                            String value = cookie.getValue();
                            if ("s".equals(name)) {
                                cookieString = name + "=" + value;
                                break;
                            }
                        }

//                        removeDialog();

                        //保存Cookie
                        MallApplication.getInstance().setCookie(SplashActivity.this, cookieString);
                        //跳转页面
                        startActivity(new Intent(SplashActivity.this, MainActivity.class));
                        finish();
                    } else {
                    }
                } else {
                    Toast.makeText(SplashActivity.this, R.string.network_is_not_good, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
            }
        });
    }
}
