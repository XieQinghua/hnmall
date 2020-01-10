package com.your.mall.activity;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.your.mall.R;
import com.your.mall.adapter.TabFragmentPagerAdapter;
import com.your.mall.base.BaseSwipeBackActivity;
import com.your.mall.fragment.OrderFiveFragment;
import com.your.mall.fragment.OrderFourFragment;
import com.your.mall.fragment.OrderOneFragment;
import com.your.mall.fragment.OrderThreeFragment;
import com.your.mall.fragment.OrderTwoFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xieqinghua.
 * 创建时间：2016/9/2
 * 类描述：订单管理页面
 * 修改备注：
 */
public class OrderManagementActivity extends BaseSwipeBackActivity implements View.OnClickListener {

    private TextView tv_title;

    private TabLayout mTabLayout;//定义TabLayout
    private ViewPager mViewPager;//定义viewPager
    private FragmentPagerAdapter myFragmentPagerAdapter; //定义adapter
    private List<Fragment> myFragmentList; //定义要装fragment的列表
    private List<String> myTitleList; //tab名称列表

    private OrderOneFragment myOrderOneFragment;
    private OrderTwoFragment myOrderTwoFragment;
    private OrderThreeFragment myOrderThreeFragment;
    private OrderFourFragment myOrderFourFragment;
    private OrderFiveFragment myOrderFiveFragment;

    private TextView tv_all, tv_nupay, tv_nudelivery, tv_nuconfirm, tv_unrate;
    private TextView line1, line2, line3, line4, line5;
    private LinearLayout tab_all, tab_nupay, tab_nudelivery, tab_nuconfirm, tab_unrate;

    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        init();
        setSelectedTab(getIntent().getExtras().getInt("index"));
    }

    private void init() {
        tv_title = (TextView) findViewById(R.id.tv_title_center);
        tv_title.setText("订单管理");

//        mTabLayout = (TabLayout) findViewById(R.id.tabs);
//        mViewPager = (ViewPager) findViewById(R.id.vp_view);
//
//        //初始化各fragment
//        myOrderOneFragment = new OrderOneFragment();
//        myOrderTwoFragment = new OrderTwoFragment();
//        myOrderThreeFragment = new OrderThreeFragment();
//        myOrderFourFragment = new OrderFourFragment();
//        myOrderFiveFragment = new OrderFiveFragment();
//        //将fragment装进列表中
//        myFragmentList = new ArrayList<>();
//
//        myFragmentList.add(myOrderOneFragment);
//        myFragmentList.add(myOrderTwoFragment);
//        myFragmentList.add(myOrderThreeFragment);
//        myFragmentList.add(myOrderFourFragment);
//        myFragmentList.add(myOrderFiveFragment);
//
//        //将名称加载tab名字列表
//        myTitleList = new ArrayList<>();
//        myTitleList.add("全部");
//        myTitleList.add("待付款");
//        myTitleList.add("待发货");
//        myTitleList.add("待收货");
//        myTitleList.add("待评价");
//
//        mTabLayout.setTabMode(TabLayout.MODE_FIXED);//设置tab模式，当前为系统默认模式
//        mTabLayout.addTab(mTabLayout.newTab().setText(myTitleList.get(0)));//为TabLayout添加tab名称
//        mTabLayout.addTab(mTabLayout.newTab().setText(myTitleList.get(1)));
//        mTabLayout.addTab(mTabLayout.newTab().setText(myTitleList.get(2)));
//        mTabLayout.addTab(mTabLayout.newTab().setText(myTitleList.get(3)));
//        mTabLayout.addTab(mTabLayout.newTab().setText(myTitleList.get(4)));
//
//        myFragmentPagerAdapter = new TabFragmentPagerAdapter(this.getSupportFragmentManager(), myFragmentList, myTitleList);
//        mViewPager.setAdapter(myFragmentPagerAdapter);
//        mViewPager.setOffscreenPageLimit(0);
//        mTabLayout.setupWithViewPager(mViewPager);//将TabLayout和ViewPager关联起来。

        tab_all = (LinearLayout) findViewById(R.id.tab_all);
        tab_nupay = (LinearLayout) findViewById(R.id.tab_nupay);
        tab_nudelivery = (LinearLayout) findViewById(R.id.tab_nudelivery);
        tab_nuconfirm = (LinearLayout) findViewById(R.id.tab_nuconfirm);
        tab_unrate = (LinearLayout) findViewById(R.id.tab_unrate);

        tab_all.setOnClickListener(this);
        tab_nupay.setOnClickListener(this);
        tab_nudelivery.setOnClickListener(this);
        tab_nuconfirm.setOnClickListener(this);
        tab_unrate.setOnClickListener(this);

        tv_all = (TextView) findViewById(R.id.tv_all);
        tv_nupay = (TextView) findViewById(R.id.tv_nupay);
        tv_nudelivery = (TextView) findViewById(R.id.tv_nudelivery);
        tv_nuconfirm = (TextView) findViewById(R.id.tv_nuconfirm);
        tv_unrate = (TextView) findViewById(R.id.tv_unrate);

        line1 = (TextView) findViewById(R.id.line1);
        line2 = (TextView) findViewById(R.id.line2);
        line3 = (TextView) findViewById(R.id.line3);
        line4 = (TextView) findViewById(R.id.line4);
        line5 = (TextView) findViewById(R.id.line5);

        fragmentManager = getSupportFragmentManager();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tab_all:
                setSelectedTab(0);
                break;
            case R.id.tab_nupay:
                setSelectedTab(1);
                break;
            case R.id.tab_nudelivery:
                setSelectedTab(2);
                break;
            case R.id.tab_nuconfirm:
                setSelectedTab(3);
                break;
            case R.id.tab_unrate:
                setSelectedTab(4);
                break;
        }
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void resetTab() {
        tv_all.setTextColor(getResources().getColor(R.color.mall_content_color));
        tv_nupay.setTextColor(getResources().getColor(R.color.mall_content_color));
        tv_nudelivery.setTextColor(getResources().getColor(R.color.mall_content_color));
        tv_nuconfirm.setTextColor(getResources().getColor(R.color.mall_content_color));
        tv_unrate.setTextColor(getResources().getColor(R.color.mall_content_color));
        line1.setVisibility(View.INVISIBLE);
        line2.setVisibility(View.INVISIBLE);
        line3.setVisibility(View.INVISIBLE);
        line4.setVisibility(View.INVISIBLE);
        line5.setVisibility(View.INVISIBLE);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void setSelectedTab(int tabIndex) {
        resetTab();

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        hideFragment(fragmentTransaction);
        switch (tabIndex) {
            case 0:
                tv_all.setTextColor(getResources().getColor(R.color.tabSelectedTextColor));
                line1.setVisibility(View.VISIBLE);
                if (myOrderOneFragment == null) {
                    myOrderOneFragment = new OrderOneFragment();
                    fragmentTransaction.add(R.id.ll_content, myOrderOneFragment);
                } else {
                    fragmentTransaction.show(myOrderOneFragment);
                }
                break;
            case 1:
                tv_nupay.setTextColor(getResources().getColor(R.color.tabSelectedTextColor));
                line2.setVisibility(View.VISIBLE);
                if (myOrderTwoFragment == null) {
                    myOrderTwoFragment = new OrderTwoFragment();
                    fragmentTransaction.add(R.id.ll_content, myOrderTwoFragment);
                } else {
                    fragmentTransaction.show(myOrderTwoFragment);
                }
                break;
            case 2:
                tv_nudelivery.setTextColor(getResources().getColor(R.color.tabSelectedTextColor));
                line3.setVisibility(View.VISIBLE);
                if (myOrderThreeFragment == null) {
                    myOrderThreeFragment = new OrderThreeFragment();
                    fragmentTransaction.add(R.id.ll_content, myOrderThreeFragment);
                } else {
                    fragmentTransaction.show(myOrderThreeFragment);
                }
                break;
            case 3:
                tv_nuconfirm.setTextColor(getResources().getColor(R.color.tabSelectedTextColor));
                line4.setVisibility(View.VISIBLE);
                if (myOrderFourFragment == null) {
                    myOrderFourFragment = new OrderFourFragment();
                    fragmentTransaction.add(R.id.ll_content, myOrderFourFragment);
                } else {
                    fragmentTransaction.show(myOrderFourFragment);
                }
                break;
            case 4:
                tv_unrate.setTextColor(getResources().getColor(R.color.tabSelectedTextColor));
                line5.setVisibility(View.VISIBLE);
                if (myOrderFiveFragment == null) {
                    myOrderFiveFragment = new OrderFiveFragment();
                    fragmentTransaction.add(R.id.ll_content, myOrderFiveFragment);
                } else {
                    fragmentTransaction.show(myOrderFiveFragment);
                }
                break;
        }
        fragmentTransaction.commit();
    }

    private void hideFragment(FragmentTransaction fragmentTransaction) {
        if (myOrderOneFragment != null) {
            fragmentTransaction.hide(myOrderOneFragment);
        }
        if (myOrderTwoFragment != null) {
            fragmentTransaction.hide(myOrderTwoFragment);
        }
        if (myOrderThreeFragment != null) {
            fragmentTransaction.hide(myOrderThreeFragment);
        }
        if (myOrderFourFragment != null) {
            fragmentTransaction.hide(myOrderFourFragment);
        }
        if (myOrderFiveFragment != null) {
            fragmentTransaction.hide(myOrderFiveFragment);
        }
    }
}
