package com.your.mall.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.your.mall.R;
import com.your.mall.adapter.ViewPagerAdapter;
import com.your.mall.application.MallApplication;
import com.your.mall.base.BaseActivity;
import com.your.mall.utils.SharedPreferencesUtil;

import java.util.ArrayList;

/**
 * Created by xieqinghua.
 * 创建时间：2016/8/30
 * 类描述：APP引导页面
 * 修改备注：
 */
public class GuideActivity extends BaseActivity implements View.OnClickListener {
    //定义viewpager
    private ViewPager mViewPager;

    private Button bt_Enter;

    //引导图片资源
    private static final int[] pics = {R.drawable.img_guide1, R.drawable.img_guide2, R.drawable.img_guide3};

    //定义一个list来存放view的集合
    private ArrayList<View> views;

    //记录当前选中位置
    private int currentIndex;

    //底部小点的图片
    private ImageView[] points;

    private ViewPagerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        views = new ArrayList<View>();
        mViewPager = (ViewPager) findViewById(R.id.vp_guide);
        bt_Enter = (Button) findViewById(R.id.bt_enter);
        //实例化Adapter
        mAdapter = new ViewPagerAdapter(views);
        initGuidePager();
    }

    /**
     * 引导页面
     */
    private void initGuidePager() {
        //定义一个布局并设置参数
        RelativeLayout.LayoutParams mParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT);

        //初始化引导页面的图片
        for (int i = 0; i < pics.length; i++) {
            ImageView iv = new ImageView(GuideActivity.this);
            iv.setLayoutParams(mParams);
            iv.setBackgroundResource(pics[i]);
            views.add(iv);
        }
        //设置数据
        mViewPager.setAdapter(mAdapter);
        //设置viewpager的动画效果，如果不需要可忽略
//        mViewPager.setPageTransformer(true, new DepthPageTransformer());


        //设置监听
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i2) {
                if (i == pics.length - 1) {
                    bt_Enter.setVisibility(View.VISIBLE);
                    bt_Enter.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //跳转到主页
                            Intent intent = new Intent(GuideActivity.this, MainActivity.class);
                            startActivity(intent);
                            //注意这里应该做清空操作。
                            SharedPreferencesUtil.saveData(MallApplication.getInstance().getContext(), "is_first", false);
                            finish();
                        }
                    });
                } else {
                    bt_Enter.setVisibility(View.GONE);
                }
            }

            /*页面选择的时候会调用*/
            @Override
            public void onPageSelected(int i) {

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

    }

    @Override
    public void onClick(View v) {
        int position = (Integer) v.getTag();
        setCurView(position);
    }


    /**
     * 设置当前页面的位置
     */
    private void setCurView(int position) {
        if (position < 0 || position >= pics.length) {
            return;
        }
        mViewPager.setCurrentItem(position);
    }
}
