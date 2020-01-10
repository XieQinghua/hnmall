package com.your.mall.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.your.mall.R;
import com.your.mall.bean.OrderDetailsBean;
import com.your.mall.utils.ImgUtils;

import java.util.ArrayList;

/**
 * Created by xieqinghua.
 * 创建时间：2016/10/12
 * 类描述：订单详情内单个商品详情
 * 修改备注：
 */
public class ChildOrderDetailsAdapter extends BaseAdapter {
    private ArrayList<OrderDetailsBean.DataBean.TradeBean.OrdersBean> list;
    private Context context;

    public ChildOrderDetailsAdapter(Context context, ArrayList<OrderDetailsBean.DataBean.TradeBean.OrdersBean> list) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_child_order_details, null);
            vh.tv_shop_name = (TextView) convertView.findViewById(R.id.tv_shop_name);
            vh.tv_status = (TextView) convertView.findViewById(R.id.tv_status);
            vh.iv_goods_img = (ImageView) convertView.findViewById(R.id.iv_goods_img);
            vh.tv_goods_name = (TextView) convertView.findViewById(R.id.tv_goods_name);
            vh.tv_goods_price = (TextView) convertView.findViewById(R.id.tv_goods_price);
            vh.tv_num = (TextView) convertView.findViewById(R.id.tv_num);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();// 拿到缓存组件
        }
        ImgUtils.setRectangleImage(vh.iv_goods_img, list.get(position).getPic_path().trim());
        vh.tv_shop_name.setText(list.get(position).getShop_name() + " ＞");
        vh.tv_status.setText(list.get(position).getStatus());
        vh.tv_goods_name.setText(list.get(position).getTitle());
        vh.tv_goods_price.setText("￥" + list.get(position).getPrice());
        vh.tv_num.setText("×" + list.get(position).getNum());
        return convertView;
    }

    class ViewHolder {
        ImageView iv_goods_img;
        TextView tv_shop_name, tv_status, tv_goods_name, tv_goods_price, tv_num;
    }
}
