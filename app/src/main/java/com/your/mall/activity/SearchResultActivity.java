package com.your.mall.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.your.mall.R;
import com.your.mall.adapter.GoodsListAdapter;
import com.your.mall.base.BaseSwipeBackActivity;
import com.your.mall.bean.GoodsListBean;
import com.your.mall.http.MallApi;
import com.your.mall.utils.JsonValidator;
import com.your.mall.utils.MyCallBack;
import com.your.mall.utils.ParseUtils;
import com.your.mall.utils.XUtils;
import com.your.mall.view.XList.XListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xieqinghua.
 * 创建时间：2016/9/22
 * 类描述：搜素结果页面
 * 修改备注：
 */
public class SearchResultActivity extends BaseSwipeBackActivity implements View.OnClickListener, XListView.IXListViewListener {
    private EditText et_search;
    private ImageView iv_empty;
    private XListView lv_search_result;
    private ImageView iv_no_product;
    private String search_keywords;
    private String cat_id;
    private ArrayList<GoodsListBean.DataBean.ListBean> list;
    private ArrayList<GoodsListBean.DataBean.ListBean> lists = new ArrayList<>();
    private GoodsListAdapter myGoodsListAdapter;

    private Handler mHandler;
    private int page_no = 1, page_size = 10, maxPage;
    private String lastUpdateTime;//最后更新时间

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seach_result);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        init();
    }

    private void init() {
        et_search = (EditText) findViewById(R.id.et_search);
//        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
//        imm.hideSoftInputFromWindow(et_search.getWindowToken(),0);
        iv_empty = (ImageView) findViewById(R.id.iv_empty);
        iv_empty.setOnClickListener(this);
        //监听EditText的文字变化
        clearText(et_search, iv_empty);

        //监听键盘搜索按键
        et_search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEND || (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
                    //搜索动作
                    if (!et_search.getText().toString().trim().equals("")) {
                        search_keywords = et_search.getText().toString().trim();
                    } else {
                        return false;
                    }

                    //点击搜索按键先隐藏键盘
                    ((InputMethodManager) et_search.getContext().getSystemService(Context.INPUT_METHOD_SERVICE))
                            .hideSoftInputFromWindow(SearchResultActivity.this
                                            .getCurrentFocus()
                                            .getWindowToken(),
                                    InputMethodManager.HIDE_NOT_ALWAYS);
                    lists.clear();
                    getData();
                    return true;
                }
                return false;
            }
        });
        if (getIntent().getStringExtra("search_keywords") != null) {
            search_keywords = getIntent().getStringExtra("search_keywords");
        } else {
            search_keywords = "";
        }
        if (getIntent().getStringExtra("cat_id") != null) {
            cat_id = getIntent().getStringExtra("cat_id");
        } else {
            cat_id = "";
        }
        lv_search_result = (XListView) findViewById(R.id.lv_search_result);
        lv_search_result.hideHeader();//隐藏头部
        lv_search_result.setPullLoadEnable(true);
        lv_search_result.setXListViewListener(this);
        mHandler = new Handler();

        iv_no_product = (ImageView) findViewById(R.id.iv_no_product);

        myGoodsListAdapter = new GoodsListAdapter(SearchResultActivity.this, lists);
        lv_search_result.setAdapter(myGoodsListAdapter);
        lv_search_result.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(SearchResultActivity.this, GoodsDetailsActivity.class);
                intent.putExtra("item_id", lists.get(position - 1).getGoods_id());
                startActivity(intent);
            }
        });

        //调用搜索接口
        getData();
    }

    private void getData() {
        String url = MallApi.APP_SEARCH;
        Map<String, String> map = new HashMap<>();
        if (!search_keywords.equals("")) {
            map.put("search_keywords", search_keywords);
        }
        if (!cat_id.equals("")) {
            map.put("cat_id", cat_id);
        }
        map.put("page_size", page_size + "");
        map.put("page_no", page_no + "");
        map.put("order", "asc");
        map.put("sort", "");

        showDialog(LOADING_DIALOG);

        XUtils.Get(url, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
//                Log.e("result", result);
                if (JsonValidator.validate(result)) {
                    GoodsListBean goodsListBean = ParseUtils.parseGoodsListBean(result);
                    if (goodsListBean.getCode().equals("0")) {
                        int count = goodsListBean.getData().getCount();
                        maxPage = (int) Math.ceil((double) count / (double) page_size);//逢小数点进1，向上取整
                        if (goodsListBean.getData().getList() != null && goodsListBean.getData().getList().size() != 0) {
                            list = (ArrayList<GoodsListBean.DataBean.ListBean>) goodsListBean.getData().getList();
                            lists.addAll(list);
                            myGoodsListAdapter.notifyDataSetChanged();
                        } else {
                            lv_search_result.setVisibility(View.GONE);
                            iv_no_product.setVisibility(View.VISIBLE);
                        }
                    } else {
                        Toast.makeText(SearchResultActivity.this, goodsListBean.getMsg(), Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(SearchResultActivity.this, R.string.unable_to_get_data, Toast.LENGTH_SHORT).show();
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
            case R.id.iv_empty:
                //清空et_search
                et_search.setText("");
                break;
        }
    }

    private void onLoad() {
        lv_search_result.hideHeader();//隐藏头部
        lv_search_result.stopRefresh();
        lv_search_result.stopLoadMore();
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("a hh:mm");
//        Date date = new Date(System.currentTimeMillis());
//        lastUpdateTime = simpleDateFormat.format(date) + "";
//        lv_search_result.setRefreshTime(lastUpdateTime);
    }

    @Override
    public void onRefresh() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
//                lists.clear();
//                page_no = 1;
//                getData();
                onLoad();
            }
        }, 0);
    }

    @Override
    public void onLoadMore() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if ((maxPage - page_no) > 0) {
                    page_no += 1;
                    getData();
                } else {
                    onLoad();
                    Toast.makeText(SearchResultActivity.this, R.string.no_more_data, Toast.LENGTH_SHORT).show();
                }
            }
        }, 1000);
    }
}
