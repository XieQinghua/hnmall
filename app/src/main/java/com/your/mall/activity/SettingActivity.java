package com.your.mall.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.your.mall.R;
import com.your.mall.application.MallApplication;
import com.your.mall.base.BaseSwipeBackActivity;
import com.your.mall.bean.LoginTepinBean;
import com.your.mall.http.MallApi;
import com.your.mall.utils.JsonValidator;
import com.your.mall.utils.MyCallBack;
import com.your.mall.utils.ParseUtils;
import com.your.mall.utils.SharedPreferencesUtil;
import com.your.mall.utils.XUtils;
import com.your.mall.view.CustomDialog;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xieqinghua.
 * 创建时间：2016/9/2
 * 类描述：设置页面
 * 修改备注：
 */
public class SettingActivity extends BaseSwipeBackActivity implements View.OnClickListener {
    private TextView tv_title;
    private TextView tv_cache;
    private TextView tv_about;
    private TextView tv_telephone;
    private Button btn_sign_out;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        tv_title = (TextView) findViewById(R.id.tv_title_center);
        tv_title.setText("更多");
        tv_cache = (TextView) findViewById(R.id.tv_cache);
        tv_about = (TextView) findViewById(R.id.tv_about);
        tv_telephone = (TextView) findViewById(R.id.tv_telephone);
        btn_sign_out = (Button) findViewById(R.id.btn_sign_out);

        if (!MallApplication.getInstance().getAutoLogin(SettingActivity.this)) {//未登录状态下，退出登录按键隐藏
            btn_sign_out.setVisibility(View.GONE);
        }

        tv_cache.setOnClickListener(this);
        tv_about.setOnClickListener(this);
        tv_telephone.setOnClickListener(this);
        btn_sign_out.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_cache:
                showCacheAlertDialog();
                break;
            case R.id.tv_about:
                startActivity(new Intent(SettingActivity.this, AboutUsActivity.class));
                break;
            case R.id.tv_telephone:
                showTelephoneAlertDialog();
                break;
            case R.id.btn_sign_out:
                showSignOutAlertDialog();
                break;
        }
    }

    public void showCacheAlertDialog() {
        CustomDialog.Builder builder = new CustomDialog.Builder(this);
        builder.setMessage("是否清除缓存？");
        builder.setTitle("温馨提示");
        builder.setPositiveButton("确定", new android.content.DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                showDialog(LOADING_DIALOG);
//                Timer timer = new Timer();
//                TimerTask task = new TimerTask() {
//                    public void run() {
                        removeDialog();
                        Toast.makeText(SettingActivity.this, "缓存清除成功！", Toast.LENGTH_SHORT).show();
//                    }
//                };
//                timer.schedule(task, 2000);
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();

    }

    public void showTelephoneAlertDialog() {
        CustomDialog.Builder builder = new CustomDialog.Builder(this);
        builder.setMessage("是否拨打客服电话？");
        builder.setTitle("温馨提示");
        builder.setPositiveButton("确定", new android.content.DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                //打电话
                Intent intent = new Intent();
                intent.setAction("android.intent.action.CALL");
                intent.addCategory("android.intent.category.DEFAULT");
                intent.setData(Uri.parse("tel:4008489448"));//客服电话
                startActivity(intent);
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }

    public void showSignOutAlertDialog() {
        CustomDialog.Builder builder = new CustomDialog.Builder(this);
        builder.setMessage("是否退出登录？");
        builder.setTitle("温馨提示");
        builder.setPositiveButton("确定", new android.content.DialogInterface.OnClickListener() {
            public void onClick(final DialogInterface dialog, int which) {
                dialog.dismiss();
                //调用退出登录接口
                showDialog(LOADING_DIALOG);
                Map<String, String> map = new HashMap<>();
                map.put("userid", MallApplication.getInstance().getUserId(SettingActivity.this));
                XUtils.Post(MallApi.APP_LOGOUT, map, new MyCallBack<String>() {
                    @Override
                    public void onSuccess(String result) {
                        super.onSuccess(result);
                        if (JsonValidator.validate(result)) {
                            LoginTepinBean loginTepinBean = ParseUtils.parseLoginTepinBean(result);
                            if (loginTepinBean.getCode().equals("0")) {
                                //退出成功清空SharedPreferences用户数据
                                MallApplication.getInstance().clearSaveParam(SettingActivity.this);
                                //清除Cookie
                                MallApplication.getInstance().clearCookie(SettingActivity.this);
                                //登录开关关闭
                                MallApplication.getInstance().setAutoLogin(SettingActivity.this, false);
                                MallApplication.getInstance().setCartNum(SettingActivity.this, "0");
                                SharedPreferencesUtil.saveData(MallApplication.getInstance().getContext(), "is_first", false);
                                finish();
                            } else {
                                Toast.makeText(SettingActivity.this, loginTepinBean.getMsg(), Toast.LENGTH_SHORT).show();
                                return;
                            }
                        } else {
                            Toast.makeText(SettingActivity.this, R.string.unable_to_get_data, Toast.LENGTH_SHORT).show();
                        }
                        removeDialog();
                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {
                        super.onError(ex, isOnCallback);
                    }
                });


            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }
}
