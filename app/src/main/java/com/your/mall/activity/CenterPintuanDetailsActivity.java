package com.your.mall.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.your.mall.R;
import com.your.mall.base.BaseSwipeBackActivity;
import com.your.mall.bean.CenterPintuanDetailsBean;
import com.your.mall.http.MallApi;
import com.your.mall.utils.ImgUtils;
import com.your.mall.utils.JsonValidator;
import com.your.mall.utils.MyCallBack;
import com.your.mall.utils.ParseUtils;
import com.your.mall.utils.TimeUtils;
import com.your.mall.utils.XUtils;
import com.your.mall.utils.onekeyshare.OneKeyShareBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xieqinghua.
 * 创建时间：2016/11/14
 * 类描述：拼团详情
 * 修改备注：
 */
public class CenterPintuanDetailsActivity extends BaseSwipeBackActivity implements View.OnClickListener {
    private static final String TAG = "CenterPintuan";
    private TextView tv_title;
    private String activity_id, item_id, kaituan_id;
    private CenterPintuanDetailsBean myCenterPintuanDetailsBean;

    private LinearLayout ll_pintuan_info;

    private ImageView iv_goods_banner;
    private TextView tv_goods_title, tv_chengtuan,
            tv_day, tv_hour, tv_minute, tv_second,
            tv_invite_pintuan, tv_back_pintuan, tv_pintuan_rule;
    private TimeCount time;
    private WebView wap_desc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_center_pintuan_details);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        init();
    }

    private void init() {
        tv_title = (TextView) findViewById(R.id.tv_title_center);
        tv_title.setText("团购详情");
        activity_id = getIntent().getStringExtra("activity_id");
        item_id = getIntent().getStringExtra("item_id");
        kaituan_id = getIntent().getStringExtra("kaituan_id");

        ll_pintuan_info = (LinearLayout) findViewById(R.id.ll_pintuan_info);
        iv_goods_banner = (ImageView) findViewById(R.id.iv_goods_banner);
        tv_goods_title = (TextView) findViewById(R.id.tv_goods_title);
        tv_chengtuan = (TextView) findViewById(R.id.tv_chengtuan);
        tv_day = (TextView) findViewById(R.id.tv_day);
        tv_hour = (TextView) findViewById(R.id.tv_hour);
        tv_minute = (TextView) findViewById(R.id.tv_minute);
        tv_second = (TextView) findViewById(R.id.tv_second);
        tv_invite_pintuan = (TextView) findViewById(R.id.tv_invite_pintuan);
        tv_back_pintuan = (TextView) findViewById(R.id.tv_back_pintuan);
        tv_pintuan_rule = (TextView) findViewById(R.id.tv_pintuan_rule);
        wap_desc = (WebView) findViewById(R.id.wap_desc);

        getData();
    }

    private void getData() {
        Map<String, String> map = new HashMap<>();
        map.put("activity_id", activity_id);
        map.put("item_id", item_id);
        map.put("kaituan_id", kaituan_id);
        showDialog(LOADING_DIALOG);
        XUtils.Get(MallApi.APP_PINGTUAN_VIEW, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                Log.e(TAG, result);
                if (JsonValidator.validate(result)) {
                    try {
                        JSONObject json = new JSONObject(result);
                        if (json.getString("code").equals("0")) {
                            myCenterPintuanDetailsBean = ParseUtils.parseCenterPintuanDetailsBean(result);
                            fillData(myCenterPintuanDetailsBean);
                        } else {
                            Toast.makeText(CenterPintuanDetailsActivity.this, json.getString("msg"), Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(CenterPintuanDetailsActivity.this, R.string.unable_to_get_data, Toast.LENGTH_SHORT).show();
                }
                removeDialog();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
            }
        });
    }

    private void fillData(final CenterPintuanDetailsBean centerPintuanDetailsBean) {
        ImgUtils.setRectangleImage(iv_goods_banner, centerPintuanDetailsBean.getData().getActivity_goods_banner());
        tv_goods_title.setText(centerPintuanDetailsBean.getData().getActivity_goods_title());
        tv_pintuan_rule.setOnClickListener(this);
        tv_chengtuan.setText("已成团" + centerPintuanDetailsBean.getData().getChengtuan() + "个");

        long now_time = TimeUtils.getTimestamp1(centerPintuanDetailsBean.getData().getNow_time());
        long end_time = TimeUtils.getTimestamp1(centerPintuanDetailsBean.getData().getEndtime());
        if (TimeUtils.getDistanceMillisecond(now_time, end_time) > 0) {
            final long millisecond = TimeUtils.getDistanceMillisecond(now_time, end_time);
            String count = TimeUtils.getDifference(now_time, end_time);
            Log.e("count", count);
            tv_day.setText(TimeUtils.getDistanceTime(millisecond).getDay() + "");
            tv_hour.setText(TimeUtils.getDistanceTime(millisecond).getHour() + "");
            tv_minute.setText(TimeUtils.getDistanceTime(millisecond).getMinute() + "");
            tv_second.setText(TimeUtils.getDistanceTime(millisecond).getSecond() + "");
            time = new TimeCount(millisecond % TimeUtils.dayLevelValue, 1000);
            time.start();
        }

        tv_invite_pintuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                share();
            }
        });
        tv_back_pintuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CenterPintuanDetailsActivity.this, PintuanActivity.class);
                startActivity(intent);
            }
        });

        String customHtml = "<!DOCTYPE html>\n" +
                "<html>\n" +
                " <head> \n" +
                "  <meta charset=\"utf-8\" /> \n" +
                "  <meta name=\"viewport\" content=\"width=device-width,\" initial-scale=\"1.0,\" maximum-scale=\"1.0\" /> \n" +
                "  <style>.fulldiv img{ width: 100%!important; }</style> \n" +
                " </head> \n" +
                " <body> \n" +
                "  <div class=\"fulldiv\"> \n" +
                centerPintuanDetailsBean.getData().getItem().getWap_desc() +
                "  </div>  \n" +
                " </body>\n" +
                "</html>";
        wap_desc.getSettings().setUseWideViewPort(true);
        wap_desc.getSettings().setLoadWithOverviewMode(true);
        wap_desc.loadDataWithBaseURL(null, customHtml, "text/html", "gb2312", null);

        //显示页面
        ll_pintuan_info.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_pintuan_rule:
                startActivity(new Intent(CenterPintuanDetailsActivity.this, PintuanRuleActivity.class)
                        .putExtra("name", "/pintuan-about.html"));
                break;
        }
    }

    /**
     * 分享
     */
    private void share() {
        OneKeyShareBuilder oneKeyShareBuilder = new OneKeyShareBuilder();
        oneKeyShareBuilder.setText(myCenterPintuanDetailsBean.getData().getShareList().getShareText())
                .setTitleUrl(myCenterPintuanDetailsBean.getData().getShareList().getShareUrl())
                .setTitle(myCenterPintuanDetailsBean.getData().getShareList().getShareText())
                .setContext(CenterPintuanDetailsActivity.this)
                .setImageUrl(myCenterPintuanDetailsBean.getData().getShareList().getShareImage())
                .setUrl(myCenterPintuanDetailsBean.getData().getShareList().getShareUrl())
                .showShareDialog();
    }

    class TimeCount extends CountDownTimer {

        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onFinish() {
        }

        @Override
        public void onTick(long millisUntilFinished) {
            toClock(millisUntilFinished);
        }

        public void toClock(long millisUntilFinished) {
            long hour = millisUntilFinished / (60 * 60 * 1000);
            long minute = (millisUntilFinished - hour * 60 * 60 * 1000) / (60 * 1000);
            long second = (millisUntilFinished - hour * 60 * 60 * 1000 - minute * 60 * 1000) / 1000;
            if (second >= 60) {
                second = second % 60;
                minute += second / 60;
            }
            if (minute >= 60) {
                minute = minute % 60;
                hour += minute / 60;
            }
            String sh = "";
            String sm = "";
            String ss = "";
            if (hour < 10) {
                sh = "0" + String.valueOf(hour);
            } else {
                sh = String.valueOf(hour);
            }
            if (minute < 10) {
                sm = "0" + String.valueOf(minute);
            } else {
                sm = String.valueOf(minute);
            }
            if (second < 10) {
                ss = "0" + String.valueOf(second);
            } else {
                ss = String.valueOf(second);
            }
//            return sh + ":" + sm + ":" + ss;
            tv_hour.setText(sh);
            tv_minute.setText(sm);
            tv_second.setText(ss);
        }
    }
}
