package com.your.mall.adapter;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.your.mall.R;
import com.your.mall.activity.BargainDetailsActivity;
import com.your.mall.bean.BargainBean;
import com.your.mall.utils.ImgUtils;

import java.util.ArrayList;

/**
 * Created by xieqinghua.
 * 创建时间：2016/11/14
 * 类描述：砍价商品适配器
 * 修改备注：
 */
public class BargainAdapter extends BaseAdapter {
    private ArrayList<BargainBean.DataBean.ListBean> list;
    private Context context;

    public BargainAdapter(Context context, ArrayList<BargainBean.DataBean.ListBean> list) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_bargain_goods, null);
            vh.iv_goods_img = (ImageView) convertView.findViewById(R.id.iv_goods_img);
            vh.iv_bargain_immediately = (ImageView) convertView.findViewById(R.id.iv_bargain_immediately);
            vh.ll_mask = (LinearLayout) convertView.findViewById(R.id.ll_mask);
            vh.tv_goods_title = (TextView) convertView.findViewById(R.id.tv_goods_title);
            vh.tv_stockprice = (TextView) convertView.findViewById(R.id.tv_stockprice);
            vh.tv_count = (TextView) convertView.findViewById(R.id.tv_count);
            vh.tv_price = (TextView) convertView.findViewById(R.id.tv_price);
            vh.tv_activity_price = (TextView) convertView.findViewById(R.id.tv_activity_price);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();// 拿到缓存组件
        }
        ImgUtils.setRectangleImage(vh.iv_goods_img, list.get(position).getItem_default_image());

        vh.tv_goods_title.setText(list.get(position).getActivity_goods_title());

        String text1 = "剩余" + list.get(position).getActivity_stockprice() + "份";
        SpannableStringBuilder style1 = new SpannableStringBuilder(text1);
        style1.setSpan(new ForegroundColorSpan(context.getResources().getColor(R.color.paleRed)),
                text1.indexOf(list.get(position).getActivity_stockprice()),
                text1.indexOf(list.get(position).getActivity_stockprice()) + list.get(position).getActivity_stockprice().length(),
                Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        vh.tv_stockprice.setText(style1);

        String text2 = "已有" + list.get(position).getCount() + "人参与砍价";
        SpannableStringBuilder style2 = new SpannableStringBuilder(text2);
        style2.setSpan(new ForegroundColorSpan(context.getResources().getColor(R.color.paleRed)),
                text2.indexOf(list.get(position).getCount()),
                text2.indexOf(list.get(position).getCount()) + list.get(position).getCount().length(),
                Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        vh.tv_count.setText(style2);

        vh.tv_price.setText("市场价：￥" + list.get(position).getPrice() + "/份");
        vh.tv_price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);//中间加横线
        vh.tv_price.setTextColor(context.getResources().getColor(R.color.mall_content_color));
        vh.tv_activity_price.setText("￥" + list.get(position).getActivity_price());
        if (!list.get(position).getActivity_stockprice().equals("0") && list.get(position).getStatus().equals("1")) {
            vh.iv_bargain_immediately.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, BargainDetailsActivity.class);
                    intent.putExtra("activity_id", list.get(position).getActivity_id());
                    intent.putExtra("item_id", list.get(position).getItem_id());
                    intent.putExtra("status","1");
                    context.startActivity(intent);
                }
            });
        }
        if (list.get(position).getStatus().equals("1")) {
            vh.ll_mask.setVisibility(View.GONE);
        } else if (list.get(position).getStatus().equals("2")) {
            vh.ll_mask.setVisibility(View.GONE);
        } else if (list.get(position).getStatus().equals("3")) {
            vh.ll_mask.setVisibility(View.VISIBLE);
        }

        return convertView;
    }

    class ViewHolder {
        ImageView iv_goods_img, iv_bargain_immediately;
        LinearLayout ll_mask;
        TextView tv_goods_title, tv_stockprice, tv_count, tv_price, tv_activity_price;
    }
}
