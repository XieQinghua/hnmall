package com.your.mall.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.your.mall.R;
import com.your.mall.activity.SearchResultActivity;
import com.your.mall.adapter.CategroyAdapter;
import com.your.mall.bean.Categroy;

import java.util.ArrayList;

/**
 * Created by xieqinghua.
 * 创建时间：2016/9/28
 * 类描述：
 * 修改备注：
 */
public class ClassTabTwoFragment extends android.support.v4.app.Fragment {

    private GridView gv1, gv2, gv3;
    private CategroyAdapter myCategroyAdapter1, myCategroyAdapter2, myCategroyAdapter3;
    private ArrayList<Categroy> list1, list2, list3;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_class_tab_two, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
    }

    private void init() {
        gv1 = (GridView) getView().findViewById(R.id.gv1);
        gv2 = (GridView) getView().findViewById(R.id.gv2);
        gv3 = (GridView) getView().findViewById(R.id.gv3);
        list1 = new ArrayList<Categroy>();
        list2 = new ArrayList<Categroy>();
        list3 = new ArrayList<Categroy>();

        list1.add(new Categroy("547", "http://www.hnmall.com/images/c8/a0/f7/b3b8a847d0b0dff14321863d8585dff0c31c048e.jpg", "饼干糕点"));
        list1.add(new Categroy("550", "http://www.hnmall.com/images/c5/36/b8/1b157df1aaf8554ce09c3453fa44fff6b58bb8d3.jpg", "坚果/炒货"));
        list1.add(new Categroy("553", "http://www.hnmall.com/images/fd/42/79/f061b1d64fd021585a2718a6abcb834990c061f9.jpg", "果脯/果干"));
        list1.add(new Categroy("556", "http://www.hnmall.com/images/39/db/86/ec6fe312968983b41b680c53624b9a21b2fb7945.jpg", "香辣零食"));
        list1.add(new Categroy("630", "http://www.hnmall.com/images/03/0a/22/54191fcdc86858d0725d3c2aaf2133009b6a97ef.png", "休闲/膨化"));
        list1.add(new Categroy("634", "http://www.hnmall.com/images/f1/e2/11/54d8da1c700cc1c22384f1186e0903c5ca8d8baf.jpg", "方便速..."));
        list1.add(new Categroy("636", "http://www.hnmall.com/images/9f/ba/ac/54222c3b0bd4714d92a2669ec9799e4b70f6af74.jpg", "槟榔/鱿鱼丝"));
        list1.add(new Categroy("641", "http://www.hnmall.com/images/4a/2a/98/472c5f74e207bd26f66e0bae21f43cb829990e11.jpg", "糖果/巧克力"));

        list2.add(new Categroy("549", "http://www.hnmall.com/images/38/dd/4a/3d1eabe9c3ee1f178c06b53081003c32a37bdf0e.jpg", "碳酸饮料"));
        list2.add(new Categroy("566", "http://www.hnmall.com/images/6e/6d/b3/540420508df719bc65db3d7e065549efa5df5a12.jpg", "果味饮料"));
        list2.add(new Categroy("568", "http://www.hnmall.com/images/4d/26/4e/5922d3a838ab0ceb8bae7f02e9947469e01466fc.jpg", "罐头"));
        list2.add(new Categroy("570", "http://www.hnmall.com/images/c6/bc/46/955fb034e98940756c1d740b656d9f25b6d832f5.png", "冲调食品"));
        list2.add(new Categroy("626", "http://www.hnmall.com/images/6b/42/98/1ef32c4957983f440bd3c7cee8331c0622f3620d.jpg", "奶制品/豆奶"));
        list2.add(new Categroy("633", "http://www.hnmall.com/images/f7/6b/35/35ff7038537eaa356a5855ec8c60788f87b8ad39.jpg", "矿泉水"));
        list2.add(new Categroy("635", "http://www.hnmall.com/images/c3/bd/33/1d9272c0614c6248d0cf6687e93a917c001134d0.jpg", "茶类饮料"));
        list2.add(new Categroy("637", "http://www.hnmall.com/images/84/15/aa/ef6dfb05b0ef82bcc8c95298404b87b9e1202fe9.jpg", "功能饮料"));

        list3.add(new Categroy("585", "http://www.hnmall.com/images/26/a6/51/f14871a150363a1ab103652b867559a87f3bb736.jpg", "滋补品"));
        list3.add(new Categroy("631", "http://www.hnmall.com/images/99/39/52/ac7947b20020fed208e7a7c0ca794e5b730a2dde.jpg", "铁皮石斛"));
        list3.add(new Categroy("690", "http://www.hnmall.com/images/92/48/56/5d127766252ca29b1c30033dd445457b6bdb76fc.jpg", "阿胶糕"));
        list3.add(new Categroy("742", "http://www.hnmall.com/images/92/86/02/f4ae7689d730d2025ffce7855b386de579195129.jpg", "年货礼盒"));

        myCategroyAdapter1 = new CategroyAdapter(getActivity(), list1);
        gv1.setAdapter(myCategroyAdapter1);
        gv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), SearchResultActivity.class);
                intent.putExtra("cat_id", list1.get(position).getId());
                startActivity(intent);
            }
        });
        myCategroyAdapter2 = new CategroyAdapter(getActivity(), list2);
        gv2.setAdapter(myCategroyAdapter2);
        gv2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), SearchResultActivity.class);
                intent.putExtra("cat_id", list2.get(position).getId());
                startActivity(intent);
            }
        });
        myCategroyAdapter3 = new CategroyAdapter(getActivity(), list3);
        gv3.setAdapter(myCategroyAdapter3);
        gv3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), SearchResultActivity.class);
                intent.putExtra("cat_id", list3.get(position).getId());
                startActivity(intent);
            }
        });
    }
}
