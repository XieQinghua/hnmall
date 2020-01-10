package com.your.mall.fragment;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

import com.your.mall.R;
import com.your.mall.activity.BargainActivity;
import com.your.mall.activity.ContentInfoActivity;
import com.your.mall.activity.GetCouponActivity;
import com.your.mall.activity.GoodsDetailsActivity;
import com.your.mall.activity.LoginActivity;
import com.your.mall.activity.PintuanActivity;
import com.your.mall.activity.SearchActivity;
import com.your.mall.activity.SearchResultActivity;
import com.your.mall.activity.ShopHomepageActivity;
import com.your.mall.activity.TopicActivity;
import com.your.mall.activity.YaoYiYaoActivity;
import com.your.mall.activity.YourGongyiActivity;
import com.your.mall.application.MallApplication;
import com.your.mall.bean.LoginTepinBean;
import com.your.mall.http.MallApi;
import com.your.mall.utils.ErrorJavaScript;
import com.your.mall.utils.IBtnCallListener;
import com.your.mall.utils.JsonValidator;
import com.your.mall.utils.MyCallBack;
import com.your.mall.utils.NetUtils;
import com.your.mall.utils.ParseUtils;
import com.your.mall.utils.XUtils;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xieqinghua.
 * 创建时间：2016/8/30
 * 类描述：首页
 * 修改备注：
 */
public class HomeFragment extends android.support.v4.app.Fragment implements View.OnClickListener, IBtnCallListener {
    IBtnCallListener btnCallListener;
    private TextView tv_search;
    private SwipeRefreshLayout swipeLayout;
    private WebView web_home;
    private String url;
    public static final int LOADING_DIALOG = 0;

    public Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case 1:
                    loadingUrl();
                    break;
                case 2:
                    loginTepin();
            }
        }
    };


    private void loginTepin() {
        if (MallApplication.getInstance().getAutoLogin(getActivity())) {//已登录状态下
            Map<String, String> map = new HashMap<>();
            map.put("mobileNumber", MallApplication.getInstance().getMobileNumber(getActivity()));
            map.put("openId", MallApplication.getInstance().getOpenId(getActivity()));
            //sso登录成功，登录服务平台
            XUtils.Post(MallApi.APP_LOGIN_TEPIN, map, new MyCallBack<String>() {
                @Override
                public void onSuccess(String result) {
                    super.onSuccess(result);
                    Log.e("result", result);
                    if (JsonValidator.validate(result)) {
                        LoginTepinBean loginTepinBean = ParseUtils.parseLoginTepinBean(result);
                        if (loginTepinBean.getCode().equals("0")) {
//                            Toast.makeText(getActivity(), "登陆web成功", Toast.LENGTH_SHORT).show();
                            Log.e("login_info", "登陆web成功");
                        } else {
                        }
                    } else {
                        Toast.makeText(getActivity(), R.string.unable_to_get_data, Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onError(Throwable ex, boolean isOnCallback) {
                    super.onError(ex, isOnCallback);
                }
            });
        } else {
//            Toast.makeText(getActivity(), "主程序没有登陆", Toast.LENGTH_SHORT).show();
            Log.e("login_info", "主程序没有登陆");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
    }

    @Override
    public void onAttach(Activity activity) {
        btnCallListener = (IBtnCallListener) activity;
        super.onAttach(activity);
    }

    @Override
    public void onResume() {
        super.onResume();
//        loadingUrl();
    }

    private void init() {
        tv_search = (TextView) getView().findViewById(R.id.tv_search);
        web_home = (WebView) getView().findViewById(R.id.web_home);
        tv_search.setOnClickListener(this);
        swipeLayout = (SwipeRefreshLayout) getView().findViewById(R.id.swipe_container);
        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //重新刷新页面
                loadingUrl();
            }
        });
        swipeLayout.setColorScheme(
                R.color.holo_blue_bright,
                R.color.holo_green_light,
                R.color.holo_orange_light,
                R.color.holo_red_light);

        loadingUrl();
    }

    /**
     * 加载链接
     */
    private void loadingUrl() {
        //设置字符集编码
        web_home.getSettings().setDefaultTextEncodingName("UTF-8");

        web_home.getSettings().setAllowFileAccess(true);
        web_home.getSettings().setUseWideViewPort(true);
        web_home.getSettings().setLoadWithOverviewMode(true);

        //开启JavaScript支持
        web_home.getSettings().setJavaScriptEnabled(true);
        String userAgent = web_home.getSettings().getUserAgentString();
        web_home.getSettings().setUserAgentString(userAgent);
        web_home.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        web_home.getSettings().setAllowFileAccess(true);
        web_home.getSettings().setAppCacheEnabled(true);
        web_home.getSettings().setDomStorageEnabled(true);
        web_home.getSettings().setDatabaseEnabled(true);

        url = MallApi.SERVER_WEB_URL + "?from=android";

        if (NetUtils.isNetworkConnected(getActivity())) {//判断网络状态

            //携带Cookie
            CookieSyncManager.createInstance(getActivity());
            CookieManager cookieManager = CookieManager.getInstance();
            cookieManager.setAcceptCookie(true);
            cookieManager.removeSessionCookie();// 移除

            String cookieString = MallApplication.getInstance().getCookie(getActivity());
//            Toast.makeText(getActivity(), cookieString, Toast.LENGTH_SHORT).show();
            Log.e("home", cookieString);

            cookieManager.setCookie(url, cookieString);
            CookieSyncManager.getInstance().sync();

            web_home.loadUrl(url);
//            web_home.addJavascriptInterface(new LoginJavaScript(getActivity(), handler), "android");
            //监听webwiew的下载进度
            web_home.setWebViewClient(
                    new WebViewClient() {
                        public boolean shouldOverrideUrlLoading(WebView view, String url) {
                            if (url.contains("/wap/categroy.html")) {//商品分类
                                btnCallListener.showClass();
                                return true;
                            } else if (url.contains("/wap/cart.html")) {//购物车
                                btnCallListener.showShoppingCart();
                                return true;
                            } else if (url.contains("/wap/list.html?cat_id=")) {//通过cat_id到搜索结果页面
                                String cat_id = url.substring(url.indexOf("=") + 1);
                                Intent intent = new Intent(getActivity(), SearchResultActivity.class);
                                intent.putExtra("cat_id", cat_id);
                                startActivity(intent);
                                return true;
                            } else if (url.contains("/wap/list.html?n=")) {//通过搜索关键词到搜索结果页面
                                String search_keywords = url.substring(url.indexOf("=") + 1);
                                try {
                                    String de_search_keywords = java.net.URLDecoder.decode(search_keywords, "utf-8");
                                    Intent intent = new Intent(getActivity(), SearchResultActivity.class);
                                    intent.putExtra("search_keywords", de_search_keywords);
                                    startActivity(intent);
                                } catch (UnsupportedEncodingException e) {
                                    e.printStackTrace();
                                }
                                return true;
                            } else if (url.contains("/wap/item.html?item_id=")) {//商品详情
                                String item_id = url.substring(url.indexOf("=") + 1);
                                Intent intent = new Intent(getActivity(), GoodsDetailsActivity.class);
                                intent.putExtra("item_id", item_id);
                                getActivity().startActivity(intent);
                                return true;
                            } else if (url.contains("/wap/shopcenter.html?shop_id=")) {//店铺主页
                                String shop_id = url.substring(url.indexOf("=") + 1);
                                Intent intent = new Intent(getActivity(), ShopHomepageActivity.class);
                                intent.putExtra("shop_id", shop_id);
                                startActivity(intent);
                                return true;
                            } else if (url.contains("/wap/bargain.html")) {//砍价页面
                                Intent intent = new Intent(getActivity(), BargainActivity.class);
                                startActivity(intent);
                                return true;
                            } else if (url.contains("/wap/pintuan.html")) {//拼团页面
                                Intent intent = new Intent(getActivity(), PintuanActivity.class);
                                startActivity(intent);
                                return true;
                            } else if (url.contains("/wap/yaoyiyao.html")) {//摇一摇页面
                                if (!MallApplication.getInstance().getAutoLogin(getActivity())) {
                                    startActivity(new Intent(getActivity(), LoginActivity.class));
                                    getActivity().overridePendingTransition(R.anim.activity_open, 0);
                                } else {
                                    Intent intent = new Intent(getActivity(), YaoYiYaoActivity.class);
                                    intent.putExtra("name", "/yaoyiyao.html");
                                    startActivity(intent);
                                }
                                return true;
                            } else if (url.contains("/wap/firstpage.html")) {//友阿公益页面
                                Intent intent = new Intent(getActivity(), YourGongyiActivity.class);
                                intent.putExtra("name", "/firstpage.html");
                                startActivity(intent);
                                return true;
                            } else if (url.contains("/wap/s-coupon.html")) {//领劵中心页面
                                Intent intent = new Intent(getActivity(), GetCouponActivity.class);
                                intent.putExtra("name", "/s-coupon.html");
                                startActivity(intent);
                                return true;
                            } else if (url.contains("/wap/content-info.html")) {//热门资讯页面
                                Intent intent = new Intent(getActivity(), ContentInfoActivity.class);
                                intent.putExtra("name", url.substring(url.indexOf("/content-info.html")));
                                startActivity(intent);
                                return true;
                            } else if (url.contains("/wap/topic.html")) {//聚实惠页面
                                Intent intent = new Intent(getActivity(), TopicActivity.class);
                                intent.putExtra("name", url.substring(url.indexOf("/topic.html")));
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
//                            getActivity().showDialog(LOADING_DIALOG);
//                            Timer timer = new Timer();
//                            TimerTask task = new TimerTask() {
//                                public void run() {
//                                    getActivity().removeDialog(0);
//                                }
//                            };
//                            timer.schedule(task, 1000);
                        }

                        @Override
                        public void onPageFinished(WebView view, String url) {
                            super.onPageFinished(view, url);
//                            getActivity().removeDialog(0);
                            swipeLayout.setRefreshing(false);
                        }

                        @Override
                        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                            super.onReceivedError(view, errorCode, description, failingUrl);
                        }
                    }
            );

            //设置下来刷新进度条
            web_home.setWebChromeClient(new WebChromeClient() {
                @Override
                public void onProgressChanged(WebView view, int newProgress) {
                    if (newProgress == 100) {
                        //隐藏进度条
                        swipeLayout.setRefreshing(false);
                    } else {
                        if (!swipeLayout.isRefreshing())
                            swipeLayout.setRefreshing(true);
                    }

                    super.onProgressChanged(view, newProgress);
                }
            });

        } else {
            //进行无网络或错误处理
            Toast.makeText(getActivity(), "网络连接异常，请检查网络", Toast.LENGTH_SHORT).show();
            web_home.loadUrl("file:///android_asset/error.html");
            web_home.addJavascriptInterface(new ErrorJavaScript(getActivity(), handler), "android");
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_search:
                startActivity(new Intent(getActivity(), SearchActivity.class));
                break;
        }
    }

    @Override
    public void showHome() {

    }

    @Override
    public void showClass() {

    }

    @Override
    public void showShoppingCart() {

    }

    @Override
    public void changeCartNum() {

    }

    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (url != null && url.equals(MallApi.SERVER_WEB_URL + "/wap/categroy.html")) {
                web_home.stopLoading();
                Toast.makeText(getActivity(), url, Toast.LENGTH_SHORT).show();
                btnCallListener.showClass();//商品分类
                return true;
            }
            return false;
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            getActivity().showDialog(LOADING_DIALOG);
            if (url != null && url.equals(MallApi.SERVER_WEB_URL + "/wap/categroy.html")) {
                web_home.stopLoading();
                Toast.makeText(getActivity(), url, Toast.LENGTH_SHORT).show();
                btnCallListener.showClass();//商品分类
            }
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            getActivity().removeDialog(LOADING_DIALOG);
        }

        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            super.onReceivedError(view, errorCode, description, failingUrl);
        }
    }
}
