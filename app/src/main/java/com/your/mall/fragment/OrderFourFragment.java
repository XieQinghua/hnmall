package com.your.mall.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.your.mall.R;
import com.your.mall.activity.MainActivity;
import com.your.mall.activity.OrderDetailsActivity;
import com.your.mall.adapter.OrderAdapter;
import com.your.mall.application.MallApplication;
import com.your.mall.bean.OrderListBean;
import com.your.mall.http.MallApi;
import com.your.mall.utils.IBtnCallListener;
import com.your.mall.utils.JsonValidator;
import com.your.mall.utils.MyCallBack;
import com.your.mall.utils.ParseUtils;
import com.your.mall.utils.XUtils;
import com.your.mall.view.XList.XListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xieqinghua.
 * 创建时间：2016/9/13
 * 类描述：待收货订单
 * 修改备注：
 */
public class OrderFourFragment extends Fragment implements View.OnClickListener, IBtnCallListener, XListView.IXListViewListener {
    private LinearLayout ll_order_empty;
    private Button btn_stroll;
    private XListView lv_order;
    private OrderAdapter myOrderAdapter;
    private ArrayList<OrderListBean.DataBean> list;
    private ArrayList<OrderListBean.DataBean> lists = new ArrayList<>();
    public static final int LOADING_DIALOG = 0;

    private Handler mHandler;
    private int page_no = 1, page_size = 10;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order_four, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ll_order_empty = (LinearLayout) getView().findViewById(R.id.ll_order_empty);
        btn_stroll = (Button) getView().findViewById(R.id.btn_stroll);
        btn_stroll.setOnClickListener(this);

        lv_order = (XListView) getView().findViewById(R.id.lv_order);
        lv_order.hideHeader();//隐藏头部
        lv_order.setPullLoadEnable(true);
        lv_order.setXListViewListener(this);

        mHandler = new Handler();
        myOrderAdapter = new OrderAdapter(getActivity(), lists);
        lv_order.setAdapter(myOrderAdapter);
        lv_order.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), OrderDetailsActivity.class);
                intent.putExtra("tid", lists.get(position).getTid());
                startActivity(intent);
            }
        });
        getData();
    }

    @Override
    public void onResume() {
        super.onResume();
        //        getData();
    }

    /**
     * 获取订单数据
     */
    private void getData() {
        Map<String, String> map = new HashMap<>();
        map.put("user_id", MallApplication.getInstance().getUserId(getActivity()));
        map.put("s", "3");//0是全部，1是待付款，2是待发货，3是已发货，4是已完成
//        map.put("page_size", page_size + "");
        map.put("page_no", page_no + "");
        getActivity().showDialog(LOADING_DIALOG);
        XUtils.Get(MallApi.APP_TRADELIST, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                Log.e("待收货", result);
                if (JsonValidator.validate(result)) {
                    OrderListBean orderListBean = ParseUtils.parseOrderListBean(result);
                    if (orderListBean.getCode().equals("0")) {
                        if (orderListBean.getData() != null && orderListBean.getData().size() != 0) {
                            list = (ArrayList<OrderListBean.DataBean>) orderListBean.getData();
                            lists.addAll(list);
                            myOrderAdapter.notifyDataSetChanged();
                            getActivity().removeDialog(LOADING_DIALOG);
                        }
                    } else {
                        //当没有获得数据时，停止上拉加载
                        onLoad();
//                        Toast.makeText(getActivity(), orderListBean.getMsg(), Toast.LENGTH_SHORT).show();
                    }

                    if (lists.size() != 0) {
                        lv_order.setVisibility(View.VISIBLE);
                        ll_order_empty.setVisibility(View.GONE);
                    } else {
                        lv_order.setVisibility(View.GONE);
                        ll_order_empty.setVisibility(View.VISIBLE);
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
                showHome();
                break;
        }
    }

    @Override
    public void showHome() {
        //显示首页操作
        Intent intent = new Intent(getActivity(), MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.putExtra("tag", "activity");
        startActivity(intent);
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

    private void onLoad() {
        lv_order.hideHeader();//隐藏头部
        lv_order.stopRefresh();
        lv_order.stopLoadMore();
    }

    @Override
    public void onRefresh() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                onLoad();
            }
        }, 0);
    }

    @Override
    public void onLoadMore() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                page_no += 1;
                getData();
            }
        }, 1000);
    }
}

