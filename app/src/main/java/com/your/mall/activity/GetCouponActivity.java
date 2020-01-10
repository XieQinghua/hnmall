package com.your.mall.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
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
import com.your.mall.http.MallApi;
import com.your.mall.utils.ErrorJavaScript;
import com.your.mall.utils.NetUtils;

/**
 * Created by xieqinghua.
 * 创建时间：2016/11/21
 * 类描述：领劵中心
 * 修改备注：
 */
public class GetCouponActivity extends BaseSwipeBackActivity {
    private TextView tv_title;
    private WebView web_get_coupon;
    private String name;
    public Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case 1:
                    loadingUrl();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_coupon);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        init();
    }

    private void init() {
        tv_title = (TextView) findViewById(R.id.tv_title_center);
        tv_title.setText("领劵中心");
        if (getIntent().getStringExtra("name") != null) {
            name = getIntent().getStringExtra("name");
        } else {
            name = "";
        }
        web_get_coupon = (WebView) findViewById(R.id.web_get_coupon);

        loadingUrl();
    }

    /**
     * 加载链接
     */
    private void loadingUrl() {
        //设置字符集编码
        web_get_coupon.getSettings().setDefaultTextEncodingName("UTF-8");

        web_get_coupon.getSettings().setAllowFileAccess(true);
        web_get_coupon.getSettings().setUseWideViewPort(true);
        web_get_coupon.getSettings().setLoadWithOverviewMode(true);

        //开启JavaScript支持
        web_get_coupon.getSettings().setJavaScriptEnabled(true);
        String userAgent = web_get_coupon.getSettings().getUserAgentString();
        web_get_coupon.getSettings().setUserAgentString(userAgent);
        web_get_coupon.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        web_get_coupon.getSettings().setAllowFileAccess(true);
        web_get_coupon.getSettings().setAppCacheEnabled(true);
        web_get_coupon.getSettings().setDomStorageEnabled(true);
        web_get_coupon.getSettings().setDatabaseEnabled(true);

        String url = MallApi.SERVER_WEB_URL + name + "?from=android";

        if (NetUtils.isNetworkConnected(GetCouponActivity.this)) {//判断网络状态
            //携带Cookie
            CookieSyncManager.createInstance(this);
            CookieManager cookieManager = CookieManager.getInstance();
            cookieManager.setAcceptCookie(true);
            cookieManager.removeSessionCookie();// 移除

            String cookieString = MallApplication.getInstance().getCookie(GetCouponActivity.this);
            cookieManager.setCookie(url, cookieString);
            CookieSyncManager.getInstance().sync();

            web_get_coupon.loadUrl(url);
            //监听webwiew的下载进度
            web_get_coupon.setWebViewClient(new WebViewClient() {

                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    view.loadUrl(url);
                    return false;
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
            Toast.makeText(GetCouponActivity.this, "网络连接异常，请检查网络", Toast.LENGTH_SHORT).show();
            web_get_coupon.loadUrl("file:///android_asset/error.html");
            web_get_coupon.addJavascriptInterface(new ErrorJavaScript(GetCouponActivity.this, handler), "android");
        }
    }
}
