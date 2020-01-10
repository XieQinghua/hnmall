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
import com.your.mall.bean.MyPintuanBean;
import com.your.mall.utils.ImgUtils;

import java.util.ArrayList;

/**
 * Created by xieqinghua.
 * 创建时间：2016/11/14
 * 类描述：我的拼团
 * 修改备注：
 */
public class MyPintuanAdapter extends BaseAdapter {
    private ArrayList<MyPintuanBean.DataBean.TradesBean> list;
    private Context context;

    public MyPintuanAdapter(Context context, ArrayList<MyPintuanBean.DataBean.TradesBean> list) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_my_pintuan, null);
            vh.iv_goods_img = (ImageView) convertView.findViewById(R.id.iv_goods_img);
            vh.iv_mask = (ImageView) convertView.findViewById(R.id.iv_mask);
            vh.tv_shop_name = (TextView) convertView.findViewById(R.id.tv_shop_name);
            vh.tv_goods_title = (TextView) convertView.findViewById(R.id.tv_goods_title);
            vh.tv_activity_number = (TextView) convertView.findViewById(R.id.tv_activity_number);
            vh.tv_activity_price = (TextView) convertView.findViewById(R.id.tv_activity_price);
            vh.tv_invite = (TextView) convertView.findViewById(R.id.tv_invite);
            vh.tv_choujiang = (TextView) convertView.findViewById(R.id.tv_choujiang);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();// 拿到缓存组件
        }
        ImgUtils.setRectangleImage(vh.iv_goods_img, list.get(position).getPic_path());

        vh.tv_shop_name.setText(list.get(position).getShop_name() + " ＞");
        vh.tv_goods_title.setText(list.get(position).getTitle());
        vh.tv_activity_number.setText(list.get(position).getActivity_number() + "人团");
        vh.tv_activity_price.setText("￥" + list.get(position).getPrice());

        if (list.get(position).getIs_success().equals("1")) {//1是进行中 2是成功 3是失败
            vh.iv_mask.setVisibility(View.GONE);
            vh.tv_invite.setVisibility(View.VISIBLE);
        } else if (list.get(position).getIs_success().equals("2")) {
            vh.iv_mask.setVisibility(View.VISIBLE);
            vh.tv_invite.setVisibility(View.GONE);
            vh.iv_mask.setImageResource(R.drawable.img_pintuan_success);
        } else if (list.get(position).getIs_success().equals("3")) {
            vh.iv_mask.setVisibility(View.VISIBLE);
            vh.tv_invite.setVisibility(View.GONE);
            vh.iv_mask.setImageResource(R.drawable.img_pintuan_failed);
        }

        if (list.get(position).getActivity_lx().equals("6")) {//活动类型 6是拼团抽奖 1是拼团
            vh.tv_choujiang.setVisibility(View.VISIBLE);
        } else if (list.get(position).getActivity_lx().equals("1")) {
            vh.tv_choujiang.setVisibility(View.GONE);
        }

        vh.tv_activity_price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);//中间加横线
        return convertView;
    }

    class ViewHolder {
        ImageView iv_goods_img, iv_mask;
        TextView tv_shop_name, tv_goods_title, tv_activity_number, tv_activity_price, tv_invite, tv_choujiang;
    }
}
