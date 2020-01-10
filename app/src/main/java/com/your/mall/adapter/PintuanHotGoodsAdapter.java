package com.your.mall.adapter;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.your.mall.R;
import com.your.mall.bean.CenterPintuanCjDetailsBean;
import com.your.mall.utils.ImgUtils;
import com.your.mall.view.CircleImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xieqinghua.
 * 创建时间：2016/11/14
 * 类描述：拼团热门商品
 * 修改备注：
 */
public class PintuanHotGoodsAdapter extends BaseAdapter {
    private ArrayList<CenterPintuanCjDetailsBean.DataBean.HotListBean> list;
    private Context context;

    public PintuanHotGoodsAdapter(Context context, ArrayList<CenterPintuanCjDetailsBean.DataBean.HotListBean> list) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_pintuan_hot_goods, null);
            vh.iv_goods_img = (ImageView) convertView.findViewById(R.id.iv_goods_img);
            vh.tv_goods_name = (TextView) convertView.findViewById(R.id.tv_goods_name);
            vh.tv_price = (TextView) convertView.findViewById(R.id.tv_price);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();// 拿到缓存组件
        }
        ImgUtils.setRectangleImage(vh.iv_goods_img, list.get(position).getImage_default_id());
        vh.tv_goods_name.setText(list.get(position).getTitle());
        vh.tv_price.setText("￥" + list.get(position).getPrice());
        return convertView;
    }

    class ViewHolder {
        ImageView iv_goods_img;
        TextView tv_goods_name, tv_price;
    }
}
