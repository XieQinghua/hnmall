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
import com.your.mall.bean.PintuanBean;
import com.your.mall.utils.ImgUtils;

import java.util.ArrayList;

/**
 * Created by xieqinghua.
 * 创建时间：2016/11/14
 * 类描述：拼团适配器
 * 修改备注：
 */
public class PintuanAdapter extends BaseAdapter {
    private ArrayList<PintuanBean.DataBean.ItemsPintuanBean> list;
    private Context context;

    public PintuanAdapter(Context context, ArrayList<PintuanBean.DataBean.ItemsPintuanBean> list) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_pintuan, null);
            vh.iv_goods_banner = (ImageView) convertView.findViewById(R.id.iv_goods_banner);
            vh.tv_goods_title = (TextView) convertView.findViewById(R.id.tv_goods_title);
            vh.tv_goods_desc = (TextView) convertView.findViewById(R.id.tv_goods_desc);
            vh.tv_number = (TextView) convertView.findViewById(R.id.tv_number);
            vh.tv_activity_price = (TextView) convertView.findViewById(R.id.tv_activity_price);
            vh.tv_price = (TextView) convertView.findViewById(R.id.tv_price);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();// 拿到缓存组件
        }
        ImgUtils.setRectangleImage(vh.iv_goods_banner, list.get(position).getActivity_goods_banner());
        vh.tv_goods_title.setText(list.get(position).getActivity_goods_title());
        vh.tv_goods_desc.setText(list.get(position).getActivity_goods_desc());
        vh.tv_number.setText(list.get(position).getActivity_number() + "人团");
        vh.tv_activity_price.setText("￥" + list.get(position).getActivity_price());
        vh.tv_price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
        vh.tv_price.setText("￥" + list.get(position).getPrice());
        return convertView;
    }

    class ViewHolder {
        ImageView iv_goods_banner;
        TextView tv_goods_title, tv_goods_desc, tv_number, tv_activity_price, tv_price;
    }
}
