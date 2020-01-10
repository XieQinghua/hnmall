package com.your.mall.activity;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.your.mall.R;
import com.your.mall.adapter.LogiInfoAdapter;
import com.your.mall.application.MallApplication;
import com.your.mall.base.BaseSwipeBackActivity;
import com.your.mall.bean.LogiInfoBean;
import com.your.mall.http.MallApi;
import com.your.mall.utils.JsonValidator;
import com.your.mall.utils.MyCallBack;
import com.your.mall.utils.ParseUtils;
import com.your.mall.utils.XUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by xieqinghua.
 * 创建时间：2016/10/16
 * 类描述：物流信息
 * 修改备注：
 */
public class LogiInfoActivity extends BaseSwipeBackActivity {
    private TextView tv_title;
    private ListView lv_logi_info;
    private LogiInfoAdapter myLogiInfoAdapter;
    private ArrayList<LogiInfoBean.DataBean> list;
    private String logi_no;
    private String corp_code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logi_info);

        init();
    }

    private void init() {
        tv_title = (TextView) findViewById(R.id.tv_title_center);
        tv_title.setText("查看物流");
        lv_logi_info = (ListView) findViewById(R.id.lv_logi_info);
        logi_no = getIntent().getStringExtra("logi_no");
        corp_code = getIntent().getStringExtra("corp_code");
        Log.e("logi_info", logi_no + ";" + corp_code);
        getData();
    }

    private void getData() {
        Map<String, String> map = new HashMap<>();
        map.put("user_id", MallApplication.getInstance().getUserId(LogiInfoActivity.this));
        map.put("logi_no", logi_no);
        map.put("corp_code", corp_code);
        showDialog(LOADING_DIALOG);
        XUtils.Get(MallApi.APP_TRADE_GETTRACK, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                Log.e("logi", result);
                if (JsonValidator.validate(result)) {
                    try {
                        JSONObject json = new JSONObject(result);
                        if (json.getString("code").equals("0")) {
                            LogiInfoBean logiInfoBean = ParseUtils.parseLogiInfoBean(result);
                            if (logiInfoBean.getData() != null && logiInfoBean.getData().size() != 0) {
                                list = (ArrayList<LogiInfoBean.DataBean>) logiInfoBean.getData();
                                myLogiInfoAdapter = new LogiInfoAdapter(LogiInfoActivity.this, list);
                                lv_logi_info.setVisibility(View.GONE);
                                lv_logi_info.setAdapter(myLogiInfoAdapter);
                                removeDialog();
                            }
                        } else {
                            Toast.makeText(LogiInfoActivity.this, json.getString("msg"), Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                } else {
                    Toast.makeText(LogiInfoActivity.this, R.string.unable_to_get_data, Toast.LENGTH_SHORT).show();
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
