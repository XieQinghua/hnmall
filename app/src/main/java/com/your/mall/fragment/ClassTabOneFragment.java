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
public class ClassTabOneFragment extends android.support.v4.app.Fragment {

    private GridView gv1, gv2, gv3;
    private CategroyAdapter myCategroyAdapter1, myCategroyAdapter2, myCategroyAdapter3;
    private ArrayList<Categroy> list1, list2, list3;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_class_tab_one, container, false);
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
        list1.add(new Categroy("720", "http://www.hnmall.com/images/09/56/2d/e41dfd72df98c5c0dcb06b3fde07f5a405650643.png", "泰国"));
        list1.add(new Categroy("721", "http://www.hnmall.com/images/d8/a6/84/e49cb91ad5fa5b0aabc2d08dc92f2781ad078f05.jpg", "美国"));
        list1.add(new Categroy("722", "http://www.hnmall.com/images/33/df/0e/2669cee48b85b7b56fb550405bf4ed1b3fa153ff.jpg", "新西兰"));
        list1.add(new Categroy("723", "http://www.hnmall.com/images/8f/06/ac/eeaa52072df4f0da23874a75e69666cc0f0d49a0.jpg", "菲律宾"));
        list1.add(new Categroy("724", "http://www.hnmall.com/images/41/2c/0f/063740f171ee9223b2cf2d9309ae6922abadab95.jpg", "巴西"));
        list1.add(new Categroy("725", "http://www.hnmall.com/images/40/37/5c/0c4df2ebbca68b416c0ee59450d199b14840389a.jpg", "南非"));
        list1.add(new Categroy("726", "http://www.hnmall.com/images/19/a2/b0/d6bdc06a33ef955d431c7d57ddfd1ccf3371a484.jpg", "墨西哥"));
        list1.add(new Categroy("727", "http://www.hnmall.com/images/94/c8/f6/47122ff0e8007e626029ab2cc5df4e5035c1d833.jpg", "越南"));
        list1.add(new Categroy("745", "http://www.hnmall.com/images/9f/3e/a0/c6d6e02cc797286682f835f0ceefb0a95aa139a9.jpg", "智利"));
        list1.add(new Categroy("750", "http://www.hnmall.com/images/e6/92/40/ab04d7d96f00d97fd837df2d3575ce46f2e13a8c.jpg", "澳大利亚"));

        list2.add(new Categroy("728", "http://www.hnmall.com/images/e5/fb/a6/513680143e4241d8a420f782a93a2081806cfad0.jpg", "苹果"));
        list2.add(new Categroy("729", "http://www.hnmall.com/images/4b/5e/75/4809fe9503da7986fbcf2a748e26168f880bd3d6.png", "柠檬"));
        list2.add(new Categroy("730", "http://www.hnmall.com/images/ad/ac/44/0c3ad9298342474ea46479e920cc7200260db4a2.jpg", "火龙果"));
        list2.add(new Categroy("731", "http://www.hnmall.com/images/70/16/be/acc929468e7b5d117eb026ddb67b0956f982b015.jpg", "提子"));
        list2.add(new Categroy("732", "http://www.hnmall.com/images/71/af/1a/70d20e477ad38cf3d7b68ab393289d5097b87770.jpg", "奇异果"));
        list2.add(new Categroy("733", "http://www.hnmall.com/images/40/1d/45/f46792a41b6c9ffaf9d86eaf5a56875d95014bda.png", "木瓜"));
        list2.add(new Categroy("734", "http://www.hnmall.com/images/e8/c5/e8/f382386e20cb504a0781bf18a286046ce883e761.png", "蜜瓜"));
        list2.add(new Categroy("735", "http://www.hnmall.com/images/b0/fe/0e/d8683cf45e7cbe19a98d595046fdc2710139715f.jpg", "梨子&枣子"));
        list2.add(new Categroy("736", "http://www.hnmall.com/images/a2/05/9c/852de317313bf6211841c4710709c2cac8e2bcff.jpg", "柚子"));
        list2.add(new Categroy("737", "http://www.hnmall.com/images/e5/69/96/d91694f75c5d64a9f93080a0dea2ac48e3ed5312.png", "菠萝"));
        list2.add(new Categroy("738", "http://www.hnmall.com/images/a2/50/cd/c166882dde2ba9266e7f3cfc454fca38afbdae4b.png", "柑橙"));
        list2.add(new Categroy("739", "http://www.hnmall.com/images/90/6b/88/ab7adf171eafcad2b913c8269c367e544e659d3e.jpg", "桂圆"));

        list3.add(new Categroy("583", "http://www.hnmall.com/images/c7/07/ca/49e5abb042d69a915038d4ec00cd16e321eb374b.jpg", "水果礼盒"));
        list3.add(new Categroy("651", "http://www.hnmall.com/images/db/3b/5a/51dd580545c3613140f6d691ae20b70565749224.jpg", "may鲜花"));
        list3.add(new Categroy("741", "http://www.hnmall.com/images/99/34/84/241ea34b9e2be4cb5bf8d8263dd45a8bb0c7460e.jpg", "友阿坚果"));

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
