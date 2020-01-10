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
import com.your.mall.application.MallApplication;
import com.your.mall.base.BaseSwipeBackActivity;
import com.your.mall.bean.PintuanCjDetailsBean;
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
 * 类描述：拼团抽奖详情
 * 修改备注：
 */
public class PintuanCjDetailsActivity extends BaseSwipeBackActivity implements View.OnClickListener {
    private static final String TAG = "PintuanCjDetailsActivity";
    private TextView tv_title;
    private String activity_id, item_id;
    private PintuanCjDetailsBean myPintuanCjDetailsBean;

    private LinearLayout ll_pintuancj_info;

    private ImageView iv_goods_banner;
    private TextView tv_goods_title, tv_pintuan_rule1, tv_pintuan_rule2, tv_check, tv_sold_quantity,
            tv_pintuan_time, tv_pintuan_people, tv_activity_price, tv_price, tv_activity_number,
            tv_str1, tv_str2, tv_str3, tv_str4;
    private LinearLayout ll_pintuancj_buy, ll_pintuancj_single_buy;
    private ImageView iv_shop_logo;
    private TextView tv_shop_name, tv_shop_descript, tv_collect_shop, tv_enter_shop;
    private WebView wap_desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pintuancj_details);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        init();
    }

    private void init() {
        tv_title = (TextView) findViewById(R.id.tv_title_center);
        tv_title.setText("团购商品");
        activity_id = getIntent().getStringExtra("activity_id");
        item_id = getIntent().getStringExtra("item_id");

        ll_pintuancj_info = (LinearLayout) findViewById(R.id.ll_pintuancj_info);

        iv_goods_banner = (ImageView) findViewById(R.id.iv_goods_banner);
        tv_goods_title = (TextView) findViewById(R.id.tv_goods_title);
        tv_pintuan_rule1 = (TextView) findViewById(R.id.tv_pintuan_rule1);
        tv_pintuan_rule2 = (TextView) findViewById(R.id.tv_pintuan_rule2);
        tv_check = (TextView) findViewById(R.id.tv_check);
        tv_sold_quantity = (TextView) findViewById(R.id.tv_sold_quantity);
        tv_pintuan_time = (TextView) findViewById(R.id.tv_pintuan_time);
        tv_pintuan_people = (TextView) findViewById(R.id.tv_pintuan_people);
        tv_activity_price = (TextView) findViewById(R.id.tv_activity_price);
        tv_price = (TextView) findViewById(R.id.tv_price);
        tv_activity_number = (TextView) findViewById(R.id.tv_activity_number);
        tv_str1 = (TextView) findViewById(R.id.tv_str1);
        tv_str2 = (TextView) findViewById(R.id.tv_str2);
        tv_str3 = (TextView) findViewById(R.id.tv_str3);
        tv_str4 = (TextView) findViewById(R.id.tv_str4);
        ll_pintuancj_buy = (LinearLayout) findViewById(R.id.ll_pintuancj_buy);
        ll_pintuancj_single_buy = (LinearLayout) findViewById(R.id.ll_pintuancj_single_buy);
        iv_shop_logo = (ImageView) findViewById(R.id.iv_shop_logo);
        tv_shop_name = (TextView) findViewById(R.id.tv_shop_name);
        tv_shop_descript = (TextView) findViewById(R.id.tv_shop_descript);
        tv_collect_shop = (TextView) findViewById(R.id.tv_collect_shop);
        tv_enter_shop = (TextView) findViewById(R.id.tv_enter_shop);
        wap_desc = (WebView) findViewById(R.id.wap_desc);

        getData();
    }

    private void getData() {
        Map<String, String> map = new HashMap<>();
        map.put("activity_id", activity_id);
        map.put("item_id", item_id);
        showDialog(LOADING_DIALOG);
        XUtils.Get(MallApi.APP_KAITUANCJ, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                Log.e(TAG, result);
                if (JsonValidator.validate(result)) {
                    try {
                        JSONObject json = new JSONObject(result);
                        if (json.getString("code").equals("0")) {
                            myPintuanCjDetailsBean = ParseUtils.parsePintuanCjDetailsBean(result);
                            fillData(myPintuanCjDetailsBean);
                        } else {
                            Toast.makeText(PintuanCjDetailsActivity.this, json.getString("msg"), Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(PintuanCjDetailsActivity.this, R.string.unable_to_get_data, Toast.LENGTH_SHORT).show();
                }
                removeDialog();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
            }
        });
    }

    private void fillData(final PintuanCjDetailsBean pintuanCjDetailsBean) {
        tv_goods_title.setText(pintuanCjDetailsBean.getData().getActivity_goods_title());
        tv_pintuan_rule1.setOnClickListener(this);
        tv_pintuan_rule2.setOnClickListener(this);
        tv_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //查看中奖名单
                Intent intent = new Intent(PintuanCjDetailsActivity.this, PintuanWinListActivity.class);
                intent.putExtra("activity_id", activity_id);
                intent.putExtra("item_id", item_id);
                startActivity(intent);
            }
        });
        tv_sold_quantity.setText("已售：" + pintuanCjDetailsBean.getData().getSold_quantity() + "件");
        tv_pintuan_time.setText("活动时间：" + pintuanCjDetailsBean.getData().getStart_time() + "~" + pintuanCjDetailsBean.getData().getEnd_time());
        tv_str1.setText(pintuanCjDetailsBean.getData().getStr1());
        tv_str2.setText(pintuanCjDetailsBean.getData().getStr2());
        tv_str3.setText(pintuanCjDetailsBean.getData().getStr3());
        tv_str4.setText(pintuanCjDetailsBean.getData().getStr4());
        ImgUtils.setRectangleImage(iv_shop_logo, pintuanCjDetailsBean.getData().getShop().getShop_logo());
        tv_shop_name.setText(pintuanCjDetailsBean.getData().getShop().getShop_name());
        String inviteNum = (Integer.parseInt(pintuanCjDetailsBean.getData().getActivity_number()) - 1) + "";
        tv_pintuan_people.setText("支付开团并邀请" + inviteNum + "人参团，详见下方拼团抽奖流程");
        tv_activity_price.setText(pintuanCjDetailsBean.getData().getActivity_price() + "/件");
        tv_price.setText(pintuanCjDetailsBean.getData().getPrice() + "/件");
        tv_activity_number.setText(pintuanCjDetailsBean.getData().getActivity_number() + "人团");
        ll_pintuancj_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PintuanCjDetailsActivity.this, OrderTallyActivity.class);
                intent.putExtra("sku_id", pintuanCjDetailsBean.getData().getDefault_sku_id());
                intent.putExtra("quantity", "1");
                intent.putExtra("obj_type", "pintuancj");
                intent.putExtra("tuan_price", pintuanCjDetailsBean.getData().getActivity_price());
                intent.putExtra("activity_id", pintuanCjDetailsBean.getData().getActivity_id());
                intent.putExtra("mode", "fastbuy");
                startActivity(intent);
            }
        });
        ll_pintuancj_single_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PintuanCjDetailsActivity.this, OrderTallyActivity.class);
                intent.putExtra("sku_id", pintuanCjDetailsBean.getData().getDefault_sku_id());
                intent.putExtra("quantity", "1");
                intent.putExtra("obj_type", "item");
                intent.putExtra("mode", "fastbuy");
                startActivity(intent);
            }
        });
        tv_shop_descript.setText(pintuanCjDetailsBean.getData().getShop().getShop_descript());
        tv_collect_shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, String> map = new HashMap<>();
                map.put("user_id", MallApplication.getInstance().getUserId(PintuanCjDetailsActivity.this));
                map.put("shop_id", pintuanCjDetailsBean.getData().getShop().getShop_id());
                showDialog(LOADING_DIALOG);
                XUtils.Post(MallApi.APP_SHOPSCOLLECT_ADD, map, new MyCallBack<String>() {
                    @Override
                    public void onSuccess(String result) {
                        super.onSuccess(result);
                        if (JsonValidator.validate(result)) {
                            try {
                                JSONObject json = new JSONObject(result);
                                if (json.getString("code").equals("0")) {
                                    Toast.makeText(PintuanCjDetailsActivity.this, "收藏成功", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(PintuanCjDetailsActivity.this, json.getString("msg"), Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        } else {
                            Toast.makeText(PintuanCjDetailsActivity.this, R.string.unable_to_get_data, Toast.LENGTH_SHORT).show();
                        }
                        removeDialog();
                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {
                        super.onError(ex, isOnCallback);
                    }
                });
            }
        });
        tv_enter_shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PintuanCjDetailsActivity.this, ShopHomepageActivity.class);
                intent.putExtra("shop_id", pintuanCjDetailsBean.getData().getShop().getShop_id());
                startActivity(intent);
            }
        });

//        String customHtml = "<!DOCTYPE html>\n" +
//                "<html>\n" +
//                " <head> \n" +
//                "  <meta charset=\"utf-8\" /> \n" +
//                "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, maximum-scale=1.0\" /> \n" +
//                "  <title></title> \n" +
//                "  <style type=\"text/css\">\n" +
//                "\t\t\t\t.img_info>p>img{ \n" +
//                "                width:100%;\n" +
//                "                }\n" +
//                "\t\t\t\t.img_info>img{ \n" +
//                "                width:100% !important;\n" +
//                "                }\n" +
//                "                </style>\n" +
//                " </head>\n" +
//                " <body>\n" +
//                " <a class=\"img_info\" id=\"img_info\"> " +
//                pintuanCjDetailsBean.getData().getItem().getWap_desc() +
//                " </a>\n" +
//                " </body>\n" +
//                "</html>";

        String customHtml = "<!DOCTYPE html>\n" +
                "<html>\n" +
                " <head> \n" +
                "  <meta charset=\"utf-8\" /> \n" +
                "  <meta name=\"viewport\" content=\"width=device-width,\" initial-scale=\"1.0,\" maximum-scale=\"1.0\" /> \n" +
                "  <style>.fulldiv img{ width: 100%!important; }</style> \n" +
                " </head> \n" +
                " <body> \n" +
                "  <div class=\"fulldiv\"> \n" +
                pintuanCjDetailsBean.getData().getItem().getWap_desc() +
                "  </div>  \n" +
                " </body>\n" +
                "</html>";
        wap_desc.getSettings().setUseWideViewPort(true);
        wap_desc.getSettings().setLoadWithOverviewMode(true);
        wap_desc.loadDataWithBaseURL(null, customHtml, "text/html", "gb2312", null);

        ImgUtils.setRectangleImage(iv_goods_banner, pintuanCjDetailsBean.getData().getActivity_goods_banner());
        //显示页面
        ll_pintuancj_info.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_pintuan_rule1:
                startActivity(new Intent(PintuanCjDetailsActivity.this, PintuanRuleActivity.class)
                        .putExtra("name", "/pintuancj-about.html"));
                break;
            case R.id.tv_pintuan_rule2:
                startActivity(new Intent(PintuanCjDetailsActivity.this, PintuanRuleActivity.class)
                        .putExtra("name", "/pintuancj-about.html"));
                break;
        }
    }
}
