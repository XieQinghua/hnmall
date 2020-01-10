package com.your.mall.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.your.mall.R;
import com.your.mall.application.MallApplication;
import com.your.mall.base.BaseSwipeBackActivity;
import com.your.mall.http.MallApi;
import com.your.mall.utils.ImgUtils;
import com.your.mall.utils.JsonValidator;
import com.your.mall.utils.MyCallBack;
import com.your.mall.utils.XUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xieqinghua.
 * 创建时间：2016/10/16
 * 类描述：我的评价页面
 * 修改备注：
 */
public class MyRateActivity extends BaseSwipeBackActivity implements View.OnClickListener {
    private TextView tv_title;
    private ImageView iv_goods_img;
    private TextView tv_goods_name, tv_goods_price, tv_num;

    private RadioGroup rg_goods_res;
    private RadioButton cb_good, cb_neutral, cb_bad;
    private String res = "good";

    private String content;
    private EditText et_content;

    private CheckBox cb11, cb12, cb13, cb14, cb15,
            cb21, cb22, cb23, cb24, cb25,
            cb31, cb32, cb33, cb34, cb35,
            cb41, cb42, cb43, cb44, cb45;
    private String tally_score,
            attitude_score,
            delivery_speed_score,
            logistics_service_score;


    private CheckBox cb_anony;
    private String anony;
    private TextView tv_submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_rate);

        init();
    }

    private void init() {
        tv_title = (TextView) findViewById(R.id.tv_title_center);
        tv_title.setText("我的评价");

        iv_goods_img = (ImageView) findViewById(R.id.iv_goods_img);
        tv_goods_name = (TextView) findViewById(R.id.tv_goods_name);
        tv_goods_price = (TextView) findViewById(R.id.tv_goods_price);
        tv_num = (TextView) findViewById(R.id.tv_num);

        rg_goods_res = (RadioGroup) findViewById(R.id.rg_goods_res);
        cb_good = (RadioButton) findViewById(R.id.cb_good);
        cb_neutral = (RadioButton) findViewById(R.id.cb_neutral);
        cb_bad = (RadioButton) findViewById(R.id.cb_bad);
        rg_goods_res.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (cb_good.getId() == checkedId) {
                    res = "good";
                } else if (cb_neutral.getId() == checkedId) {
                    res = "neutral";
                } else if (cb_bad.getId() == checkedId) {
                    res = "bad";
                }
            }
        });

        et_content = (EditText) findViewById(R.id.et_content);

        cb11 = (CheckBox) findViewById(R.id.cb11);
        cb12 = (CheckBox) findViewById(R.id.cb12);
        cb13 = (CheckBox) findViewById(R.id.cb13);
        cb14 = (CheckBox) findViewById(R.id.cb14);
        cb15 = (CheckBox) findViewById(R.id.cb15);

        cb21 = (CheckBox) findViewById(R.id.cb21);
        cb22 = (CheckBox) findViewById(R.id.cb22);
        cb23 = (CheckBox) findViewById(R.id.cb23);
        cb24 = (CheckBox) findViewById(R.id.cb24);
        cb25 = (CheckBox) findViewById(R.id.cb25);

        cb31 = (CheckBox) findViewById(R.id.cb31);
        cb32 = (CheckBox) findViewById(R.id.cb32);
        cb33 = (CheckBox) findViewById(R.id.cb33);
        cb34 = (CheckBox) findViewById(R.id.cb34);
        cb35 = (CheckBox) findViewById(R.id.cb35);

        cb41 = (CheckBox) findViewById(R.id.cb41);
        cb42 = (CheckBox) findViewById(R.id.cb42);
        cb43 = (CheckBox) findViewById(R.id.cb43);
        cb44 = (CheckBox) findViewById(R.id.cb44);
        cb45 = (CheckBox) findViewById(R.id.cb45);

        cb11.setOnClickListener(this);
        cb12.setOnClickListener(this);
        cb13.setOnClickListener(this);
        cb14.setOnClickListener(this);
        cb15.setOnClickListener(this);

        cb21.setOnClickListener(this);
        cb22.setOnClickListener(this);
        cb23.setOnClickListener(this);
        cb24.setOnClickListener(this);
        cb25.setOnClickListener(this);

        cb31.setOnClickListener(this);
        cb32.setOnClickListener(this);
        cb33.setOnClickListener(this);
        cb34.setOnClickListener(this);
        cb35.setOnClickListener(this);

        cb41.setOnClickListener(this);
        cb42.setOnClickListener(this);
        cb43.setOnClickListener(this);
        cb44.setOnClickListener(this);
        cb45.setOnClickListener(this);

        cb_anony = (CheckBox) findViewById(R.id.cb_anony);

        tv_submit = (TextView) findViewById(R.id.tv_submit);

        ImgUtils.setRectangleImage(iv_goods_img, getIntent().getStringExtra("pic_path").trim());
        tv_goods_name.setText(getIntent().getStringExtra("title"));
        tv_goods_price.setText("￥" + getIntent().getStringExtra("price"));
        tv_num.setText("×" + getIntent().getStringExtra("num"));

        tv_submit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cb11:
                setStarNum(1, 1);
                break;
            case R.id.cb12:
                setStarNum(1, 2);
                break;
            case R.id.cb13:
                setStarNum(1, 3);
                break;
            case R.id.cb14:
                setStarNum(1, 4);
                break;
            case R.id.cb15:
                setStarNum(1, 5);
                break;
            case R.id.cb21:
                setStarNum(2, 1);
                break;
            case R.id.cb22:
                setStarNum(2, 2);
                break;
            case R.id.cb23:
                setStarNum(2, 3);
                break;
            case R.id.cb24:
                setStarNum(2, 4);
                break;
            case R.id.cb25:
                setStarNum(2, 5);
                break;
            case R.id.cb31:
                setStarNum(3, 1);
                break;
            case R.id.cb32:
                setStarNum(3, 2);
                break;
            case R.id.cb33:
                setStarNum(3, 3);
                break;
            case R.id.cb34:
                setStarNum(3, 4);
                break;
            case R.id.cb35:
                setStarNum(3, 5);
                break;
            case R.id.cb41:
                setStarNum(4, 1);
                break;
            case R.id.cb42:
                setStarNum(4, 2);
                break;
            case R.id.cb43:
                setStarNum(4, 3);
                break;
            case R.id.cb44:
                setStarNum(4, 4);
                break;
            case R.id.cb45:
                setStarNum(4, 5);
                break;
            case R.id.tv_submit:
                submit();
                break;
        }
    }

    /**
     * 点击后设置星星数
     */
    private void setStarNum(int line, int num) {
        boolean b1 = false;
        boolean b2 = false;
        boolean b3 = false;
        boolean b4 = false;
        boolean b5 = false;

        switch (num) {
            case 1:
                b1 = true;
                break;
            case 2:
                b1 = true;
                b2 = true;
                break;
            case 3:
                b1 = true;
                b2 = true;
                b3 = true;
                break;
            case 4:
                b1 = true;
                b2 = true;
                b3 = true;
                b4 = true;
                break;
            case 5:
                b1 = true;
                b2 = true;
                b3 = true;
                b4 = true;
                b5 = true;
                break;

            default:
                break;
        }
        if (line == 1) {
            cb11.setChecked(b1);
            cb12.setChecked(b2);
            cb13.setChecked(b3);
            cb14.setChecked(b4);
            cb15.setChecked(b5);
        } else if (line == 2) {
            cb21.setChecked(b1);
            cb22.setChecked(b2);
            cb23.setChecked(b3);
            cb24.setChecked(b4);
            cb25.setChecked(b5);
        } else if (line == 3) {
            cb31.setChecked(b1);
            cb32.setChecked(b2);
            cb33.setChecked(b3);
            cb34.setChecked(b4);
            cb35.setChecked(b5);
        } else if (line == 4) {
            cb41.setChecked(b1);
            cb42.setChecked(b2);
            cb43.setChecked(b3);
            cb44.setChecked(b4);
            cb45.setChecked(b5);
        }
    }


    private void submit() {
        content = et_content.getText().toString().trim();
        if (cb_anony.isChecked()) {
            anony = "1";
        } else {
            anony = "0";
        }

        if (cb11.isChecked() && !cb12.isChecked()) {
            tally_score = "1";
        } else if (cb12.isChecked() && !cb13.isChecked()) {
            tally_score = "2";
        } else if (cb13.isChecked() && !cb14.isChecked()) {
            tally_score = "3";
        } else if (cb14.isChecked() && !cb15.isChecked()) {
            tally_score = "4";
        } else if (cb15.isChecked()) {
            tally_score = "5";
        } else {
            Toast.makeText(MyRateActivity.this, "商品描述尚未打星", Toast.LENGTH_SHORT).show();
            return;
        }

        if (cb21.isChecked() && !cb22.isChecked()) {
            attitude_score = "1";
        } else if (cb22.isChecked() && !cb23.isChecked()) {
            attitude_score = "2";
        } else if (cb23.isChecked() && !cb24.isChecked()) {
            attitude_score = "3";
        } else if (cb24.isChecked() && !cb25.isChecked()) {
            attitude_score = "4";
        } else if (cb25.isChecked()) {
            attitude_score = "5";
        } else {
            Toast.makeText(MyRateActivity.this, "商家服务态度尚未打星", Toast.LENGTH_SHORT).show();
            return;
        }

        if (cb31.isChecked() && !cb32.isChecked()) {
            delivery_speed_score = "1";
        } else if (cb32.isChecked() && !cb33.isChecked()) {
            delivery_speed_score = "2";
        } else if (cb33.isChecked() && !cb34.isChecked()) {
            delivery_speed_score = "3";
        } else if (cb34.isChecked() && !cb35.isChecked()) {
            delivery_speed_score = "4";
        } else if (cb35.isChecked()) {
            delivery_speed_score = "5";
        } else {
            Toast.makeText(MyRateActivity.this, "商家发货速度尚未打星", Toast.LENGTH_SHORT).show();
            return;
        }

        if (cb41.isChecked() && !cb42.isChecked()) {
            logistics_service_score = "1";
        } else if (cb42.isChecked() && !cb43.isChecked()) {
            logistics_service_score = "2";
        } else if (cb43.isChecked() && !cb44.isChecked()) {
            logistics_service_score = "3";
        } else if (cb44.isChecked() && !cb45.isChecked()) {
            logistics_service_score = "4";
        } else if (cb45.isChecked()) {
            logistics_service_score = "5";
        } else {
            Toast.makeText(MyRateActivity.this, "物流发货速度尚未打星", Toast.LENGTH_SHORT).show();
            return;
        }

        Map<String, String> map = new HashMap<>();
        map.put("user_id", MallApplication.getInstance().getUserId(MyRateActivity.this));
        map.put("tid", getIntent().getStringExtra("tid"));
        map.put("tally_score", tally_score);
        map.put("attitude_score", attitude_score);
        map.put("delivery_speed_score", delivery_speed_score);
        map.put("logistics_service_score", logistics_service_score);
        map.put("anony", anony);
        map.put("oid", getIntent().getStringExtra("oid"));
        map.put("content", content);
        map.put("res", res);
        showDialog(LOADING_DIALOG);
        XUtils.Post(MallApi.APP_MEMBER_RATE_ADD, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                Log.e("result", result);
                if (JsonValidator.validate(result)) {
                    try {
                        JSONObject json = new JSONObject(result);
                        if (json.getString("code").equals("0")) {
                            Toast.makeText(MyRateActivity.this, json.getString("msg"), Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(MyRateActivity.this, json.getString("msg"), Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(MyRateActivity.this, R.string.unable_to_get_data, Toast.LENGTH_SHORT).show();
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