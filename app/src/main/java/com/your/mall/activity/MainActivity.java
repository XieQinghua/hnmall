package com.your.mall.activity;

import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.your.mall.R;
import com.your.mall.adapter.FragmentTabAdapter;
import com.your.mall.application.MallApplication;
import com.your.mall.base.BaseActivity;
import com.your.mall.fragment.CenterFragment;
import com.your.mall.fragment.ClassFragment;
import com.your.mall.fragment.HomeFragment;
import com.your.mall.fragment.ShoppingFragment;
import com.your.mall.receiver.NetReceiver;
import com.your.mall.utils.IBtnCallListener;

import java.util.ArrayList;


/**
 * Created by xieqinghua.
 * 创建时间：2016/8/30
 * 类描述：主页面
 * 修改备注：
 */
public class MainActivity extends BaseActivity implements IBtnCallListener {

    private ArrayList<Fragment> fragments;
    public FragmentTransaction ft;
    private RadioGroup myRadioGroup;
    private RadioButton rb_home, rb_class, rb_shopping, rb_center;
    private TextView tv_cart_num;
    NetReceiver mReceiver = new NetReceiver();
    IntentFilter mFilter = new IntentFilter();

    private static MainActivity mMainActivity = null;//MainActivity不会被系统回收

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mMainActivity = MainActivity.this;
        mFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
//        this.registerReceiver(mReceiver, mFilter);
        init();

        new FragmentTabAdapter(mMainActivity, getSupportFragmentManager(), fragments, R.id.content, myRadioGroup);
        // 默认显示第一页
        ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.content, fragments.get(0));
        ft.addToBackStack(fragments.get(0).getClass().getName());
        ft.commit();

        //如果是从别的Activity跳转回来，则显示第一页
        if (getIntent().getStringExtra("tag") != null && getIntent().getStringExtra("tag").equals("activity")) {
            showHome();
        }

    }

    /**
     * 初始化控件
     */
    private void init() {
        myRadioGroup = (RadioGroup) findViewById(R.id.main_radiogroup);
        rb_home = (RadioButton) findViewById(R.id.maintabs_radio_home);
        rb_class = (RadioButton) findViewById(R.id.maintabs_radio_class);
        rb_shopping = (RadioButton) findViewById(R.id.maintabs_radio_shopping);
        rb_center = (RadioButton) findViewById(R.id.maintabs_radio_center);
        tv_cart_num = (TextView) findViewById(R.id.tv_cart_num);
        tv_cart_num.setText(MallApplication.getInstance().getCartNum(this));
        fragments = new ArrayList<Fragment>();
        fragments.add(new HomeFragment());
        fragments.add(new ClassFragment());
        fragments.add(new ShoppingFragment());
        fragments.add(new CenterFragment());
    }


    @Override
    protected void onResume() {
        tv_cart_num.setText(MallApplication.getInstance().getCartNum(this));
        super.onResume();

    }


    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy() {
//        this.unregisterReceiver(mReceiver);
        super.onDestroy();
        mMainActivity = null;
    }

    /**
     * 显示首页
     */
    @Override
    public void showHome() {
        for (int i = 0; i < fragments.size(); i++) {
            Fragment fragment = fragments.get(i);
            if (i == 0) {
                ft.show(fragment);
            } else {
                ft.hide(fragment);
            }
        }
        // 显示第一页
        rb_home.setChecked(true);
    }

    /**
     * 显示分类
     */
    @Override
    public void showClass() {
        for (int i = 0; i < fragments.size(); i++) {
            Fragment fragment = fragments.get(i);
            if (i == 1) {
                ft.show(fragment);
            } else {
                ft.hide(fragment);
            }
        }
        // 显示第二页
        rb_class.setChecked(true);
    }

    /**
     * 显示购物车
     */
    @Override
    public void showShoppingCart() {
        for (int i = 0; i < fragments.size(); i++) {
            Fragment fragment = fragments.get(i);
            if (i == 2) {
                ft.show(fragment);
            } else {
                ft.hide(fragment);
            }
        }
        //显示第三页
        rb_shopping.setChecked(true);
    }

    @Override
    public void changeCartNum() {
        tv_cart_num.setText(MallApplication.getInstance().getCartNum(this));
    }

    /**
     * 主页监听按两次返回键退出
     *
     * @param keyCode
     * @param event
     * @return
     */
    private long mExitTime;

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                mExitTime = System.currentTimeMillis();
            } else {
                finishAll();
                //清除Cookie
                MallApplication.getInstance().clearCookie(this);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
