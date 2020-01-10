package com.your.mall.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.your.mall.R;
import com.your.mall.adapter.WinPeopleAdapter;
import com.your.mall.bean.PeopleListBean;

import java.util.ArrayList;

/**
 * Created by xieqinghua.
 * 创建时间：2016/11/24
 * 类描述：拼团抽奖中奖人Fragment
 * 修改备注：
 */
public class WinPeopleFragment extends Fragment {
    private ListView lv_win_people;
    private Context context;
    private ArrayList<PeopleListBean> list;
    private WinPeopleAdapter myWinPeopleAdapter;

//    public WinPeopleFragment(ArrayList<PeopleListBean> list) {
//        this.list = list;
//    }

    public WinPeopleFragment() {

    }

    public static WinPeopleFragment getInstance(ArrayList<PeopleListBean> list) {
        WinPeopleFragment fragment = new WinPeopleFragment();
        Bundle argument = new Bundle();
        argument.putSerializable("list", list);
        fragment.setArguments(argument);
        return fragment;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle argument = getArguments();
        if (argument != null) {
            this.list = (ArrayList<PeopleListBean>) argument.getSerializable("list");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pintuancj_win_people, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        lv_win_people = (ListView) getView().findViewById(R.id.lv_win_people);
        myWinPeopleAdapter = new WinPeopleAdapter(getActivity(), list);
        lv_win_people.setAdapter(myWinPeopleAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}

