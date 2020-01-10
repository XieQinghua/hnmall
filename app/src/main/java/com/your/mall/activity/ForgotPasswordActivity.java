package com.your.mall.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.your.mall.R;
import com.your.mall.base.BaseSwipeBackActivity;
import com.your.mall.http.MallApi;
import com.your.mall.utils.IsIDCard;
import com.your.mall.utils.JsonValidator;
import com.your.mall.utils.MyCallBack;
import com.your.mall.utils.XUtils;

import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xieqinghua.
 * 创建时间：2016/9/7
 * 类描述：忘记密码页面
 * 修改备注：
 */
public class ForgotPasswordActivity extends BaseSwipeBackActivity implements View.OnClickListener {
    private TextView tv_title;
    private EditText et_phone_number, et_verification_code;
    private ImageView iv_empty1, iv_empty2;
    private TextView tv_get_code;
    private String phoneNumber, verifyCode;
    private Button btn_next;
    private TimeCount time;//倒计时提示

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        init();
    }

    private void init() {
        tv_title = (TextView) findViewById(R.id.tv_title_center);
        tv_title.setText("找回密码");
        et_phone_number = (EditText) findViewById(R.id.et_phone_number);
        et_verification_code = (EditText) findViewById(R.id.et_verification_code);
        iv_empty1 = (ImageView) findViewById(R.id.iv_empty1);
        iv_empty2 = (ImageView) findViewById(R.id.iv_empty2);
        tv_get_code = (TextView) findViewById(R.id.tv_get_code);
        btn_next = (Button) findViewById(R.id.btn_next);

        iv_empty1.setOnClickListener(this);
        iv_empty2.setOnClickListener(this);
        tv_get_code.setOnClickListener(this);

        //监听et_phone_number的文字变化
        clearText(et_phone_number, iv_empty1);
        //监听et_verification_code的文字变化
        clearText(et_verification_code, iv_empty2);

        btn_next.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_empty1:
                et_phone_number.setText("");
                break;
            case R.id.iv_empty2:
                et_verification_code.setText("");
                break;
            case R.id.tv_get_code:
                getCode();
                break;
            case R.id.btn_next:
                next();
                break;
        }
    }

    /**
     * 获取验证码
     */
    private void getCode() {
        time = new TimeCount(60000, 1000);
        phoneNumber = et_phone_number.getText().toString().trim();
        //判断手机号码正确性
        if (!IsIDCard.isValidMobileNo(phoneNumber)) {
            Toast.makeText(ForgotPasswordActivity.this, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
            return;
        }
        //获取找回密码验证码
        Map<String, String> map = new HashMap<>();
        map.put("mobileNumber", phoneNumber);
        showDialog(LOADING_DIALOG);
        XUtils.ssoGet(MallApi.SSO_LOST_PASSWORDSMS, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                if (JsonValidator.validate(result)) {
                    try {
                        JSONObject json = new JSONObject(result);
                        if (json.getInt("returnCode") == 0) {
                            //获取验证码成功，倒计时开始
                            time.start();
//                            Toast.makeText(ForgotPasswordActivity.this, "获取验证码成功", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(ForgotPasswordActivity.this, json.getString("message"), Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(ForgotPasswordActivity.this, R.string.unable_to_get_data, Toast.LENGTH_SHORT).show();
                }
                removeDialog();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
            }
        });
    }

    /**
     * 点击下一步的操作
     */
    private void next() {
        phoneNumber = et_phone_number.getText().toString().trim();
        verifyCode = et_verification_code.getText().toString().trim();
        //判断手机号码正确性
        if (!IsIDCard.isValidMobileNo(phoneNumber)) {
            Toast.makeText(ForgotPasswordActivity.this, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
            return;
        }
        if (verifyCode.equals("")) {
            Toast.makeText(ForgotPasswordActivity.this, "请输入验证码", Toast.LENGTH_SHORT).show();
            return;
        }
        //判断验证码是否正确
        Map<String, String> map = new HashMap<>();
        map.put("mobileNumber", phoneNumber);
        map.put("verifyCode", verifyCode);
        showDialog(LOADING_DIALOG);
        XUtils.ssoGet(MallApi.SSO_LOST_PASSWORD, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                if (JsonValidator.validate(result)) {
                    try {
                        JSONObject json = new JSONObject(result);
                        if (json.getInt("returnCode") == 0) {
                            //核实通过，跳转下一步页面
                            Intent intent = new Intent(ForgotPasswordActivity.this, ResetPasswordActivity.class);
                            intent.putExtra("mobileNumber", phoneNumber);
                            intent.putExtra("code", json.getString("code"));
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(ForgotPasswordActivity.this, json.getString("message"), Toast.LENGTH_SHORT).show();
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(ForgotPasswordActivity.this, R.string.unable_to_get_data, Toast.LENGTH_SHORT).show();
                }
                removeDialog();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
            }
        });
    }

    class TimeCount extends CountDownTimer {
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            tv_get_code.setClickable(false);
            tv_get_code.setText(millisUntilFinished / 1000 + "秒后重新发送");
        }

        @Override
        public void onFinish() {
            tv_get_code.setText("获取验证码");
            tv_get_code.setClickable(true);
        }
    }
}
