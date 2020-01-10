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
import com.your.mall.fragment.CollectGoodsFragment;
import com.your.mall.fragment.CollectShopFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xieqinghua.
 * 创建时间：2016/9/2
 * 类描述：我的收藏
 * 修改备注：
 */
public class CollectActivity extends BaseSwipeBackActivity {

    private TextView tv_title;
    private TabLayout mTabLayout;//定义TabLayout
    private ViewPager mViewPager;//定义viewPager
    private FragmentPagerAdapter myFragmentPagerAdapter; //定义adapter
    private List<Fragment> myFragmentList; //定义要装fragment的列表
    private List<String> myTitleList; //tab名称列表

    private CollectGoodsFragment myCollectGoodsFragment;
    private CollectShopFragment myCollectShopFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        init();

    }

    private void init() {
        tv_title = (TextView) findViewById(R.id.tv_title_center);
        tv_title.setText("我的收藏");

        mTabLayout = (TabLayout) findViewById(R.id.tabs);
        mViewPager = (ViewPager) findViewById(R.id.vp_view);

        myCollectGoodsFragment = new CollectGoodsFragment();
        myCollectShopFragment = new CollectShopFragment();
        myFragmentList = new ArrayList<>();
        myFragmentList.add(myCollectGoodsFragment);
        myFragmentList.add(myCollectShopFragment);

        myTitleList = new ArrayList<>();
        myTitleList.add("商品收藏");
        myTitleList.add("店铺收藏");

        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        mTabLayout.addTab(mTabLayout.newTab().setText(myTitleList.get(0)));
        mTabLayout.addTab(mTabLayout.newTab().setText(myTitleList.get(1)));

        myFragmentPagerAdapter = new TabFragmentPagerAdapter(this.getSupportFragmentManager(), myFragmentList, myTitleList);
        mViewPager.setAdapter(myFragmentPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }
}
