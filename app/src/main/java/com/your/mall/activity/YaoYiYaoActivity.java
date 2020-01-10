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
 * 类描述：摇一摇
 * 修改备注：
 */
public class YaoYiYaoActivity extends BaseSwipeBackActivity {
    private TextView tv_title;
    private WebView web_yaoyiyao;
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
        setContentView(R.layout.activity_yaoyiyao);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        init();
    }

    private void init() {
        tv_title = (TextView) findViewById(R.id.tv_title_center);
        tv_title.setText("摇一摇");
        if (getIntent().getStringExtra("name") != null) {
            name = getIntent().getStringExtra("name");
        } else {
            name = "";
        }
        web_yaoyiyao = (WebView) findViewById(R.id.web_yaoyiyao);

        loadingUrl();
    }

    /**
     * 加载链接
     */
    private void loadingUrl() {
        //设置字符集编码
        web_yaoyiyao.getSettings().setDefaultTextEncodingName("UTF-8");

        web_yaoyiyao.getSettings().setAllowFileAccess(true);
        web_yaoyiyao.getSettings().setUseWideViewPort(true);
        web_yaoyiyao.getSettings().setLoadWithOverviewMode(true);

        //开启JavaScript支持
        web_yaoyiyao.getSettings().setJavaScriptEnabled(true);
        String userAgent = web_yaoyiyao.getSettings().getUserAgentString();
        web_yaoyiyao.getSettings().setUserAgentString(userAgent);
        web_yaoyiyao.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        web_yaoyiyao.getSettings().setAllowFileAccess(true);
        web_yaoyiyao.getSettings().setAppCacheEnabled(true);
        web_yaoyiyao.getSettings().setDomStorageEnabled(true);
        web_yaoyiyao.getSettings().setDatabaseEnabled(true);

        String url = MallApi.SERVER_WEB_URL + name + "?from=android";

        if (NetUtils.isNetworkConnected(YaoYiYaoActivity.this)) {//判断网络状态
            //携带Cookie
            CookieSyncManager.createInstance(this);
            CookieManager cookieManager = CookieManager.getInstance();
            cookieManager.setAcceptCookie(true);
            cookieManager.removeSessionCookie();// 移除

            String cookieString = MallApplication.getInstance().getCookie(YaoYiYaoActivity.this);
            cookieManager.setCookie(url, cookieString);
            CookieSyncManager.getInstance().sync();

            web_yaoyiyao.loadUrl(url);
            //监听webwiew的下载进度
            web_yaoyiyao.setWebViewClient(new WebViewClient() {

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
            Toast.makeText(YaoYiYaoActivity.this, "网络连接异常，请检查网络", Toast.LENGTH_SHORT).show();
            web_yaoyiyao.loadUrl("file:///android_asset/error.html");
            web_yaoyiyao.addJavascriptInterface(new ErrorJavaScript(YaoYiYaoActivity.this, handler), "android");
        }
    }
}
