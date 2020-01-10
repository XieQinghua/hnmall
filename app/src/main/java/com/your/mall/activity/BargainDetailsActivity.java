package com.your.mall.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.your.mall.R;
import com.your.mall.adapter.BargainPeopleAdapter;
import com.your.mall.base.BaseSwipeBackActivity;
import com.your.mall.bean.BargainDetailsBean;
import com.your.mall.bean.ShareInfoBean;
import com.your.mall.http.MallApi;
import com.your.mall.utils.ImgUtils;
import com.your.mall.utils.JsonValidator;
import com.your.mall.utils.MyCallBack;
import com.your.mall.utils.ParseUtils;
import com.your.mall.utils.XUtils;
import com.your.mall.utils.onekeyshare.OneKeyShareBuilder;
import com.your.mall.view.CircleImageView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xieqinghua.
 * 创建时间：2016/11/14
 * 类描述：砍价详情
 * 修改备注：
 */
public class BargainDetailsActivity extends BaseSwipeBackActivity implements View.OnClickListener {
    private static final String TAG = "BargainDetailsActivity";
    private TextView tv_title;
    private String activity_id, item_id, status;
    private Button btn_invite;
    private BargainDetailsBean bargainDetailsBean;
    private ShareInfoBean shareInfoBean;
    private String bargain_id;
    private LinearLayout ll_bargain_info;
    private ImageView iv_bargain_banner, iv_bargain_rule, iv_mask;
    private CircleImageView iv_head_img;
    private TextView tv_goods_title, tv_activity_price, tv_bargain_price, tv_price;
    private TextView tv_bargain_buy;

    private ListView lv_people;
    private ArrayList<ShareInfoBean.DataBean.PeopleBean> list;
    private BargainPeopleAdapter myBargainPeopleAdapter;

    public Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case 1:
                    //获取分享信息
                    getShareInfo();
                    break;
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bargain_details);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        init();
    }

    private void init() {
        tv_title = (TextView) findViewById(R.id.tv_title_center);
        tv_title.setText("砍价详情");
        activity_id = getIntent().getStringExtra("activity_id");
        item_id = getIntent().getStringExtra("item_id");
        status = getIntent().getStringExtra("status");

        ll_bargain_info = (LinearLayout) findViewById(R.id.ll_bargain_info);
        iv_bargain_banner = (ImageView) findViewById(R.id.iv_bargain_banner);
        iv_bargain_rule = (ImageView) findViewById(R.id.iv_bargain_rule);
        iv_mask = (ImageView) findViewById(R.id.iv_mask);
        iv_head_img = (CircleImageView) findViewById(R.id.iv_head_img);
        tv_goods_title = (TextView) findViewById(R.id.tv_goods_title);
        tv_activity_price = (TextView) findViewById(R.id.tv_activity_price);
        tv_bargain_price = (TextView) findViewById(R.id.tv_bargain_price);
        tv_price = (TextView) findViewById(R.id.tv_price);
        tv_bargain_buy = (TextView) findViewById(R.id.tv_bargain_buy);

        iv_bargain_rule.setOnClickListener(this);
        btn_invite = (Button) findViewById(R.id.btn_invite);
        btn_invite.setOnClickListener(this);
        lv_people = (ListView) findViewById(R.id.lv_people);
        getData();
        getBargainId();
    }

    private void getData() {
        Map<String, String> map = new HashMap<>();
        map.put("activity_id", activity_id);
        map.put("item_id", item_id);
//        showDialog(LOADING_DIALOG);
        XUtils.Get(MallApi.APP_BARGAIN_VIEW, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                Log.e(TAG, result);
                if (JsonValidator.validate(result)) {
                    try {
                        JSONObject json = new JSONObject(result);
                        if (json.getString("code").equals("0")) {
                            bargainDetailsBean = ParseUtils.parseBargainDetailsBean(result);
                        } else {
                            Toast.makeText(BargainDetailsActivity.this, json.getString("msg"), Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(BargainDetailsActivity.this, R.string.unable_to_get_data, Toast.LENGTH_SHORT).show();
                }
//                removeDialog();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
            }
        });
    }

    private void getBargainId() {
        Map<String, String> map = new HashMap<>();
        map.put("activity_id", activity_id);
        map.put("item_id", item_id);
        XUtils.Get(MallApi.APP_BARGAIN_START, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                Log.e(TAG, result);
                if (JsonValidator.validate(result)) {
                    try {
                        JSONObject json = new JSONObject(result);
                        if (json.getString("code").equals("0")) {
                            JSONObject data = json.getJSONObject("data");
                            bargain_id = data.getString("id") + "";
                            new Thread() {
                                @Override
                                public void run() {
                                    super.run();
                                    Message msg = Message.obtain();
                                    msg.what = 1;
                                    handler.sendMessage(msg);
                                }
                            }.start();
                        } else {
                            Toast.makeText(BargainDetailsActivity.this, json.getString("msg"), Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(BargainDetailsActivity.this, R.string.unable_to_get_data, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
            }
        });
    }

    private void getShareInfo() {
        Map<String, String> map = new HashMap<>();
        map.put("bargain_id", bargain_id);
        showDialog(LOADING_DIALOG);
        XUtils.Get(MallApi.APP_BARGAIN_SHARE, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                Log.e("share", result);
                if (JsonValidator.validate(result)) {
                    try {
                        JSONObject json = new JSONObject(result);
                        if (json.getString("code").equals("0")) {
                            shareInfoBean = ParseUtils.parseShareInfoBean(result);

                            ll_bargain_info.setVisibility(View.VISIBLE);
                            btn_invite.setVisibility(View.VISIBLE);

                            ImgUtils.setRectangleImageNoCache(iv_bargain_banner, shareInfoBean.getData().getActivity_goods_banner());
                            ImgUtils.setCircleImage(iv_head_img, shareInfoBean.getData().getHeadimgurl());
                            tv_goods_title.setText(shareInfoBean.getData().getActivity_goods_title());
                            tv_activity_price.setText("￥" + shareInfoBean.getData().getActivity_price());
                            String text = "现价" + shareInfoBean.getData().getBargain_price() + "元";
                            SpannableStringBuilder style = new SpannableStringBuilder(text);
                            style.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.paleRed)),
                                    text.indexOf(shareInfoBean.getData().getBargain_price()),
                                    text.indexOf(shareInfoBean.getData().getBargain_price()) + shareInfoBean.getData().getBargain_price().length(),
                                    Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
                            tv_bargain_price.setText(style);
                            tv_price.setText("￥" + shareInfoBean.getData().getPrice());

                            //1进行中2已完成3已失效
                            if (status.equals("1")) {
                                btn_invite.setText("邀请好友砍价");
                                btn_invite.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        share();
                                    }
                                });
                                tv_bargain_buy.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent intent = new Intent(BargainDetailsActivity.this, OrderTallyActivity.class);
                                        intent.putExtra("sku_id", shareInfoBean.getData().getDefault_sku_id());
                                        intent.putExtra("quantity", "1");
                                        intent.putExtra("obj_type", "bargain");
                                        intent.putExtra("activity_id", shareInfoBean.getData().getActivity_id());
                                        intent.putExtra("mode", "fastbuy");
                                        startActivity(intent);
                                    }
                                });
                            } else if (status.equals("2")) {

                            } else if (status.equals("3")) {
                                iv_mask.setVisibility(View.VISIBLE);
                                btn_invite.setText("我要发起砍价");
                                btn_invite.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent intent = new Intent(BargainDetailsActivity.this, BargainActivity.class);
                                        startActivity(intent);
                                    }
                                });
                            }

                            if (shareInfoBean.getData().getPeople() != null && shareInfoBean.getData().getPeople().size() != 0) {
                                list = (ArrayList<ShareInfoBean.DataBean.PeopleBean>) shareInfoBean.getData().getPeople();
                                myBargainPeopleAdapter = new BargainPeopleAdapter(BargainDetailsActivity.this, list);
                                lv_people.setAdapter(myBargainPeopleAdapter);
                            }
                        } else {
                            Toast.makeText(BargainDetailsActivity.this, json.getString("msg"), Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    removeDialog();
                } else {
                    Toast.makeText(BargainDetailsActivity.this, R.string.unable_to_get_data, Toast.LENGTH_SHORT).show();
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
        switch (v.getId()) {
            case R.id.iv_bargain_rule:
                startActivity(new Intent(BargainDetailsActivity.this, BargainRuleActivity.class));
                break;
        }
    }

    /**
     * 分享
     */
    private void share() {
        OneKeyShareBuilder oneKeyShareBuilder = new OneKeyShareBuilder();
        String text = shareInfoBean.getData().getShareList().getShareText();
        oneKeyShareBuilder.setText(text)
                .setTitleUrl(shareInfoBean.getData().getShareList().getShareUrl())
                .setTitle(shareInfoBean.getData().getShareList().getShareTitle())
                .setContext(BargainDetailsActivity.this)
                .setImageUrl(shareInfoBean.getData().getShareList().getShareImage())
                .setUrl(shareInfoBean.getData().getShareList().getShareUrl())
                .showShareDialog();
    }
}
