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
import com.your.mall.bean.CouponBean;
import com.your.mall.utils.ImgUtils;
import com.your.mall.view.CircleImageView;

import java.util.ArrayList;

/**
 * Created by xieqinghua.
 * 创建时间：2016/10/12
 * 类描述：优惠券适配器
 * 修改备注：
 */
public class CouponAdapter extends BaseAdapter {
    private ArrayList<CouponBean.DataBean> list;
    private Context context;

    public CouponAdapter(Context context, ArrayList<CouponBean.DataBean> list) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_coupon_info, null);
            vh.tv_deduct_money = (TextView) convertView.findViewById(R.id.tv_deduct_money);
            vh.tv_coupon_platform = (TextView) convertView.findViewById(R.id.tv_coupon_platform);
            vh.tv_coupon_name = (TextView) convertView.findViewById(R.id.tv_coupon_name);
            vh.tv_user_time = (TextView) convertView.findViewById(R.id.tv_user_time);
            vh.iv_tag = (ImageView) convertView.findViewById(R.id.iv_tag);
            vh.iv_shop_logo = (CircleImageView) convertView.findViewById(R.id.iv_shop_logo);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();// 拿到缓存组件
        }

        vh.tv_deduct_money.setText("￥" + list.get(position).getDeduct_money());
        vh.tv_coupon_platform.setText(list.get(position).getCoupon_platform());
        vh.tv_coupon_name.setText(list.get(position).getCoupon_name());
        vh.tv_user_time.setText("使用期限：" + list.get(position).getCanuse_start_time() + " - " + list.get(position).getCanuse_end_time());
        if (list.get(position).getIs_valid().equals("2")) {//已过期
            vh.iv_tag.setImageDrawable(context.getDrawable(R.drawable.icon_expired_tag));
        } else if (list.get(position).getIs_valid().equals("1")) {//未使用
            vh.iv_tag.setVisibility(View.GONE);
        } else if (list.get(position).getIs_valid().equals("0")) {//已使用
            vh.iv_tag.setImageDrawable(context.getDrawable(R.drawable.icon_used_tag));
        }
        ImgUtils.setCircleImage(vh.iv_shop_logo, list.get(position).getShop_logo());
        return convertView;
    }

    class ViewHolder {
        TextView tv_deduct_money, tv_coupon_platform, tv_coupon_name, tv_user_time;
        ImageView iv_tag;
        CircleImageView iv_shop_logo;
    }
}
