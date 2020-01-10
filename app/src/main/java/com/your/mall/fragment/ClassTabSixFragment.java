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
public class ClassTabSixFragment extends android.support.v4.app.Fragment {

    private GridView gv1, gv2, gv3, gv4;
    private CategroyAdapter myCategroyAdapter1, myCategroyAdapter2, myCategroyAdapter3, myCategroyAdapter4;
    private ArrayList<Categroy> list1, list2, list3, list4;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_class_tab_six, container, false);
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
        gv4 = (GridView) getView().findViewById(R.id.gv4);
        list1 = new ArrayList<Categroy>();
        list2 = new ArrayList<Categroy>();
        list3 = new ArrayList<Categroy>();
        list4 = new ArrayList<Categroy>();

        list1.add(new Categroy("542", "http://www.hnmall.com/images/f9/cc/3d/32d77ab4daf478cec5a00dfe41fc599cdf01d2a4.jpg", "雕塑工艺品"));
        list1.add(new Categroy("544", "http://www.hnmall.com/images/d5/14/39/10ecab64f4d63d7cba6e9753e40467757e90c97d.jpg", "瓷 窑"));
        list1.add(new Categroy("552", "http://www.hnmall.com/images/2c/7d/b1/70680dbc07ff97a5b8c5e02610cd5f7b2f9a5ccd.jpg", "炭雕工艺"));
        list1.add(new Categroy("554", "http://www.hnmall.com/images/24/6f/e9/f9b6a479688f3ba3864fd481cb25ba8558a211ec.jpg", "浏阳菊花石"));

        list2.add(new Categroy("546", "http://www.hnmall.com/images/d9/7a/17/9ba857bf9838bd4bb82ebf3b0417870cc9129c98.jpg", "民间特色"));
        list2.add(new Categroy("642", "http://www.hnmall.com/images/55/9b/3a/ee8a404081ebfefa9ee3b9d02e2ba1e8c5f85b0e.jpg", "文房精品"));
        list2.add(new Categroy("652", "http://www.hnmall.com/images/27/5f/09/1666790c5243268835967d34d9fb9484e4f8c58c.jpg", "家居生活"));

        list3.add(new Categroy("543", "http://www.hnmall.com/images/44/70/a0/894706faf2f72aa491093e744fcc6eef2fbdd79c.jpg", "刺 绣"));
        list3.add(new Categroy("654", "http://www.hnmall.com/images/84/35/bd/6245bbb7ed17137eceab8c63a0a70f1aa5c3006b.jpg", "服饰用品"));
        list3.add(new Categroy("655", "http://www.hnmall.com/images/47/80/ce/6d2d2b8a4ff02dca499b69b239e1671e20168fa4.png", "双面湘绣"));

        list4.add(new Categroy("748", "http://www.hnmall.com/images/05/39/75/383199171d5f593b9b7852b76c29567242612a8f.jpg", "礼券"));

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
        myCategroyAdapter4 = new CategroyAdapter(getActivity(), list4);
        gv4.setAdapter(myCategroyAdapter4);
        gv4.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), SearchResultActivity.class);
                intent.putExtra("cat_id", list4.get(position).getId());
                startActivity(intent);
            }
        });
    }
}
