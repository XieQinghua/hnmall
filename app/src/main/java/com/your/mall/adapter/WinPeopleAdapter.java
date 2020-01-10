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
import com.your.mall.bean.PeopleListBean;
import com.your.mall.bean.ShareInfoBean;
import com.your.mall.utils.ImgUtils;
import com.your.mall.view.CircleImageView;

import java.util.ArrayList;

/**
 * Created by xieqinghua.
 * 创建时间：2016/11/23
 * 类描述：拼团抽奖中奖名单中奖人Adapter
 * 修改备注：
 */
public class WinPeopleAdapter extends BaseAdapter {
    private ArrayList<PeopleListBean> list;
    private Context context;

    public WinPeopleAdapter(Context context, ArrayList<PeopleListBean> list) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_pintuan_win_people, null);
            vh.iv_head_img = (CircleImageView) convertView.findViewById(R.id.iv_head_img);
            vh.tv_rank = (TextView) convertView.findViewById(R.id.tv_rank);
            vh.tv_nickname = (TextView) convertView.findViewById(R.id.tv_nickname);
            vh.tv_prize = (TextView) convertView.findViewById(R.id.tv_prize);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();// 拿到缓存组件
        }
        ImgUtils.setCircleImage(vh.iv_head_img, list.get(position).getHeadimgurl());
        vh.tv_rank.setText(list.get(position).getRank());
        if (list.get(position).getNickname().equals("")) {
            vh.tv_nickname.setText("匿名");
        } else {
            vh.tv_nickname.setText(list.get(position).getNickname());
        }
        vh.tv_prize.setText(list.get(position).getPackage_name());
        return convertView;
    }

    class ViewHolder {
        CircleImageView iv_head_img;
        TextView tv_rank, tv_nickname, tv_prize;
    }
}
