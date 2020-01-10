package com.your.mall.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.your.mall.R;
import com.your.mall.base.BaseActivity;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by xieqinghua.
 * 创建时间：2016/9/5
 * 类描述：搜索页面
 * 修改备注：
 */
public class SearchActivity extends BaseActivity implements View.OnClickListener {
    private EditText et_search;
    private ImageView iv_empty;
    private TextView tv_cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        init();
    }

    private void init() {
        et_search = (EditText) findViewById(R.id.et_search);
        //获取编辑框焦点
        et_search.setFocusable(true);
        et_search.setFocusableInTouchMode(true);
        et_search.requestFocus();
        //打开软键盘
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
                           public void run() {
                               InputMethodManager inputManager =
                                       (InputMethodManager) et_search.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                               inputManager.showSoftInput(et_search, 0);
                           }
                       },
                100);

        iv_empty = (ImageView) findViewById(R.id.iv_empty);
        tv_cancel = (TextView) findViewById(R.id.tv_cancel);
        iv_empty.setOnClickListener(this);
        tv_cancel.setOnClickListener(this);
        //监听EditText的文字变化
        clearText(et_search, iv_empty);
        //监听键盘搜索按键
        et_search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEND || (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN)) {
                    // 先隐藏键盘
                    ((InputMethodManager) et_search.getContext().getSystemService(Context.INPUT_METHOD_SERVICE))
                            .hideSoftInputFromWindow(SearchActivity.this
                                            .getCurrentFocus()
                                            .getWindowToken(),
                                    InputMethodManager.HIDE_NOT_ALWAYS);
                    //跳转搜索结果页面
                    Intent intent = new Intent(SearchActivity.this, SearchResultActivity.class);
                    if (!et_search.getText().toString().trim().equals("")) {
                        intent.putExtra("search_keywords", et_search.getText().toString().trim());
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(SearchActivity.this, "请输入搜索内容", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_empty:
                //清空et_search
                et_search.setText("");
                break;
            case R.id.tv_cancel:
                this.finish();
                break;
        }
    }
}
