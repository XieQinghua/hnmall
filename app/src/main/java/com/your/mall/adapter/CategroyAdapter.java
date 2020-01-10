package com.your.mall.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.your.mall.R;
import com.your.mall.bean.Categroy;
import com.your.mall.utils.ImgUtils;
import com.your.mall.view.CircleImageView;

import java.util.ArrayList;

/**
 * Created by xieqinghua.
 * 创建时间：2016/9/22
 * 类描述：商品分类GridView的Adapter
 * 修改备注：
 */
public class CategroyAdapter extends BaseAdapter {
    private ArrayList<Categroy> list;
    private Context context;

    public CategroyAdapter(Context context, ArrayList<Categroy> list) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_categroy, null);
            vh.iv_categroy = (CircleImageView) convertView.findViewById(R.id.iv_categroy);
            vh.tv_categroy_name = (TextView) convertView.findViewById(R.id.tv_categroy_name);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();// 拿到缓存组件
        }
        ImgUtils.setCircleImage(vh.iv_categroy, list.get(position).getImageUrl());
        vh.tv_categroy_name.setText(list.get(position).getName());
        return convertView;
    }

    class ViewHolder {
        CircleImageView iv_categroy;
        TextView tv_categroy_name;
    }
}
