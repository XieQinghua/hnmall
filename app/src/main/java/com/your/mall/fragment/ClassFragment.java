package com.your.mall.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.your.mall.R;
import com.your.mall.adapter.ClassFragmentTabAdapter;

import java.util.ArrayList;

/**
 * Created by xieqinghua.
 * 创建时间：2016/8/30
 * 类描述：分类
 * 修改备注：
 */
public class ClassFragment extends android.support.v4.app.Fragment implements View.OnClickListener {

    private ArrayList<Fragment> fragments;
    public FragmentTransaction ft;
    private RadioGroup myRadioGroup;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_class, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
        new ClassFragmentTabAdapter(getActivity(), getActivity().getSupportFragmentManager(), fragments, R.id.categroy_content, myRadioGroup);
        // 默认显示第一页
        ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.add(R.id.categroy_content, fragments.get(0));
        ft.addToBackStack(fragments.get(0).getClass().getName());
        ft.commit();
    }

    private void init() {
        myRadioGroup = (RadioGroup) getView().findViewById(R.id.rg_class);
        fragments = new ArrayList<Fragment>();
        fragments.add(new ClassTabOneFragment());
        fragments.add(new ClassTabTwoFragment());
        fragments.add(new ClassTabThreeFragment());
        fragments.add(new ClassTabFourFragment());
        fragments.add(new ClassTabFiveFragment());
        fragments.add(new ClassTabSixFragment());
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

        }
    }
}
