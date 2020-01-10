package com.your.mall.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.WindowManager;
import android.widget.TextView;

import com.your.mall.R;
import com.your.mall.adapter.TabFragmentPagerAdapter;
import com.your.mall.base.BaseSwipeBackActivity;
import com.your.mall.fragment.CouponExpiredFragment;
import com.your.mall.fragment.CouponNotUsedFragment;
import com.your.mall.fragment.CouponUsedFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xieqinghua.
 * 创建时间：2016/9/2
 * 类描述：我的优惠券
 * 修改备注：
 */
public class CouponActivity extends BaseSwipeBackActivity {
    private TextView tv_title;
    private TabLayout mTabLayout;//定义TabLayout
    private ViewPager mViewPager;//定义viewPager
    private FragmentPagerAdapter myFragmentPagerAdapter; //定义adapter
    private List<Fragment> myFragmentList; //定义要装fragment的列表
    private List<String> myTitleList; //tab名称列表

    private CouponNotUsedFragment myCouponNotUsedFragment;
    private CouponUsedFragment myCouponUsedFragment;
    private CouponExpiredFragment myExpiredFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupon);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        init();

    }

    private void init() {
        tv_title = (TextView) findViewById(R.id.tv_title_center);
        tv_title.setText("我的优惠券");

        mTabLayout = (TabLayout) findViewById(R.id.tabs);
        mViewPager = (ViewPager) findViewById(R.id.vp_view);


        myCouponNotUsedFragment = new CouponNotUsedFragment();
        myCouponUsedFragment = new CouponUsedFragment();
        myExpiredFragment = new CouponExpiredFragment();
        myFragmentList = new ArrayList<>();
        myFragmentList.add(myCouponNotUsedFragment);
        myFragmentList.add(myCouponUsedFragment);
        myFragmentList.add(myExpiredFragment);

        myTitleList = new ArrayList<>();
        myTitleList.add("未使用");
        myTitleList.add("已使用");
        myTitleList.add("已过期");

        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        mTabLayout.addTab(mTabLayout.newTab().setText(myTitleList.get(0)));
        mTabLayout.addTab(mTabLayout.newTab().setText(myTitleList.get(1)));
        mTabLayout.addTab(mTabLayout.newTab().setText(myTitleList.get(2)));

        myFragmentPagerAdapter = new TabFragmentPagerAdapter(this.getSupportFragmentManager(), myFragmentList, myTitleList);
        mViewPager.setAdapter(myFragmentPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);

    }
}
