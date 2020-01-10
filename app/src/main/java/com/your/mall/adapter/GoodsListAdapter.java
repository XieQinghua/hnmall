package com.your.mall.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.your.mall.R;
import com.your.mall.bean.GoodsListBean;
import com.your.mall.utils.ImgUtils;

import java.util.ArrayList;

/**
 * Created by xieqinghua.
 * 创建时间：2016/9/22
 * 类描述：商品搜索列表适配器
 * 修改备注：
 */
public class GoodsListAdapter extends BaseAdapter {
    private ArrayList<GoodsListBean.DataBean.ListBean> list;
    private Context context;

    public GoodsListAdapter(Context context, ArrayList<GoodsListBean.DataBean.ListBean> list) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_goods_info, null);
            vh.iv_goods_img = (ImageView) convertView.findViewById(R.id.iv_goods_img);
            vh.tv_goods_name = (TextView) convertView.findViewById(R.id.tv_goods_name);
            vh.tv_goods_price = (TextView) convertView.findViewById(R.id.tv_goods_price);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();// 拿到缓存组件
        }
        ImgUtils.setRectangleImage(vh.iv_goods_img, list.get(position).getGoods_img());
        vh.tv_goods_name.setText(list.get(position).getGoods_name());
        vh.tv_goods_price.setText(list.get(position).getShop_price());

        return convertView;
    }

    class ViewHolder {
        ImageView iv_goods_img;
        TextView tv_goods_name, tv_goods_price;
    }
}
