package com.your.mall.adapter;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.your.mall.R;
import com.your.mall.application.MallApplication;
import com.your.mall.bean.ShoppingCartBean;
import com.your.mall.http.MallApi;
import com.your.mall.utils.AmountGoodEvent;
import com.your.mall.utils.ImgUtils;
import com.your.mall.utils.JsonValidator;
import com.your.mall.utils.MyCallBack;
import com.your.mall.utils.UpdateCartEvent;
import com.your.mall.utils.XUtils;
import com.your.mall.view.AmountView;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xieqinghua.
 * 创建时间：2016/9/22
 * 类描述：购物车商品适配器（购物车的二级适配器）
 * 修改备注：
 */
public class CartGoodsAdapter extends BaseAdapter {
    private ArrayList<ShoppingCartBean.DataBean.GoodListBean> list;
    private Context context;
    private Handler handler;

    public CartGoodsAdapter(Context context, ArrayList<ShoppingCartBean.DataBean.GoodListBean> list, Handler handler) {
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

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder vh;
        if (convertView == null) {
            vh = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_cart_goods_info, null);
            vh.cb_goods = (CheckBox) convertView.findViewById(R.id.cb_goods);
            vh.iv_goods_img = (ImageView) convertView.findViewById(R.id.iv_goods_img);
            vh.iv_mask = (ImageView) convertView.findViewById(R.id.iv_mask);//遮罩
            vh.tv_goods_name = (TextView) convertView.findViewById(R.id.tv_goods_name);
            vh.tv_goods_price = (TextView) convertView.findViewById(R.id.tv_goods_price);
            vh.tv_promotions_info = (TextView) convertView.findViewById(R.id.tv_promotions_info);
//            vh.iv_jian = (ImageView) convertView.findViewById(R.id.iv_jian);
//            vh.iv_jia = (ImageView) convertView.findViewById(R.id.iv_jia);
//            vh.tv_quantity = (TextView) convertView.findViewById(R.id.tv_quantity);
            vh.mAmountView = (AmountView) convertView.findViewById(R.id.amount_view);
            vh.iv_cart_remove = (ImageView) convertView.findViewById(R.id.iv_cart_remove);
            vh.ll_promotion_info = (LinearLayout) convertView.findViewById(R.id.ll_promotion_info);
            vh.tv_promotion_name = (TextView) convertView.findViewById(R.id.tv_promotion_name);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();// 拿到缓存组件
        }
        if (list.get(position).getIs_checked().equals("1")) {//选中
            vh.cb_goods.setChecked(true);
        } else {//未选中
            vh.cb_goods.setChecked(false);
        }
        final String id = list.get(position).getItem_id();
        vh.cb_goods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (vh.cb_goods.isChecked()) {
                    EventBus.getDefault().post(new UpdateCartEvent(id, true));
                } else {
                    EventBus.getDefault().post(new UpdateCartEvent(id, false));
                }
            }
        });
        if (list.get(position).getValid().equals("0")) {//已下架
            ImgUtils.setRectangleImage(vh.iv_goods_img, list.get(position).getImage_default_id());
            //下架字样遮罩
            vh.iv_mask.setVisibility(View.VISIBLE);
            vh.iv_mask.setImageDrawable(context.getResources().getDrawable(R.drawable.img_offtheshelf));
//            vh.iv_mask.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.img_offtheshelf));
        } else if (list.get(position).getStore().equals("0")) {//已售罄
            ImgUtils.setRectangleImage(vh.iv_goods_img, list.get(position).getImage_default_id());
            //售罄字样遮罩
            vh.iv_mask.setVisibility(View.VISIBLE);
            vh.iv_mask.setImageDrawable(context.getResources().getDrawable(R.drawable.img_sellout));
        } else {
            ImgUtils.setRectangleImage(vh.iv_goods_img, list.get(position).getImage_default_id());
            vh.iv_mask.setVisibility(View.GONE);
        }
        vh.tv_goods_name.setText(list.get(position).getTitle());
        vh.tv_goods_price.setText("￥" + list.get(position).getPrice());
        if (list.size() == 1) {//TODO 判断如果该店铺下购物车只有一项商品，则显示“单品免邮”信息
            vh.tv_promotions_info.setVisibility(View.VISIBLE);
        }
        vh.mAmountView.setAmount(list.get(position).getQuantity());
        vh.mAmountView.setGoods_storage(100);
        vh.mAmountView.setOnAmountChangeListener(new AmountView.OnAmountChangeListener() {
            @Override
            public void onAmountChange(View view, int amount) {
//                Toast.makeText(GoodsDetailsActivity.this, "Amount=>  " + amount, Toast.LENGTH_SHORT).show();
                String quantity = amount + "";
                EventBus.getDefault().post(new AmountGoodEvent(list.get(position).getItem_id(), amount + ""));
            }
        });

        vh.iv_cart_remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //移出购物车操作
                Map<String, String> map = new HashMap<>();
                map.put("user_id", MallApplication.getInstance().getUserId(context));
                map.put("cart_id", list.get(position).getCart_id() + "");
                XUtils.Post(MallApi.APP_CART_DEL, map, new MyCallBack<String>() {
                    @Override
                    public void onSuccess(String result) {
                        super.onSuccess(result);
                        Log.e("del", result);
                        if (JsonValidator.validate(result)) {
                            try {
                                JSONObject json = new JSONObject(result);
                                if (json.getString("code").equals("0")) {
                                    list.remove(position);
                                    CartGoodsAdapter.this.notifyDataSetChanged();
                                    JSONObject data = json.getJSONObject("data");
                                    MallApplication.getInstance().setCartNum(context, data.getInt("cart_num") + "");
                                    new Thread() {
                                        @Override
                                        public void run() {
                                            super.run();
                                            Message msg = Message.obtain();
                                            msg.what = 1;
                                            handler.sendMessage(msg);
                                        }
                                    }.start();
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
        if (list.get(position).getPromotions() != null && list.get(position).getPromotions().size() != 0) {
            vh.ll_promotion_info.setVisibility(View.VISIBLE);
            vh.tv_promotion_name.setText(list.get(position).getPromotions().get(0).getPromotion_name());
        } else {
            vh.ll_promotion_info.setVisibility(View.GONE);
        }
        return convertView;
    }

    class ViewHolder {
        CheckBox cb_goods;
        ImageView iv_goods_img, iv_mask;
        TextView tv_goods_name;
        TextView tv_goods_price, tv_promotions_info;
        //        ImageView iv_jian, iv_jia;
//        TextView tv_quantity;
        AmountView mAmountView;
        ImageView iv_cart_remove;
        LinearLayout ll_promotion_info;
        TextView tv_promotion_name;
    }

//    public void setOnCheckedChanged(onCheckedChanged listener) {
//        this.listener = listener;
//    }
}
