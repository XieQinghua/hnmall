package com.your.mall.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.your.mall.R;
import com.your.mall.adapter.CartOrderTallyAdapter;
import com.your.mall.application.MallApplication;
import com.your.mall.base.BaseSwipeBackActivity;
import com.your.mall.bean.OrderTallyBean;

import com.your.mall.bean.TotalBean;
import com.your.mall.http.MallApi;
import com.your.mall.utils.JsonValidator;
import com.your.mall.utils.MyCallBack;
import com.your.mall.utils.ParseUtils;
import com.your.mall.utils.StringUtils;
import com.your.mall.utils.XUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xieqinghua.
 * 创建时间：2016/9/19
 * 类描述：购物车订单结算页面
 * 修改备注：
 */
public class CartOrderTallyActivity extends BaseSwipeBackActivity {
    private static final String TAG = "CartTally";
    private TextView tv_title;

    private LinearLayout ll_order_info;

    private RelativeLayout rl_consignee_info;
    private TextView tv_address_empty;
    private TextView tv_consignee, tv_mobile, tv_address;

    private TextView tv_amount_payable;
    private TextView tv_submit;

    private String message;
    private RadioGroup rg_send_time;
    private RadioButton rb_workaday, rb_holidays, rb_unlimited;
    private String send_time = "仅工作日送货";
    private String addr_id;

    private ListView lv_shop;
    private ArrayList<OrderTallyBean.DataBean.CartInfoBean> list;
    private ArrayList<TotalBean.DataBean.ShopBean> cost_list;
    private CartOrderTallyAdapter myCartOrderTallyAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_order_tally);
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

        lv_shop = (ListView) findViewById(R.id.lv_shop);

        getData();
    }

    private void getData() {
        Map<String, String> map = new HashMap<>();
        map.put("user_id", MallApplication.getInstance().getUserId(CartOrderTallyActivity.this));
        map.put("mode", "cart");
        showDialog(LOADING_DIALOG);
        XUtils.Get(MallApi.APP_CART_CHECKOUT, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                Log.e(TAG, result);
                if (JsonValidator.validate(result)) {
                    final OrderTallyBean orderTallyBean = ParseUtils.parseOrderTallyBean(result);
                    if (orderTallyBean.getCode().equals("0")) {
                        list = (ArrayList<OrderTallyBean.DataBean.CartInfoBean>) orderTallyBean.getData().getCartInfo();

                        List<String> shopIds = new ArrayList<>();
                        List<String> templateIds = new ArrayList<>();

                        for (OrderTallyBean.DataBean.CartInfoBean cartInfoBean : list) {
                            shopIds.add(cartInfoBean.getShop_id());
                            templateIds.add(cartInfoBean.getDtyList().get(0).getTemplate_id());
                        }

                        Map<String, String> map = new HashMap<>();
                        map.put("user_id", MallApplication.getInstance().getUserId(CartOrderTallyActivity.this));
                        map.put("addr_id", orderTallyBean.getData().getDef_addr().getAddr_id());
                        map.put("shop_id", StringUtils.stringJoin(shopIds, ","));
                        map.put("template_id", StringUtils.stringJoin(templateIds, ","));
                        map.put("mode", "cart");
                        XUtils.Get(MallApi.APP_CART_TOTAL, map, new MyCallBack<String>() {
                            @Override
                            public void onSuccess(String result) {
                                super.onSuccess(result);
                                Log.e("total", result);
                                if (JsonValidator.validate(result)) {
                                    TotalBean totalBean = ParseUtils.parseTotalBean(result);
                                    if (totalBean.getCode().equals("0")) {
                                        cost_list = (ArrayList<TotalBean.DataBean.ShopBean>) totalBean.getData().getShop();
                                        fillDate(orderTallyBean, totalBean);
                                        removeDialog();
                                    } else {
                                        Toast.makeText(CartOrderTallyActivity.this, totalBean.getMsg(), Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    Toast.makeText(CartOrderTallyActivity.this, R.string.unable_to_get_data, Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onError(Throwable ex, boolean isOnCallback) {
                                super.onError(ex, isOnCallback);
                            }
                        });
                    } else {
                        Toast.makeText(CartOrderTallyActivity.this, orderTallyBean.getMsg(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(CartOrderTallyActivity.this, R.string.unable_to_get_data, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
            }
        });
    }

    private void fillDate(final OrderTallyBean orderTallyBean, final TotalBean totalBean) {


        myCartOrderTallyAdapter = new CartOrderTallyAdapter(CartOrderTallyActivity.this, list, cost_list);
        lv_shop.setAdapter(myCartOrderTallyAdapter);

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
                    Intent intent = new Intent(CartOrderTallyActivity.this, AddressActivity.class);
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
                    Intent intent = new Intent(CartOrderTallyActivity.this, AddressActivity.class);
                    intent.putExtra("tag", TAG);
                    startActivityForResult(intent, 2);
                }
            });
        }

        ll_order_info.setVisibility(View.VISIBLE);

        tv_amount_payable.setText("应付金额：￥" + totalBean.getData().getAllPayment());

        tv_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (addr_id.equals("")) {
                    Toast.makeText(CartOrderTallyActivity.this, "请选择收货地址！", Toast.LENGTH_SHORT).show();
                    return;
                }

                List<String> shippingList = new ArrayList<>();
                List<String> trade_memoList = new ArrayList<>();

                for (OrderTallyBean.DataBean.CartInfoBean cartInfoBean : list) {
                    shippingList.add(cartInfoBean.getShop_id() + ":" + cartInfoBean.getDtyList().get(0).getTemplate_id());
                    trade_memoList.add(cartInfoBean.getShop_id() + ":123456");
                }

                Map<String, String> map = new HashMap<>();
                map.put("user_id", MallApplication.getInstance().getUserId(CartOrderTallyActivity.this));
                map.put("mode", "cart");
                map.put("addr_id", addr_id);
                map.put("depositvalue", "0");
                map.put("payment", totalBean.getData().getAllPayment());
                map.put("send_time", send_time);
                map.put("shipping", StringUtils.stringJoin(shippingList, ";"));
                map.put("trade_memo", StringUtils.stringJoin(trade_memoList, ";"));
                showDialog(LOADING_DIALOG);

//                Log.e("user_id", MallApplication.getInstance().getUserId(CartOrderTallyActivity.this));
//                Log.e("mode", "fastbuy");
//                Log.e("addr_id", orderTallyBean.getData().getDef_addr().getAddr_id());
//                Log.e("depositvalue", "0");
//                Log.e("payment", totalBean.getData().getAllPayment());
//                Log.e("shipping", StringUtils.stringJoin(shippingList, ";"));
//                Log.e("trade_memo", StringUtils.stringJoin(trade_memoList, ";"));
                XUtils.tradeAddPost(MallApi.APP_TRADE_ADD, map, new MyCallBack<String>() {
                            @Override
                            public void onSuccess(String result) {
                                super.onSuccess(result);
                                Log.e("Payment", result);
                                if (JsonValidator.validate(result)) {
//                                    TradeAddBean tradeAddBean = ParseUtils.parseTradeAddBean(result);
//                                    if (tradeAddBean.getCode().equals("0")) {
//                                        String url = tradeAddBean.getData().getUrl();
//                                        Intent intent = new Intent(CartOrderTallyActivity.this, PaymentActivity.class);
//                                        intent.putExtra("url", url);
//                                        startActivity(intent);
//                                        //关闭页面，防止从支付页面返回，造成购物车数据异常
//                                        finish();
//                                    } else {
//                                        Toast.makeText(CartOrderTallyActivity.this, tradeAddBean.getMsg(), Toast.LENGTH_SHORT).show();
//                                        removeDialog();
//                                    }

                                    try {
                                        JSONObject json = new JSONObject(result);
                                        if (json.getString("code").equals("0")) {
                                            JSONObject data = json.getJSONObject("data");
                                            String url = data.getString("url");
                                            String cart_num = data.getString("cart_num");
                                            MallApplication.getInstance().setCartNum(CartOrderTallyActivity.this, cart_num);
                                            Intent intent = new Intent(CartOrderTallyActivity.this, PaymentActivity.class);
                                            intent.putExtra("url", url);
                                            startActivity(intent);
                                            //关闭页面，防止从支付页面返回，造成购物车数据异常
                                            finish();
                                        } else {
                                            Toast.makeText(CartOrderTallyActivity.this, json.getString("msg"), Toast.LENGTH_SHORT).show();
                                            removeDialog();
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                } else {
                                    Toast.makeText(CartOrderTallyActivity.this, R.string.unable_to_get_data, Toast.LENGTH_SHORT).show();
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
