package com.your.mall.adapter;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Paint;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.your.mall.R;
import com.your.mall.bean.OrderTallyBean;
import com.your.mall.utils.ImgUtils;

import java.util.ArrayList;

/**
 * Created by xieqinghua.
 * 创建时间：2016/11/18
 * 类描述：购物车订单结算的商品适配器
 * 修改备注：
 */
public class CartOrderTallyGoodsAdapter extends BaseAdapter {
    private ArrayList<OrderTallyBean.DataBean.CartInfoBean.GoodListBean> list;
    private Context context;

    public CartOrderTallyGoodsAdapter(Context context, ArrayList<OrderTallyBean.DataBean.CartInfoBean.GoodListBean> list) {
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

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder vh;
        if (convertView == null) {
            vh = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_cart_order_tally_goods, null);
            vh.iv_goods_img = (ImageView) convertView.findViewById(R.id.iv_goods_img);
            vh.tv_goods_name = (TextView) convertView.findViewById(R.id.tv_goods_name);
            vh.tv_goods_price = (TextView) convertView.findViewById(R.id.tv_goods_price);
            vh.tv_old_price = (TextView) convertView.findViewById(R.id.tv_old_price);
            vh.tv_num = (TextView) convertView.findViewById(R.id.tv_num);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();// 拿到缓存组件
        }

        ImgUtils.setRectangleImage(vh.iv_goods_img, list.get(position).getImage_default_id());
        vh.tv_goods_name.setText(list.get(position).getTitle());
        vh.tv_goods_price.setText("￥" + list.get(position).getTotal_price());
        vh.tv_old_price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);//中间加横线
        vh.tv_old_price.setText("￥" + list.get(position).getPrice());
        vh.tv_num.setText("×" + list.get(position).getQuantity());
        return convertView;
    }

    class ViewHolder {
        ImageView iv_goods_img;
        TextView tv_goods_name, tv_goods_price, tv_old_price, tv_num;
    }
}
