package com.your.mall.activity;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.your.mall.R;
import com.your.mall.application.MallApplication;
import com.your.mall.base.BaseSwipeBackActivity;
import com.your.mall.bean.AddCartBean;
import com.your.mall.bean.GoodsDetailsBean;
import com.your.mall.bean.GoodsDetailsBean1;
import com.your.mall.bean.SpecBean;
import com.your.mall.http.MallApi;
import com.your.mall.utils.ImgUtils;
import com.your.mall.utils.JsonValidator;
import com.your.mall.utils.MyCallBack;
import com.your.mall.utils.ParseUtils;
import com.your.mall.utils.XUtils;
import com.your.mall.view.AmountView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xieqinghua.
 * 创建时间：2016/9/29
 * 类描述：商品详情
 * 修改备注：
 */
public class GoodsDetailsActivity extends BaseSwipeBackActivity implements View.OnClickListener {
    private String item_id;

    private ViewPager vp_list_image;
    private ArrayList<String> picList;
    private ArrayList<ImageView> imageViewList;
    private LinearLayout ll_point;//切换点布局
    private List<View> points; // 图片标题正文的那些点

    private TextView tv_title, tv_price;

    private AmountView mAmountView;
    private String quantity = "1";

    private TextView tv_promotion_tag, tv_promotion_name;
    private LinearLayout ll_promotion_info;
    private View line_promotion;

    private RadioButton rb_goods, rb_coupon;
    private LinearLayout ll_coupon_info;
    private View line_coupon;

    private TextView tv_spec_name;
    private RadioButton rb_spec1, rb_spec2, rb_spec3;
    private LinearLayout ll_spec_info;
    private View line_spec;

    private TextView tv_after_sale;
    private TextView tv_percentage, tv_total_rate;
    private ImageView iv_shop_logo;
    private TextView tv_shop_name, tv_shop_descript;
    private TextView tv_collect_shop, tv_enter_shop;
    private TextView tv_collect_goods, tv_shopping_cart, tv_join_cart, tv_buy;
    private TextView tv_cart_num;

    private WebView wap_desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_details);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        init();
    }

    private void init() {
        if (getIntent().getStringExtra("item_id") != null) {
            item_id = getIntent().getStringExtra("item_id");
        } else {
            item_id = "";
        }

        vp_list_image = (ViewPager) findViewById(R.id.vp_list_image);
        //设置主题图长高参数（宽高比1:1）
        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        final ViewGroup.LayoutParams para = vp_list_image.getLayoutParams();
        para.width = wm.getDefaultDisplay().getWidth();
        para.height = wm.getDefaultDisplay().getWidth() * 1 / 1;//活动主题图片长高设置为屏幕宽度1/1
        vp_list_image.setLayoutParams(para);
        ll_point = (LinearLayout) findViewById(R.id.ll_point);

        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_price = (TextView) findViewById(R.id.tv_price);

        mAmountView = (AmountView) findViewById(R.id.amount_view);
        mAmountView.setGoods_storage(100);
        mAmountView.setOnAmountChangeListener(new AmountView.OnAmountChangeListener() {
            @Override
            public void onAmountChange(View view, int amount) {
//                Toast.makeText(GoodsDetailsActivity.this, "Amount=>  " + amount, Toast.LENGTH_SHORT).show();
                quantity = amount + "";
            }
        });

        tv_promotion_tag = (TextView) findViewById(R.id.tv_promotion_tag);
        tv_promotion_name = (TextView) findViewById(R.id.tv_promotion_name);
        ll_promotion_info = (LinearLayout) findViewById(R.id.ll_promotion_info);
        line_promotion = (View) findViewById(R.id.line_promotion);

        rb_goods = (RadioButton) findViewById(R.id.rb_goods);
        rb_coupon = (RadioButton) findViewById(R.id.rb_coupon);
        ll_coupon_info = (LinearLayout) findViewById(R.id.ll_coupon_info);
        line_coupon = (View) findViewById(R.id.line_coupon);

        rb_spec1 = (RadioButton) findViewById(R.id.rb_spec1);
        rb_spec2 = (RadioButton) findViewById(R.id.rb_spec2);
        rb_spec3 = (RadioButton) findViewById(R.id.rb_spec3);
        tv_spec_name = (TextView) findViewById(R.id.tv_spec_name);
        ll_spec_info = (LinearLayout) findViewById(R.id.ll_spec_info);
        line_spec = (View) findViewById(R.id.line_spec);

        tv_after_sale = (TextView) findViewById(R.id.tv_after_sale);

        tv_percentage = (TextView) findViewById(R.id.tv_percentage);
        tv_total_rate = (TextView) findViewById(R.id.tv_total_rate);

        iv_shop_logo = (ImageView) findViewById(R.id.iv_shop_logo);

        tv_shop_name = (TextView) findViewById(R.id.tv_shop_name);
        tv_shop_descript = (TextView) findViewById(R.id.tv_shop_descript);

        tv_collect_shop = (TextView) findViewById(R.id.tv_collect_shop);
        tv_enter_shop = (TextView) findViewById(R.id.tv_enter_shop);

        tv_collect_goods = (TextView) findViewById(R.id.tv_collect_goods);
        tv_shopping_cart = (TextView) findViewById(R.id.tv_shopping_cart);
        tv_join_cart = (TextView) findViewById(R.id.tv_join_cart);
        tv_buy = (TextView) findViewById(R.id.tv_buy);

        tv_cart_num = (TextView) findViewById(R.id.tv_cart_num);
        tv_cart_num.setText(MallApplication.getInstance().getCartNum(this));

        wap_desc = (WebView) findViewById(R.id.wap_desc);

        getData();
    }

    private void getData() {
        String url = MallApi.APP_GOODS;
        Map<String, String> map = new HashMap<>();
        if (!item_id.equals("")) {
            map.put("item_id", item_id);
        }
        if (MallApplication.getInstance().getAutoLogin(GoodsDetailsActivity.this)) {//已登录状态下，传user_id
            map.put("user_id", MallApplication.getInstance().getUserId(GoodsDetailsActivity.this));
        }
        showDialog(LOADING_DIALOG);

        XUtils.Get(url, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                Log.e("result", result);
                if (JsonValidator.validate(result)) {
                    GoodsDetailsBean goodsDetailsBean = ParseUtils.parseGoodsDetailsBean(result);
                    if (goodsDetailsBean.getCode().equals("0")) {
                        if (goodsDetailsBean.getData().getItem().getSpec_desc().equals("1")) {
                            /***************当返回spec字段有内容时解析，获得SpecBean********************/
                            GoodsDetailsBean1 goodsDetailsBean1 = ParseUtils.parseGoodsDetailsBean1(result);
                            SpecBean specBean = goodsDetailsBean1.getData().getItem().getSpec();
                            //填充数据
                            fillData(goodsDetailsBean, specBean);
                        } else {
                            //填充数据
                            fillData(goodsDetailsBean, null);
                        }
                    } else {
                        Toast.makeText(GoodsDetailsActivity.this, goodsDetailsBean.getMsg(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(GoodsDetailsActivity.this, R.string.unable_to_get_data, Toast.LENGTH_SHORT).show();
                }
                removeDialog();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
            }
        });
    }

    /**
     * 填充数据
     *
     * @param goodsDetailsBean
     */
    private void fillData(final GoodsDetailsBean goodsDetailsBean, final SpecBean specBean) {
        //设置主题图片图集
        if (goodsDetailsBean.getData().getItem().getList_image() != null && goodsDetailsBean.getData().getItem().getList_image().size() != 0) {
            picList = (ArrayList<String>) goodsDetailsBean.getData().getItem().getList_image();
            imageViewList = new ArrayList<ImageView>();
            for (int i = 0; i < picList.size(); i++) {
                ImageView imageView = new ImageView(GoodsDetailsActivity.this);
                imageViewList.add(imageView);
                //为imageView加载网络图片
                ImgUtils.setRectangleImage(imageViewList.get(i), picList.get(i));
            }
            points = new ArrayList<View>();
            for (int i = 0; i < picList.size(); i++) {
                ImageView view = new ImageView(GoodsDetailsActivity.this);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//            int margin = (int) getResources().getDimension(R.dimen.activity_vertical_margin);
                layoutParams.setMargins(8, 8, 8, 8);
                view.setLayoutParams(layoutParams);
                if (i == 0) {
                    view.setBackgroundResource(R.drawable.party_point_select);
                } else {
                    view.setBackgroundResource(R.drawable.party_point_normal);
                }
                ll_point.addView(view);
                points.add(view);
            }
            vp_list_image.setAdapter(new MyPagerAdapter());
            vp_list_image.setOnPageChangeListener(new MyPageChangeListener());
//            vp_list_image.setCurrentItem(1000 * imageViewList.size()); //设置ViewPager的默认项, 设置为长度的1000倍，开始就能往左滑动
        }

        tv_title.setText(goodsDetailsBean.getData().getItem().getTitle());
        tv_price.setText("￥" + goodsDetailsBean.getData().getItem().getPrice());

        if (goodsDetailsBean.getData().getPromotionDetail() != null) {
            ll_promotion_info.setVisibility(View.VISIBLE);
            line_promotion.setVisibility(View.VISIBLE);
            tv_promotion_tag.setText(goodsDetailsBean.getData().getPromotionDetail().get(0).getPromotion_tag());//TODO 促销信息是list
            tv_promotion_name.setText(goodsDetailsBean.getData().getPromotionDetail().get(0).getPromotion_name());
        } else {
            ll_promotion_info.setVisibility(View.GONE);
            line_promotion.setVisibility(View.GONE);
        }

        if (goodsDetailsBean.getData().getItem().getIssupport_productscoupon() == 1) {
            ll_coupon_info.setVisibility(View.VISIBLE);
            line_coupon.setVisibility(View.VISIBLE);
            rb_goods.setChecked(true);
        } else {
            ll_coupon_info.setVisibility(View.GONE);
            line_coupon.setVisibility(View.GONE);
        }

        if (goodsDetailsBean.getData().getItem().getSpec_desc().equals("1")) {
            ll_spec_info.setVisibility(View.VISIBLE);
            line_spec.setVisibility(View.VISIBLE);
            tv_spec_name.setText(specBean.getSku_name());
            rb_spec1.setVisibility(View.VISIBLE);
            rb_spec1.setText(specBean.getSpecSku().get(0).getSku_value_name());
        } else if (goodsDetailsBean.getData().getItem().getSpec_desc().equals("0")) {
            ll_spec_info.setVisibility(View.GONE);
            line_spec.setVisibility(View.GONE);
        }

        tv_after_sale.setText("由" + goodsDetailsBean.getData().getShop().getShop_name() + "提供售后服务");

        tv_percentage.setText(goodsDetailsBean.getData().getCountRate().getGood().getPercentage());
        tv_total_rate.setText(goodsDetailsBean.getData().getCountRate().getTotal());

        ImgUtils.setRectangleImage(iv_shop_logo, goodsDetailsBean.getData().getShop().getShop_logo());

        tv_shop_name.setText(goodsDetailsBean.getData().getShop().getShop_name());
        tv_shop_descript.setText(goodsDetailsBean.getData().getShop().getShop_descript());

        tv_collect_shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, String> map = new HashMap<>();
                if (!MallApplication.getInstance().getAutoLogin(GoodsDetailsActivity.this)) {//未登录状态下点击跳转登录页面
                    startActivity(new Intent(GoodsDetailsActivity.this, LoginActivity.class));
                    GoodsDetailsActivity.this.overridePendingTransition(R.anim.activity_open, 0);
                    return;
                } else {
                    map.put("user_id", MallApplication.getInstance().getUserId(GoodsDetailsActivity.this));
                }
                map.put("shop_id", goodsDetailsBean.getData().getShop().getShop_id());
                showDialog(LOADING_DIALOG);
                XUtils.Post(MallApi.APP_SHOPSCOLLECT_ADD, map, new MyCallBack<String>() {
                    @Override
                    public void onSuccess(String result) {
                        super.onSuccess(result);
                        if (JsonValidator.validate(result)) {
                            try {
                                JSONObject json = new JSONObject(result);
                                if (json.getString("code").equals("0")) {
                                    Toast.makeText(GoodsDetailsActivity.this, "收藏成功", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(GoodsDetailsActivity.this, json.getString("msg"), Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        } else {
                            Toast.makeText(GoodsDetailsActivity.this, R.string.unable_to_get_data, Toast.LENGTH_SHORT).show();
                        }
                        removeDialog();
                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {
                        super.onError(ex, isOnCallback);
                    }
                });

            }
        });
        tv_enter_shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GoodsDetailsActivity.this, ShopHomepageActivity.class);
                intent.putExtra("shop_id", goodsDetailsBean.getData().getShop().getShop_id());
                startActivity(intent);
            }
        });
        if (goodsDetailsBean.getData().getItem().getValid().equals("0")) {
            tv_join_cart.setBackgroundColor(getResources().getColor(R.color.darkGray));
            tv_buy.setBackgroundColor(getResources().getColor(R.color.darkGray));
        }
        /**收藏商品**/
        if (goodsDetailsBean.getData().getIs_collect().equals("1")) {
            tv_collect_goods.setText("收藏");
            tv_collect_goods.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //收藏商品
                    Map<String, String> map = new HashMap<>();
                    if (!MallApplication.getInstance().getAutoLogin(GoodsDetailsActivity.this)) {//未登录状态下点击跳转登录页面
                        startActivity(new Intent(GoodsDetailsActivity.this, LoginActivity.class));
                        GoodsDetailsActivity.this.overridePendingTransition(R.anim.activity_open, 0);
                        return;
                    } else {
                        map.put("user_id", MallApplication.getInstance().getUserId(GoodsDetailsActivity.this));
                    }
                    map.put("goods_id", goodsDetailsBean.getData().getItem().getItem_id());
                    showDialog(LOADING_DIALOG);
                    XUtils.Post(MallApi.APP_COLLECT_ADD, map, new MyCallBack<String>() {
                        @Override
                        public void onSuccess(String result) {
                            super.onSuccess(result);
                            if (JsonValidator.validate(result)) {
                                try {
                                    JSONObject json = new JSONObject(result);
                                    if (json.getString("code").equals("0")) {
                                        Toast.makeText(GoodsDetailsActivity.this, "收藏成功", Toast.LENGTH_SHORT).show();
                                        tv_collect_goods.setText("已收藏");
                                    } else {
                                        Toast.makeText(GoodsDetailsActivity.this, json.getString("msg"), Toast.LENGTH_SHORT).show();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            } else {
                                Toast.makeText(GoodsDetailsActivity.this, R.string.unable_to_get_data, Toast.LENGTH_SHORT).show();
                            }
                            removeDialog();
                        }

                        @Override
                        public void onError(Throwable ex, boolean isOnCallback) {
                            super.onError(ex, isOnCallback);
                        }
                    });
                }
            });
        } else {
            tv_collect_goods.setText("已收藏");
            tv_collect_goods.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(GoodsDetailsActivity.this, "该商品已经收藏了", Toast.LENGTH_SHORT).show();
                }
            });
        }
        /**跳转购物车**/
        tv_shopping_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GoodsDetailsActivity.this, ShoppingCartActivity.class));
            }
        });
        /**加入购物车**/
        tv_join_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //加入购物车
                Map<String, String> map = new HashMap<>();
                if (!MallApplication.getInstance().getAutoLogin(GoodsDetailsActivity.this)) {//未登录状态下点击弹出提示
                    Toast.makeText(GoodsDetailsActivity.this, "请先登录", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (goodsDetailsBean.getData().getItem().getValid().equals("0")) {
                    Toast.makeText(GoodsDetailsActivity.this, "商品未上架，暂不能购买", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (goodsDetailsBean.getData().getItem().getSpec_desc().equals("1") && rb_spec1.isChecked() == false) {
                    Toast.makeText(GoodsDetailsActivity.this, "请先选择完整规格", Toast.LENGTH_SHORT).show();
                    return;
                } else if (goodsDetailsBean.getData().getItem().getSpec_desc().equals("1") && rb_spec1.isChecked() == true) {
                    map.put("sku_id", specBean.getSpecSku().get(0).getSku_id() + "");
                } else {
                    map.put("sku_id", goodsDetailsBean.getData().getItem().getDefault_sku_id());
                }
                map.put("user_id", MallApplication.getInstance().getUserId(GoodsDetailsActivity.this));
                map.put("quantity", quantity);
                map.put("obj_type", "item");
                map.put("mode", "cart");
                showDialog(LOADING_DIALOG);
                XUtils.Post(MallApi.APP_CART_ADD, map, new MyCallBack<String>() {
                    @Override
                    public void onSuccess(String result) {
                        super.onSuccess(result);
                        if (JsonValidator.validate(result)) {
                            AddCartBean addCartBean = ParseUtils.parseAddCartBean(result);
                            if (addCartBean.getCode().equals("0")) {
                                Toast.makeText(GoodsDetailsActivity.this, addCartBean.getMsg(), Toast.LENGTH_SHORT).show();
                                MallApplication.getInstance().setCartNum(GoodsDetailsActivity.this, addCartBean.getData().getCart_num());
                                tv_cart_num.setText(addCartBean.getData().getCart_num());
                            } else {
                                Toast.makeText(GoodsDetailsActivity.this, addCartBean.getMsg(), Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(GoodsDetailsActivity.this, R.string.unable_to_get_data, Toast.LENGTH_SHORT).show();
                        }
                        removeDialog();
                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {
                        super.onError(ex, isOnCallback);
                    }
                });
            }
        });
        /**立即购买**/
        tv_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!MallApplication.getInstance().getAutoLogin(GoodsDetailsActivity.this)) {//未登录状态下点击弹出提示
                    Toast.makeText(GoodsDetailsActivity.this, "请先登录", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (goodsDetailsBean.getData().getItem().getValid().equals("0")) {
                    Toast.makeText(GoodsDetailsActivity.this, "商品未上架，暂不能购买", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent = new Intent(GoodsDetailsActivity.this, OrderTallyActivity.class);
                if (goodsDetailsBean.getData().getItem().getSpec_desc().equals("1") && !rb_spec1.isChecked()) {
                    Toast.makeText(GoodsDetailsActivity.this, "请先选择完整规格", Toast.LENGTH_SHORT).show();
                    return;
                } else if (goodsDetailsBean.getData().getItem().getSpec_desc().equals("1") && rb_spec1.isChecked()) {
                    intent.putExtra("sku_id", specBean.getSpecSku().get(0).getSku_id() + "");
                } else {
                    intent.putExtra("sku_id", goodsDetailsBean.getData().getItem().getDefault_sku_id());
                }
                intent.putExtra("quantity", quantity);
                intent.putExtra("obj_type", "item");
                intent.putExtra("mode", "fastbuy");
                startActivity(intent);
            }
        });

        String customHtml = "<!DOCTYPE html>\n" +
                "<html>\n" +
                " <head> \n" +
                "  <meta charset=\"utf-8\" /> \n" +
                "  <meta name=\"viewport\" content=\"width=device-width,\" initial-scale=\"1.0,\" maximum-scale=\"1.0\" /> \n" +
                "  <style>.fulldiv img{ width: 100%!important; }</style> \n" +
                " </head> \n" +
                " <body> \n" +
                "  <div class=\"fulldiv\"> \n" +
                goodsDetailsBean.getData().getItem().getWap_desc() +
                "  </div>  \n" +
                " </body>\n" +
                "</html>";
        wap_desc.getSettings().setUseWideViewPort(true);
        wap_desc.getSettings().setLoadWithOverviewMode(true);
        wap_desc.loadDataWithBaseURL(null, customHtml, "text/html", "gb2312", null);
    }


    /**
     * 自定义pageradapter  适配viewpager
     */
    public class MyPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
//            return Integer.MAX_VALUE;
            return picList.size();
        }

        /**
         * 根据指定的下标 创建viewpager中展示的item  返回当前page中的view对象
         * 第一个参数表示 当前管理page的视图组
         * 第二个参数表示 指定下标
         */
        @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
//            try {
//                ((ViewPager) container).addView(imageViewList.get(position % picList.size()), 0);
//            } catch (Exception e) {
//                //handler something
//            }
//            imageViewList.get(position % picList.size()).setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    // TODO 显示图册
//                    Toast.makeText(GoodsDetailsActivity.this, "点击了第" + (position % picList.size() + 1) + "张图片", Toast.LENGTH_SHORT).show();
//                }
//            });
//
//            return imageViewList.get(position % picList.size());


            ((ViewPager) container).addView(imageViewList.get(position), 0);
            imageViewList.get(position).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO 显示图册
//                    Toast.makeText(GoodsDetailsActivity.this, "点击了第" + (position + 1) + "张图片", Toast.LENGTH_SHORT).show();
                }
            });
            return imageViewList.get(position);

        }

        /**
         * 根据指定的下标移除视图组中的view对象
         * 第一个参数表示 视图组对象
         * 第二个参数表示 当前移除的视图的下标
         * 第三个参数表示 instantiateItem 返回的object对象
         */
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
//            ((ViewPager) container).removeView(imageViewList.get(position % imageViewList.size()));
            ((ViewPager) container).removeView(imageViewList.get(position));
        }

        /**
         * 表示判断viewpager中展示的view对象与instantiateItem对象是否时同一个对象
         */
        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public void restoreState(Parcelable arg0, ClassLoader arg1) {

        }

        @Override
        public Parcelable saveState() {
            return null;
        }

        @Override
        public void startUpdate(View arg0) {

        }

        @Override
        public void finishUpdate(View arg0) {

        }
    }

    /**
     * 当ViewPager中页面的状态发生改变时调用
     *
     * @author xieqinghua
     */
    private class MyPageChangeListener implements ViewPager.OnPageChangeListener {

        /**
         * This method will be invoked when a new page becomes selected.
         * position: Position index of the new selected page.
         */
        public void onPageSelected(int position) {
//            setImageBackground(position % picList.size());
            setImageBackground(position);
        }

        /**
         * 设置选中的圆点的背景
         *
         * @param selectItems
         */
        private void setImageBackground(int selectItems) {
            for (int i = 0; i < points.size(); i++) {
                if (i == selectItems) {
                    points.get(i).setBackgroundResource(R.drawable.party_point_select);
                } else {
                    points.get(i).setBackgroundResource(R.drawable.party_point_normal);
                }
            }
        }

        public void onPageScrollStateChanged(int arg0) {
        }

        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }
    }


    @Override
    public void onClick(View v) {

    }
}
