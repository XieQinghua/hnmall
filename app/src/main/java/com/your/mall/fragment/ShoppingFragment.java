package com.your.mall.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.your.mall.R;
import com.your.mall.activity.CartOrderTallyActivity;
import com.your.mall.adapter.ShoppingCartAdapter;
import com.your.mall.application.MallApplication;
import com.your.mall.bean.ShoppingCartBean;
import com.your.mall.http.MallApi;
import com.your.mall.utils.AmountGoodEvent;
import com.your.mall.utils.IBtnCallListener;
import com.your.mall.utils.JsonValidator;
import com.your.mall.utils.MyCallBack;
import com.your.mall.utils.ParseUtils;
import com.your.mall.utils.StringUtils;
import com.your.mall.utils.UpdateCartEvent;
import com.your.mall.utils.UpdateShopEvent;
import com.your.mall.utils.XUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xieqinghua.
 * 创建时间：2016/8/30
 * 类描述：购物车
 * 修改备注：
 */
public class ShoppingFragment extends android.support.v4.app.Fragment implements View.OnClickListener, IBtnCallListener {
    IBtnCallListener btnCallListener;
    private LinearLayout ll_cart_empty;
    private Button btn_stroll;

    private RelativeLayout rl_shopping_cart_info;
    private ListView lv_shopping_cart;
    private ShoppingCartAdapter myShoppingCartAdapter;
    private ArrayList<ShoppingCartBean.DataBean> list;

    private List<ShoppingCartBean.DataBean.GoodListBean> selectList;

    private CheckBox cb_all;
    private TextView tv_all_payment, tv_post_info, tv_tally;
    public static final int LOADING_DIALOG = 0;

    public Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case 1:
                    btnCallListener.changeCartNum();
                    getData();
                    break;
                default:
                    break;
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shopping, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        init();
        initListener();
    }

    @Override
    public void onResume() {
        super.onResume();
        selectList.clear();
        getData();
    }

    @Override
    public void onAttach(Activity activity) {
        btnCallListener = (IBtnCallListener) activity;
        super.onAttach(activity);
    }

    private void init() {
        selectList = new ArrayList<>();
        ll_cart_empty = (LinearLayout) getView().findViewById(R.id.ll_cart_empty);
        btn_stroll = (Button) getView().findViewById(R.id.btn_stroll);
        btn_stroll.setOnClickListener(this);

        cb_all = (CheckBox) getView().findViewById(R.id.cb_all);
        tv_all_payment = (TextView) getView().findViewById(R.id.tv_all_payment);
        tv_post_info = (TextView) getView().findViewById(R.id.tv_post_info);
        tv_tally = (TextView) getView().findViewById(R.id.tv_tally);

        rl_shopping_cart_info = (RelativeLayout) getView().findViewById(R.id.rl_shopping_cart_info);
        lv_shopping_cart = (ListView) getView().findViewById(R.id.lv_shopping_cart);

        getData();
    }

    private void initListener() {
        cb_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb_all.isChecked()) {
                    for (int i = 0; i < list.size(); i++) {
                        ((CheckBox) lv_shopping_cart.getChildAt(i).findViewById(R.id.cb_shop_name)).setChecked(true);
                    }
                    myShoppingCartAdapter.setItemSelect("1");
                    selectList.clear();
                    for (ShoppingCartBean.DataBean dataBean : list) {
                        selectList.addAll(dataBean.getGood_list());
                    }
                } else {
                    int isChoice_all = 0;
                    // 设置全部取消
                    for (int i = 0; i < list.size(); i++) {
                        // 判断列表每一行是否处于选中状态，如果处于选中状态，则计数+1
                        if (((CheckBox) (lv_shopping_cart.getChildAt(i)).findViewById(R.id.cb_shop_name)).isChecked()) {
                            // 计算出列表选中状态的数量
                            isChoice_all += 1;
                        }
                    }
                    // 判断列表选中数是否等于列表的总数，如果等于，那么就需要执行全部取消操作
                    if (isChoice_all == list.size()) {
                        // 如果没有选中了全选，那么就将列表的每一行都不选
                        for (int i = 0; i < list.size(); i++) {
                            // 列表每一行都取消
                            ((CheckBox) (lv_shopping_cart.getChildAt(i)).findViewById(R.id.cb_shop_name)).setChecked(false);
                        }
                    }
                    myShoppingCartAdapter.setItemSelect("0");
                    selectList.clear();
                }
                updateCartStatus();
            }
        });
    }

    /**
     * 获取购物车数据
     */
    private void getData() {
        if (!MallApplication.getInstance().getAutoLogin(getActivity())) {//未登录状态下，显示空页面
            rl_shopping_cart_info.setVisibility(View.GONE);
            ll_cart_empty.setVisibility(View.VISIBLE);
            return;
        }
        Map<String, String> map = new HashMap<>();
        map.put("user_id", MallApplication.getInstance().getUserId(getActivity()));
        getActivity().showDialog(LOADING_DIALOG);
        XUtils.Get(MallApi.APP_CART_LIST, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                Log.e("cart", result);
                if (JsonValidator.validate(result)) {
                    selectList.clear();
                    ShoppingCartBean shoppingCartBean = ParseUtils.parseShoppingCartBean(result);

                    if (shoppingCartBean.getCode().equals("0")) {
                        //遍历网络请求获取数据设置选中的金额
                        List<ShoppingCartBean.DataBean.GoodListBean> goodListBeanList =
                                new ArrayList<ShoppingCartBean.DataBean.GoodListBean>();
                        for (int i = 0; i < shoppingCartBean.getData().size(); i++) {
                            goodListBeanList.addAll(shoppingCartBean.getData().get(i).getGood_list());
                        }
                        for (ShoppingCartBean.DataBean.GoodListBean goodListBean : goodListBeanList) {
                            if ("1".equals(goodListBean.getIs_checked())) {
                                selectList.add(goodListBean);
                            }
                        }
                        if (isAllChecked(shoppingCartBean.getData())) {
                            cb_all.setChecked(true);
                        } else {
                            cb_all.setChecked(false);
                        }
                        //setTotalPrice();
                        tv_all_payment.setText("￥" + shoppingCartBean.getMoneydata().getTotalAfterDiscount());
                        tv_tally.setText("去结算（" + shoppingCartBean.getMoneydata().getNumber() + "）");
                        if (!shoppingCartBean.getMoneydata().getNumber().equals("0")) {
                            tv_tally.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent = new Intent(getActivity(), CartOrderTallyActivity.class);
                                    startActivity(intent);
                                }
                            });
                        } else {
                            tv_tally.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Toast.makeText(getActivity(), "尚未选中任何商品！", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                        if (shoppingCartBean.getData() != null && shoppingCartBean.getData().size() != 0) {
                            list = (ArrayList<ShoppingCartBean.DataBean>) shoppingCartBean.getData();
                            myShoppingCartAdapter = new ShoppingCartAdapter(getActivity(), list, handler);
                            lv_shopping_cart.setAdapter(myShoppingCartAdapter);
                            rl_shopping_cart_info.setVisibility(View.VISIBLE);
                            ll_cart_empty.setVisibility(View.GONE);
                        } else {
                            rl_shopping_cart_info.setVisibility(View.GONE);
                            ll_cart_empty.setVisibility(View.VISIBLE);
                        }
                    } else {
                        Toast.makeText(getActivity(), shoppingCartBean.getMsg(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getActivity(), R.string.unable_to_get_data, Toast.LENGTH_SHORT).show();
                }
                getActivity().removeDialog(LOADING_DIALOG);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_stroll:
                //跳转首页
                btnCallListener.showHome();
                break;
//            case R.id.tv_tally:
//                Intent intent = new Intent(getActivity(), CartOrderTallyActivity.class);
//                startActivity(intent);
//                break;
        }
    }

    /**
     * 设置总价格
     */
    private void setCb_all() {
        float totalPrice = 0;
        for (ShoppingCartBean.DataBean.GoodListBean goodListBean : selectList) {
            totalPrice = totalPrice + Integer.parseInt(goodListBean.getQuantity()) * Float.parseFloat(goodListBean.getPrice());
        }
    }

    @Override
    public void showHome() {

    }

    @Override
    public void showClass() {

    }

    @Override
    public void showShoppingCart() {

    }

    @Override
    public void changeCartNum() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    /**
     * 商店选中反选监听回调
     *
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void updateCart(UpdateCartEvent event) {
        List<ShoppingCartBean.DataBean.GoodListBean> goodListBeanArrayList = new ArrayList<>();
        for (ShoppingCartBean.DataBean dataBean : list) {
            goodListBeanArrayList.addAll(dataBean.getGood_list());
        }
        for (ShoppingCartBean.DataBean.GoodListBean listBean : goodListBeanArrayList) {
            if (listBean.getItem_id().equals(event.id)) {
                if (event.isChoice) {
                    listBean.setIs_checked("1");
                } else {
                    listBean.setIs_checked("0");
                }
            }
        }
//        for (ShoppingCartBean.DataBean dataBean : list) {
//            List<String> status = new ArrayList<>();
//            for (ShoppingCartBean.DataBean.GoodListBean goodListBean1 : dataBean.getGood_list()) {
//                status.add(goodListBean1.getIs_checked());
//            }
//            if (status.contains("1")) {
//                dataBean.setIs_checked("1");
//            } else {
//                dataBean.setIs_checked("0");
//            }
//        }

        for (ShoppingCartBean.DataBean dataBean : list) {
            List<String> status = new ArrayList<>();
            for (ShoppingCartBean.DataBean.GoodListBean goodListBean1 : dataBean.getGood_list()) {
                status.add(goodListBean1.getIs_checked());
            }
            if (status.contains("0")) {
                dataBean.setIs_checked("0");
            } else {
                dataBean.setIs_checked("1");
            }
        }

        for (ShoppingCartBean.DataBean.GoodListBean goodListBean : goodListBeanArrayList) {
            if (event.id.equals(goodListBean.getItem_id())) {
                if (event.isChoice) {
                    if (selectList.contains(goodListBean)) {

                    } else {
                        selectList.add(goodListBean);
                    }
                } else {
                    if (selectList.contains(goodListBean)) {
                        selectList.remove(goodListBean);

                    } else {
                    }
                }
            }


        }
        myShoppingCartAdapter.notifyDataSetChanged();
        if (!event.isChoice) {
            cb_all.setChecked(false);
        }
        updateCartStatus();
    }

    /**
     * good选中反选监听回调
     *
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void updateShop(UpdateShopEvent event) {
        List<ShoppingCartBean.DataBean.GoodListBean> goodListBeen = new ArrayList<>();
        for (ShoppingCartBean.DataBean dataBean : list) {
            goodListBeen.addAll(dataBean.getGood_list());
        }
        for (ShoppingCartBean.DataBean dataBean : list) {

            if (dataBean.getShop_id().equals(event.shopId)) {
                if (!event.isChose) {
                    dataBean.setIs_checked("0");
                }
                for (ShoppingCartBean.DataBean.GoodListBean goodListBean : dataBean.getGood_list()) {
                    if (event.isChose) {
                        goodListBean.setIs_checked("1");
                        dataBean.setIs_checked("1");
                        if (selectList.contains(goodListBean)) {
                        } else {
                            selectList.add(goodListBean);
                        }
                    } else {
                        goodListBean.setIs_checked("0");
                        dataBean.setIs_checked("0");
                        if (selectList.contains(goodListBean)) {
                            selectList.remove(goodListBean);
                        } else {
                        }
                    }
                }
            }
        }
        updateCartStatus();
        myShoppingCartAdapter = new ShoppingCartAdapter(getActivity(), list, handler);
        lv_shopping_cart.setAdapter(myShoppingCartAdapter);
        if (!event.isChose) {
            cb_all.setChecked(false);
        }
    }

    /**
     * 数量+-监听回调
     *
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void updateAmount(AmountGoodEvent event) {
        List<ShoppingCartBean.DataBean.GoodListBean> goodListBeens = new ArrayList<>();
        for (ShoppingCartBean.DataBean dataBean : list) {
            goodListBeens.addAll(dataBean.getGood_list());
        }
        for (ShoppingCartBean.DataBean.GoodListBean goodListBean : goodListBeens) {
            if (event.goodId.equals(goodListBean.getItem_id())) {
                goodListBean.setQuantity(event.nums);
            }
        }
        updateCartStatus();
    }

    /**
     * 更新购物车接口
     */
    private void updateCartStatus() {
        List<ShoppingCartBean.DataBean.GoodListBean> goodListBeens = new ArrayList<>();
        List<String> cartIds = new ArrayList<>();
        List<String> nums = new ArrayList<>();
        List<String> isCheckeds = new ArrayList<>();
        List<String> promotionids = new ArrayList<>();
        List<String> modes = new ArrayList<>();
        for (ShoppingCartBean.DataBean dataBean : list) {
            goodListBeens.addAll(dataBean.getGood_list());
        }
        for (ShoppingCartBean.DataBean.GoodListBean goodListBean : goodListBeens) {
            cartIds.add(goodListBean.getCart_id());
//            if ("0".equals(goodListBean.getIs_checked())) {
//                nums.add("1");
//            } else {
            nums.add(goodListBean.getQuantity());
//            }
            isCheckeds.add(goodListBean.getIs_checked());
            promotionids.add(goodListBean.getSelected_promotion());
        }
        Map<String, String> map = new HashMap<>();
        map.put("user_id", MallApplication.getInstance().getUserId(getActivity()));
        map.put("obj_type", "item");
        map.put("cart_id", StringUtils.stringJoin(cartIds, ","));
        map.put("cart_num", StringUtils.stringJoin(nums, ","));
        map.put("is_checked", StringUtils.stringJoin(isCheckeds, ","));
        map.put("promotionid", StringUtils.stringJoin(isCheckeds, ","));
        map.put("mode", "cart");
        getActivity().showDialog(LOADING_DIALOG);
        XUtils.Post(MallApi.APP_CART_UPDATE, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                Log.e("cart_update", result);
                if (JsonValidator.validate(result)) {
                    try {
                        JSONObject object = new JSONObject(result);
                        if (object.getString("code").equals("0")) {
                            JSONObject json = object.getJSONObject("moneydata");
                            String total = json.getString("totalAfterDiscount");
                            String nums = json.getString("number");
                            tv_all_payment.setText("￥" + total);
                            tv_tally.setText("去结算（" + nums + "）");
                            if (!nums.equals("0")) {
                                tv_tally.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent intent = new Intent(getActivity(), CartOrderTallyActivity.class);
                                        startActivity(intent);
                                    }
                                });
                            } else {
                                tv_tally.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Toast.makeText(getActivity(), "尚未选中任何商品！", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                            if (isAllChecked(list)) {
                                cb_all.setChecked(true);
                            } else {
                                cb_all.setChecked(false);
                            }
                        } else {
                            Toast.makeText(getActivity(), object.getString("msg"), Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    getActivity().removeDialog(LOADING_DIALOG);
                } else {
                    Toast.makeText(getActivity(), R.string.unable_to_get_data, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean isAllChecked(List<ShoppingCartBean.DataBean> goodList) {
        List<ShoppingCartBean.DataBean.GoodListBean> goodListBeanList = new ArrayList<>();
        for (ShoppingCartBean.DataBean dataBean : goodList) {
            goodListBeanList.addAll(dataBean.getGood_list());
        }
        boolean isAll = true;
        for (ShoppingCartBean.DataBean.GoodListBean goodListBean : goodListBeanList) {
            if ("0".equals(goodListBean.getIs_checked())) {
                isAll = false;
            }
        }
        return isAll;
    }
}
