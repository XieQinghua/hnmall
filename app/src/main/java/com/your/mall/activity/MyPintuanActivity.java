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
import com.your.mall.adapter.MyPintuanAdapter;
import com.your.mall.application.MallApplication;
import com.your.mall.base.BaseSwipeBackActivity;
import com.your.mall.bean.MyPintuanBean;
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
 * 类描述：我的拼团
 * 修改备注：
 */
public class MyPintuanActivity extends BaseSwipeBackActivity {
    private static final String TAG = "MyPintuanActivity";
    private TextView tv_title;
    private ListView lv_my_pintuan;
    private ArrayList<MyPintuanBean.DataBean.TradesBean> list;
    private MyPintuanAdapter myPintuanAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_pingtuan);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        init();
    }

    private void init() {
        tv_title = (TextView) findViewById(R.id.tv_title_center);
        tv_title.setText("我的团购列表");

        lv_my_pintuan = (ListView) findViewById(R.id.lv_my_pintuan);

        //TODO 设置setOnItemClickListener
        lv_my_pintuan.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (list.get(position).getActivity_lx().equals("1")) {
                    Intent intent = new Intent(MyPintuanActivity.this, CenterPintuanDetailsActivity.class);
                    intent.putExtra("activity_id", list.get(position).getActivity_id());
                    intent.putExtra("item_id", list.get(position).getItem_id());
                    intent.putExtra("kaituan_id", list.get(position).getKaituan_id());
                    startActivity(intent);
                } else if (list.get(position).getActivity_lx().equals("6")) {
                    Intent intent = new Intent(MyPintuanActivity.this, CenterPintuanCjDetailsActivity.class);
                    intent.putExtra("activity_id", list.get(position).getActivity_id());
                    intent.putExtra("item_id", list.get(position).getItem_id());
                    intent.putExtra("kaituan_id", list.get(position).getKaituan_id());
                    startActivity(intent);
                }

            }
        });

        getData();
    }

    private void getData() {
        Map<String, String> map = new HashMap<>();
        map.put("user_id", MallApplication.getInstance().getUserId(MyPintuanActivity.this));
        showDialog(LOADING_DIALOG);
        XUtils.Get(MallApi.APP_MEMBER_PINTUAN, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                Log.e(TAG, result);
                if (JsonValidator.validate(result)) {
                    try {
                        JSONObject json = new JSONObject(result);
                        if (json.getString("code").equals("0")) {
                            MyPintuanBean myPintuanBean = ParseUtils.parseMyPintuanBean(result);
                            if (myPintuanBean.getData().getTrades() != null && myPintuanBean.getData().getTrades().size() != 0) {
                                list = (ArrayList<MyPintuanBean.DataBean.TradesBean>) myPintuanBean.getData().getTrades();
                                myPintuanAdapter = new MyPintuanAdapter(MyPintuanActivity.this, list);
                                lv_my_pintuan.setVisibility(View.VISIBLE);
                                lv_my_pintuan.setAdapter(myPintuanAdapter);
                                myPintuanAdapter.notifyDataSetChanged();
                            } else {
                                lv_my_pintuan.setVisibility(View.GONE);
                                Toast.makeText(MyPintuanActivity.this, myPintuanBean.getMsg(), Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(MyPintuanActivity.this, json.getString("msg"), Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(MyPintuanActivity.this, R.string.unable_to_get_data, Toast.LENGTH_SHORT).show();
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
