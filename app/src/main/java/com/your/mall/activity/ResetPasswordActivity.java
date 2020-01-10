package com.your.mall.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.your.mall.R;
import com.your.mall.base.BaseSwipeBackActivity;
import com.your.mall.http.MallApi;
import com.your.mall.utils.JsonValidator;
import com.your.mall.utils.MyCallBack;
import com.your.mall.utils.SHA1Utils;
import com.your.mall.utils.XUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xieqinghua.
 * 创建时间：2016/10/10
 * 类描述：重置密码页面
 * 修改备注：
 */
public class ResetPasswordActivity extends BaseSwipeBackActivity implements View.OnClickListener {
    private TextView tv_title;
    private EditText et_password, et_rpassword;
    private ImageView iv_empty1, iv_empty2;
    private String mobileNumber, code, password, rpassword;

    private Button btn_confirm_change;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        init();
    }

    private void init() {
        tv_title = (TextView) findViewById(R.id.tv_title_center);
        tv_title.setText("找回密码");
        et_password = (EditText) findViewById(R.id.et_password);
        et_rpassword = (EditText) findViewById(R.id.et_rpassword);
        iv_empty1 = (ImageView) findViewById(R.id.iv_empty1);
        iv_empty2 = (ImageView) findViewById(R.id.iv_empty2);
        btn_confirm_change = (Button) findViewById(R.id.btn_confirm_change);
        iv_empty1.setOnClickListener(this);
        iv_empty2.setOnClickListener(this);
        //监听et_password的文字变化
        clearText(et_password, iv_empty1);
        //监听et_rpassword的文字变化
        clearText(et_rpassword, iv_empty2);
        btn_confirm_change.setOnClickListener(this);
        mobileNumber = getIntent().getStringExtra("mobileNumber");
        code = getIntent().getStringExtra("code");
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
                confirmChange();
                break;
        }
    }

    /**
     * 确认修改
     */
    private void confirmChange() {
        password = et_password.getText().toString().trim();
        rpassword = et_rpassword.getText().toString().trim();
        if ((password.length() < 6 || password.length() > 16) || (rpassword.length() < 6 || rpassword.length() > 16)) {
            Toast.makeText(ResetPasswordActivity.this, "请输入6到16位新密码", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!password.equals(rpassword)) {
            Toast.makeText(ResetPasswordActivity.this, "密码输入不一致", Toast.LENGTH_SHORT).show();
            return;
        }
        //重置密码
        Map<String, String> map = new HashMap<>();
        map.put("mobileNumber", mobileNumber);
        map.put("code", code);
        map.put("password", SHA1Utils.hex_sha1(password));
        map.put("rpassword", SHA1Utils.hex_sha1(rpassword));
        showDialog(LOADING_DIALOG);
        XUtils.ssoGet(MallApi.SSO_CHANGE_PASSWORD, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                if (JsonValidator.validate(result)) {
                    try {
                        JSONObject json = new JSONObject(result);
                        if (json.getInt("returnCode") == 0) {
                            Toast.makeText(ResetPasswordActivity.this, "密码重置成功", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(ResetPasswordActivity.this, json.getString("message"), Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(ResetPasswordActivity.this, R.string.unable_to_get_data, Toast.LENGTH_SHORT).show();
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
