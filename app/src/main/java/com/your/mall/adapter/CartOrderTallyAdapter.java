package com.your.mall.adapter;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.your.mall.R;
import com.your.mall.activity.GoodsDetailsActivity;

import com.your.mall.bean.OrderTallyBean;
import com.your.mall.bean.TotalBean;

import java.util.ArrayList;

/**
 * Created by xieqinghua.
 * 创建时间：2016/11/18
 * 类描述：购物车订单结算适配器
 * 修改备注：
 */
public class CartOrderTallyAdapter extends BaseAdapter {
    private ArrayList<OrderTallyBean.DataBean.CartInfoBean> list;
    private ArrayList<TotalBean.DataBean.ShopBean> cost_list;
    private ArrayList<OrderTallyBean.DataBean.CartInfoBean.GoodListBean> goodList;
    private CartOrderTallyGoodsAdapter myCartOrderTallyGoodsAdapter;
    private Context context;

    public CartOrderTallyAdapter(Context context, ArrayList<OrderTallyBean.DataBean.CartInfoBean> list,
                                 ArrayList<TotalBean.DataBean.ShopBean> cost_list) {
        this.list = list;
        this.cost_list = cost_list;
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

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder vh;
        if (convertView == null) {
            vh = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_cart_order_tally, null);
            vh.tv_shop_name = (TextView) convertView.findViewById(R.id.tv_shop_name);
            vh.tv_promotion_info = (TextView) convertView.findViewById(R.id.tv_promotion_info);
            vh.tv_cost_info = (TextView) convertView.findViewById(R.id.tv_cost_info);
            vh.tv_total_cost = (TextView) convertView.findViewById(R.id.tv_total_cost);
            vh.et_message = (EditText) convertView.findViewById(R.id.et_message);
            vh.lv_goods_info = (ListView) convertView.findViewById(R.id.lv_goods_info);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();// 拿到缓存组件
        }
        vh.tv_shop_name.setText(list.get(position).getShop_name());
        if (!list.get(position).getPromotion_info().equals("")) {
            vh.tv_promotion_info.setVisibility(View.VISIBLE);
            vh.tv_promotion_info.setText("（" + list.get(position).getPromotion_info() + "）");
        } else {
            vh.tv_promotion_info.setVisibility(View.INVISIBLE);
        }
        vh.tv_cost_info.setText("共" + cost_list.get(position).getItemnum()
                + "件商品  运费：￥" +
                cost_list.get(position).getPost_fee() +
                "  减免：-￥" +
                cost_list.get(position).getDiscount_fee());
        vh.tv_total_cost.setText("￥" + cost_list.get(position).getPayment());
        vh.et_message.getText().toString().trim();

        goodList = (ArrayList<OrderTallyBean.DataBean.CartInfoBean.GoodListBean>) list.get(position).getGood_list();
        myCartOrderTallyGoodsAdapter = new CartOrderTallyGoodsAdapter(context, goodList);
        vh.lv_goods_info.setAdapter(myCartOrderTallyGoodsAdapter);
        vh.lv_goods_info.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                Intent intent = new Intent(context, GoodsDetailsActivity.class);
                intent.putExtra("item_id", list.get(position).getGood_list().get(i).getItem_id());
                context.startActivity(intent);
            }
        });
        return convertView;
    }

    class ViewHolder {
        TextView tv_shop_name, tv_promotion_info, tv_cost_info, tv_total_cost;
        EditText et_message;
        ListView lv_goods_info;
    }
}
