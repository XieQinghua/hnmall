package com.your.mall.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.your.mall.R;
import com.your.mall.utils.onekeyshare.ShareDialog;

import java.util.ArrayList;

/**
 * Created by xieqinghua.
 * 创建时间：2016/9/22
 * 类描述：分享表格
 * 修改备注：
 */
public class ShareGridViewAdapter extends BaseAdapter {
    private ArrayList<ShareDialog.ShareCell> list = new ArrayList<>();
    private Context context;

    public ShareGridViewAdapter(Context context, ArrayList<ShareDialog.ShareCell> list) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_share_gridview, null);
            vh.img = (ImageView) convertView.findViewById(R.id.img);
            vh.name = (TextView) convertView.findViewById(R.id.name);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();// 拿到缓存组件
        }
        vh.img.setImageResource(list.get(position).getImageId());
        vh.name.setText(list.get(position).getName());
        return convertView;
    }

    class ViewHolder {
        ImageView img;
        TextView name;
    }
}
