package com.your.mall.activity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.your.mall.R;
import com.your.mall.adapter.PintuanHotGoodsAdapter;
import com.your.mall.adapter.PintuanPeopleAdapter;
import com.your.mall.adapter.PintuanWinPeopleAdapter;
import com.your.mall.base.BaseSwipeBackActivity;
import com.your.mall.bean.CenterPintuanCjDetailsBean;
import com.your.mall.http.MallApi;
import com.your.mall.utils.ImgUtils;
import com.your.mall.utils.JsonValidator;
import com.your.mall.utils.MyCallBack;
import com.your.mall.utils.ParseUtils;
import com.your.mall.utils.TimeUtils;
import com.your.mall.utils.XUtils;
import com.your.mall.utils.onekeyshare.OneKeyShareBuilder;
import com.your.mall.view.WinListRuleDialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xieqinghua.
 * 创建时间：2016/11/14
 * 类描述：拼团抽奖详情
 * 修改备注：
 */
public class CenterPintuanCjDetailsActivity extends BaseSwipeBackActivity implements View.OnClickListener {
    private static final String TAG = "CenterPintuanCj";
    private TextView tv_title;
    private String activity_id, item_id, kaituan_id;
    private CenterPintuanCjDetailsBean myCenterPintuanCjDetailsBean;

    private LinearLayout ll_pintuancj_info, ll_button_info;
    private ImageView iv_goods_img, iv_mask;
    private TextView tv_goods_title, tv_activity_number, tv_activity_price, tv_price, tv_rule;
    private GridView gv_pintuan_member, gv_hot_goods;
    private RelativeLayout rl_pintuan_win_info;
    private TextView tv_join_number, tv_time_count;
    private LinearLayout ll_win_list_info;
    private ListView lv_win_people;
    private TextView tv_pintuan1, tv_pintuan2;

    private TimeCount time;

    private PintuanPeopleAdapter myPintuanPeopleAdapter;
    private PintuanHotGoodsAdapter myPintuanHotGoodsAdapter;
    private PintuanWinPeopleAdapter myPintuanWinPeopleAdapter;

    private ArrayList<CenterPintuanCjDetailsBean.DataBean.HotListBean> hotList;
    private ArrayList<CenterPintuanCjDetailsBean.DataBean.PeopleList2Bean> winPeoplelist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_center_pintuancj_details);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        init();
    }

    private void init() {
        tv_title = (TextView) findViewById(R.id.tv_title_center);
        tv_title.setText("团购商品");
        activity_id = getIntent().getStringExtra("activity_id");
        item_id = getIntent().getStringExtra("item_id");
        kaituan_id = getIntent().getStringExtra("kaituan_id");

        ll_pintuancj_info = (LinearLayout) findViewById(R.id.ll_pintuancj_info);
        ll_button_info = (LinearLayout) findViewById(R.id.ll_button_info);
        iv_goods_img = (ImageView) findViewById(R.id.iv_goods_img);
        iv_mask = (ImageView) findViewById(R.id.iv_mask);
        tv_goods_title = (TextView) findViewById(R.id.tv_goods_title);
        tv_activity_number = (TextView) findViewById(R.id.tv_activity_number);
        tv_activity_price = (TextView) findViewById(R.id.tv_activity_price);
        tv_price = (TextView) findViewById(R.id.tv_price);
        tv_price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);//中间加横线
        tv_rule = (TextView) findViewById(R.id.tv_rule);
        gv_pintuan_member = (GridView) findViewById(R.id.gv_pintuan_member);
        gv_hot_goods = (GridView) findViewById(R.id.gv_hot_goods);
        rl_pintuan_win_info = (RelativeLayout) findViewById(R.id.rl_pintuan_win_info);
        tv_join_number = (TextView) findViewById(R.id.tv_join_number);
        tv_time_count = (TextView) findViewById(R.id.tv_time_count);
        ll_win_list_info = (LinearLayout) findViewById(R.id.ll_win_list_info);
        lv_win_people = (ListView) findViewById(R.id.lv_win_people);
        tv_pintuan1 = (TextView) findViewById(R.id.tv_pintuan1);
        tv_pintuan2 = (TextView) findViewById(R.id.tv_pintuan2);

        getData();
    }

    private void getData() {
        Map<String, String> map = new HashMap<>();
        map.put("activity_id", activity_id);
        map.put("item_id", item_id);
        map.put("kaituan_id", kaituan_id);
        showDialog(LOADING_DIALOG);
        XUtils.Get(MallApi.APP_PINGTUANCJ_VIEW, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                Log.e(TAG, result);
                if (JsonValidator.validate(result)) {
                    try {
                        JSONObject json = new JSONObject(result);
                        if (json.getString("code").equals("0")) {
                            myCenterPintuanCjDetailsBean = ParseUtils.parseCenterPintuanCjDetailsBean(result);
                            fillData(myCenterPintuanCjDetailsBean);
                        } else {
                            Toast.makeText(CenterPintuanCjDetailsActivity.this, json.getString("msg"), Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(CenterPintuanCjDetailsActivity.this, R.string.unable_to_get_data, Toast.LENGTH_SHORT).show();
                }
                removeDialog();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
            }
        });
    }

    private void fillData(final CenterPintuanCjDetailsBean centerPintuanCjDetailsBean) {
        ImgUtils.setRectangleImage(iv_goods_img, centerPintuanCjDetailsBean.getData().getActivity_goods_banner());
        tv_goods_title.setText(centerPintuanCjDetailsBean.getData().getActivity_goods_title());
        tv_activity_number.setText(centerPintuanCjDetailsBean.getData().getActivity_number() + "人团");
        tv_activity_price.setText("￥" + centerPintuanCjDetailsBean.getData().getActivity_price());
        tv_price.setText(centerPintuanCjDetailsBean.getData().getPrice());
        tv_rule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final WinListRuleDialog dialog = new WinListRuleDialog(CenterPintuanCjDetailsActivity.this);
                dialog.getTv_time().setText("活动时间：" + centerPintuanCjDetailsBean.getData().getStart_time() + "~" + centerPintuanCjDetailsBean.getData().getEnd_time());
                dialog.getStr1().setText(centerPintuanCjDetailsBean.getData().getStr1());
                dialog.getStr2().setText(centerPintuanCjDetailsBean.getData().getStr2());
                dialog.getStr3().setText(centerPintuanCjDetailsBean.getData().getStr3());
                dialog.getStr4().setText(centerPintuanCjDetailsBean.getData().getStr4());
                dialog.getCancel().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.getAlertDialog().dismiss();
                    }
                });
            }
        });

        myPintuanPeopleAdapter = new PintuanPeopleAdapter(CenterPintuanCjDetailsActivity.this,
                (ArrayList<String>) centerPintuanCjDetailsBean.getData().getPeople_list());
        gv_pintuan_member.setAdapter(myPintuanPeopleAdapter);

        String inviteNum = (Integer.parseInt(centerPintuanCjDetailsBean.getData().getActivity_number()) - 1) + "";
        if (centerPintuanCjDetailsBean.getData().getIs_success().equals("1")) {//1是进行中 2是成功 3是失败
            iv_mask.setVisibility(View.GONE);

            rl_pintuan_win_info.setVisibility(View.VISIBLE);
            ll_win_list_info.setVisibility(View.GONE);
            String text = "还差" + inviteNum + "人，拼团成功即可抽奖";
            SpannableStringBuilder style = new SpannableStringBuilder(text);
            style.setSpan(new ForegroundColorSpan(CenterPintuanCjDetailsActivity.this.getResources().getColor(R.color.paleRed)),
                    text.indexOf(inviteNum),
                    text.indexOf(inviteNum) + inviteNum.length(),
                    Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
            tv_join_number.setText(style);
            long now_time = TimeUtils.getTimestamp1(centerPintuanCjDetailsBean.getData().getNow_time());
            long end_time = TimeUtils.getTimestamp1(centerPintuanCjDetailsBean.getData().getEnd_time());
            if (TimeUtils.getDistanceMillisecond(now_time, end_time) > 0) {
                final long millisecond = TimeUtils.getDistanceMillisecond(now_time, end_time);
                time = new TimeCount(millisecond, 1000);
                time.start();
            } else {
                tv_time_count.setText("00 ：00 ：00");
            }

            tv_pintuan1.setVisibility(View.VISIBLE);
            tv_pintuan2.setVisibility(View.VISIBLE);
            tv_pintuan1.setText("更多拼团");
            tv_pintuan2.setText("还差" + inviteNum + "人拼团成功");
            tv_pintuan1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(CenterPintuanCjDetailsActivity.this, PintuanActivity.class);
                    startActivity(intent);
                }
            });
            tv_pintuan2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    share();
                }
            });

            ll_win_list_info.setVisibility(View.GONE);
            gv_hot_goods.setVisibility(View.VISIBLE);
            hotList = (ArrayList<CenterPintuanCjDetailsBean.DataBean.HotListBean>) centerPintuanCjDetailsBean.getData().getHotList();
            myPintuanHotGoodsAdapter = new PintuanHotGoodsAdapter(CenterPintuanCjDetailsActivity.this, hotList);
            gv_hot_goods.setAdapter(myPintuanHotGoodsAdapter);
            gv_hot_goods.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(CenterPintuanCjDetailsActivity.this, GoodsDetailsActivity.class);
                    intent.putExtra("item_id", hotList.get(position).getItem_id() + "");
                    startActivity(intent);
                }
            });
        } else if (centerPintuanCjDetailsBean.getData().getIs_success().equals("2")) {
            iv_mask.setVisibility(View.VISIBLE);
            iv_mask.setImageResource(R.drawable.img_my_pintuan_success);

            rl_pintuan_win_info.setVisibility(View.GONE);
            ll_win_list_info.setVisibility(View.VISIBLE);
            gv_hot_goods.setVisibility(View.GONE);
            //显示中奖名单
            winPeoplelist = (ArrayList<CenterPintuanCjDetailsBean.DataBean.PeopleList2Bean>) centerPintuanCjDetailsBean.getData().getPeople_list2();
            myPintuanWinPeopleAdapter = new PintuanWinPeopleAdapter(CenterPintuanCjDetailsActivity.this, winPeoplelist);
            lv_win_people.setAdapter(myPintuanWinPeopleAdapter);

            tv_pintuan1.setVisibility(View.GONE);
            tv_pintuan2.setVisibility(View.VISIBLE);
            tv_pintuan2.setText("更多拼团");
            tv_pintuan2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(CenterPintuanCjDetailsActivity.this, PintuanActivity.class);
                    startActivity(intent);
                }
            });

        } else if (centerPintuanCjDetailsBean.getData().getIs_success().equals("3")) {
            iv_mask.setVisibility(View.VISIBLE);
            iv_mask.setImageResource(R.drawable.img_my_pintuan_failed);

            rl_pintuan_win_info.setVisibility(View.VISIBLE);
            ll_win_list_info.setVisibility(View.GONE);
            String text = "还差" + inviteNum + "人，拼团成功即可抽奖";
            SpannableStringBuilder style = new SpannableStringBuilder(text);
            style.setSpan(new ForegroundColorSpan(CenterPintuanCjDetailsActivity.this.getResources().getColor(R.color.paleRed)),
                    text.indexOf(inviteNum),
                    text.indexOf(inviteNum) + inviteNum.length(),
                    Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
            tv_join_number.setText(style);
            long now_time = TimeUtils.getTimestamp1(centerPintuanCjDetailsBean.getData().getNow_time());
            long end_time = TimeUtils.getTimestamp1(centerPintuanCjDetailsBean.getData().getEnd_time());
            if (TimeUtils.getDistanceMillisecond(now_time, end_time) > 0) {
                final long millisecond = TimeUtils.getDistanceMillisecond(now_time, end_time);
                time = new TimeCount(millisecond, 1000);
                time.start();
            } else {
                tv_time_count.setText("00 ：00 ：00");
            }

            tv_pintuan1.setVisibility(View.GONE);
            tv_pintuan2.setVisibility(View.VISIBLE);
            tv_pintuan2.setText("更多拼团");
            tv_pintuan2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(CenterPintuanCjDetailsActivity.this, PintuanActivity.class);
                    startActivity(intent);
                }
            });

            ll_win_list_info.setVisibility(View.GONE);
            gv_hot_goods.setVisibility(View.VISIBLE);
            hotList = (ArrayList<CenterPintuanCjDetailsBean.DataBean.HotListBean>) centerPintuanCjDetailsBean.getData().getHotList();
            myPintuanHotGoodsAdapter = new PintuanHotGoodsAdapter(CenterPintuanCjDetailsActivity.this, hotList);
            gv_hot_goods.setAdapter(myPintuanHotGoodsAdapter);
            gv_hot_goods.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(CenterPintuanCjDetailsActivity.this, GoodsDetailsActivity.class);
                    intent.putExtra("item_id", hotList.get(position).getItem_id() + "");
                    startActivity(intent);
                }
            });
        }

        ll_pintuancj_info.setVisibility(View.VISIBLE);
        ll_button_info.setVisibility(View.VISIBLE);
    }

    /**
     * 分享
     */
    private void share() {
        OneKeyShareBuilder oneKeyShareBuilder = new OneKeyShareBuilder();
        oneKeyShareBuilder.setText(myCenterPintuanCjDetailsBean.getData().getShareList().getShareText())
                .setTitleUrl(myCenterPintuanCjDetailsBean.getData().getShareList().getShareUrl())
                .setTitle(myCenterPintuanCjDetailsBean.getData().getShareList().getShareTitle())
                .setContext(CenterPintuanCjDetailsActivity.this)
                .setImageUrl(myCenterPintuanCjDetailsBean.getData().getShareList().getShareImage())
                .setUrl(myCenterPintuanCjDetailsBean.getData().getShareList().getShareUrl())
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
            tv_time_count.setText(sh + " ：" + sm + " ：" + ss);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

        }
    }
}
