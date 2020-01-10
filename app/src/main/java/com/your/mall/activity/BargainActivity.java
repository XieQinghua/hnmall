package com.your.mall.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.your.mall.R;
import com.your.mall.adapter.BargainAdapter;
import com.your.mall.base.BaseSwipeBackActivity;
import com.your.mall.bean.BargainBean;
import com.your.mall.http.MallApi;
import com.your.mall.utils.JsonValidator;
import com.your.mall.utils.MyCallBack;
import com.your.mall.utils.ParseUtils;
import com.your.mall.utils.TimeUtils;
import com.your.mall.utils.XUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by xieqinghua.
 * 创建时间：2016/11/14
 * 类描述：全民砍价
 * 修改备注：
 */
public class BargainActivity extends BaseSwipeBackActivity implements View.OnClickListener {
    private static final String TAG = "BargainActivity";
    private TextView tv_title;
    private ImageView iv_bargain_rule;
    private RelativeLayout rl_time_info;
    private TextView tv_month, tv_day, tv_hour, tv_minute, tv_second;
    private int hour, minute, second;
    private TimeCount time;
    private TextView tv_time_remain;
    private LinearLayout ll_time_count;
    private ListView lv_bargain;
    private ArrayList<BargainBean.DataBean.ListBean> list;
    private BargainAdapter myBargainAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bargain);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        init();
    }

    private void init() {
        tv_title = (TextView) findViewById(R.id.tv_title_center);
        tv_title.setText("全民砍价");
        iv_bargain_rule = (ImageView) findViewById(R.id.iv_bargain_rule);
        iv_bargain_rule.setOnClickListener(this);
        rl_time_info = (RelativeLayout) findViewById(R.id.rl_time_info);
        tv_month = (TextView) findViewById(R.id.tv_month);
        tv_day = (TextView) findViewById(R.id.tv_day);
        tv_hour = (TextView) findViewById(R.id.tv_hour);
        tv_minute = (TextView) findViewById(R.id.tv_minute);
        tv_second = (TextView) findViewById(R.id.tv_second);

        tv_title = (TextView) findViewById(R.id.tv_title_center);
        tv_time_remain = (TextView) findViewById(R.id.tv_time_remain);
        ll_time_count = (LinearLayout) findViewById(R.id.ll_time_count);
        lv_bargain = (ListView) findViewById(R.id.lv_bargain);
        getData();
    }

    private void getData() {
        showDialog(LOADING_DIALOG);
        XUtils.Get(MallApi.APP_BARGAIN, null, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                Log.e(TAG, result);
                if (JsonValidator.validate(result)) try {
                    JSONObject json = new JSONObject(result);
                    if (json.getString("code").equals("0")) {
                        BargainBean bargainBean = ParseUtils.parseBargainBean(result);

                        if (bargainBean.getData().getStatus().equals("1")) {
                            tv_time_remain.setText("距离活动还剩");
                            ll_time_count.setVisibility(View.VISIBLE);

                            long now_time = TimeUtils.getTimestamp(bargainBean.getData().getNow_time());
                            long end_time = TimeUtils.getTimestamp(bargainBean.getData().getEnd_time());
                            final long millisecond = TimeUtils.getDistanceMillisecond(now_time, end_time);
                            String count = TimeUtils.getDifference(now_time, end_time);
                            Log.e("count", count);
                            tv_month.setText(TimeUtils.getDistanceTime(millisecond).getMonth() + "");
                            tv_day.setText(TimeUtils.getDistanceTime(millisecond).getDay() + "");
                            tv_hour.setText(TimeUtils.getDistanceTime(millisecond).getHour() + "");
                            tv_minute.setText(TimeUtils.getDistanceTime(millisecond).getMinute() + "");
                            tv_second.setText(TimeUtils.getDistanceTime(millisecond).getSecond() + "");

                            time = new TimeCount(millisecond % TimeUtils.dayLevelValue, 1000);
                            time.start();

                        } else if (bargainBean.getData().getStatus().equals("2")) {
                            tv_time_remain.setText("活动已结束");
                            tv_time_remain.setTextColor(getResources().getColor(R.color.paleRed));
                            tv_time_remain.setTextSize(12);
                            ll_time_count.setVisibility(View.GONE);
                        } else if (bargainBean.getData().getStatus().equals("3")) {
                            tv_time_remain.setText("活动已结束");
                            tv_time_remain.setTextColor(getResources().getColor(R.color.paleRed));
                            tv_time_remain.setTextSize(12);
                            ll_time_count.setVisibility(View.GONE);
                        }

                        if (bargainBean.getData().getList() != null && bargainBean.getData().getList().size() != 0) {
                            list = (ArrayList<BargainBean.DataBean.ListBean>) bargainBean.getData().getList();
                            myBargainAdapter = new BargainAdapter(BargainActivity.this, list);
                            rl_time_info.setVisibility(View.VISIBLE);
                            lv_bargain.setVisibility(View.VISIBLE);
                            lv_bargain.setAdapter(myBargainAdapter);
                            myBargainAdapter.notifyDataSetChanged();
                        }
                    } else {
                        Toast.makeText(BargainActivity.this, json.getString("msg"), Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                else {
                    Toast.makeText(BargainActivity.this, R.string.unable_to_get_data, Toast.LENGTH_SHORT).show();
                }
                removeDialog();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_bargain_rule:
                startActivity(new Intent(BargainActivity.this, BargainRuleActivity.class));
                break;
        }
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
