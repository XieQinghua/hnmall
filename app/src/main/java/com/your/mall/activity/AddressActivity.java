package com.your.mall.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.your.mall.R;
import com.your.mall.adapter.AddressAdapter;
import com.your.mall.application.MallApplication;
import com.your.mall.base.BaseSwipeBackActivity;
import com.your.mall.bean.AddressBean;
import com.your.mall.http.MallApi;
import com.your.mall.utils.JsonValidator;
import com.your.mall.utils.MyCallBack;
import com.your.mall.utils.ParseUtils;
import com.your.mall.utils.XUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xieqinghua.
 * 创建时间：2016/9/2
 * 类描述：地址管理页面
 * 修改备注：
 */
public class AddressActivity extends BaseSwipeBackActivity implements View.OnClickListener {
    private TextView tv_title;
    private Button btn_add_address;
    private ListView lv_address;
    private AddressAdapter myAddressAdapter;
    private ArrayList<AddressBean.DataBean.ListBean> list;
    private TextView tv_noaddress;
    Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case 1:
                    getData();
                    break;
            }
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        //解决华为虚拟键遮挡布局底部按键
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        tv_title = (TextView) findViewById(R.id.tv_title_center);
        tv_title.setText("地址管理");
        lv_address = (ListView) findViewById(R.id.lv_address);
        tv_noaddress = (TextView) findViewById(R.id.tv_noaddress);
        btn_add_address = (Button) findViewById(R.id.btn_add_address);
        btn_add_address.setOnClickListener(this);

        if (getIntent().getStringExtra("tag") != null) {
            lv_address.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = AddressActivity.this.getIntent();
                    intent.putExtra("addr_id", list.get(position).getAddress_id() + "");
                    intent.putExtra("consignee", list.get(position).getConsignee());
                    intent.putExtra("mobile", list.get(position).getMobile());
                    intent.putExtra("address", list.get(position).getP_name() + list.get(position).getC_name()
                            + list.get(position).getE_name() + list.get(position).getAddr());
                    //设置当前Activity的结果码
                    AddressActivity.this.setResult(0, intent);
                    //关闭当前的Activity
                    AddressActivity.this.finish();
                }
            });
        }

        getData();

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void getData() {
        Map<String, String> map = new HashMap<>();
        map.put("user_id", MallApplication.getInstance().getUserId(AddressActivity.this));
        showDialog(LOADING_DIALOG);
        XUtils.Get(MallApi.APP_ADDRESS, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                if (JsonValidator.validate(result)) {
                    Log.e("address", result);
                    AddressBean addressBean = ParseUtils.parseAddressBean(result);
                    if (addressBean.getCode().equals("0")) {
                        if (addressBean.getData().getList() != null && addressBean.getData().getList().size() != 0) {
                            list = (ArrayList<AddressBean.DataBean.ListBean>) addressBean.getData().getList();
                            myAddressAdapter = new AddressAdapter(AddressActivity.this, list, handler);
                            lv_address.setAdapter(myAddressAdapter);
                            lv_address.setVisibility(View.VISIBLE);
                            tv_noaddress.setVisibility(View.GONE);
                        } else {
                            lv_address.setVisibility(View.GONE);
                            tv_noaddress.setVisibility(View.VISIBLE);
                        }
                    } else {
                        Toast.makeText(AddressActivity.this, addressBean.getMsg(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(AddressActivity.this, R.string.unable_to_get_data, Toast.LENGTH_SHORT).show();
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
            case R.id.btn_add_address:
                Intent intent = new Intent(AddressActivity.this, AddAddressActivity.class);
                startActivityForResult(intent, 1);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1 && resultCode == 0) {
            getData();
        }
    }
}

