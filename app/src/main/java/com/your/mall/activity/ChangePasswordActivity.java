package com.your.mall.activity;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.your.mall.R;
import com.your.mall.application.MallApplication;
import com.your.mall.base.BaseSwipeBackActivity;
import com.your.mall.bean.LoginBean;
import com.your.mall.http.MallApi;
import com.your.mall.utils.IsIDCard;
import com.your.mall.utils.JsonValidator;
import com.your.mall.utils.MyCallBack;
import com.your.mall.utils.ParseUtils;
import com.your.mall.utils.SHA1Utils;
import com.your.mall.utils.XUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xieqinghua.
 * 创建时间：2016/9/9
 * 类描述：修改登录密码
 * 修改备注：
 */
public class ChangePasswordActivity extends BaseSwipeBackActivity implements View.OnClickListener {
    private TextView tv_title;
    private EditText et_old_password, et_new_password, et_again_password;
    private ImageView iv_empty1, iv_empty2, iv_empty3;
    private Button btn_change;

    private String opassword, password, rpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        init();

    }

    private void init() {
        tv_title = (TextView) findViewById(R.id.tv_title_center);
        et_old_password = (EditText) findViewById(R.id.et_old_password);
        et_new_password = (EditText) findViewById(R.id.et_new_password);
        et_again_password = (EditText) findViewById(R.id.et_again_password);
        iv_empty1 = (ImageView) findViewById(R.id.iv_empty1);
        iv_empty2 = (ImageView) findViewById(R.id.iv_empty2);
        iv_empty3 = (ImageView) findViewById(R.id.iv_empty3);
        btn_change = (Button) findViewById(R.id.btn_change);

        //监听EditText的文字变化
        clearText(et_old_password, iv_empty1);
        clearText(et_new_password, iv_empty2);
        clearText(et_again_password, iv_empty3);

        tv_title.setText("修改登录密码");
        iv_empty1.setOnClickListener(this);
        iv_empty2.setOnClickListener(this);
        iv_empty3.setOnClickListener(this);
        btn_change.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_empty1:
                et_old_password.setText("");
                break;
            case R.id.iv_empty2:
                et_new_password.setText("");
                break;
            case R.id.iv_empty3:
                et_again_password.setText("");
                break;
            case R.id.btn_change:
                confirmChange();
                break;
        }
    }

    /**
     * 确认修改
     */
    private void confirmChange() {
        opassword = et_old_password.getText().toString().trim();
        password = et_new_password.getText().toString().trim();
        rpassword = et_again_password.getText().toString().trim();

        if (opassword.length() < 6 || opassword.length() > 16) {
            Toast.makeText(ChangePasswordActivity.this, "请输入6到16位原密码", Toast.LENGTH_SHORT).show();
            return;
        }
        if ((password.length() < 6 || password.length() > 16) || (rpassword.length() < 6 || rpassword.length() > 16)) {
            Toast.makeText(ChangePasswordActivity.this, "请输入6到16位新密码", Toast.LENGTH_SHORT).show();
            return;
        }
        if (IsIDCard.isConSpeCharacters(opassword)) {
            Toast.makeText(ChangePasswordActivity.this, "原密码不能包含中文以及特殊字符", Toast.LENGTH_SHORT).show();
            return;
        }
        if (IsIDCard.isConSpeCharacters(password) || IsIDCard.isConSpeCharacters(rpassword)) {
            Toast.makeText(ChangePasswordActivity.this, "新密码不能包含中文以及特殊字符", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!password.equals(rpassword)) {
            Toast.makeText(ChangePasswordActivity.this, "新密码与确认密码不一致", Toast.LENGTH_SHORT).show();
            return;
        }
        //先调取登录接口，登录成功以后获取accessToken和openId
        Map<String, String> map = new HashMap<>();
        map.put("loginname", MallApplication.getInstance().getMobileNumber(ChangePasswordActivity.this));
        map.put("password", SHA1Utils.hex_sha1(MallApplication.getInstance().getPassWord(ChangePasswordActivity.this)));

        showDialog(LOADING_DIALOG);
        //sso登录
        XUtils.ssoGet(MallApi.SSO_LOGIN, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                if (JsonValidator.validate(result)) {
                    final LoginBean loginBean = ParseUtils.parseLoginBean(result);
                    if (loginBean.getReturnCode() == 0) {
                        final String accessToken = loginBean.getAccessToken();
                        final String openId = loginBean.getOpenId();
                        //调用修改密码接口
                        Map<String, String> map = new HashMap<>();
                        map.put("accessToken", accessToken);
                        map.put("openId", openId);
                        map.put("opassword", SHA1Utils.hex_sha1(opassword));
                        map.put("password", SHA1Utils.hex_sha1(password));
                        map.put("rpassword", SHA1Utils.hex_sha1(rpassword));
                        XUtils.ssoGet(MallApi.SSO_CHANGE_PERSONAGE_PASSWORD, map, new MyCallBack<String>() {
                            @Override
                            public void onSuccess(String result) {
                                super.onSuccess(result);
//                                Log.e("result", result);
                                if (JsonValidator.validate(result)) {
                                    try {
                                        JSONObject json = new JSONObject(result);
                                        if (json.getInt("returnCode") == 0) {
                                            //修改成功，设置自动登录标示为false
                                            MallApplication.getInstance().setAutoLogin(ChangePasswordActivity.this, false);
                                            //保存密码
                                            MallApplication.getInstance().setPassWord(ChangePasswordActivity.this, password);
                                            Toast.makeText(ChangePasswordActivity.this, "修改成功，下次请用新密码登陆", Toast.LENGTH_SHORT).show();
                                            finish();
                                        } else {
                                            Toast.makeText(ChangePasswordActivity.this, json.getString("message"), Toast.LENGTH_SHORT).show();
                                        }

                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                } else {
                                    Toast.makeText(ChangePasswordActivity.this, R.string.unable_to_get_data, Toast.LENGTH_SHORT).show();
                                }
                                removeDialog();
                            }

                            @Override
                            public void onError(Throwable ex, boolean isOnCallback) {
                                super.onError(ex, isOnCallback);
                            }
                        });
                    }
                } else {
                    Toast.makeText(ChangePasswordActivity.this, R.string.unable_to_get_data, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
            }
        });
    }
}

