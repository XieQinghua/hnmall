package com.your.mall.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.your.mall.R;
import com.your.mall.activity.GoodsDetailsActivity;
import com.your.mall.application.MallApplication;
import com.your.mall.bean.ZujiBean;
import com.your.mall.http.MallApi;
import com.your.mall.utils.ImgUtils;
import com.your.mall.utils.JsonValidator;
import com.your.mall.utils.MyCallBack;
import com.your.mall.utils.XUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xieqinghua.
 * 创建时间：2016/9/22
 * 类描述：商品搜索列表适配器
 * 修改备注：
 */
public class ZujiAdapter extends BaseAdapter {
    private ArrayList<ZujiBean.DataBean.ItemsBean> list;
    private Context context;

    public ZujiAdapter(Context context, ArrayList<ZujiBean.DataBean.ItemsBean> list) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_my_zuji, null);
            vh.iv_goods_img = (ImageView) convertView.findViewById(R.id.iv_goods_img);
            vh.tv_title = (TextView) convertView.findViewById(R.id.tv_title);
            vh.tv_goods_price = (TextView) convertView.findViewById(R.id.tv_goods_price);
            vh.tv_ctime = (TextView) convertView.findViewById(R.id.tv_ctime);
            vh.tv_del = (TextView) convertView.findViewById(R.id.tv_del);
            vh.tv_buy = (TextView) convertView.findViewById(R.id.tv_buy);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();// 拿到缓存组件
        }
        ImgUtils.setRectangleImage(vh.iv_goods_img, list.get(position).getImage_default_id());
        vh.tv_title.setText(list.get(position).getTitle());
        vh.tv_goods_price.setText("￥" + list.get(position).getPrice());
        vh.tv_ctime.setText(list.get(position).getCtime() + "来过");
        vh.tv_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //用户中心删除我的足迹
                Map<String, String> map = new HashMap<>();
                map.put("user_id", MallApplication.getInstance().getUserId(context));
                map.put("item_id", list.get(position).getItem_id() + "");
                XUtils.Post(MallApi.APP_MEMBER_ZUJI_DEL, map, new MyCallBack<String>() {
                    @Override
                    public void onSuccess(String result) {
                        super.onSuccess(result);
                        if (JsonValidator.validate(result)) {
                            try {
                                JSONObject json = new JSONObject(result);
                                if (json.getString("code").equals("0")) {
                                    list.remove(position);
                                    ZujiAdapter.this.notifyDataSetChanged();
                                    Toast.makeText(context, json.getString("msg"), Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(context, json.getString("msg"), Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        } else {
                            Toast.makeText(context, R.string.unable_to_get_data, Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {
                        super.onError(ex, isOnCallback);
                    }
                });
            }
        });
        vh.tv_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, GoodsDetailsActivity.class);
                intent.putExtra("item_id", list.get(position).getItem_id());
                context.startActivity(intent);
            }
        });
        return convertView;
    }

    class ViewHolder {
        ImageView iv_goods_img;
        TextView tv_title, tv_goods_price, tv_ctime, tv_del, tv_buy;
    }
}
