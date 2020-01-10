package com.your.mall.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.WindowManager;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

import com.your.mall.R;
import com.your.mall.application.MallApplication;
import com.your.mall.base.BaseSwipeBackActivity;
import com.your.mall.bean.LoginTepinBean;
import com.your.mall.http.MallApi;
import com.your.mall.utils.ErrorJavaScript;
import com.your.mall.utils.JsonValidator;
import com.your.mall.utils.MyCallBack;
import com.your.mall.utils.NetUtils;
import com.your.mall.utils.ParseUtils;
import com.your.mall.utils.XUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xieqinghua.
 * 创建时间：2016/9/19
 * 类描述：订单支付页面
 * 修改备注：
 */
public class PaymentActivity extends BaseSwipeBackActivity {
    private TextView tv_title;
    private WebView web_payment;
    private String url;
    public Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case 2:
                    loginTepin();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        init();
    }

    private void init() {
        tv_title = (TextView) findViewById(R.id.tv_title_center);
        tv_title.setText("订单支付");

        web_payment = (WebView) findViewById(R.id.web_payment);
        url = getIntent().getStringExtra("url") + "&from=android";

        loadingUrl();
    }

    /**
     * 加载链接
     */
    private void loadingUrl() {
        //设置字符集编码
        web_payment.getSettings().setDefaultTextEncodingName("UTF-8");
        web_payment.getSettings().setAllowFileAccess(true);
        web_payment.getSettings().setUseWideViewPort(true);
        web_payment.getSettings().setLoadWithOverviewMode(true);
        //开启JavaScript支持
        web_payment.getSettings().setJavaScriptEnabled(true);
        String userAgent = web_payment.getSettings().getUserAgentString();
        web_payment.getSettings().setUserAgentString(userAgent);
        web_payment.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        web_payment.getSettings().setAllowFileAccess(true);
        web_payment.getSettings().setAppCacheEnabled(true);
        web_payment.getSettings().setDomStorageEnabled(true);
        web_payment.getSettings().setDatabaseEnabled(true);

        if (NetUtils.isNetworkConnected(PaymentActivity.this)) {//判断网络状态

//            web_payment.loadUrl("http://nbh.e9448.com/android_login.html");
//            web_payment.addJavascriptInterface(new LoginJavaScript(PaymentActivity.this, handler), "android");

            //携带Cookie
            CookieSyncManager.createInstance(this);
            CookieManager cookieManager = CookieManager.getInstance();
            cookieManager.setAcceptCookie(true);
            cookieManager.removeSessionCookie();// 移除

            String cookieString = MallApplication.getInstance().getCookie(PaymentActivity.this);
//            Toast.makeText(PaymentActivity.this, cookieString, Toast.LENGTH_SHORT).show();
            Log.e("pament", cookieString);

            cookieManager.setCookie(url, cookieString);
            CookieSyncManager.getInstance().sync();

            web_payment.loadUrl(url);
//            web_payment.addJavascriptInterface(new LoginJavaScript(PaymentActivity.this, handler), "android");
            //监听webwiew的下载进度
            web_payment.setWebViewClient(new WebViewClient() {
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    if (url.contains("tradelist.html")) {//跳转订单列表
                        Intent intent = new Intent(PaymentActivity.this, OrderManagementActivity.class);
                        intent.putExtra("index", 0);
                        startActivity(intent);
                        finish();
                        return true;
                    } else if (url.contains("bargain-share.html")) {
                        //砍价商品支付成功 跳转砍价完成界面
                        Intent intent = new Intent(PaymentActivity.this, MyBargainActivity.class);
                        startActivity(intent);
                        finish();
                        return true;
                    } else if (url.contains("pintuan-view.html")) {
                        //非抽奖的拼团商品支付成功 跳转非抽奖的拼团完成界面
                        Intent intent = new Intent(PaymentActivity.this, MyPintuanActivity.class);
                        startActivity(intent);
                        finish();
                        return true;
                    } else if (url.contains("pintuancj-view.html")) {
                        //抽奖的拼团商品支付成功 跳转抽奖的拼团完成界面
                        Intent intent = new Intent(PaymentActivity.this, MyPintuanActivity.class);
                        startActivity(intent);
                        finish();
                        return true;
                    } else {
                        view.loadUrl(url);
                        return false;
                    }
                }

                @Override
                public void onPageStarted(WebView view, String url, Bitmap favicon) {
                    super.onPageStarted(view, url, favicon);
                    showDialog(LOADING_DIALOG);
                }

                @Override
                public void onPageFinished(WebView view, String url) {
                    super.onPageFinished(view, url);
                    removeDialog();
                }

                @Override
                public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                    super.onReceivedError(view, errorCode, description, failingUrl);
                }
            });

        } else {
            //进行无网络或错误处理
            Toast.makeText(PaymentActivity.this, "网络连接异常，请检查网络", Toast.LENGTH_SHORT).show();
            web_payment.loadUrl("file:///android_asset/error.html");
            web_payment.addJavascriptInterface(new ErrorJavaScript(PaymentActivity.this, handler), "android");
        }
    }

    private void loginTepin() {
        if (MallApplication.getInstance().getAutoLogin(PaymentActivity.this)) {//已登录状态下
            Map<String, String> map = new HashMap<>();
            map.put("mobileNumber", MallApplication.getInstance().getMobileNumber(PaymentActivity.this));
            map.put("openId", MallApplication.getInstance().getOpenId(PaymentActivity.this));
            //sso登录成功，登录服务平台
            XUtils.Post(MallApi.APP_LOGIN_TEPIN, map, new MyCallBack<String>() {
                @Override
                public void onSuccess(String result) {
                    super.onSuccess(result);
                    Log.e("result", result);
                    if (JsonValidator.validate(result)) {
                        LoginTepinBean loginTepinBean = ParseUtils.parseLoginTepinBean(result);
                        if (loginTepinBean.getCode().equals("0")) {
//                            Toast.makeText(PaymentActivity.this, "登陆web成功", Toast.LENGTH_SHORT).show();
                            Log.e("login_info", "登陆web成功");
                        } else {
                        }
                    } else {
                        Toast.makeText(PaymentActivity.this, R.string.unable_to_get_data, Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onError(Throwable ex, boolean isOnCallback) {
                    super.onError(ex, isOnCallback);
                }
            });
        } else {
//            Toast.makeText(PaymentActivity.this, "主程序没有登陆", Toast.LENGTH_SHORT).show();
            Log.e("login_info", "主程序没有登陆");
        }
    }
}
