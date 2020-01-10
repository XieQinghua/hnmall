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
import com.your.mall.http.MallApi;
import com.your.mall.utils.ErrorJavaScript;
import com.your.mall.utils.NetUtils;

/**
 * Created by xieqinghua.
 * 创建时间：2016/9/30
 * 类描述：店铺主页
 * 修改备注：
 */
public class ShopHomepageActivity extends BaseSwipeBackActivity {
    private TextView tv_title;
    private WebView web_shop_homepage;
    private String shop_id;
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
        setContentView(R.layout.activity_shop_homepage);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        init();
    }

    private void init() {
        tv_title = (TextView) findViewById(R.id.tv_title_center);
        tv_title.setText("店铺主页");
        if (getIntent().getStringExtra("shop_id") != null) {
            shop_id = getIntent().getStringExtra("shop_id");
        } else {
            shop_id = "";
        }
        web_shop_homepage = (WebView) findViewById(R.id.web_shop_homepage);

        loadingUrl();
    }

    /**
     * 加载链接
     */
    private void loadingUrl() {
        //设置字符集编码
        web_shop_homepage.getSettings().setDefaultTextEncodingName("UTF-8");

        web_shop_homepage.getSettings().setAllowFileAccess(true);
        web_shop_homepage.getSettings().setUseWideViewPort(true);
        web_shop_homepage.getSettings().setLoadWithOverviewMode(true);

        //开启JavaScript支持
        web_shop_homepage.getSettings().setJavaScriptEnabled(true);
        String userAgent = web_shop_homepage.getSettings().getUserAgentString();
        web_shop_homepage.getSettings().setUserAgentString(userAgent);
        web_shop_homepage.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        web_shop_homepage.getSettings().setAllowFileAccess(true);
        web_shop_homepage.getSettings().setAppCacheEnabled(true);
        web_shop_homepage.getSettings().setDomStorageEnabled(true);
        web_shop_homepage.getSettings().setDatabaseEnabled(true);

        String url = MallApi.SERVER_WEB_URL + "/shopcenter.html?shop_id=" + shop_id + "&from=android";

        if (NetUtils.isNetworkConnected(ShopHomepageActivity.this)) {//判断网络状态
            //携带Cookie
            CookieSyncManager.createInstance(this);
            CookieManager cookieManager = CookieManager.getInstance();
            cookieManager.setAcceptCookie(true);
            cookieManager.removeSessionCookie();// 移除

            String cookieString = MallApplication.getInstance().getCookie(ShopHomepageActivity.this);
            cookieManager.setCookie(url, cookieString);
            CookieSyncManager.getInstance().sync();

            web_shop_homepage.loadUrl(url);
            //监听webwiew的下载进度
            web_shop_homepage.setWebViewClient(new WebViewClient() {

                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    if (url.contains("/wap/item.html?item_id=")) {//商品详情
                        String item_id = url.substring(url.indexOf("=") + 1);
                        Intent intent = new Intent(ShopHomepageActivity.this, GoodsDetailsActivity.class);
                        intent.putExtra("item_id", item_id);
                        startActivity(intent);
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
            Toast.makeText(ShopHomepageActivity.this, "网络连接异常，请检查网络", Toast.LENGTH_SHORT).show();
            web_shop_homepage.loadUrl("file:///android_asset/error.html");
            web_shop_homepage.addJavascriptInterface(new ErrorJavaScript(ShopHomepageActivity.this, handler), "android");
        }
    }
}
