package com.your.mall.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.your.mall.R;
import com.your.mall.application.MallApplication;
import com.your.mall.bean.CollectGoodsBean;
import com.your.mall.http.MallApi;
import com.your.mall.utils.ImgUtils;
import com.your.mall.utils.JsonValidator;
import com.your.mall.utils.MyCallBack;
import com.your.mall.utils.XUtils;
import com.your.mall.view.CustomDialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xieqinghua.
 * 创建时间：2016/10/10
 * 类描述：收藏商品适配器
 * 修改备注：
 */
public class CollectGoodsAdapter extends BaseAdapter {
    private ArrayList<CollectGoodsBean.DataBean.ListBean> list;
    private Context context;

    public CollectGoodsAdapter(Context context, ArrayList<CollectGoodsBean.DataBean.ListBean> list) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_collection_goods, null);
            vh.iv_goods_img = (ImageView) convertView.findViewById(R.id.iv_goods_img);
            vh.tv_goods_name = (TextView) convertView.findViewById(R.id.tv_goods_name);
            vh.tv_goods_price = (TextView) convertView.findViewById(R.id.tv_goods_price);
            vh.iv_collection = (ImageView) convertView.findViewById(R.id.iv_collection);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        ImgUtils.setRectangleImage(vh.iv_goods_img, list.get(position).getGoods_thumb());
        vh.tv_goods_name.setText(list.get(position).getGoods_name());
        vh.tv_goods_price.setText("￥" + list.get(position).getShop_price());
        vh.iv_collection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomDialog.Builder builder = new CustomDialog.Builder(context);
                builder.setMessage("是否取消收藏该商品？");
                builder.setTitle("温馨提示");
                builder.setPositiveButton("确定", new android.content.DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //取消收藏
                        Map<String, String> map = new HashMap<>();
                        map.put("user_id", MallApplication.getInstance().getUserId(context));
                        map.put("goods_id", list.get(position).getGoods_id() + "");
                        XUtils.Post(MallApi.APP_COLLECT_DEL, map, new MyCallBack<String>() {
                            @Override
                            public void onSuccess(String result) {
                                super.onSuccess(result);
                                if (JsonValidator.validate(result)) {
                                    try {
                                        JSONObject json = new JSONObject(result);
                                        if (json.getString("code").equals("0")) {
                                            list.remove(position);
                                            CollectGoodsAdapter.this.notifyDataSetChanged();
                                            Toast.makeText(context, "取消收藏成功", Toast.LENGTH_SHORT).show();
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
                        dialog.dismiss();
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.create().show();

            }
        });

        return convertView;
    }

    class ViewHolder {
        ImageView iv_goods_img;
        TextView tv_goods_name, tv_goods_price;
        ImageView iv_collection;
    }
}
