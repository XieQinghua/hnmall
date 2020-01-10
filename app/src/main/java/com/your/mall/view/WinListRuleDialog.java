package com.your.mall.view;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Display;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.your.mall.R;


/**
 * Created by xieqinghua.
 * 创建时间：2016/11/24
 * 类描述：中奖页面半透明抽奖规则
 * 修改备注：
 */
public class WinListRuleDialog {
    private AlertDialog alertDialog;
    private TextView tv_time, str1, str2, str3, str4;
    private ImageView cancel;

    public WinListRuleDialog(final Context context) {
        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.setInverseBackgroundForced(true);
        alertDialog.show();

        Window window = alertDialog.getWindow();
        window.setBackgroundDrawable(new ColorDrawable(0xA0ffffff));
        window.clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        window.setGravity(Gravity.CENTER);

        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = (int) (display.getWidth());
        lp.height = (int) (display.getHeight());
        window.setAttributes(lp);

        window.setContentView(R.layout.dialog_winlist_rule);
        tv_time = (TextView) window.findViewById(R.id.tv_time);
        str1 = (TextView) window.findViewById(R.id.tv_str1);
        str2 = (TextView) window.findViewById(R.id.tv_str2);
        str3 = (TextView) window.findViewById(R.id.tv_str3);
        str4 = (TextView) window.findViewById(R.id.tv_str4);
        cancel = (ImageView) window.findViewById(R.id.cancel);
    }

    public AlertDialog getAlertDialog() {
        return alertDialog;
    }

    public void setAlertDialog(AlertDialog alertDialog) {
        this.alertDialog = alertDialog;
    }

    public TextView getTv_time() {
        return tv_time;
    }

    public void setTv_time(TextView tv_time) {
        this.tv_time = tv_time;
    }

    public TextView getStr1() {
        return str1;
    }

    public void setStr1(TextView str1) {
        this.str1 = str1;
    }

    public TextView getStr2() {
        return str2;
    }

    public void setStr2(TextView str2) {
        this.str2 = str2;
    }

    public TextView getStr3() {
        return str3;
    }

    public void setStr3(TextView str3) {
        this.str3 = str3;
    }

    public TextView getStr4() {
        return str4;
    }

    public void setStr4(TextView str4) {
        this.str4 = str4;
    }

    public ImageView getCancel() {
        return cancel;
    }

    public void setCancel(ImageView cancel) {
        this.cancel = cancel;
    }
}
