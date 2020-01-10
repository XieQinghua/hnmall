package com.your.mall.adapter;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.your.mall.R;
import com.your.mall.bean.ShareInfoBean;
import com.your.mall.utils.ImgUtils;
import com.your.mall.view.CircleImageView;

import java.util.ArrayList;

/**
 * Created by xieqinghua.
 * 创建时间：2016/11/14
 * 类描述：好友砍价记录适配器
 * 修改备注：
 */
public class BargainPeopleAdapter extends BaseAdapter {
    private ArrayList<ShareInfoBean.DataBean.PeopleBean> list;
    private Context context;

    public BargainPeopleAdapter(Context context, ArrayList<ShareInfoBean.DataBean.PeopleBean> list) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_bargain_people, null);
            vh.iv_head_img = (CircleImageView) convertView.findViewById(R.id.iv_head_img);
            vh.tv_nickname = (TextView) convertView.findViewById(R.id.tv_nickname);
            vh.tv_price = (TextView) convertView.findViewById(R.id.tv_price);
            vh.tv_content = (TextView) convertView.findViewById(R.id.tv_content);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();// 拿到缓存组件
        }
        ImgUtils.setCircleImage(vh.iv_head_img, list.get(position).getHeadimgurl());

        vh.tv_nickname.setText(list.get(position).getNickname());

        String text = "砍掉" + list.get(position).getPrice() + "元";
        SpannableStringBuilder style = new SpannableStringBuilder(text);
        style.setSpan(new ForegroundColorSpan(context.getResources().getColor(R.color.paleRed)),
                text.indexOf(list.get(position).getPrice()),
                text.indexOf(list.get(position).getPrice()) + list.get(position).getPrice().length(),
                Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        vh.tv_price.setText(style);
        vh.tv_content.setText(list.get(position).getContent());

        return convertView;
    }

    class ViewHolder {
        CircleImageView iv_head_img;
        TextView tv_nickname, tv_price, tv_content;
    }
}
