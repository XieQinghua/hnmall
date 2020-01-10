package com.your.mall.activity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.your.mall.R;
import com.your.mall.adapter.ZujiAdapter;
import com.your.mall.application.MallApplication;
import com.your.mall.base.BaseSwipeBackActivity;
import com.your.mall.bean.ZujiBean;
import com.your.mall.http.MallApi;
import com.your.mall.utils.JsonValidator;
import com.your.mall.utils.MyCallBack;
import com.your.mall.utils.ParseUtils;
import com.your.mall.utils.XUtils;
import com.your.mall.view.XList.XListView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xieqinghua.
 * 创建时间：2016/11/14
 * 类描述：我的足迹
 * 修改备注：
 */
public class MyZujiActivity extends BaseSwipeBackActivity implements XListView.IXListViewListener {
    private static final String TAG = "MyZujiActivity";
    private TextView tv_title;
    private XListView lv_zuji;
    private TextView tv_no_collect;
    private ArrayList<ZujiBean.DataBean.ItemsBean> list;
    private ArrayList<ZujiBean.DataBean.ItemsBean> lists = new ArrayList<>();
    private ZujiAdapter myZujiAdapter;

    private Handler mHandler;
    private int page_no = 1, page_size = 10, maxPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_zuji);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        init();
    }

    private void init() {
        tv_title = (TextView) findViewById(R.id.tv_title_center);
        tv_title.setText("我的足迹");

        tv_no_collect = (TextView) findViewById(R.id.tv_no_collect);
        lv_zuji = (XListView) findViewById(R.id.lv_zuji);
        lv_zuji.hideHeader();//隐藏头部
        lv_zuji.setPullLoadEnable(true);
        lv_zuji.setXListViewListener(this);
        mHandler = new Handler();
        myZujiAdapter = new ZujiAdapter(MyZujiActivity.this, lists);
        lv_zuji.setAdapter(myZujiAdapter);

        getData();
    }

    private void getData() {
        Map<String, String> map = new HashMap<>();
        map.put("user_id", MallApplication.getInstance().getUserId(MyZujiActivity.this));
        map.put("page_size", page_size + "");
        map.put("page_no", page_no + "");
        showDialog(LOADING_DIALOG);
        XUtils.Get(MallApi.APP_MEMBER_ZUJI, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                Log.e(TAG, result);
                if (JsonValidator.validate(result)) {
                    try {
                        JSONObject json = new JSONObject(result);
                        if (json.getString("code").equals("0")) {
                            ZujiBean zujiBean = ParseUtils.parseZujiBean(result);
                            int count = Integer.parseInt(zujiBean.getCount());
                            maxPage = (int) Math.ceil((double) count / (double) page_size);//逢小数点进1，向上取整
                            if (zujiBean.getData().getItems() != null && zujiBean.getData().getItems().size() != 0) {
                                list = (ArrayList<ZujiBean.DataBean.ItemsBean>) zujiBean.getData().getItems();
                                lists.addAll(list);
                                myZujiAdapter.notifyDataSetChanged();
                            } else {
                                lv_zuji.setVisibility(View.GONE);
                                tv_no_collect.setVisibility(View.VISIBLE);
                            }
                        } else {
                            Toast.makeText(MyZujiActivity.this, json.getString("msg"), Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(MyZujiActivity.this, R.string.unable_to_get_data, Toast.LENGTH_SHORT).show();
                }
                removeDialog();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
            }
        });
    }

    private void onLoad() {
        lv_zuji.hideHeader();//隐藏头部
        lv_zuji.stopRefresh();
        lv_zuji.stopLoadMore();
    }

    @Override
    public void onRefresh() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                onLoad();
            }
        }, 0);
    }

    @Override
    public void onLoadMore() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if ((maxPage - page_no) > 0) {
                    page_no += 1;
                    getData();
                } else {
                    onLoad();
                    Toast.makeText(MyZujiActivity.this, R.string.no_more_data, Toast.LENGTH_SHORT).show();
                }
            }
        }, 1000);
    }
}
