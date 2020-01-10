package com.your.mall.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.your.mall.R;
import com.your.mall.application.MallApplication;
import com.your.mall.base.BaseSwipeBackActivity;
import com.your.mall.bean.LoginBean;
import com.your.mall.bean.LoginTepinBean;
import com.your.mall.http.MallApi;
import com.your.mall.utils.JsonValidator;
import com.your.mall.utils.MyCallBack;
import com.your.mall.utils.NetUtils;
import com.your.mall.utils.ParseUtils;
import com.your.mall.utils.SHA1Utils;
import com.your.mall.utils.XUtils;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.http.cookie.DbCookieStore;

import java.net.HttpCookie;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xieqinghua.
 * 创建时间：2016/8/31
 * 类描述：登录页面
 * 修改备注：
 */
public class LoginActivity extends BaseSwipeBackActivity implements View.OnClickListener {
    private static final String TAG = "LoginActivity";
    private String cookieString;
    private TextView tv_title;
    private TextView tv_left_title;
    private ImageView iv_back;
    private EditText et_account_number, et_password;
    private ImageView iv_empty1, iv_empty2;
    private TextView tv_register, tv_forgot_password;
    private String loginname = "";
    private String password = "";
    private boolean autoLogin = false;// 登录开关
    private Button btn_login;
    private String openId;
    private String mobileNumber;
    private String passWord;
    private String accessToken;
    private String userId;
    private String userName;
    private String regTime;
    private String cartNum;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }


    private void init() {
        tv_title = (TextView) findViewById(R.id.tv_title_center);
        tv_left_title = (TextView) findViewById(R.id.tv_left_title);
        iv_back = (ImageView) findViewById(R.id.iv_back);
        iv_back.setVisibility(View.GONE);
        tv_left_title.setText("取消");
        tv_left_title.setOnClickListener(this);
        tv_title.setText("用户登录");

        et_account_number = (EditText) findViewById(R.id.et_account_number);
        et_password = (EditText) findViewById(R.id.et_password);
        iv_empty1 = (ImageView) findViewById(R.id.iv_empty1);
        iv_empty2 = (ImageView) findViewById(R.id.iv_empty2);
        tv_register = (TextView) findViewById(R.id.tv_register);
        tv_forgot_password = (TextView) findViewById(R.id.tv_forgot_password);
        btn_login = (Button) findViewById(R.id.btn_login);

        iv_empty1.setOnClickListener(this);
        iv_empty2.setOnClickListener(this);

        //监听et_account_number的文字变化
        clearText(et_account_number, iv_empty1);
        //监听et_password的文字变化
        clearText(et_password, iv_empty2);

        tv_register.setOnClickListener(this);
        tv_forgot_password.setOnClickListener(this);
        btn_login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_left_title:
                finish();
                overridePendingTransition(0, R.anim.activity_close);
                break;
            case R.id.iv_empty1:
                et_account_number.setText("");
                break;
            case R.id.iv_empty2:
                et_password.setText("");
                break;
            case R.id.tv_register:
                //跳转注册页面
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                break;
            case R.id.tv_forgot_password:
                //跳转忘记密码页面
                startActivity(new Intent(LoginActivity.this, ForgotPasswordActivity.class));
                break;
            case R.id.btn_login:
                ssoLogin();
                break;
        }
    }

    private void ssoLogin() {
        loginname = et_account_number.getText().toString().trim();
        password = et_password.getText().toString().trim();
        //点击登录按键隐藏键盘
        ((InputMethodManager) et_password.getContext().getSystemService(Context.INPUT_METHOD_SERVICE))
                .hideSoftInputFromWindow(LoginActivity.this
                                .getCurrentFocus()
                                .getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);

        Map<String, String> map = new HashMap<>();
        if (!loginname.equals("")) {
            map.put("loginname", loginname);
        } else {
            Toast.makeText(LoginActivity.this, "请输入登录账号", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!password.equals("")) {
            map.put("password", SHA1Utils.hex_sha1(password));
        } else {
            Toast.makeText(LoginActivity.this, "请输入密码", Toast.LENGTH_SHORT).show();
            return;
        }
//        Log.e("password", SHA1Utils.hex_sha1(password));

        showDialog(LOADING_DIALOG);
        if (!NetUtils.isNetworkConnected(this)) {
            //判断网络是否可用
            Toast.makeText(this, R.string.network_is_not_good, Toast.LENGTH_SHORT).show();
            return;
        }
        //sso登录
        XUtils.ssoGet(MallApi.SSO_LOGIN, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
//                Log.e(TAG, result);
                if (JsonValidator.validate(result)) {
                    final LoginBean loginBean = ParseUtils.parseLoginBean(result);
                    if (loginBean.getReturnCode() == 0) {
                        openId = loginBean.getOpenId();
                        mobileNumber = loginBean.getMobileNumber();
                        passWord = password;
                        accessToken = loginBean.getAccessToken();
                        Map<String, String> map1 = new HashMap<>();
                        map1.put("mobileNumber", mobileNumber);
                        map1.put("openId", loginBean.getOpenId());
                        //sso登录成功，登录服务平台
                        XUtils.Post(MallApi.APP_LOGIN_TEPIN, map1, new MyCallBack<String>() {
                            @Override
                            public void onSuccess(String result) {
                                super.onSuccess(result);
//                                Log.e(TAG, result);
                                if (JsonValidator.validate(result)) {
                                    try {
                                        JSONObject json = new JSONObject(result);
                                        if (json.getString("code").equals("0")) {
                                            LoginTepinBean loginTepinBean = ParseUtils.parseLoginTepinBean(result);
                                            //登录服务平台成功，保存参数，跳转首页
                                            autoLogin = true;
                                            userId = loginTepinBean.getData().getUser_id();
                                            userName = loginTepinBean.getData().getUser_name();
                                            regTime = loginTepinBean.getData().getReg_time();
                                            cartNum = loginTepinBean.getData().getCart_num();
                                            //保存用户数据到应用SharedPreferences
                                            MallApplication.getInstance().saveLoginParam(LoginActivity.this,
                                                    autoLogin,
                                                    openId,
                                                    mobileNumber,
                                                    passWord,
                                                    accessToken,
                                                    userId,
                                                    userName,
                                                    regTime,
                                                    cartNum);

                                            DbCookieStore instance = DbCookieStore.INSTANCE;
                                            List<HttpCookie> cookies = instance.getCookies();
//                                        String str = "";
//                                        for (int i = 0; i < cookies.size(); i++) {
//                                            str += cookies.get(i).getName() + "=" + cookies.get(i).getValue() + ";";
//                                        }
//                                        cookieString = str.substring(0, str.length() - 1);
                                            for (HttpCookie cookie : cookies) {
                                                String name = cookie.getName();
                                                String value = cookie.getValue();
                                                if ("s".equals(name)) {
                                                    cookieString = name + "=" + value;
                                                    break;
                                                }
                                            }
                                            Log.e(TAG, cookieString);
                                            //保存Cookie
                                            MallApplication.getInstance().setCookie(LoginActivity.this, cookieString);

                                            Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                                            finish();
                                            overridePendingTransition(0, R.anim.activity_close);

                                        } else {
                                            Toast.makeText(LoginActivity.this, json.getString("msg"), Toast.LENGTH_SHORT).show();
                                            return;
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                } else {
                                    Toast.makeText(LoginActivity.this, R.string.unable_to_get_data, Toast.LENGTH_SHORT).show();
                                }
                                removeDialog();
                            }

                            @Override
                            public void onError(Throwable ex, boolean isOnCallback) {
                                super.onError(ex, isOnCallback);
                            }
                        });
                    } else {
                        Toast.makeText(LoginActivity.this, loginBean.getMessage(), Toast.LENGTH_SHORT).show();
                        removeDialog();
                        return;
                    }
                } else {
                    Toast.makeText(LoginActivity.this, R.string.unable_to_get_data, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
            }
        });
    }
}
