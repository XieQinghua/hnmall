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
public class ClassTabFourFragment extends android.support.v4.app.Fragment {
    private GridView gv1, gv2, gv3;
    private CategroyAdapter myCategroyAdapter1, myCategroyAdapter2, myCategroyAdapter3;
    private ArrayList<Categroy> list1, list2, list3;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_class_tab_four, container, false);
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

        list1.add(new Categroy("555", "http://www.hnmall.com/images/eb/0c/c1/cfb85fde098564e78fe553b4693025a153d7986c.jpg", "食用油"));
        list1.add(new Categroy("562", "http://www.hnmall.com/images/3c/0f/84/49505feb90ad8d263c15c74e67eabdfac027a4cf.jpg", "调味品"));
        list1.add(new Categroy("581", "http://www.hnmall.com/images/52/ff/2b/65e9a3f75a887435afb5bbff8572f8ba2598fd32.jpg", "米/杂粮"));
        list1.add(new Categroy("670", "http://www.hnmall.com/images/16/12/4a/ef3987049a52d3bb1c78506e681809bf550ce510.jpg", "面条/面粉"));
        list1.add(new Categroy("672", "http://www.hnmall.com/images/20/40/1d/692e72c5f2dcda6175373e02d648a37aa7b15b47.jpg", "推荐品牌"));

        list2.add(new Categroy("603", "http://www.hnmall.com/images/1c/d2/02/b42ad1e38719b8144bca8fb922f6e18facd92cd2.jpg", "豆制品类"));
        list2.add(new Categroy("604", "http://www.hnmall.com/images/c2/4f/b8/e35fc5a7af4512ed8e3c888ff9cc5a80d6b86077.jpg", "水产类干货"));
        list2.add(new Categroy("605", "http://www.hnmall.com/images/a3/0c/18/ac6ebbfbc0459c4b2153ec9415923071c7d57326.jpg", "菌菇类干货"));
        list2.add(new Categroy("606", "http://www.hnmall.com/images/8a/cc/5a/fe883830e7a77cc0b24d8ec8d561688198ca6502.jpg", "桂圆/莲子"));
        list2.add(new Categroy("607", "http://www.hnmall.com/images/ab/0c/2c/209b7c3938e3b683e532d9ace401a70be81710a5.jpg", "干货礼盒"));
        list2.add(new Categroy("682", "http://www.hnmall.com/images/75/f2/dc/f30dad8a94846e1f48ef5da705c8db2e6bbe9184.jpg", "蔬菜类干货"));
        list2.add(new Categroy("683", "http://www.hnmall.com/images/eb/92/c3/a224e74a84e0fb3268638bdbf4d6db580d1c8897.jpg", "药材类干货"));

        list3.add(new Categroy("575", "http://www.hnmall.com/images/54/83/58/889c8a6e8c4120e8684ee87fdc4242c6d9bd8422.jpg", "罐头"));
        list3.add(new Categroy("598", "http://www.hnmall.com/images/78/0b/76/b1d6568df73dda4a7edf9753bee26b49bb203720.png", "时蔬素食"));
        list3.add(new Categroy("600", "http://www.hnmall.com/images/82/f0/61/6bb00920a5389c36105170b1ed2a66ef52c97538.jpg", "皮蛋/咸蛋"));
        list3.add(new Categroy("614", "http://www.hnmall.com/images/ef/c6/1a/f70fc56b4ee8c17acf30b39c81836ce9e2e82f45.jpg", "湘厨湘味"));
        list3.add(new Categroy("615", "http://www.hnmall.com/images/1f/2d/43/5dee4d093af0970fea705a628b97876067fd34e4.jpg", "禽肉/蛋"));
        list3.add(new Categroy("617", "http://www.hnmall.com/images/7f/3b/48/b6602df088ac5dd6e4949405e98bbf5084ed6bbc.jpg", "腊肠/腊肉"));
        list3.add(new Categroy("681", "http://www.hnmall.com/images/fa/78/24/68a42b115602a2042ec137f4bb83d6467107f425.jpg", "海鲜水产"));
        list3.add(new Categroy("686", "http://www.hnmall.com/images/5e/e2/ca/a6062b5554cac8e01d2fc5925d91b03a2a4ffe30.jpg", "酱板鸭/鱼"));
        list3.add(new Categroy("705", "http://www.hnmall.com/images/34/a5/70/503a89c06a3428c605f3d3367973cf56114ee7bc.jpg", "鲜猪肉"));
        list3.add(new Categroy("746", "http://www.hnmall.com/images/44/92/8b/02d380f74530849176d83260b3b88917e8b081f2.jpg", "下饭菜"));
        list3.add(new Categroy("749", "http://www.hnmall.com/images/ea/1c/b2/5927ebd51e0689ff2191b59003704b83f6918da9.jpg", "卤味"));

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
