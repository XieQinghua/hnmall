package com.your.mall.adapter;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.your.mall.R;
import com.your.mall.utils.ImgUtils;
import com.your.mall.view.CircleImageView;

import java.util.ArrayList;

/**
 * Created by xieqinghua.
 * 创建时间：2016/11/14
 * 类描述：拼团人适配器
 * 修改备注：
 */
public class PintuanPeopleAdapter extends BaseAdapter {
    private ArrayList<String> list;
    private Context context;

    public PintuanPeopleAdapter(Context context, ArrayList<String> list) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_pintuan_people, null);
            vh.iv_head_img = (CircleImageView) convertView.findViewById(R.id.iv_head_img);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();// 拿到缓存组件
        }
        ImgUtils.setCircleImage(vh.iv_head_img, list.get(position));

        return convertView;
    }

    class ViewHolder {
        CircleImageView iv_head_img;
    }
}
