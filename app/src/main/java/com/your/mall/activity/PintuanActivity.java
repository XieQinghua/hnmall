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
import com.your.mall.adapter.PintuanAdapter;
import com.your.mall.adapter.PintuanCjAdapter;
import com.your.mall.base.BaseSwipeBackActivity;
import com.your.mall.bean.PintuanBean;
import com.your.mall.http.MallApi;
import com.your.mall.utils.JsonValidator;
import com.your.mall.utils.MyCallBack;
import com.your.mall.utils.ParseUtils;
import com.your.mall.utils.XUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by xieqinghua.
 * 创建时间：2016/11/14
 * 类描述：拼团
 * 修改备注：
 */
public class PintuanActivity extends BaseSwipeBackActivity {
    private static final String TAG = "PintuanActivity";
    private TextView tv_title;
    private ListView lv_pingtuan_cj, lv_pingtuan;
    private ArrayList<PintuanBean.DataBean.ItemsPintuancjBean> list1;
    private PintuanCjAdapter myPintuanCjAdapter;
    private ArrayList<PintuanBean.DataBean.ItemsPintuanBean> list2;
    private PintuanAdapter myPintuanAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pintuan);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        init();
    }

    private void init() {
        tv_title = (TextView) findViewById(R.id.tv_title_center);
        tv_title.setText("拼团");
        lv_pingtuan_cj = (ListView) findViewById(R.id.lv_pingtuan_cj);
        lv_pingtuan = (ListView) findViewById(R.id.lv_pingtuan);
        lv_pingtuan_cj.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(PintuanActivity.this, PintuanCjDetailsActivity.class);
                intent.putExtra("activity_id", list1.get(position).getActivity_id());
                intent.putExtra("item_id", list1.get(position).getItem_id());
                startActivity(intent);
            }
        });
        lv_pingtuan.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(PintuanActivity.this, PintuanDetailsActivity.class);
                intent.putExtra("activity_id", list2.get(position).getActivity_id());
                intent.putExtra("item_id", list2.get(position).getItem_id());
                startActivity(intent);
            }
        });
        getData();
    }

    private void getData() {
        showDialog(LOADING_DIALOG);
        XUtils.Get(MallApi.APP_PINGTUANCJ, null, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                Log.e(TAG, result);
                if (JsonValidator.validate(result)) {
                    try {
                        JSONObject json = new JSONObject(result);
                        if (json.getString("code").equals("0")) {
                            PintuanBean pintuanBean = ParseUtils.parsePintuanBean(result);
                            if (pintuanBean.getData().getItems_pintuancj() != null && pintuanBean.getData().getItems_pintuancj().size() != 0) {
                                list1 = (ArrayList<PintuanBean.DataBean.ItemsPintuancjBean>) pintuanBean.getData().getItems_pintuancj();
                                myPintuanCjAdapter = new PintuanCjAdapter(PintuanActivity.this, list1);
                                lv_pingtuan_cj.setVisibility(View.VISIBLE);
                                lv_pingtuan_cj.setAdapter(myPintuanCjAdapter);
                                myPintuanCjAdapter.notifyDataSetChanged();
                            }
                            if (pintuanBean.getData().getItems_pintuan() != null && pintuanBean.getData().getItems_pintuan().size() != 0) {
                                list2 = (ArrayList<PintuanBean.DataBean.ItemsPintuanBean>) pintuanBean.getData().getItems_pintuan();
                                myPintuanAdapter = new PintuanAdapter(PintuanActivity.this, list2);
                                lv_pingtuan.setVisibility(View.VISIBLE);
                                lv_pingtuan.setAdapter(myPintuanAdapter);
                                myPintuanAdapter.notifyDataSetChanged();
                            }
                        } else {
                            Toast.makeText(PintuanActivity.this, json.getString("msg"), Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(PintuanActivity.this, R.string.unable_to_get_data, Toast.LENGTH_SHORT).show();
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
