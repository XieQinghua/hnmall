package com.your.mall.activity;

import android.graphics.Paint;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.your.mall.R;
import com.your.mall.adapter.TabFragmentPagerAdapter;
import com.your.mall.base.BaseSwipeBackActivity;
import com.your.mall.bean.PeopleListBean;
import com.your.mall.bean.PintuanCjPrizeBean;
import com.your.mall.fragment.WinPeopleFragment;
import com.your.mall.http.MallApi;
import com.your.mall.utils.ImgUtils;
import com.your.mall.utils.JsonValidator;
import com.your.mall.utils.MyCallBack;
import com.your.mall.utils.ParseUtils;
import com.your.mall.utils.XUtils;
import com.your.mall.view.WinListRuleDialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xieqinghua.
 * 创建时间：2016/11/24
 * 类描述：拼团中奖名单
 * 修改备注：
 */
public class PintuanWinListActivity extends BaseSwipeBackActivity {
    private static final String TAG = "PintuanWinListActivity";
    private TextView tv_title;
    private String activity_id, item_id, kaituan_id;

    private TabLayout mTabLayout;//定义TabLayout
    private ViewPager mViewPager;//定义viewPager
    private FragmentPagerAdapter myFragmentPagerAdapter; //定义adapter
    private List<Fragment> myFragmentList; //定义要装fragment的列表
    private List<String> myTitleList; //tab名称列表

    private LinearLayout ll_winlist_info;
    private ImageView iv_goods_img;
    private TextView tv_goods_title, tv_activity_number, tv_activity_price, tv_price, tv_rule;

    private WinPeopleFragment winPeopleFragment1, winPeopleFragment2;

    private PintuanCjPrizeBean pintuanCjPrizeBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pintuan_win_list);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        init();
    }

    private void init() {
        tv_title = (TextView) findViewById(R.id.tv_title_center);
        tv_title.setText("中奖名单");

        ll_winlist_info = (LinearLayout) findViewById(R.id.ll_winlist_info);
        iv_goods_img = (ImageView) findViewById(R.id.iv_goods_img);
        tv_goods_title = (TextView) findViewById(R.id.tv_goods_title);
        tv_activity_number = (TextView) findViewById(R.id.tv_activity_number);
        tv_activity_price = (TextView) findViewById(R.id.tv_activity_price);
        tv_price = (TextView) findViewById(R.id.tv_price);
        tv_price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);//中间加横线
        tv_rule = (TextView) findViewById(R.id.tv_rule);

        activity_id = getIntent().getStringExtra("activity_id");
        item_id = getIntent().getStringExtra("item_id");

        mTabLayout = (TabLayout) findViewById(R.id.tabs);
        mViewPager = (ViewPager) findViewById(R.id.vp_view);

        myTitleList = new ArrayList<>();
        myTitleList.add("一等奖");
        myTitleList.add("二等奖");

        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        mTabLayout.addTab(mTabLayout.newTab().setText(myTitleList.get(0)));
        mTabLayout.addTab(mTabLayout.newTab().setText(myTitleList.get(1)));

        getData();
    }

    private void getData() {
        Map<String, String> map = new HashMap<>();
        map.put("activity_id", activity_id);
        map.put("item_id", item_id);
        showDialog(LOADING_DIALOG);
        XUtils.Get(MallApi.APP_PINGTUANCJ_PRIZE, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                Log.e(TAG, result);
                if (JsonValidator.validate(result)) {
                    try {
                        JSONObject json = new JSONObject(result);
                        if (json.getString("code").equals("0")) {
                            pintuanCjPrizeBean = ParseUtils.parsePintuanCjPrizeBean(result);
                            fillData(pintuanCjPrizeBean);
                        } else {
                            Toast.makeText(PintuanWinListActivity.this, json.getString("msg"), Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(PintuanWinListActivity.this, R.string.unable_to_get_data, Toast.LENGTH_SHORT).show();
                }
                removeDialog();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
            }
        });
    }

    private void fillData(final PintuanCjPrizeBean pintuanCjPrizeBean) {
        ImgUtils.setRectangleImage(iv_goods_img, pintuanCjPrizeBean.getData().getActivity_goods_banner());
        tv_goods_title.setText(pintuanCjPrizeBean.getData().getActivity_goods_title());
        tv_activity_number.setText(pintuanCjPrizeBean.getData().getActivity_number() + "人团");
        tv_activity_price.setText("￥" + pintuanCjPrizeBean.getData().getActivity_price());
        tv_price.setText(pintuanCjPrizeBean.getData().getPrice());
        tv_rule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final WinListRuleDialog dialog = new WinListRuleDialog(PintuanWinListActivity.this);
                dialog.getTv_time().setText("活动时间：" + pintuanCjPrizeBean.getData().getStart_time() + "~" + pintuanCjPrizeBean.getData().getEnd_time());
                dialog.getStr1().setText(pintuanCjPrizeBean.getData().getStr1());
                dialog.getStr2().setText(pintuanCjPrizeBean.getData().getStr2());
                dialog.getStr3().setText(pintuanCjPrizeBean.getData().getStr3());
                dialog.getStr4().setText(pintuanCjPrizeBean.getData().getStr4());
                dialog.getCancel().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.getAlertDialog().dismiss();
                    }
                });
            }
        });

        winPeopleFragment1 = new WinPeopleFragment();
        Bundle bundle1 = new Bundle();
        bundle1.putSerializable("list", (ArrayList<PeopleListBean>) pintuanCjPrizeBean.getData().getPeople_list1());
        winPeopleFragment1.setArguments(bundle1);

        winPeopleFragment2 = new WinPeopleFragment();
        Bundle bundle2 = new Bundle();
        bundle2.putSerializable("list", (ArrayList<PeopleListBean>) pintuanCjPrizeBean.getData().getPeople_list2());
        winPeopleFragment2.setArguments(bundle2);

        myFragmentList = new ArrayList<>();
        myFragmentList.add(winPeopleFragment1);
        myFragmentList.add(winPeopleFragment2);
        myFragmentPagerAdapter = new TabFragmentPagerAdapter(this.getSupportFragmentManager(), myFragmentList, myTitleList);
        mViewPager.setAdapter(myFragmentPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        //显示页面
        ll_winlist_info.setVisibility(View.VISIBLE);
    }
}
