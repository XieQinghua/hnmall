package com.your.mall.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

import com.your.mall.R;
import com.your.mall.base.BaseSwipeBackActivity;
import com.your.mall.http.MallApi;
import com.your.mall.utils.ErrorJavaScript;
import com.your.mall.utils.NetUtils;

/**
 * Created by xieqinghua.
 * 创建时间：2016/11/14
 * 类描述：拼团活动规则
 * 修改备注：
 */
public class PintuanRuleActivity extends BaseSwipeBackActivity {
    private TextView tv_title;
    private WebView web_rule;
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
        setContentView(R.layout.activity_pintuan_rule);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        init();
    }

    private void init() {
        tv_title = (TextView) findViewById(R.id.tv_title_center);
        tv_title.setText("团购规则");
        web_rule = (WebView) findViewById(R.id.web_rule);
        name = getIntent().getStringExtra("name");
        loadingUrl();
    }

    /**
     * 加载链接
     */
    private void loadingUrl() {
        //设置字符集编码
        web_rule.getSettings().setDefaultTextEncodingName("UTF-8");
        //开启JavaScript支持
        web_rule.getSettings().setJavaScriptEnabled(true);

        String url = MallApi.SERVER_WEB_URL + name + "?from=android";

        if (NetUtils.isNetworkConnected(PintuanRuleActivity.this)) {//判断网络状态
            web_rule.loadUrl(url);
            //监听webwiew的下载进度
            web_rule.setWebViewClient(new WebViewClient() {

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
            Toast.makeText(PintuanRuleActivity.this, "网络连接异常，请检查网络", Toast.LENGTH_SHORT).show();
            web_rule.loadUrl("file:///android_asset/error.html");
            web_rule.addJavascriptInterface(new ErrorJavaScript(PintuanRuleActivity.this, handler), "android");
        }
    }
}
