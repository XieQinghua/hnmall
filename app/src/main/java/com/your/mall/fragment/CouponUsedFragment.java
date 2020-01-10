package com.your.mall.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.your.mall.R;
import com.your.mall.adapter.CouponAdapter;
import com.your.mall.application.MallApplication;
import com.your.mall.bean.CouponBean;
import com.your.mall.http.MallApi;
import com.your.mall.utils.JsonValidator;
import com.your.mall.utils.MyCallBack;
import com.your.mall.utils.ParseUtils;
import com.your.mall.utils.XUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xieqinghua.
 * 创建时间：2016/9/13
 * 类描述：优惠券已使用
 * 修改备注：
 */
public class CouponUsedFragment extends android.support.v4.app.Fragment {
    private ListView lv_used_coupon;
    private CouponAdapter myCouponAdapter;
    private ArrayList<CouponBean.DataBean> list;
    private TextView tv_no_coupon;
    public static final int LOADING_DIALOG = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_coupon_used, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        lv_used_coupon = (ListView) getView().findViewById(R.id.lv_used_coupon);
        tv_no_coupon = (TextView) getView().findViewById(R.id.tv_no_coupon);
        getData();
    }

    private void getData() {
        Map<String, String> map = new HashMap<>();
        map.put("user_id", MallApplication.getInstance().getUserId(getActivity()));
        map.put("state", "0");//0是已使用，1是未使用，2是过期
        getActivity().showDialog(LOADING_DIALOG);
        XUtils.Get(MallApi.APP_COUPON_LIST, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
//                Log.e("已使用", result);
                if (JsonValidator.validate(result)) {
                    CouponBean couponBean = ParseUtils.parseCouponBean(result);
                    if (couponBean.getCode().equals("0")) {
                        list = (ArrayList<CouponBean.DataBean>) couponBean.getData();
                        if (list != null && list.size() != 0) {
                            myCouponAdapter = new CouponAdapter(getActivity(), list);
                            lv_used_coupon.setVisibility(View.VISIBLE);
                            tv_no_coupon.setVisibility(View.GONE);
                            lv_used_coupon.setAdapter(myCouponAdapter);
                        } else {
                            lv_used_coupon.setVisibility(View.GONE);
                            tv_no_coupon.setVisibility(View.VISIBLE);
                        }
                    } else {
                        Toast.makeText(getActivity(), couponBean.getMsg(), Toast.LENGTH_SHORT).show();
                        lv_used_coupon.setVisibility(View.GONE);
                        tv_no_coupon.setVisibility(View.VISIBLE);
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
}
