package com.your.mall.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.your.mall.R;
import com.your.mall.activity.ShopHomepageActivity;
import com.your.mall.adapter.CollectShopAdapter;
import com.your.mall.application.MallApplication;
import com.your.mall.bean.CollectShopBean;
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
 * 类描述：店铺收藏
 * 修改备注：
 */
public class CollectShopFragment extends android.support.v4.app.Fragment {

    private ListView lv_collect_shop;
    private CollectShopAdapter myCollectShopAdapter;
    private ArrayList<CollectShopBean.DataBean.ListBean> list;
    private TextView tv_no_collect;
    public static final int LOADING_DIALOG = 0;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_collect_shop, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        lv_collect_shop = (ListView) getView().findViewById(R.id.lv_collect_shop);
        tv_no_collect = (TextView) getView().findViewById(R.id.tv_no_collect);
        getData();
    }

    private void getData() {
        Map<String, String> map = new HashMap<>();
        map.put("user_id", MallApplication.getInstance().getUserId(getActivity()));
        getActivity().showDialog(LOADING_DIALOG);
        XUtils.Get(MallApi.APP_SHOPSCOLLECT_LIST, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                if (JsonValidator.validate(result)) {
                    CollectShopBean collectShopBean = ParseUtils.parseCollectShopBean(result);
                    if (collectShopBean.getCode().equals("0")) {
                        list = (ArrayList<CollectShopBean.DataBean.ListBean>) collectShopBean.getData().getList();
                        if (list != null && list.size() != 0) {
                            myCollectShopAdapter = new CollectShopAdapter(getActivity(), list);
                            lv_collect_shop.setAdapter(myCollectShopAdapter);
                            lv_collect_shop.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    Intent intent = new Intent(getActivity(), ShopHomepageActivity.class);
                                    intent.putExtra("shop_id", list.get(position).getShop_id() + "");
                                    startActivity(intent);
                                }
                            });
                        } else {
                            lv_collect_shop.setVisibility(View.GONE);
                            tv_no_collect.setVisibility(View.VISIBLE);
                        }
                    } else {
//                        Toast.makeText(getActivity(), collectShopBean.getMsg(), Toast.LENGTH_SHORT).show();
                        lv_collect_shop.setVisibility(View.GONE);
                        tv_no_collect.setVisibility(View.VISIBLE);
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

