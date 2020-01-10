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
public class ClassTabThreeFragment extends android.support.v4.app.Fragment {

    private GridView gv1, gv2, gv3;
    private CategroyAdapter myCategroyAdapter1, myCategroyAdapter2, myCategroyAdapter3;
    private ArrayList<Categroy> list1, list2, list3;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_class_tab_three, container, false);
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

        list1.add(new Categroy("698", "http://www.hnmall.com/images/3d/fb/e9/a57273b80e3013c29f91a5c1264d9a5b9ff773f1.jpg", "进口牛排"));
        list1.add(new Categroy("701", "http://www.hnmall.com/images/a3/97/f0/48b63911a18fb92c0801997c2a44c69585957b42.jpg", "进口调味品"));

        list2.add(new Categroy("691", "http://www.hnmall.com/images/7c/9f/d8/b8bfa3153f381632057f47a86bf45d16d3341aad.jpg", "进口饼..."));
        list2.add(new Categroy("696", "http://www.hnmall.com/images/7b/b5/26/011b24cd12ce58066308a22e67cae01062f4a165.jpg", "进口巧克力"));
        list2.add(new Categroy("699", "http://www.hnmall.com/images/54/8a/70/df0491a86d544d916f0b4c04739e491566d04a0c.jpg", "进口糖果"));

        list3.add(new Categroy("692", "http://www.hnmall.com/images/0a/fb/e4/3e4e20086816e5e5432fe5f971d8d9cf565f46b8.png", "进口麦片"));
        list3.add(new Categroy("693", "http://www.hnmall.com/images/10/ca/b7/c4bd853a61b9bbf4a502a0d2dd07d82ccf668d8c.gif", "进口蜂..."));
        list3.add(new Categroy("695", "http://www.hnmall.com/images/35/fa/6e/7918b39181cb89c870c9e8b3ea0cb0ddbcd654a3.jpg", "进口奶"));
        list3.add(new Categroy("697", "http://www.hnmall.com/images/5c/35/5f/559ecb00782e2bdb8177689fc229944f242cd1a7.jpg", "进口酒"));

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
