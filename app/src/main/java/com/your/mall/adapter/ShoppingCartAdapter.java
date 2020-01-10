package com.your.mall.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import com.your.mall.R;
import com.your.mall.activity.GoodsDetailsActivity;
import com.your.mall.bean.ShoppingCartBean;
import com.your.mall.utils.UpdateShopEvent;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

/**
 * Created by xieqinghua.
 * 创建时间：2016/9/22
 * 类描述：购物车适配器，listview嵌套
 * 修改备注：
 */
public class ShoppingCartAdapter extends BaseAdapter {
    private ArrayList<ShoppingCartBean.DataBean> list;
    private ArrayList<ShoppingCartBean.DataBean.GoodListBean> goodList;
    private Context context;
    private Handler handler;
    private CartGoodsAdapter myCartGoodsAdapter;

    public ShoppingCartAdapter(Context context, ArrayList<ShoppingCartBean.DataBean> list, Handler handler) {
        this.list = list;
        this.context = context;
        this.handler = handler;
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_cart_info, null);
            vh.cb_shop_name = (CheckBox) convertView.findViewById(R.id.cb_shop_name);
            vh.tv_promotion_info = (TextView) convertView.findViewById(R.id.tv_promotion_info);
            vh.lv_goods_info = (ListView) convertView.findViewById(R.id.lv_goods_info);

            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();// 拿到缓存组件
        }
        if (list.get(position).getIs_checked().equals("1")) {//选中
            vh.cb_shop_name.setChecked(true);
            vh.cb_shop_name.setText(list.get(position).getShop_name());
        } else {//未选中
            vh.cb_shop_name.setChecked(false);
            vh.cb_shop_name.setText(list.get(position).getShop_name());
        }


        vh.cb_shop_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (vh.cb_shop_name.isChecked()){
                    EventBus.getDefault().post(new UpdateShopEvent(list.get(position).getShop_id(),true));
                }
                else {
                    EventBus.getDefault().post(new UpdateShopEvent(list.get(position).getShop_id(),false));
                }
            }
        });


        if (!list.get(position).getPromotion_info().equals("")) {
            vh.tv_promotion_info.setVisibility(View.VISIBLE);
            vh.tv_promotion_info.setText("（" + list.get(position).getPromotion_info() + "）");
        }
        goodList = (ArrayList<ShoppingCartBean.DataBean.GoodListBean>) list.get(position).getGood_list();
        myCartGoodsAdapter = new CartGoodsAdapter(context, goodList, handler);
        vh.lv_goods_info.setAdapter(myCartGoodsAdapter);
        vh.lv_goods_info.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                Intent intent = new Intent(context, GoodsDetailsActivity.class);
                intent.putExtra("item_id", list.get(position).getGood_list().get(i).getItem_id());
                context.startActivity(intent);
            }
        });
        return convertView;
    }

    class ViewHolder {
        CheckBox cb_shop_name;
        TextView tv_promotion_info;
        ListView lv_goods_info;
    }

    //public void setOnCheckedChanged(onCheckedChanged listener) {
    //    this.listener = listener;
    //}

    /**
     * 设置选中非选中更新状态
     * @param checked
     */
    public void setItemSelect(String checked){
        for (ShoppingCartBean.DataBean dataBean:list){
            dataBean.setIs_checked(checked);
            for (ShoppingCartBean.DataBean.GoodListBean goodListBean:dataBean.getGood_list()){
                goodListBean.setIs_checked(checked);
            }
        }

        notifyDataSetChanged();
    }
}
