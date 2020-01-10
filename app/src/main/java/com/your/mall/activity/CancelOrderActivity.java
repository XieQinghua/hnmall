package com.your.mall.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.your.mall.R;
import com.your.mall.application.MallApplication;
import com.your.mall.base.BaseSwipeBackActivity;
import com.your.mall.http.MallApi;
import com.your.mall.utils.JsonValidator;
import com.your.mall.utils.MyCallBack;
import com.your.mall.utils.XUtils;
import com.your.mall.view.CustomDialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xieqinghua.
 * 创建时间：2016/9/19
 * 类描述：取消订单
 * 修改备注：
 */
public class CancelOrderActivity extends BaseSwipeBackActivity {
    private TextView tv_title;
    private TextView tv_total_fee, tv_post_fee, tv_cancel_order;
    private RadioGroup rg_cancel_reason;
    private RadioButton rb1, rb2, rb3, rb4, rb5, rb6, rb7, rb8, rb9, rb10, rb11;
    private String cancel_reason = "现在不想购买";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancel_order);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        init();
    }

    private void init() {
        tv_title = (TextView) findViewById(R.id.tv_title_center);
        tv_title.setText("取消订单");
        tv_total_fee = (TextView) findViewById(R.id.tv_total_fee);
        tv_post_fee = (TextView) findViewById(R.id.tv_post_fee);
        tv_cancel_order = (TextView) findViewById(R.id.tv_cancel_order);
        tv_total_fee.setText("￥" + getIntent().getStringExtra("total_fee"));
        tv_post_fee.setText("￥" + getIntent().getStringExtra("post_fee"));
        rg_cancel_reason = (RadioGroup) findViewById(R.id.rg_cancel_reason);
        rb1 = (RadioButton) findViewById(R.id.rb1);
        rb2 = (RadioButton) findViewById(R.id.rb2);
        rb3 = (RadioButton) findViewById(R.id.rb3);
        rb4 = (RadioButton) findViewById(R.id.rb4);
        rb5 = (RadioButton) findViewById(R.id.rb5);
        rb6 = (RadioButton) findViewById(R.id.rb6);
        rb7 = (RadioButton) findViewById(R.id.rb7);
        rb8 = (RadioButton) findViewById(R.id.rb8);
        rb9 = (RadioButton) findViewById(R.id.rb9);
        rb10 = (RadioButton) findViewById(R.id.rb10);
        rb11 = (RadioButton) findViewById(R.id.rb11);
        rg_cancel_reason.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (rb1.getId() == checkedId) {
                    cancel_reason = rb1.getText().toString();
                } else if (rb2.getId() == checkedId) {
                    cancel_reason = rb2.getText().toString();
                } else if (rb3.getId() == checkedId) {
                    cancel_reason = rb3.getText().toString();
                } else if (rb4.getId() == checkedId) {
                    cancel_reason = rb4.getText().toString();
                } else if (rb5.getId() == checkedId) {
                    cancel_reason = rb5.getText().toString();
                } else if (rb6.getId() == checkedId) {
                    cancel_reason = rb6.getText().toString();
                } else if (rb7.getId() == checkedId) {
                    cancel_reason = rb7.getText().toString();
                } else if (rb8.getId() == checkedId) {
                    cancel_reason = rb8.getText().toString();
                } else if (rb9.getId() == checkedId) {
                    cancel_reason = rb9.getText().toString();
                } else if (rb10.getId() == checkedId) {
                    cancel_reason = rb10.getText().toString();
                } else if (rb11.getId() == checkedId) {
                    cancel_reason = rb11.getText().toString();
                }
            }
        });
        tv_cancel_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomDialog.Builder builder = new CustomDialog.Builder(CancelOrderActivity.this);
                builder.setMessage("是否取消该订单？");
                builder.setTitle("温馨提示");
                builder.setPositiveButton("确定", new android.content.DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        cancelOrder();
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.create().show();

            }
        });
    }

    /**
     * 取消订单
     */
    private void cancelOrder() {
        Map<String, String> map = new HashMap<>();
        map.put("user_id", MallApplication.getInstance().getUserId(CancelOrderActivity.this));
        Log.e("tid", getIntent().getStringExtra("tid"));
        map.put("tid", getIntent().getStringExtra("tid"));
        map.put("cancel_reason", cancel_reason);
        showDialog(LOADING_DIALOG);
        XUtils.Post(MallApi.APP_TRADE_CANCEL, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                Log.e("result", result);
                if (JsonValidator.validate(result)) {
                    try {
                        JSONObject json = new JSONObject(result);
                        if (json.getString("code").equals("0")) {
                            Toast.makeText(CancelOrderActivity.this, json.getString("msg"), Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(CancelOrderActivity.this, json.getString("msg"), Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(CancelOrderActivity.this, R.string.unable_to_get_data, Toast.LENGTH_SHORT).show();
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
