package com.your.mall.receiver;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.your.mall.R;
import com.your.mall.utils.NetUtils;

public class NetReceiver extends BroadcastReceiver {
    private Toast toast;
    private boolean isConnected;

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        //注册广播
        context.registerReceiver(mHomeKeyEventReceiver, new IntentFilter(
                Intent.ACTION_CLOSE_SYSTEM_DIALOGS));
        if (ConnectivityManager.CONNECTIVITY_ACTION.equals(action)) {
            isConnected = NetUtils.isNetworkConnected(context);
            System.out.println("网络状态：" + isConnected);
            System.out.println("wifi状态：" + NetUtils.isWifiConnected(context));
            System.out.println("移动网络状态：" + NetUtils.isMobileConnected(context));
            System.out.println("网络连接类型：" + NetUtils.getConnectedType(context));
            if (isConnected) {
            } else {
                LayoutInflater inflater = LayoutInflater.from(context);
                View layout = inflater.inflate(R.layout.tiast_layout, null);
                toast = new Toast(context);
                toast.setGravity(Gravity.FILL_HORIZONTAL | Gravity.TOP, 0, 100);
                toast.setDuration(Toast.LENGTH_LONG);
                toast.setView(layout);
                toast.show();
            }
        }
    }

    public static void showNoNetWorkDlg(final Context context) {
        View dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_update, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(dialogView);
        final AlertDialog dialog = builder.show();
        //点击屏幕外侧，dialog不消失
        dialog.setCanceledOnTouchOutside(false);
        dialogView.findViewById(R.id.negativeButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialogView.findViewById(R.id.positiveButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 跳转到系统的网络设置界面
                Intent intent = null;
                // 先判断当前系统版本
                if (android.os.Build.VERSION.SDK_INT > 10) {  // 3.0以上
                    intent = new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS);
                } else {
                    intent = new Intent();
                    intent.setClassName("com.android.settings", "com.android.settings.WirelessSettings");
                }
                context.startActivity(intent);
                dialog.dismiss();
            }
        });
    }

    /**
     * 监听是否点击了home键将客户端推到后台
     */
    private BroadcastReceiver mHomeKeyEventReceiver = new BroadcastReceiver() {
        String SYSTEM_REASON = "reason";
        String SYSTEM_HOME_KEY = "homekey";
        String SYSTEM_HOME_KEY_LONG = "recentapps";

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(Intent.ACTION_CLOSE_SYSTEM_DIALOGS)) {
                String reason = intent.getStringExtra(SYSTEM_REASON);
                if (TextUtils.equals(reason, SYSTEM_HOME_KEY)) {
                    //表示按了home键,程序到了后台
                    if (isConnected) {
                    } else {
                        toast.cancel();
                    }
                } else if (TextUtils.equals(reason, SYSTEM_HOME_KEY_LONG)) {
                    //表示长按home键,显示最近使用的程序列表
                }
            }
        }
    };
}
