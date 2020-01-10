package com.your.mall.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.your.mall.R;
import com.your.mall.base.BaseSwipeBackActivity;
import com.your.mall.http.MallApi;
import com.your.mall.utils.MyCallBack;
import com.your.mall.utils.XUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xieqinghua.
 * 创建时间：2016/8/30
 * 类描述：
 * 修改备注：
 */
public class TestActivity extends BaseSwipeBackActivity implements View.OnClickListener {
    private EditText et1, et2;
    private Button btn_submit;
    private TextView tv_json;
    String text1, text2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);
        et1 = (EditText) findViewById(R.id.et1);
        et2 = (EditText) findViewById(R.id.et2);
        btn_submit = (Button) findViewById(R.id.btn_submit);
        tv_json = (TextView) findViewById(R.id.tv_json);

        btn_submit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_submit:
                text1 = et1.getText().toString().trim();
                text2 = et2.getText().toString().trim();

                String url = MallApi.APP_GOODS;
                Map<String, String> map = new HashMap<>();
                if (!text1.equals("")) {
                    map.put("item_id", text1);
                }
                if (!text2.equals("")) {
                    map.put("shop_id", text2);
                }

                XUtils.Get(url, map, new MyCallBack<String>() {
                    @Override
                    public void onSuccess(String result) {
                        super.onSuccess(result);
                        Log.e("test", result.toString());
                        tv_json.setText(result.toString());
                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {
                        super.onError(ex, isOnCallback);
                    }
                });
                break;
        }
    }
}
