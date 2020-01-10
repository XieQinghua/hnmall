package com.your.mall.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.your.mall.R;
import com.your.mall.adapter.ChildOrderDetailsAdapter;
import com.your.mall.application.MallApplication;
import com.your.mall.base.BaseSwipeBackActivity;
import com.your.mall.bean.OrderDetailsBean;
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
 * 创建时间：2016/9/19
 * 类描述：订单详情页面
 * 修改备注：
 */
public class OrderDetailsActivity extends BaseSwipeBackActivity {
    private TextView tv_title;
    private String tid;

    private TextView tv_consignee, tv_mobile, tv_address;

    private ListView lv_child_order_details;
    private ArrayList<OrderDetailsBean.DataBean.TradeBean.OrdersBean> list;
    private ChildOrderDetailsAdapter myChildOrderDetailsAdapter;

    private TextView tv_total_fee, tv_post_fee, tv_discount_fee, tv_payment, tv_tid, tv_created_time;

    private LinearLayout ll_pay_info;
    private TextView tv_pay, tv_cancel_order;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        init();
    }

    private void init() {
        tv_title = (TextView) findViewById(R.id.tv_title_center);
        tv_title.setText("订单详情");
        tid = getIntent().getStringExtra("tid");

        tv_consignee = (TextView) findViewById(R.id.tv_consignee);
        tv_mobile = (TextView) findViewById(R.id.tv_mobile);
        tv_address = (TextView) findViewById(R.id.tv_address);

        lv_child_order_details = (ListView) findViewById(R.id.lv_child_order_details);

        tv_total_fee = (TextView) findViewById(R.id.tv_total_fee);
        tv_post_fee = (TextView) findViewById(R.id.tv_post_fee);
        tv_discount_fee = (TextView) findViewById(R.id.tv_discount_fee);
        tv_payment = (TextView) findViewById(R.id.tv_payment);
        tv_tid = (TextView) findViewById(R.id.tv_tid);
        tv_created_time = (TextView) findViewById(R.id.tv_created_time);

        ll_pay_info = (LinearLayout) findViewById(R.id.ll_pay_info);
        tv_pay = (TextView) findViewById(R.id.tv_pay);
        tv_cancel_order = (TextView) findViewById(R.id.tv_cancel_order);

        getData();
    }

    private void getData() {
        Map<String, String> map = new HashMap<>();
        map.put("user_id", MallApplication.getInstance().getUserId(OrderDetailsActivity.this));
        map.put("tid", tid);
        showDialog(LOADING_DIALOG);
        XUtils.Get(MallApi.APP_TRADEDETAIL, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                Log.e("OrderDetails", result);
                if (JsonValidator.validate(result)) {
                    OrderDetailsBean orderDetailsBean = ParseUtils.parseOrderDetailsBean(result);
                    if (orderDetailsBean.getCode().equals("0")) {
                        fillData(orderDetailsBean);
                    } else {
                        Toast.makeText(OrderDetailsActivity.this, orderDetailsBean.getMsg(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(OrderDetailsActivity.this, R.string.unable_to_get_data, Toast.LENGTH_SHORT).show();
                }
                removeDialog();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
            }
        });
    }

    private void fillData(final OrderDetailsBean orderDetailsBean) {
        tv_consignee.setText("收货人：" + orderDetailsBean.getData().getTrade().getReceiver_name());
        tv_mobile.setText(orderDetailsBean.getData().getTrade().getReceiver_mobile());
        tv_address.setText("收货地址：" + orderDetailsBean.getData().getTrade().getReceiver_state() +
                orderDetailsBean.getData().getTrade().getReceiver_city() +
                orderDetailsBean.getData().getTrade().getReceiver_district() +
                orderDetailsBean.getData().getTrade().getReceiver_address());

        list = (ArrayList<OrderDetailsBean.DataBean.TradeBean.OrdersBean>) orderDetailsBean.getData().getTrade().getOrders();
        myChildOrderDetailsAdapter = new ChildOrderDetailsAdapter(this, list);
        lv_child_order_details.setAdapter(myChildOrderDetailsAdapter);

        tv_total_fee.setText("￥" + orderDetailsBean.getData().getTrade().getTotal_fee());
        tv_post_fee.setText("￥" + orderDetailsBean.getData().getTrade().getPost_fee());
        tv_discount_fee.setText("￥" + orderDetailsBean.getData().getTrade().getDiscount_fee());
        tv_payment.setText("￥" + orderDetailsBean.getData().getTrade().getPayment());
        tv_tid.setText("订单编号：" + orderDetailsBean.getData().getTrade().getTid());
        tv_created_time.setText("创建时间：" + orderDetailsBean.getData().getTrade().getCreated_time());

        if (orderDetailsBean.getData().getTrade().getStatus().equals("待付款")) {
            ll_pay_info.setVisibility(View.VISIBLE);
            tv_pay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String paymentUrl = MallApi.SERVER_PAYMENT_URL + "/create.html?tid=" + orderDetailsBean.getData().getTrade().getTid();
                    XUtils.Get(paymentUrl, null, new MyCallBack<String>() {
                        @Override
                        public void onSuccess(String result) {
                            super.onSuccess(result);
                            if (JsonValidator.validate(result)) {
                                try {
                                    JSONObject json = new JSONObject(result);
                                    if (json.getBoolean("success")) {
                                        String url = json.getString("redirect");
                                        Intent intent = new Intent(OrderDetailsActivity.this, PaymentActivity.class);
                                        intent.putExtra("url", url);
                                        Log.e("url", url);
                                        startActivity(intent);
                                        finish();
                                    } else {
                                        Toast.makeText(OrderDetailsActivity.this, json.getString("message"), Toast.LENGTH_SHORT).show();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            } else {
                            }
                        }

                        @Override
                        public void onError(Throwable ex, boolean isOnCallback) {
                            super.onError(ex, isOnCallback);
                        }
                    });
                }
            });
            tv_cancel_order.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(OrderDetailsActivity.this, CancelOrderActivity.class);
                    intent.putExtra("tid", orderDetailsBean.getData().getTrade().getTid());
                    intent.putExtra("total_fee", orderDetailsBean.getData().getTrade().getTotal_fee());
                    intent.putExtra("post_fee", orderDetailsBean.getData().getTrade().getPost_fee());
                    startActivity(intent);
                }
            });
        } else {
            ll_pay_info.setVisibility(View.GONE);
        }
    }
}
