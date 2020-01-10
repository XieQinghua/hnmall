package com.your.mall.adapter;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Paint;
import android.os.Build;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.your.mall.R;
import com.your.mall.bean.MyBargainBean;
import com.your.mall.utils.ImgUtils;

import java.util.ArrayList;

/**
 * Created by xieqinghua.
 * 创建时间：2016/11/14
 * 类描述：我的砍价
 * 修改备注：
 */
public class MyBargainAdapter extends BaseAdapter {
    private ArrayList<MyBargainBean.DataBean> list;
    private Context context;

    public MyBargainAdapter(Context context, ArrayList<MyBargainBean.DataBean> list) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_my_bargain, null);
            vh.iv_goods_img = (ImageView) convertView.findViewById(R.id.iv_goods_img);
            vh.iv_bargain_status = (ImageView) convertView.findViewById(R.id.iv_bargain_status);
            vh.tv_goods_title = (TextView) convertView.findViewById(R.id.tv_goods_title);
            vh.tv_number = (TextView) convertView.findViewById(R.id.tv_number);
            vh.tv_price = (TextView) convertView.findViewById(R.id.tv_price);
            vh.tv_activity_price = (TextView) convertView.findViewById(R.id.tv_activity_price);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();// 拿到缓存组件
        }
        ImgUtils.setRectangleImage(vh.iv_goods_img, list.get(position).getImage_default_id());

        if (list.get(position).getStatus().equals("1")) {
            vh.iv_bargain_status.setImageResource(R.drawable.img_bargain_in);
        } else if (list.get(position).getStatus().equals("2")) {
            vh.iv_bargain_status.setImageResource(R.drawable.img_bargain_finish);
        } else if (list.get(position).getStatus().equals("3")) {
            vh.iv_bargain_status.setImageResource(R.drawable.img_bargain_lose);
        }

        vh.tv_goods_title.setText(list.get(position).getActivity_goods_title());

        String text1 = "已有" + list.get(position).getNumber() + "人参与砍价";
        SpannableStringBuilder style1 = new SpannableStringBuilder(text1);
        style1.setSpan(new ForegroundColorSpan(context.getResources().getColor(R.color.paleRed)),
                text1.indexOf(list.get(position).getNumber()),
                text1.indexOf(list.get(position).getNumber()) + list.get(position).getNumber().length(),
                Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        vh.tv_number.setText(style1);

        vh.tv_price.setText(list.get(position).getActivity_price());

        vh.tv_activity_price.setText("原价：￥" + list.get(position).getPrice());
        vh.tv_activity_price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);//中间加横线
        return convertView;
    }

    class ViewHolder {
        ImageView iv_goods_img, iv_bargain_status;
        TextView tv_goods_title, tv_number, tv_price, tv_activity_price;
    }
}
