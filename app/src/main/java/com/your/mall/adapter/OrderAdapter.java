package com.your.mall.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.your.mall.R;
import com.your.mall.activity.CancelOrderActivity;
import com.your.mall.activity.LogiInfoActivity;
import com.your.mall.activity.MyRateActivity;
import com.your.mall.activity.OrderDetailsActivity;
import com.your.mall.activity.PaymentActivity;
import com.your.mall.application.MallApplication;
import com.your.mall.bean.OrderListBean;
import com.your.mall.http.MallApi;
import com.your.mall.utils.JsonValidator;
import com.your.mall.utils.MyCallBack;
import com.your.mall.utils.XUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xieqinghua.
 * 创建时间：2016/10/12
 * 类描述：订单适配器
 * 修改备注：
 */
public class OrderAdapter extends BaseAdapter {
    private ArrayList<OrderListBean.DataBean> list;
    private ArrayList<OrderListBean.DataBean.OrderBean> orderList;
    private Context context;

    public OrderAdapter(Context context, ArrayList<OrderListBean.DataBean> list) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder vh;
        if (convertView == null) {
            vh = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_order_info, null);
            vh.tv_shop_name = (TextView) convertView.findViewById(R.id.tv_shop_name);
            vh.tv_status = (TextView) convertView.findViewById(R.id.tv_status);
            vh.tv_cost_info = (TextView) convertView.findViewById(R.id.tv_cost_info);
            vh.lv_child_order = (ListView) convertView.findViewById(R.id.lv_child_order);
            vh.line = (View) convertView.findViewById(R.id.line);
            vh.ll_info = (LinearLayout) convertView.findViewById(R.id.ll_info);
            vh.tv_botton1 = (TextView) convertView.findViewById(R.id.tv_botton1);
            vh.tv_botton2 = (TextView) convertView.findViewById(R.id.tv_botton2);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();// 拿到缓存组件
        }

        vh.tv_shop_name.setText(list.get(position).getShop_name() + " ＞");
        vh.tv_status.setText(list.get(position).getStatus());
        vh.tv_cost_info.setText("共" + list.get(position).getItemnum() + "件商品  合计：￥" +
                list.get(position).getTotal_fee() + "（含运费￥" + list.get(position).getPost_fee() + "）");
        if (list.get(position).getStatus().equals("已关闭") || list.get(position).getStatus().equals("待发货")) {
            vh.line.setVisibility(View.GONE);
            vh.ll_info.setVisibility(View.GONE);
        } else if (list.get(position).getStatus().equals("待付款")) {
            vh.line.setVisibility(View.VISIBLE);
            vh.ll_info.setVisibility(View.VISIBLE);
            vh.tv_botton1.setText("取消订单");
            vh.tv_botton2.setText("付款");
            vh.tv_botton1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //跳转取消订单页面
                    Intent intent = new Intent(context, CancelOrderActivity.class);
                    intent.putExtra("tid", list.get(position).getTid());
                    intent.putExtra("total_fee", list.get(position).getTotal_fee());
                    intent.putExtra("post_fee", list.get(position).getPost_fee());
                    context.startActivity(intent);
                }
            });
            vh.tv_botton2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String paymentUrl = MallApi.SERVER_PAYMENT_URL + "/create.html?tid=" + list.get(position).getTid();
                    XUtils.Get(paymentUrl, null, new MyCallBack<String>() {
                        @Override
                        public void onSuccess(String result) {
                            super.onSuccess(result);
                            if (JsonValidator.validate(result)) {
                                try {
                                    JSONObject json = new JSONObject(result);
                                    if (json.getBoolean("success")) {
                                        String url = json.getString("redirect");
                                        Intent intent = new Intent(context, PaymentActivity.class);
                                        intent.putExtra("url", url);
                                        Log.e("url", url);
                                        context.startActivity(intent);
                                        ((Activity) context).finish();
                                    } else {
                                        Toast.makeText(context, json.getString("message"), Toast.LENGTH_SHORT).show();
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
        } else if (list.get(position).getStatus().equals("已发货")) {
            vh.line.setVisibility(View.VISIBLE);
            vh.ll_info.setVisibility(View.VISIBLE);
            vh.tv_botton1.setText("查看物流");
            vh.tv_botton2.setText("确认收货");
            vh.tv_botton1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, LogiInfoActivity.class);
                    intent.putExtra("logi_no", list.get(position).getLogi().getLogi_no());
                    intent.putExtra("corp_code", list.get(position).getLogi().getCorp_code());
                    context.startActivity(intent);
                }
            });
            vh.tv_botton2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Map<String, String> map = new HashMap<>();
                    map.put("user_id", MallApplication.getInstance().getUserId(context));
                    map.put("tid", list.get(position).getTid());
                    XUtils.Post(MallApi.APP_TRADE_CONFIRM, map, new MyCallBack<String>() {
                        @Override
                        public void onSuccess(String result) {
                            super.onSuccess(result);
                            Log.e("result", result);
                            if (JsonValidator.validate(result)) {
                                try {
                                    JSONObject json = new JSONObject(result);
                                    if (json.getString("code").equals("0")) {
                                        Toast.makeText(context, json.getString("msg"), Toast.LENGTH_SHORT).show();
                                        list.remove(position);
                                        notifyDataSetChanged();
                                    } else {
                                        Toast.makeText(context, json.getString("msg"), Toast.LENGTH_SHORT).show();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }


                            } else {
                                Toast.makeText(context, R.string.unable_to_get_data, Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onError(Throwable ex, boolean isOnCallback) {
                            super.onError(ex, isOnCallback);
                        }
                    });
                }
            });
        } else if (list.get(position).getStatus().equals("已完成")) {
            vh.line.setVisibility(View.VISIBLE);
            vh.ll_info.setVisibility(View.VISIBLE);
            vh.tv_botton1.setVisibility(View.GONE);
            vh.tv_botton2.setText("去评价");
            vh.tv_botton2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, MyRateActivity.class);
                    intent.putExtra("tid", list.get(position).getTid());
                    intent.putExtra("oid", list.get(position).getOrder().get(0).getOid());
                    intent.putExtra("pic_path", list.get(position).getOrder().get(0).getPic_path());
                    intent.putExtra("title", list.get(position).getOrder().get(0).getTitle());
                    intent.putExtra("price", list.get(position).getOrder().get(0).getPrice());
                    intent.putExtra("num", list.get(position).getOrder().get(0).getNum());
                    context.startActivity(intent);
                }
            });
        }
        orderList = (ArrayList<OrderListBean.DataBean.OrderBean>) list.get(position).getOrder();
        ChildOrderAdapter myChildOrderAdapter = new ChildOrderAdapter(context, orderList);
        vh.lv_child_order.setAdapter(myChildOrderAdapter);
        vh.lv_child_order.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                Intent intent = new Intent(context, OrderDetailsActivity.class);
                intent.putExtra("tid", list.get(position).getOrder().get(i).getTid());
                context.startActivity(intent);
            }
        });
        return convertView;
    }

    class ViewHolder {
        TextView tv_shop_name, tv_status;
        TextView tv_cost_info;
        View line;//分隔线
        LinearLayout ll_info;
        TextView tv_botton1, tv_botton2;
        ListView lv_child_order;
    }
}
