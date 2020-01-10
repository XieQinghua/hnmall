package com.your.mall.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.your.mall.R;
import com.your.mall.base.BaseSwipeBackActivity;
import com.your.mall.fragment.ShoppingFragment;
import com.your.mall.utils.IBtnCallListener;

/**
 * Created by xieqinghua.
 * 创建时间：2016/9/2
 * 类描述：购物车的Acitivity页面，从商品详情跳转
 * 修改备注：
 */
public class ShoppingCartActivity extends BaseSwipeBackActivity implements IBtnCallListener {
    private LinearLayout content;
    private FragmentTransaction ft;
    private ShoppingFragment myShoppingFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        init();
    }

    private void init() {
        content = (LinearLayout) findViewById(R.id.content);
        myShoppingFragment = new ShoppingFragment();
        ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.content, myShoppingFragment);
//        ft.addToBackStack(String.valueOf(myShoppingFragment));
        ft.commit();
        ft.show(myShoppingFragment);
    }

    @Override
    public void showHome() {
        //显示首页操作
        Intent intent = new Intent(ShoppingCartActivity.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.putExtra("tag", "activity");
        startActivity(intent);
    }

    @Override
    public void showClass() {

    }

    @Override
    public void showShoppingCart() {

    }

    @Override
    public void changeCartNum() {

    }
}
