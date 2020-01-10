package com.your.mall.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.RadioGroup;

import java.util.List;

/**
 * Created by xieqinghua.
 * 创建时间：2016/8/30
 * 类描述：用于传入参数之间的切换数据和切换特效 外加Top同时切换fragment
 * 修改备注：
 */

public class ClassFragmentTabAdapter implements RadioGroup.OnCheckedChangeListener {
    private List<Fragment> fragments; // 一个tab页面对应一个Fragment
    private RadioGroup rgs; // 用于切换tab
    private FragmentManager fragmentManager; // Fragment所属的Activity
    private int fragmentContentId; // Activity中所要被替换的区域的id
    private int currentTab; // 当前Tab页面索引
    private OnRgsExtraCheckedChangedListener onRgsExtraCheckedChangedListener; // 用于让调用者在切换tab时候增加新的功能

    public ClassFragmentTabAdapter(Context context, FragmentManager fragmentManager, List<Fragment> fragments, int fragmentContentId, RadioGroup rgs) {
        this.fragments = fragments;
        this.rgs = rgs;
        this.fragmentManager = fragmentManager;
        this.fragmentContentId = fragmentContentId;
        // 默认显示第一页
//        FragmentTransaction ft = fragmentManager.beginTransaction();
//        ft.add(fragmentContentId, fragments.get(0));
//        ft.addToBackStack(fragments.get(0).getClass().getName());
//        ft.commit();
        rgs.setOnCheckedChangeListener(this);

    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
        for (int i = 0; i < rgs.getChildCount(); i++) {
            if (rgs.getChildAt(i).getId() == checkedId) {
                Fragment fragment = fragments.get(i);
                FragmentTransaction ft = obtainFragmentTransaction(i);
                getCurrentFragment().onPause(); // 暂停当前tab
                getCurrentFragment().onStop(); // 暂停当前tab
                if (fragment.isAdded()) {
                    fragment.onStart(); // 启动目标tab的onStart()
                    fragment.onResume(); // 启动目标tab的onResume()
                } else {
                    ft.addToBackStack(fragment.getClass().getName());
                    ft.add(fragmentContentId, fragment);
                }
                showTab(i); // 显示目标tab
                ft.commit();

                // 如果设置了切换tab额外功能功能接口
                if (null != onRgsExtraCheckedChangedListener) {
                    onRgsExtraCheckedChangedListener.OnRgsExtraCheckedChanged(radioGroup, checkedId, i);
                }

            }
        }

    }

    /**
     * 切换tab
     *
     * @param idx
     */
    private void showTab(int idx) {
        for (int i = 0; i < fragments.size(); i++) {
            Fragment fragment = fragments.get(i);
            FragmentTransaction ft = obtainFragmentTransaction(idx);
            if (idx == i) {
                ft.show(fragment);
            } else {
                ft.hide(fragment);
            }
            ft.commit();
        }
        currentTab = idx; // 更新目标tab为当前tab
    }

    /**
     * 获取一个带动画的FragmentTransaction
     *
     * @param index
     * @return
     */
    private FragmentTransaction obtainFragmentTransaction(int index) {
        FragmentTransaction ft = fragmentManager.beginTransaction();
        // 设置切换动画
//        ft.setCustomAnimations(R.anim.cu_push_right_in, R.anim.cu_push_left_out);
//        if (index > currentTab) {
//            ft.setCustomAnimations(R.anim.slide_in_from_left, R.anim.slide_out_to_left);
//        } else {
//            ft.setCustomAnimations(R.anim.slide_in_from_right, R.anim.slide_out_to_right);
//        }
        return ft;
    }

    public int getCurrentTab() {
        return currentTab;
    }

    public Fragment getCurrentFragment() {
        return fragments.get(currentTab);
    }

    public OnRgsExtraCheckedChangedListener getOnRgsExtraCheckedChangedListener() {
        return onRgsExtraCheckedChangedListener;
    }

    public void setOnRgsExtraCheckedChangedListener(OnRgsExtraCheckedChangedListener onRgsExtraCheckedChangedListener) {
        this.onRgsExtraCheckedChangedListener = onRgsExtraCheckedChangedListener;
    }

    /**
     * 切换tab额外功能功能接口
     */
    public static interface OnRgsExtraCheckedChangedListener {
        public void OnRgsExtraCheckedChanged(RadioGroup radioGroup, int checkedId, int index);
    }
}
