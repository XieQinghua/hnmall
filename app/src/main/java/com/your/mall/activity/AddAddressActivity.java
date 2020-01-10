package com.your.mall.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.your.mall.R;
import com.your.mall.application.MallApplication;
import com.your.mall.base.BaseSwipeBackActivity;
import com.your.mall.http.MallApi;
import com.your.mall.utils.IsIDCard;
import com.your.mall.utils.JsonValidator;
import com.your.mall.utils.MyCallBack;
import com.your.mall.utils.XUtils;
import com.your.mall.view.ChangeAddressPopupwindow;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xieqinghua.
 * 创建时间：2016/9/6
 * 类描述：添加收货地址页面
 * 修改备注：
 */
public class AddAddressActivity extends BaseSwipeBackActivity implements View.OnClickListener {
    private TextView tv_title;
    private TextView et_address;
    private EditText et_name;
    private EditText et_telephone;
    private EditText et_detail_address;
    private String et_name_text, et_telephone_text, et_detail_address_text;
    private ImageView iv_empty1, iv_empty2, iv_empty3;
    private String address_id,
            user_id,
            consignee,
            p_name,
            c_name,
            e_name,
            p_id,
            c_id,
            e_id,
            address,
            zipcode,
            tel,
            mobile;
    private Button btn_submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_address);
        init();
    }

    private void init() {
        tv_title = (TextView) findViewById(R.id.tv_title_center);
        tv_title.setText("新增收货地址");

        et_name = (EditText) findViewById(R.id.et_name);
        et_telephone = (EditText) findViewById(R.id.et_telephone);
        et_detail_address = (EditText) findViewById(R.id.et_detail_address);
        iv_empty1 = (ImageView) findViewById(R.id.iv_empty1);
        iv_empty2 = (ImageView) findViewById(R.id.iv_empty2);
        iv_empty3 = (ImageView) findViewById(R.id.iv_empty3);
        et_address = (TextView) findViewById(R.id.et_address);

        btn_submit = (Button) findViewById(R.id.btn_submit);

        iv_empty1.setOnClickListener(this);
        iv_empty2.setOnClickListener(this);
        iv_empty3.setOnClickListener(this);
        clearText(et_name, iv_empty1);
        clearText(et_telephone, iv_empty2);
        clearText(et_detail_address, iv_empty3);
        //收货地址弹出POP窗口
        et_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChangeAddressPopupwindow mChangeAddressPopupwindow = new ChangeAddressPopupwindow(AddAddressActivity.this);
                mChangeAddressPopupwindow.setAddress("湖南省", "长沙市", "芙蓉区");
                mChangeAddressPopupwindow.showAtLocation(et_address, Gravity.BOTTOM, 0, 0);
                mChangeAddressPopupwindow.setAddresskListener(new ChangeAddressPopupwindow.OnAddressCListener() {

                    @Override
                    public void onClick(String province, String city, String area,
                                        String province_id, String city_id, String area_id) {
                        et_address.setText(province + city + area);

                        p_name = province;
                        c_name = city;
                        e_name = area;

                        p_id = province_id;
                        c_id = city_id;
                        e_id = area_id;

                        et_address.setTextColor(getResources().getColor(R.color.mall_content_color));
                    }
                });
            }
        });

        if (getIntent().getStringExtra("address_id") != null) {
            address_id = getIntent().getStringExtra("address_id");
            user_id = getIntent().getStringExtra("user_id");
            consignee = getIntent().getStringExtra("consignee");
            p_name = getIntent().getStringExtra("p_name");
            c_name = getIntent().getStringExtra("c_name");
            e_name = getIntent().getStringExtra("e_name");
            p_id = getIntent().getStringExtra("p_id");
            c_id = getIntent().getStringExtra("c_id");
            e_id = getIntent().getStringExtra("e_id");
            address = getIntent().getStringExtra("addr");
            zipcode = getIntent().getStringExtra("zipcode");
            tel = getIntent().getStringExtra("tel");
            mobile = getIntent().getStringExtra("mobile");
            et_name.setText(consignee);
            et_name.setSelection(et_name.getText().length());//光标显示在末尾
            et_telephone.setText(mobile);
            et_address.setText(p_name + c_name + e_name);
            et_address.setTextColor(getResources().getColor(R.color.mall_content_color));
            et_detail_address.setText(address);
            iv_empty1.setVisibility(View.INVISIBLE);
            iv_empty2.setVisibility(View.INVISIBLE);
            iv_empty3.setVisibility(View.INVISIBLE);
        }
        btn_submit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_empty1:
                et_name.setText("");
                break;
            case R.id.iv_empty2:
                et_telephone.setText("");
                break;
            case R.id.iv_empty3:
                et_detail_address.setText("");
                break;
            case R.id.btn_submit:
                submitAddress();
                break;
        }
    }

    /**
     * 添加修改会员收货地址
     */
    private void submitAddress() {
        et_name_text = et_name.getText().toString().trim();
        et_telephone_text = et_telephone.getText().toString().trim();
        et_detail_address_text = et_detail_address.getText().toString().trim();
        if (et_name_text.equals("")) {
            Toast.makeText(AddAddressActivity.this, "请输入收货人姓名", Toast.LENGTH_SHORT).show();
            return;
        }
        if (et_telephone_text.equals("")) {
            Toast.makeText(AddAddressActivity.this, "请输入手机号码", Toast.LENGTH_SHORT).show();
            return;
        }
        if (et_address.getText().toString().trim().equals("请输入收货人地址")) {
            Toast.makeText(AddAddressActivity.this, "请输入收货人地址", Toast.LENGTH_SHORT).show();
            return;
        }
        if (et_detail_address_text.equals("")) {
            Toast.makeText(AddAddressActivity.this, "请输入详细地址", Toast.LENGTH_SHORT).show();
            return;
        }
        //判断手机号码正确性
        if (!IsIDCard.isValidMobileNo(et_telephone_text)) {
            Toast.makeText(AddAddressActivity.this, "错误的手机号", Toast.LENGTH_SHORT).show();
            return;
        }
        //调用接口
        Map<String, String> map = new HashMap<>();
        if (address_id != null) {
            map.put("address_id", address_id);
        } else {
            map.put("address_id", "");
        }
//                address_id	string	地址id
//                user_id	string	用户id
//                consignee	string	会员名称
//                p_name	string	省份名称
//                c_name	string	城市名称
//                e_name	string	地区名称
//                p_id	string	省份id
//                c_id	string	城市id
//                e_id	string	地区id
//                addr	string	详细地区
//                zipcode	string	邮编
//                tel	string	电话
//                mobile	string	手机
        map.put("user_id", MallApplication.getInstance().getUserId(AddAddressActivity.this));
        map.put("consignee", et_name_text);
        map.put("p_name", p_name);
        map.put("c_name", c_name);
        map.put("e_name", e_name);
        if (p_id != null) {
            map.put("p_id", p_id);
        } else {
            map.put("p_id", "");
        }
        if (c_id != null) {
            map.put("c_id", c_id);
        } else {
            map.put("c_id", "");
        }
        if (e_id != null) {
            map.put("e_id", e_id);
        } else {
            map.put("e_id", "");
        }
        map.put("address", et_detail_address_text);
        if (zipcode != null) {
            map.put("zipcode", zipcode);
        } else {
            map.put("zipcode", "");
        }
        if (tel != null) {
            map.put("tel", tel);
        } else {
            map.put("tel", "");
        }
        map.put("mobile", et_telephone_text);
        showDialog(LOADING_DIALOG);
        XUtils.Post(MallApi.APP_ADDRESS_ADD, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                Log.e("result", result);
                if (JsonValidator.validate(result)) {
                    try {
                        JSONObject json = new JSONObject(result);
                        if (json.getString("code").equals("0")) {
                            Toast.makeText(AddAddressActivity.this, json.getString("msg"), Toast.LENGTH_SHORT).show();
                            Intent intent = AddAddressActivity.this.getIntent();
                            //设置当前Activity的结果码
                            AddAddressActivity.this.setResult(0, intent);
                            //关闭当前的Activity
                            AddAddressActivity.this.finish();
                        } else {
                            Toast.makeText(AddAddressActivity.this, json.getString("msg"), Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(AddAddressActivity.this, R.string.unable_to_get_data, Toast.LENGTH_SHORT).show();
                }
                removeDialog();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
            }
        });

    }
}
