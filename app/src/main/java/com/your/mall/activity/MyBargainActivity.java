package com.your.mall.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.your.mall.R;
import com.your.mall.adapter.MyBargainAdapter;
import com.your.mall.application.MallApplication;
import com.your.mall.base.BaseSwipeBackActivity;
import com.your.mall.bean.MyBargainBean;
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
 * 创建时间：2016/11/14
 * 类描述：我的砍价
 * 修改备注：
 */
public class MyBargainActivity extends BaseSwipeBackActivity {
    private static final String TAG = "MyBargainActivity";
    private TextView tv_title;
    private ListView lv_my_bargain;
    private ArrayList<MyBargainBean.DataBean> list;
    private MyBargainAdapter myBargainAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_bargain);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        init();
    }

    private void init() {
        tv_title = (TextView) findViewById(R.id.tv_title_center);
        tv_title.setText("我的砍价");
        lv_my_bargain = (ListView) findViewById(R.id.lv_my_bargain);
        lv_my_bargain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MyBargainActivity.this, BargainDetailsActivity.class);
                intent.putExtra("activity_id", list.get(position).getActivity_id());
                intent.putExtra("item_id", list.get(position).getItem_id());
                intent.putExtra("status", list.get(position).getStatus());
                startActivity(intent);
            }
        });
        getData();
    }

    private void getData() {
        Map<String, String> map = new HashMap<>();
        map.put("user_id", MallApplication.getInstance().getUserId(MyBargainActivity.this));
        showDialog(LOADING_DIALOG);
        XUtils.Get(MallApi.APP_MEMBER_BARGAIN, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                Log.e(TAG, result);
                if (JsonValidator.validate(result)) {
                    try {
                        JSONObject json = new JSONObject(result);
                        if (json.getString("code").equals("0")) {
                            MyBargainBean myBargainBean = ParseUtils.parseMyBargainBean(result);
                            if (myBargainBean.getData() != null && myBargainBean.getData().size() != 0) {
                                list = (ArrayList<MyBargainBean.DataBean>) myBargainBean.getData();
                                myBargainAdapter = new MyBargainAdapter(MyBargainActivity.this, list);
                                lv_my_bargain.setVisibility(View.VISIBLE);
                                lv_my_bargain.setAdapter(myBargainAdapter);
                                myBargainAdapter.notifyDataSetChanged();
                            } else {
                                lv_my_bargain.setVisibility(View.GONE);
                                Toast.makeText(MyBargainActivity.this, myBargainBean.getMsg(), Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(MyBargainActivity.this, json.getString("msg"), Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(MyBargainActivity.this, R.string.unable_to_get_data, Toast.LENGTH_SHORT).show();
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
