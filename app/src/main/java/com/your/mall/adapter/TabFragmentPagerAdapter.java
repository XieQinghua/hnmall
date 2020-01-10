package com.your.mall.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by xieqinghua.
 * 创建时间：2016/9/13
 * 类描述：切换Fragment适配器
 * 修改备注：
 */
public class TabFragmentPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragmentList; //fragment列表
    private List<String> titleList; //tab名的列表

    public TabFragmentPagerAdapter(FragmentManager fm, List<Fragment> fragmentList, List<String> titleList) {
        super(fm);
        this.fragmentList = fragmentList;
        this.titleList = titleList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return titleList.size();
    }

    //此方法用来显示tab上的名字
    @Override
    public CharSequence getPageTitle(int position) {
        return titleList.get(position % titleList.size());
    }
}
