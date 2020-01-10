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
public class ClassTabFiveFragment extends android.support.v4.app.Fragment {

    private GridView gv1, gv2, gv3;
    private CategroyAdapter myCategroyAdapter1, myCategroyAdapter2, myCategroyAdapter3;
    private ArrayList<Categroy> list1, list2, list3;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_class_tab_five, container, false);
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

        list1.add(new Categroy("536", "http://www.hnmall.com/images/56/93/2c/36d69c947ebd9e9e821245fb156580b339921396.jpg", "白酒"));
        list1.add(new Categroy("537", "http://www.hnmall.com/images/fb/11/4f/a80bcd5a340ca3a45fad80d516cfcb129467451e.jpg", "洋酒"));
        list1.add(new Categroy("538", "http://www.hnmall.com/images/0b/eb/d5/d1ed8b226b202e964fcb9ee5f322bd105c9983f1.jpg", "红酒"));
        list1.add(new Categroy("539", "http://www.hnmall.com/images/a1/09/69/189a60729b069a4d8e0d8a9ffee6973b4d8f8ff9.jpg", "果酒"));
        list1.add(new Categroy("540", "http://www.hnmall.com/images/e5/2a/24/098b24234c7dcf936cbfadf19826ad1b15709db1.jpg", "保健酒"));
        list1.add(new Categroy("640", "http://www.hnmall.com/images/4a/5f/9e/97f8e96ece6900310bd3e6257db15a188310f088.jpg", "啤酒"));
        list1.add(new Categroy("643", "http://www.hnmall.com/images/b3/80/8b/d7d1a7d7a83911c428cda7f450043a115c6c5957.jpg", "起泡酒"));
        list1.add(new Categroy("650", "http://www.hnmall.com/images/af/9f/b3/79cce26512bbfb72483cb7e839a571f33551d15d.jpg", "清酒"));
        list1.add(new Categroy("656", "http://www.hnmall.com/images/ee/f3/41/f7eb8344a8d8f6e8a9bd0c38f2506f55c641e220.jpg", "预调酒"));

        list2.add(new Categroy("530", "http://www.hnmall.com/images/bb/f6/69/37e51f584b7ae0b085eb730b0f430066aca2c79e.jpg", "白茶"));
        list2.add(new Categroy("531", "http://www.hnmall.com/images/4f/58/03/909195a59f6c86ad00703af8f45be91717255f16.jpg", "绿茶"));
        list2.add(new Categroy("532", "http://www.hnmall.com/images/a2/fa/db/0ba1cb9dad8003f54517547fd02c4099978b3a62.jpg", "黑茶"));
        list2.add(new Categroy("533", "http://www.hnmall.com/images/86/ad/0e/b81e3fc1c7cac96aac11cc3158f6d50b3e777ccd.jpg", "黄茶"));
        list2.add(new Categroy("534", "http://www.hnmall.com/images/3a/c9/6f/cdaf38e2cb28d32ed9f7401919d3fbe614af529c.jpg", "红茶"));
        list2.add(new Categroy("535", "http://www.hnmall.com/images/3c/89/d9/c37d0c66e7432e3632340c30a3cdd912f702df74.jpg", "保健茶"));
        list2.add(new Categroy("657", "http://www.hnmall.com/images/8d/36/ff/4f23e89bc3576131b125e20275f19120b8e4f2e1.jpg", "乌龙茶"));
        list2.add(new Categroy("658", "http://www.hnmall.com/images/11/f4/72/3748c4fbda1a8ef684481d85065883e16a10b6de.jpg", "花草茶"));
        list2.add(new Categroy("659", "http://www.hnmall.com/images/64/56/a3/6aa2e24571f1e7a698797d6e709e06d3b9ef43a1.jpg", "袋泡茶"));

        list3.add(new Categroy("661", "http://www.hnmall.com/images/2a/de/76/0630370f3fb0059a9438ac9ae6ac71d7aa65fff2.jpg", "怡清源"));
        list3.add(new Categroy("662", "http://www.hnmall.com/images/18/8b/7a/95c230a775f4dca9b11d6fdd24396a9f85c6cfda.jpg", "君山"));
        list3.add(new Categroy("663", "http://www.hnmall.com/images/64/3b/96/7e4ec86f59b7f15913ce6b9d4f4cae67dbdd69d6.jpg", "领峰山"));
        list3.add(new Categroy("664", "http://www.hnmall.com/images/72/36/da/66a76dbfaa5633137092a41c3e0bf42ccb86b5c9.jpg", "对白茶舍"));
        list3.add(new Categroy("665", "http://www.hnmall.com/images/7c/6c/0d/947ad6aca322536312be1a1efff7e11f4c7169c6.jpg", "古洞春"));
        list3.add(new Categroy("666", "http://www.hnmall.com/images/cf/b8/a4/2b3c0907da9b301eee9188300e658f71d0f2f504.jpg", "拉菲"));
        list3.add(new Categroy("667", "http://www.hnmall.com/images/06/1d/f3/b83fa38001cf93669c5b384614119d95dfbb5b22.jpg", "纳新"));
        list3.add(new Categroy("668", "http://www.hnmall.com/images/d5/02/85/f69dc22c6ddbf39823b3ce7d3e88cc3e4b5cfd56.jpg", "嘉升名酒"));
        list3.add(new Categroy("669", "http://www.hnmall.com/images/0f/e3/26/d72dff47061a45f29a19bd9a47e70e34c3079c9d.jpg", "隆平酒"));

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
