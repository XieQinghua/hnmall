package com.your.mall.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.your.mall.R;
import com.your.mall.application.MallApplication;
import com.your.mall.base.BaseSwipeBackActivity;
import com.your.mall.bean.LoginTepinBean;
import com.your.mall.bean.RegisterBean;
import com.your.mall.http.MallApi;
import com.your.mall.utils.JsonValidator;
import com.your.mall.utils.MyCallBack;
import com.your.mall.utils.ParseUtils;
import com.your.mall.utils.SHA1Utils;
import com.your.mall.utils.XUtils;

import org.xutils.http.cookie.DbCookieStore;

import java.net.HttpCookie;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xieqinghua.
 * 创建时间：2016/10/10
 * 类描述：二级注册页面，确认密码页面
 * 修改备注：
 */
public class SecondRegisterActivity extends BaseSwipeBackActivity implements View.OnClickListener {
    private static final String TAG = "SecondRegister";
    private String cookieString;
    private TextView tv_title;
    private EditText et_password, et_rpassword;
    private ImageView iv_empty1, iv_empty2;
    private String mobileNumber, verifyCode, password, rpassword;
    private Button btn_confirm_change;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        init();
    }

    private void init() {
        tv_title = (TextView) findViewById(R.id.tv_title_center);
        tv_title.setText("用户注册");
        et_password = (EditText) findViewById(R.id.et_password);
        et_rpassword = (EditText) findViewById(R.id.et_rpassword);
        iv_empty1 = (ImageView) findViewById(R.id.iv_empty1);
        iv_empty2 = (ImageView) findViewById(R.id.iv_empty2);
        btn_confirm_change = (Button) findViewById(R.id.btn_confirm_change);
        btn_confirm_change.setText("注册完成");
        iv_empty1.setOnClickListener(this);
        iv_empty2.setOnClickListener(this);
        //监听et_password的文字变化
        clearText(et_password, iv_empty1);
        //监听et_rpassword的文字变化
        clearText(et_rpassword, iv_empty2);
        btn_confirm_change.setOnClickListener(this);
        mobileNumber = getIntent().getStringExtra("mobileNumber");
        verifyCode = getIntent().getStringExtra("verifyCode");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_empty1:
                et_password.setText("");
                break;
            case R.id.iv_empty2:
                et_rpassword.setText("");
                break;
            case R.id.btn_confirm_change:
                registrComplete();
                break;
        }
    }

    /**
     * 注册完成
     */
    private void registrComplete() {
        password = et_password.getText().toString().trim();
        rpassword = et_rpassword.getText().toString().trim();
        if ((password.length() < 6 || password.length() > 16) || (rpassword.length() < 6 || rpassword.length() > 16)) {
            Toast.makeText(SecondRegisterActivity.this, "请输入6到16位新密码", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!password.equals(rpassword)) {
            Toast.makeText(SecondRegisterActivity.this, "密码输入不一致", Toast.LENGTH_SHORT).show();
            return;
        }
        //注册
        Map<String, String> map = new HashMap<>();
        map.put("mobileNumber", mobileNumber);
        map.put("password", SHA1Utils.hex_sha1(password));
        map.put("rpassword", SHA1Utils.hex_sha1(rpassword));
        map.put("verifyCode", verifyCode);
        showDialog(LOADING_DIALOG);
        XUtils.ssoGet(MallApi.SSO_REGISTER, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                Log.e("result", result);
                if (JsonValidator.validate(result)) {

                    RegisterBean registerBean = ParseUtils.parseRegisterBean(result);
                    if (registerBean.getReturnCode() == 0) {
                        //注册成功
//                        Toast.makeText(SecondRegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                        //登录服务平台
                        final String openId = registerBean.getOpenId();
                        final String mobileNumber = registerBean.getMobileNumber();
                        final String passWord = password;
                        final String accessToken = registerBean.getAccessToken();

                        Map<String, String> map1 = new HashMap<>();
                        map1.put("mobileNumber", mobileNumber);
                        map1.put("openId", registerBean.getOpenId());
                        //sso登录成功，登录服务平台
                        XUtils.Post(MallApi.APP_LOGIN_TEPIN, map1, new MyCallBack<String>() {
                            @Override
                            public void onSuccess(String result) {
                                super.onSuccess(result);
//                                Log.e("result1", result);
                                if (JsonValidator.validate(result)) {
                                    LoginTepinBean loginTepinBean = ParseUtils.parseLoginTepinBean(result);
                                    if (loginTepinBean.getCode().equals("0")) {
                                        //登录服务平台成功，保存参数，跳转首页
                                        String userId = loginTepinBean.getData().getUser_id();
                                        String userName = loginTepinBean.getData().getUser_name();
                                        String regTime = loginTepinBean.getData().getReg_time() + "";
                                        String cartNum = loginTepinBean.getData().getCart_num();
                                        //保存用户数据到应用SharedPreferences
                                        MallApplication.getInstance().saveLoginParam(SecondRegisterActivity.this,
                                                true,
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
                                        MallApplication.getInstance().setCookie(SecondRegisterActivity.this, cookieString);

                                        //跳转首页
                                        Toast.makeText(SecondRegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                                        finish();
                                        overridePendingTransition(0, R.anim.activity_close);
                                    } else {
                                        Toast.makeText(SecondRegisterActivity.this, loginTepinBean.getMsg(), Toast.LENGTH_SHORT).show();
                                        return;
                                    }
                                } else {
                                    Toast.makeText(SecondRegisterActivity.this, R.string.unable_to_get_data, Toast.LENGTH_SHORT).show();
                                }
                                removeDialog();
                            }

                            @Override
                            public void onError(Throwable ex, boolean isOnCallback) {
                                super.onError(ex, isOnCallback);
                            }
                        });
                    } else {
                        Toast.makeText(SecondRegisterActivity.this, registerBean.getMessage(), Toast.LENGTH_SHORT).show();
                        removeDialog();
                    }
//                    try {
//                        JSONObject json = new JSONObject(result);
//                        if (json.getInt("returnCode") == 0) {
//                            Toast.makeText(SecondRegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
//                            //注册成功，登录服务平台
////                            startActivity(new Intent(SecondRegisterActivity.this, LoginActivity.class));
////                            overridePendingTransition(R.anim.activity_open, 0);
//                            finish();
//                        } else {
//                            Toast.makeText(SecondRegisterActivity.this, json.getString("message"), Toast.LENGTH_SHORT).show();
//                        }
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
                } else {
                    Toast.makeText(SecondRegisterActivity.this, R.string.unable_to_get_data, Toast.LENGTH_SHORT).show();
                }
                removeDialog();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
            }
        });
    }
}
