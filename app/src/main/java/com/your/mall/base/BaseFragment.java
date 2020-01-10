package com.your.mall.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Pinger on 2016/10/10.
 */

public abstract class BaseFragment extends Fragment {

    //控件是否已经初始化
    private boolean isCreateView = false;
    //是否已经加载过数据
    private boolean isLoadData = false;

    protected View mRootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = initView(inflater, container, savedInstanceState);
        }
        isCreateView = true;
        return mRootView;
    }

    /**
     * 初始化控件
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    public abstract View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);


    public abstract void initData();

    //此方法在控件初始化前调用，所以不能在此方法中直接操作控件会出现空指针
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && isCreateView) {
            lazyLoad();
        }
    }

    private void lazyLoad() {
        //如果没有加载过就加载，否则就不再加载了
        if (!isLoadData) {
            //加载数据操作
            isLoadData = true;
        }
        initData();
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //第一个fragment会调用
        if (getUserVisibleHint())
            lazyLoad();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}

//public abstract class BaseFragment extends Fragment {
//
//    protected View mRootView;
//    public Context mContext;
//    protected boolean isVisible;
//    private boolean isPrepared;
//
//
//    @Override
//    public void setUserVisibleHint(boolean isVisibleToUser) {
//        super.setUserVisibleHint(isVisibleToUser);
//        if (getUserVisibleHint()) {
//            isVisible = true;
//            lazyLoad();
//        } else {
//            isVisible = false;
//            onInvisible();
//        }
//    }
//
//
//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        mContext = getActivity();
//        setHasOptionsMenu(true);
//    }
//
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        if (mRootView == null) {
//            mRootView = initView(inflater, container, savedInstanceState);
//        }
//        return mRootView;
//    }
//
//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//
//        isPrepared = true;
//        lazyLoad();
//    }
//
//    /**
//     * 懒加载
//     */
//    protected void lazyLoad() {
//        if (!isPrepared || !isVisible) {
//            return;
//        }
//
//        initData();
//    }
//
//    // 不可见
//    protected void onInvisible() {
//
//    }
//
//    public abstract View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);
//
//    public abstract void initData();
//}





