package com.your.mall.utils;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.webkit.JavascriptInterface;

/**
 * Created by xieqinghua.
 * 创建时间：2016/9/22
 * 类描述：登陆交互类
 * 修改备注：
 */
public class LoginJavaScript {
    private Handler handler = null;
    private Context context;

    public LoginJavaScript(Context context, Handler handler) {
        this.handler = handler;
        this.context = context;
    }

    /**
     * 登陆web
     * <p/>
     * javascript:android.loginTepin();
     */
    @JavascriptInterface
    public void loginTepin() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                Message msg = Message.obtain();
                msg.what = 2;
                handler.sendMessage(msg);
            }
        }.start();
    }

}
