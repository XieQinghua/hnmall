package com.your.mall.base;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.your.mall.view.CustomProgressDialog;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


/**
 * Created by xieqinghua.
 * 创建时间：2016/8/30
 * 类描述：Activity基类
 * 修改备注：
 */
public class BaseActivity extends FragmentActivity {
    /**
     * 获取到前台进程的Activity  就是当前在运行的activity
     */
    private static Activity mForegroundActivity = null;
    /**
     * 用户保存所有用户操作过的activity
     */
    private static List<BaseActivity> mActivities = new LinkedList<BaseActivity>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        synchronized (mActivities) {
            mActivities.add(this);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        this.mForegroundActivity = null;
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mActivities.remove(this);
    }

    /**
     * Activity的返回操作调用此方法
     *
     * @param view
     */
    public void back(View view) {
        finish();
    }

    /**
     * 获得当前Activity
     *
     * @return
     */
    public static Activity getForegroundActivity() {
        return mForegroundActivity;
    }

    /**
     * 关闭所有Activity，除了参数传递的Activity
     */
    public static void finishAll(BaseActivity except) {
        List<BaseActivity> copy;
        synchronized (mActivities) {
            copy = new ArrayList<BaseActivity>(mActivities);
        }
        for (BaseActivity activity : copy) {
            if (activity != except) {
                activity.finish();
            }
        }
    }

    /**
     * 封装监听EditText的文字变化及清空键
     *
     * @param editText
     * @param imageView
     */
    public void clearText(final EditText editText, final ImageView imageView) {

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                imageView.setVisibility(View.VISIBLE);
                imageView.setClickable(true);
                if (editText.getText().toString().trim().equals("")) {
                    imageView.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        editText.setOnFocusChangeListener(new android.view.View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    // 此处为得到焦点时的处理内容
                } else {
                    // 此处为失去焦点时的处理内容
                    imageView.setVisibility(View.INVISIBLE);
                }
            }
        });
    }

    /**
     * 关闭所有Activity
     */
    public static void finishAll() {
        List<BaseActivity> copy;
        synchronized (mActivities) {
            copy = new ArrayList<BaseActivity>(mActivities);
        }
        for (BaseActivity activity : copy) {
            activity.finish();
        }
    }

    private CustomProgressDialog pd;
    public static final int LOADING_DIALOG = 0;
    public static final int SUBMIT_DIALOG = 1;

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case LOADING_DIALOG:
                pd = CustomProgressDialog.createDialog(this);
                pd.setCanceledOnTouchOutside(false);
                pd.show();
                pd.setCancelable(true);
                return pd;
            case SUBMIT_DIALOG:
                pd = CustomProgressDialog.createDialog(this);
                pd.show();
                pd.setCancelable(true);
                return pd;
        }
        return null;
    }

    public void removeDialog() {
        if (pd != null && pd.isShowing()) {
            pd.cancel();
        }
    }
}
