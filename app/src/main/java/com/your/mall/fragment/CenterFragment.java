package com.your.mall.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.your.mall.R;
import com.your.mall.activity.AddressActivity;
import com.your.mall.activity.CollectActivity;
import com.your.mall.activity.CouponActivity;
import com.your.mall.activity.LoginActivity;
import com.your.mall.activity.MyBargainActivity;
import com.your.mall.activity.MyPintuanActivity;
import com.your.mall.activity.MyZujiActivity;
import com.your.mall.activity.OrderManagementActivity;
import com.your.mall.activity.RegisterActivity;
import com.your.mall.activity.SafeActivity;
import com.your.mall.activity.SettingActivity;
import com.your.mall.application.MallApplication;
import com.your.mall.bean.CenterBean;
import com.your.mall.http.MallApi;
import com.your.mall.utils.JsonValidator;
import com.your.mall.utils.MyCallBack;
import com.your.mall.utils.ParseUtils;
import com.your.mall.utils.XUtils;
import com.your.mall.view.CustomDialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xieqinghua.
 * 创建时间：2016/8/30
 * 类描述：用户中心
 * 修改备注：
 */
public class CenterFragment extends android.support.v4.app.Fragment implements View.OnClickListener {
    private View view;
    private LinearLayout ll_account_info;//账号信息
    private ImageView iv_cover;//封面
    private ImageView iv_setting;//设置
    private TextView tv_register, tv_login;
    private TextView tv_user_name;//用户名
    private TextView tv_grade_name;//会员等级
    private LinearLayout ll_num;
    private TextView tv_nupay, tv_nudelivery, tv_nuconfirm, tv_unrate,
            tv_nupay_num, tv_nudelivery_num, tv_nuconfirm_num, tv_unrate_num;
    private TextView tv_safe;//账户与安全
    private TextView tv_order;//我的订单
    private TextView tv_coupon;//我的优惠券
    private TextView tv_collect;//我的收藏
    private TextView tv_address;//地址管理
    private TextView tv_my_bargain;//我的砍价
    private TextView tv_my_pingtuan;//我的拼团
    private TextView tv_my_zuji;//我的足迹

    private String username, deposit, point;//用户名，余额，积分

    public static final int LOADING_DIALOG = 0;
    private static final String TAG = "Center";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_center, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (MallApplication.getInstance().getAutoLogin(getActivity())) {//已登录状态下，隐藏“注册”“登陆”按键
            tv_register.setVisibility(View.GONE);
            tv_login.setVisibility(View.GONE);
            ll_account_info.setVisibility(View.VISIBLE);
            ll_num.setVisibility(View.VISIBLE);

            getData();

        } else {
            tv_register.setVisibility(View.VISIBLE);
            tv_login.setVisibility(View.VISIBLE);
            ll_account_info.setVisibility(View.GONE);
            ll_num.setVisibility(View.GONE);
        }
    }

    /**
     * 初始化控件
     */
    private void init() {
        iv_cover = (ImageView) getView().findViewById(R.id.iv_cover);
        WindowManager wm = (WindowManager) getActivity().getSystemService(Context.WINDOW_SERVICE);
        ViewGroup.LayoutParams para = iv_cover.getLayoutParams();
        para.width = wm.getDefaultDisplay().getWidth();
        para.height = wm.getDefaultDisplay().getWidth() * 1 / 2;
        iv_cover.setLayoutParams(para);

        ll_account_info = (LinearLayout) getView().findViewById(R.id.ll_account_info);

        tv_user_name = (TextView) getView().findViewById(R.id.tv_user_name);
        tv_grade_name = (TextView) getView().findViewById(R.id.tv_grade_name);

        ll_num = (LinearLayout) getView().findViewById(R.id.ll_num);
        tv_nupay = (TextView) getView().findViewById(R.id.tv_nupay);
        tv_nudelivery = (TextView) getView().findViewById(R.id.tv_nudelivery);
        tv_nuconfirm = (TextView) getView().findViewById(R.id.tv_nuconfirm);
        tv_unrate = (TextView) getView().findViewById(R.id.tv_unrate);
        tv_nupay_num = (TextView) getView().findViewById(R.id.tv_nupay_num);
        tv_nudelivery_num = (TextView) getView().findViewById(R.id.tv_nudelivery_num);
        tv_nuconfirm_num = (TextView) getView().findViewById(R.id.tv_nuconfirm_num);
        tv_unrate_num = (TextView) getView().findViewById(R.id.tv_unrate_num);

        tv_register = (TextView) getView().findViewById(R.id.tv_register);
        tv_login = (TextView) getView().findViewById(R.id.tv_login);
        if (!MallApplication.getInstance().getAutoLogin(getActivity())) {
            tv_register.setVisibility(View.VISIBLE);
            tv_login.setVisibility(View.VISIBLE);
            ll_account_info.setVisibility(View.GONE);
        } else {
            tv_register.setVisibility(View.GONE);
            tv_login.setVisibility(View.GONE);
            ll_account_info.setVisibility(View.VISIBLE);
        }

        iv_setting = (ImageView) getView().findViewById(R.id.iv_setting);
        tv_safe = (TextView) getView().findViewById(R.id.tv_safe);
        tv_order = (TextView) getView().findViewById(R.id.tv_order);
        tv_coupon = (TextView) getView().findViewById(R.id.tv_coupon);
        tv_collect = (TextView) getView().findViewById(R.id.tv_collect);
        tv_address = (TextView) getView().findViewById(R.id.tv_address);
        tv_my_bargain = (TextView) getView().findViewById(R.id.tv_my_bargain);
        tv_my_pingtuan = (TextView) getView().findViewById(R.id.tv_my_pingtuan);
        tv_my_zuji = (TextView) getView().findViewById(R.id.tv_my_zuji);

        iv_setting.setOnClickListener(this);

        tv_register.setOnClickListener(this);
        tv_login.setOnClickListener(this);

        tv_nupay.setOnClickListener(this);
        tv_nudelivery.setOnClickListener(this);
        tv_nuconfirm.setOnClickListener(this);
        tv_unrate.setOnClickListener(this);

        tv_safe.setOnClickListener(this);
        tv_order.setOnClickListener(this);
        tv_coupon.setOnClickListener(this);
        tv_collect.setOnClickListener(this);
        tv_address.setOnClickListener(this);
        tv_my_bargain.setOnClickListener(this);
        tv_my_pingtuan.setOnClickListener(this);
        tv_my_zuji.setOnClickListener(this);

//        getData();
    }

    private void getData() {
        Map<String, String> map = new HashMap<>();
        map.put("user_id", MallApplication.getInstance().getUserId(getActivity()));
        XUtils.Post(MallApi.APP_MEMBER_INDEX, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                Log.e(TAG, result);
                if (JsonValidator.validate(result)) {
                    try {
                        JSONObject json = new JSONObject(result);
                        if (json.getString("code").equals("0")) {
                            CenterBean centerBean = ParseUtils.parseCenterBean(result);
                            tv_user_name.setText(centerBean.getData().getUsername());
                            tv_grade_name.setText(centerBean.getData().getGrade_name());
                            username = centerBean.getData().getUsername();
                            deposit = centerBean.getData().getDeposit();
                            point = centerBean.getData().getPoint();

                            if (!centerBean.getData().getNupay().equals("0")) {
                                tv_nupay_num.setVisibility(View.VISIBLE);
                                tv_nupay_num.setText(centerBean.getData().getNupay());
                            } else {
                                tv_nupay_num.setVisibility(View.GONE);
                            }
                            if (!centerBean.getData().getNudelivery().equals("0")) {
                                tv_nudelivery_num.setVisibility(View.VISIBLE);
                                tv_nudelivery_num.setText(centerBean.getData().getNudelivery());
                            } else {
                                tv_nudelivery_num.setVisibility(View.GONE);
                            }
                            if (!centerBean.getData().getNuconfirm().equals("0")) {
                                tv_nuconfirm_num.setVisibility(View.VISIBLE);
                                tv_nuconfirm_num.setText(centerBean.getData().getNuconfirm());
                            } else {
                                tv_nuconfirm_num.setVisibility(View.GONE);
                            }
                            if (!centerBean.getData().getUnrate().equals("0")) {
                                tv_unrate_num.setVisibility(View.VISIBLE);
                                tv_unrate_num.setText(centerBean.getData().getUnrate());
                            } else {
                                tv_unrate_num.setVisibility(View.GONE);
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(getActivity(), R.string.unable_to_get_data, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
            }
        });
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.iv_setting:
                startActivity(new Intent(getActivity(), SettingActivity.class));
                break;
            case R.id.tv_register:
                intent = new Intent(getActivity(), RegisterActivity.class);
                intent.putExtra("tag", "center");
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.activity_open, 0);
                break;
            case R.id.tv_login:
                startActivity(new Intent(getActivity(), LoginActivity.class));
                getActivity().overridePendingTransition(R.anim.activity_open, 0);
                break;
            case R.id.tv_order:
                if (!MallApplication.getInstance().getAutoLogin(getActivity())) {
                    showAlertDialog();
                } else {
                    intent = new Intent(getActivity(), OrderManagementActivity.class);
                    intent.putExtra("index", 0);
                    startActivity(intent);
                }
                break;
            case R.id.tv_nupay:
                if (!MallApplication.getInstance().getAutoLogin(getActivity())) {//未登录状态弹出提示对话框
                    showAlertDialog();
                } else {
                    intent = new Intent(getActivity(), OrderManagementActivity.class);
                    intent.putExtra("index", 1);
                    startActivity(intent);
                }
                break;
            case R.id.tv_nudelivery:
                if (!MallApplication.getInstance().getAutoLogin(getActivity())) {//未登录状态弹出提示对话框
                    showAlertDialog();
                } else {
                    intent = new Intent(getActivity(), OrderManagementActivity.class);
                    intent.putExtra("index", 2);
                    startActivity(intent);
                }
                break;
            case R.id.tv_nuconfirm:
                if (!MallApplication.getInstance().getAutoLogin(getActivity())) {//未登录状态弹出提示对话框
                    showAlertDialog();
                } else {
                    intent = new Intent(getActivity(), OrderManagementActivity.class);
                    intent.putExtra("index", 3);
                    startActivity(intent);
                }
                break;
            case R.id.tv_unrate:
                if (!MallApplication.getInstance().getAutoLogin(getActivity())) {//未登录状态弹出提示对话框
                    showAlertDialog();
                } else {
                    intent = new Intent(getActivity(), OrderManagementActivity.class);
                    intent.putExtra("index", 4);
                    startActivity(intent);
                }
                break;
            case R.id.tv_safe:
                if (!MallApplication.getInstance().getAutoLogin(getActivity())) {//未登录状态弹出提示对话框
                    showAlertDialog();
                } else {
                    intent = new Intent(getActivity(), SafeActivity.class);
                    intent.putExtra("username", username);
                    intent.putExtra("deposit", deposit);
                    intent.putExtra("point", point);
                    startActivity(intent);
                }
                break;
            case R.id.tv_coupon:
                if (!MallApplication.getInstance().getAutoLogin(getActivity())) {
                    showAlertDialog();
                } else {
                    startActivity(new Intent(getActivity(), CouponActivity.class));
                }
                break;
            case R.id.tv_collect:
                if (!MallApplication.getInstance().getAutoLogin(getActivity())) {
                    showAlertDialog();
                } else {
                    startActivity(new Intent(getActivity(), CollectActivity.class));
                }
                break;
            case R.id.tv_address:
                if (!MallApplication.getInstance().getAutoLogin(getActivity())) {
                    showAlertDialog();
                } else {
                    startActivity(new Intent(getActivity(), AddressActivity.class));
                }
                break;
            case R.id.tv_my_bargain:
                if (!MallApplication.getInstance().getAutoLogin(getActivity())) {
                    showAlertDialog();
                } else {
                    startActivity(new Intent(getActivity(), MyBargainActivity.class));
                }
                break;
            case R.id.tv_my_pingtuan:
                if (!MallApplication.getInstance().getAutoLogin(getActivity())) {
                    showAlertDialog();
                } else {
                    startActivity(new Intent(getActivity(), MyPintuanActivity.class));
                }
                break;
            case R.id.tv_my_zuji:
                if (!MallApplication.getInstance().getAutoLogin(getActivity())) {
                    showAlertDialog();
                } else {
                    startActivity(new Intent(getActivity(), MyZujiActivity.class));
                }
                break;
        }
    }

    public void showAlertDialog() {
        CustomDialog.Builder builder = new CustomDialog.Builder(getActivity());
        builder.setMessage("您尚未登录，请登录！");
        builder.setTitle("温馨提示");
        builder.setPositiveButton("确定", new android.content.DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                //跳转登录页面
                startActivity(new Intent(getActivity(), LoginActivity.class));
                getActivity().overridePendingTransition(R.anim.activity_open, 0);
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
}
