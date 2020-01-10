package com.your.mall.activity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.your.mall.R;
import com.your.mall.adapter.MyBargainAdapter;
import com.your.mall.application.MallApplication;
import com.your.mall.base.BaseSwipeBackActivity;
import com.your.mall.bean.AddCartBean;
import com.your.mall.bean.MyBargainBean;
import com.your.mall.bean.OrderTallyBean;
import com.your.mall.bean.TotalBean;
import com.your.mall.bean.TradeAddBean;
import com.your.mall.http.MallApi;
import com.your.mall.utils.ImgUtils;
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
 * 创建时间：2016/9/19
 * 类描述：订单结算页面(商品详情立即购买-结算，砍价详情立即购买结算)
 * 修改备注：
 */
public class OrderTallyActivity extends BaseSwipeBackActivity {
    private static final String TAG = "OrderTallyActivity";
    private TextView tv_title;

    private LinearLayout ll_order_info;

    private RelativeLayout rl_consignee_info;
    private TextView tv_address_empty;
    private TextView tv_consignee, tv_mobile, tv_address;

    private TextView tv_shop_name, tv_promotion_info;

    private ImageView iv_goods_img;
    private TextView tv_goods_name, tv_goods_price, tv_old_price, tv_num;

    private EditText et_message;
    private String message;

    private TextView tv_cost_info, tv_total_cost;

    private TextView tv_amount_payable;
    private TextView tv_submit;

    private RadioGroup rg_send_time;
    private RadioButton rb_workaday, rb_holidays, rb_unlimited;
    private String send_time = "仅工作日送货";

    private String sku_id;
    private String quantity;
    private String obj_type;
    private String mode;
    private String addr_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_tally);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        init();
    }

    private void init() {
        tv_title = (TextView) findViewById(R.id.tv_title_center);
        tv_title.setText("订单结算");

        ll_order_info = (LinearLayout) findViewById(R.id.ll_order_info);

        rl_consignee_info = (RelativeLayout) findViewById(R.id.rl_consignee_info);
        tv_address_empty = (TextView) findViewById(R.id.tv_address_empty);
        tv_consignee = (TextView) findViewById(R.id.tv_consignee);
        tv_mobile = (TextView) findViewById(R.id.tv_mobile);
        tv_address = (TextView) findViewById(R.id.tv_address);

        tv_shop_name = (TextView) findViewById(R.id.tv_shop_name);
        tv_promotion_info = (TextView) findViewById(R.id.tv_promotion_info);

        iv_goods_img = (ImageView) findViewById(R.id.iv_goods_img);
        tv_goods_name = (TextView) findViewById(R.id.tv_goods_name);
        tv_goods_price = (TextView) findViewById(R.id.tv_goods_price);
        tv_old_price = (TextView) findViewById(R.id.tv_old_price);
        tv_num = (TextView) findViewById(R.id.tv_num);

        et_message = (EditText) findViewById(R.id.et_message);
        message = et_message.getText().toString().trim();
        tv_cost_info = (TextView) findViewById(R.id.tv_cost_info);
        tv_total_cost = (TextView) findViewById(R.id.tv_total_cost);

        tv_amount_payable = (TextView) findViewById(R.id.tv_amount_payable);
        tv_submit = (TextView) findViewById(R.id.tv_submit);

        rg_send_time = (RadioGroup) findViewById(R.id.rg_send_time);
        rb_workaday = (RadioButton) findViewById(R.id.rb_workaday);
        rb_holidays = (RadioButton) findViewById(R.id.rb_holidays);
        rb_unlimited = (RadioButton) findViewById(R.id.rb_unlimited);

        rg_send_time.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (rb_workaday.getId() == checkedId) {
                    send_time = rb_workaday.getText().toString();
                } else if (rb_holidays.getId() == checkedId) {
                    send_time = rb_holidays.getText().toString();
                } else if (rb_unlimited.getId() == checkedId) {
                    send_time = rb_unlimited.getText().toString();
                }
            }
        });

        sku_id = getIntent().getStringExtra("sku_id");
        quantity = getIntent().getStringExtra("quantity");
        obj_type = getIntent().getStringExtra("obj_type");
        mode = getIntent().getStringExtra("mode");
        getData();
    }

    private void getData() {
        //先调加入购物车接口
        Map<String, String> map = new HashMap<>();
        map.put("user_id", MallApplication.getInstance().getUserId(OrderTallyActivity.this));
        map.put("sku_id", sku_id);
        map.put("quantity", quantity);
        map.put("obj_type", obj_type);
        if (getIntent().getStringExtra("tuan_price") != null) {
            map.put("tuan_price", getIntent().getStringExtra("tuan_price"));
        }
        if (getIntent().getStringExtra("activity_id") != null) {
            map.put("activity_id", getIntent().getStringExtra("activity_id"));
        }
        map.put("mode", mode);
        showDialog(LOADING_DIALOG);
        XUtils.Post(MallApi.APP_CART_ADD, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                Log.e("fastbuy", result);
                if (JsonValidator.validate(result)) {
                    try {
                        JSONObject json = new JSONObject(result);
                        if (json.getString("code").equals("0")) {
//                            AddCartBean addCartBean = ParseUtils.parseAddCartBean(result);
                            //订单结算
                            Map<String, String> map = new HashMap<>();
                            map.put("user_id", MallApplication.getInstance().getUserId(OrderTallyActivity.this));
                            map.put("mode", "fastbuy");
                            XUtils.Get(MallApi.APP_CART_CHECKOUT, map, new MyCallBack<String>() {
                                @Override
                                public void onSuccess(String result) {
                                    super.onSuccess(result);
                                    Log.e("checkout", result);
                                    if (JsonValidator.validate(result)) {
                                        final OrderTallyBean orderTallyBean = ParseUtils.parseOrderTallyBean(result);
                                        if (orderTallyBean.getCode().equals("0")) {
                                            Map<String, String> map = new HashMap<>();
                                            map.put("user_id", MallApplication.getInstance().getUserId(OrderTallyActivity.this));
                                            map.put("addr_id", orderTallyBean.getData().getDef_addr().getAddr_id());
                                            map.put("shop_id", orderTallyBean.getData().getCartInfo().get(0).getShop_id());
                                            map.put("template_id", orderTallyBean.getData().getCartInfo().get(0).getDtyList().get(0).getTemplate_id());
                                            map.put("mode", "fastbuy");
                                            XUtils.Get(MallApi.APP_CART_TOTAL, map, new MyCallBack<String>() {
                                                @Override
                                                public void onSuccess(String result) {
                                                    super.onSuccess(result);
                                                    Log.e("total", result);
                                                    if (JsonValidator.validate(result)) {
                                                        TotalBean totalBean = ParseUtils.parseTotalBean(result);
                                                        if (totalBean.getCode().equals("0")) {
                                                            fillDate(orderTallyBean, totalBean);
                                                            removeDialog();
                                                        } else {
                                                            Toast.makeText(OrderTallyActivity.this, totalBean.getMsg(), Toast.LENGTH_SHORT).show();
                                                        }
                                                    } else {
                                                        Toast.makeText(OrderTallyActivity.this, R.string.unable_to_get_data, Toast.LENGTH_SHORT).show();
                                                    }
                                                }

                                                @Override
                                                public void onError(Throwable ex, boolean isOnCallback) {
                                                    super.onError(ex, isOnCallback);
                                                }
                                            });
                                        } else {
                                            Toast.makeText(OrderTallyActivity.this, orderTallyBean.getMsg(), Toast.LENGTH_SHORT).show();
                                        }
                                    } else {
                                        Toast.makeText(OrderTallyActivity.this, R.string.unable_to_get_data, Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onError(Throwable ex, boolean isOnCallback) {
                                    super.onError(ex, isOnCallback);
                                }
                            });
                        } else {
                            Toast.makeText(OrderTallyActivity.this, json.getString("msg"), Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                } else {
                    Toast.makeText(OrderTallyActivity.this, R.string.unable_to_get_data, Toast.LENGTH_SHORT).show();
                }
                removeDialog();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
            }
        });
    }

    private void fillDate(final OrderTallyBean orderTallyBean, final TotalBean totalBean) {
        if (orderTallyBean.getData().getIs_def_addr().equals("1")) {
            addr_id = orderTallyBean.getData().getDef_addr().getAddr_id();
            rl_consignee_info.setVisibility(View.VISIBLE);
            tv_address_empty.setVisibility(View.GONE);
            tv_consignee.setText("收货人：" + orderTallyBean.getData().getDef_addr().getName());
            tv_mobile.setText(orderTallyBean.getData().getDef_addr().getMobile());
            tv_address.setText(orderTallyBean.getData().getDef_addr().getAddress());
            rl_consignee_info.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(OrderTallyActivity.this, AddressActivity.class);
                    intent.putExtra("tag", TAG);
                    startActivityForResult(intent, 1);
                }
            });
        } else {
            addr_id = "";
            rl_consignee_info.setVisibility(View.INVISIBLE);
            tv_address_empty.setVisibility(View.VISIBLE);
            tv_address_empty.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(OrderTallyActivity.this, AddressActivity.class);
                    intent.putExtra("tag", TAG);
                    startActivityForResult(intent, 2);
                }
            });
        }
        tv_shop_name.setText(orderTallyBean.getData().getCartInfo().get(0).getShop_name() + " ＞");
        if (!orderTallyBean.getData().getCartInfo().get(0).getPromotion_info().equals("")) {
            tv_promotion_info.setVisibility(View.VISIBLE);
            tv_promotion_info.setText("（" + orderTallyBean.getData().getCartInfo().get(0).getPromotion_info() + "）");
        } else {
            tv_promotion_info.setVisibility(View.INVISIBLE);
        }
        ImgUtils.setRectangleImage(iv_goods_img, orderTallyBean.getData().getCartInfo().get(0).getGood_list().get(0).getImage_default_id());
        tv_goods_name.setText(orderTallyBean.getData().getCartInfo().get(0).getGood_list().get(0).getTitle());
        tv_goods_price.setText("￥" + orderTallyBean.getData().getCartInfo().get(0).getGood_list().get(0).getTotal_price());
        tv_old_price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);//中间加横线
        tv_old_price.setText("￥" + orderTallyBean.getData().getCartInfo().get(0).getGood_list().get(0).getPrice());
        tv_num.setText("×" + orderTallyBean.getData().getCartInfo().get(0).getGood_list().get(0).getQuantity());

        tv_cost_info.setText("共" + totalBean.getData().getShop().get(0).getItemnum()
                + "件商品  运费：￥" +
                totalBean.getData().getShop().get(0).getPost_fee() +
                "  减免：-￥" +
                totalBean.getData().getShop().get(0).getDiscount_fee());
        tv_total_cost.setText("￥" + totalBean.getData().getShop().get(0).getPayment());

        ll_order_info.setVisibility(View.VISIBLE);

        tv_amount_payable.setText("应付金额：￥" + totalBean.getData().getAllPayment());

        tv_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (addr_id.equals("")) {
                    Toast.makeText(OrderTallyActivity.this, "请选择收货地址！", Toast.LENGTH_SHORT).show();
                    return;
                }
                Map<String, String> map = new HashMap<>();
                map.put("user_id", MallApplication.getInstance().getUserId(OrderTallyActivity.this));
                map.put("mode", "fastbuy");
                map.put("addr_id", addr_id);
                map.put("depositvalue", "0");
                map.put("payment", totalBean.getData().getAllPayment());
                map.put("send_time", send_time);
                map.put("shipping", orderTallyBean.getData().getCartInfo().get(0).getGood_list().get(0).getShop_id() + ":" +
                        orderTallyBean.getData().getCartInfo().get(0).getDtyList().get(0).getTemplate_id() + ";");
                map.put("trade_memo", orderTallyBean.getData().getCartInfo().get(0).getGood_list().get(0).getShop_id() + ":" +
                        message);//买家留言
                showDialog(LOADING_DIALOG);
//                Log.e("user_id", MallApplication.getInstance().getUserId(OrderTallyActivity.this));
//                Log.e("mode", "fastbuy");
//                Log.e("addr_id", orderTallyBean.getData().getDef_addr().getAddr_id());
//                Log.e("depositvalue", "0");
//                Log.e("payment", totalBean.getData().getAllPayment());
//                Log.e("shipping", orderTallyBean.getData().getCartInfo().get(0).getGood_list().get(0).getShop_id() + ":" +
//                        orderTallyBean.getData().getCartInfo().get(0).getDtyList().get(0).getTemplate_id() + ";");
//                Log.e("trade_memo", orderTallyBean.getData().getCartInfo().get(0).getGood_list().get(0).getShop_id() + ":" +
//                        message);
                XUtils.tradeAddPost(MallApi.APP_TRADE_ADD, map, new MyCallBack<String>() {
                            @Override
                            public void onSuccess(String result) {
                                super.onSuccess(result);
                                Log.e("Payment", result);
                                if (JsonValidator.validate(result)) {
                                    TradeAddBean tradeAddBean = ParseUtils.parseTradeAddBean(result);
                                    if (tradeAddBean.getCode().equals("0")) {
                                        String url = tradeAddBean.getData().getUrl();
                                        Intent intent = new Intent(OrderTallyActivity.this, PaymentActivity.class);
                                        intent.putExtra("url", url);
                                        startActivity(intent);
                                        //关闭页面，防止从支付页面返回，造成购物车数据异常
                                        finish();
                                    } else {
                                        Toast.makeText(OrderTallyActivity.this, tradeAddBean.getMsg(), Toast.LENGTH_SHORT).show();
                                        removeDialog();
                                    }
                                } else {

                                }
                                removeDialog();
                            }

                            @Override
                            public void onError(Throwable ex, boolean isOnCallback) {
                                super.onError(ex, isOnCallback);
                            }
                        }
                );
            }
        });
    }

    // 回调方法，从第地址页面页面回来的时候会执行这个方法
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        String addr_id1 = data.getStringExtra("addr_id");
        String consignee1 = data.getStringExtra("consignee");
        String mobile1 = data.getStringExtra("mobile");
        String address1 = data.getStringExtra("address");

        if (requestCode == 1 && resultCode == 0) {
            addr_id = addr_id1;
            rl_consignee_info.setVisibility(View.VISIBLE);
            tv_address_empty.setVisibility(View.GONE);
            tv_consignee.setText("收货人：" + consignee1);
            tv_mobile.setText(mobile1);
            tv_address.setText(address1);
        } else if (requestCode == 2 && resultCode == 0) {
            addr_id = addr_id1;
            rl_consignee_info.setVisibility(View.VISIBLE);
            tv_address_empty.setVisibility(View.GONE);
            tv_consignee.setText("收货人：" + consignee1);
            tv_mobile.setText(mobile1);
            tv_address.setText(address1);
        }
    }
}
