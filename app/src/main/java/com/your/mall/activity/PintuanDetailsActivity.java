package com.your.mall.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.your.mall.R;
import com.your.mall.base.BaseSwipeBackActivity;
import com.your.mall.bean.PintuanDetailsBean;
import com.your.mall.http.MallApi;
import com.your.mall.utils.ImgUtils;
import com.your.mall.utils.JsonValidator;
import com.your.mall.utils.MyCallBack;
import com.your.mall.utils.ParseUtils;
import com.your.mall.utils.XUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xieqinghua.
 * 创建时间：2016/11/14
 * 类描述：拼团详情
 * 修改备注：
 */
public class PintuanDetailsActivity extends BaseSwipeBackActivity implements View.OnClickListener {
    private static final String TAG = "PintuanDetailsActivity";
    private TextView tv_title;
    private String activity_id, item_id;
    private PintuanDetailsBean myPintuanDetailsBean;

    private LinearLayout ll_pintuan_info;

    private ImageView iv_goods_banner;
    private TextView tv_goods_title, tv_str1, tv_str2,
            tv_activity_price, tv_price, tv_activity_number, tv_pintuan_rule;
    private LinearLayout ll_pintuan_buy, ll_pintuan_single_buy;
    private WebView wap_desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pintuan_details);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        init();
    }

    private void init() {
        tv_title = (TextView) findViewById(R.id.tv_title_center);
        tv_title.setText("团购商品");
        activity_id = getIntent().getStringExtra("activity_id");
        item_id = getIntent().getStringExtra("item_id");

        ll_pintuan_info = (LinearLayout) findViewById(R.id.ll_pintuan_info);
        tv_str1 = (TextView) findViewById(R.id.tv_str1);
        tv_str2 = (TextView) findViewById(R.id.tv_str2);
        tv_activity_price = (TextView) findViewById(R.id.tv_activity_price);
        tv_price = (TextView) findViewById(R.id.tv_price);
        tv_activity_number = (TextView) findViewById(R.id.tv_activity_number);
        iv_goods_banner = (ImageView) findViewById(R.id.iv_goods_banner);
        tv_goods_title = (TextView) findViewById(R.id.tv_goods_title);
        tv_pintuan_rule = (TextView) findViewById(R.id.tv_pintuan_rule);
        ll_pintuan_buy = (LinearLayout) findViewById(R.id.ll_pintuan_buy);
        ll_pintuan_single_buy = (LinearLayout) findViewById(R.id.ll_pintuan_single_buy);
        wap_desc = (WebView) findViewById(R.id.wap_desc);

        getData();
    }

    private void getData() {
        Map<String, String> map = new HashMap<>();
        map.put("activity_id", activity_id);
        map.put("item_id", item_id);
        showDialog(LOADING_DIALOG);
        XUtils.Get(MallApi.APP_KAITUAN_VIEW, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                Log.e(TAG, result);
                if (JsonValidator.validate(result)) {
                    try {
                        JSONObject json = new JSONObject(result);
                        if (json.getString("code").equals("0")) {
                            myPintuanDetailsBean = ParseUtils.parsePintuanDetailsBean(result);
                            fillData(myPintuanDetailsBean);
                        } else {
                            Toast.makeText(PintuanDetailsActivity.this, json.getString("msg"), Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(PintuanDetailsActivity.this, R.string.unable_to_get_data, Toast.LENGTH_SHORT).show();
                }
                removeDialog();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
            }
        });
    }

    private void fillData(final PintuanDetailsBean pintuanDetailsBean) {
        tv_goods_title.setText(pintuanDetailsBean.getData().getActivity_goods_title());
        tv_pintuan_rule.setOnClickListener(this);
        tv_str1.setText(pintuanDetailsBean.getData().getStr1());
        tv_str2.setText(pintuanDetailsBean.getData().getStr2());
        tv_activity_price.setText(pintuanDetailsBean.getData().getActivity_price() + "/件");
        tv_price.setText(pintuanDetailsBean.getData().getPrice() + "/件");
        tv_activity_number.setText(pintuanDetailsBean.getData().getActivity_number() + "人团");
        ll_pintuan_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PintuanDetailsActivity.this, OrderTallyActivity.class);
                intent.putExtra("sku_id", pintuanDetailsBean.getData().getItem().getDefault_sku_id());
                intent.putExtra("quantity", "1");
                intent.putExtra("obj_type", "pintuan");
                intent.putExtra("tuan_price", pintuanDetailsBean.getData().getActivity_price());
                intent.putExtra("activity_id", pintuanDetailsBean.getData().getActivity_id());
                intent.putExtra("mode", "fastbuy");
                startActivity(intent);
            }
        });
        ll_pintuan_single_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PintuanDetailsActivity.this, OrderTallyActivity.class);
                intent.putExtra("sku_id", pintuanDetailsBean.getData().getItem().getDefault_sku_id());
                intent.putExtra("quantity", "1");
                intent.putExtra("obj_type", "item");
                intent.putExtra("mode", "fastbuy");
                startActivity(intent);
            }
        });

        String customHtml = "<!DOCTYPE html>\n" +
                "<html>\n" +
                " <head> \n" +
                "  <meta charset=\"utf-8\" /> \n" +
                "  <meta name=\"viewport\" content=\"width=device-width,\" initial-scale=\"1.0,\" maximum-scale=\"1.0\" /> \n" +
                "  <style>.fulldiv img{ width: 100%!important; }</style> \n" +
                " </head> \n" +
                " <body> \n" +
                "  <div class=\"fulldiv\"> \n" +
                pintuanDetailsBean.getData().getItem().getWap_desc() +
                "  </div>  \n" +
                " </body>\n" +
                "</html>";
        wap_desc.getSettings().setUseWideViewPort(true);
        wap_desc.getSettings().setLoadWithOverviewMode(true);
        wap_desc.loadDataWithBaseURL(null, customHtml, "text/html", "gb2312", null);

        ImgUtils.setRectangleImage(iv_goods_banner, pintuanDetailsBean.getData().getActivity_goods_banner());
        //显示页面
        ll_pintuan_info.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_pintuan_rule:
                startActivity(new Intent(PintuanDetailsActivity.this, PintuanRuleActivity.class)
                        .putExtra("name", "/pintuan-about.html"));
                break;
        }
    }
}
