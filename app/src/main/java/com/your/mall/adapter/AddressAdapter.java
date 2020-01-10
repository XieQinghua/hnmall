package com.your.mall.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.your.mall.R;
import com.your.mall.activity.AddAddressActivity;
import com.your.mall.application.MallApplication;
import com.your.mall.bean.AddressBean;
import com.your.mall.http.MallApi;
import com.your.mall.utils.JsonValidator;
import com.your.mall.utils.MyCallBack;
import com.your.mall.utils.XUtils;
import com.your.mall.view.CustomDialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xieqinghua.
 * 创建时间：2016/10/10
 * 类描述：用户收货地址适配器
 * 修改备注：
 */
public class AddressAdapter extends BaseAdapter {
    private ArrayList<AddressBean.DataBean.ListBean> list;
    private Context context;
    private Handler handler;
    private HashMap<Integer, Boolean> isSelectedMap;
    private ArrayList<AddressBean.DataBean.ListBean> beSelectedData = new ArrayList();

    public AddressAdapter(Context context, ArrayList<AddressBean.DataBean.ListBean> list, Handler handler) {
        this.context = context;
        this.list = list;
        this.handler = handler;
        if (isSelectedMap != null)
            isSelectedMap = null;
        isSelectedMap = new HashMap<Integer, Boolean>();
        for (int i = 0; i < list.size(); i++) {
            isSelectedMap.put(i, false);
        }
        // 清除已经选择的项
        if (beSelectedData.size() > 0) {
            beSelectedData.clear();
        }

        for (int i = 0; i < list.size(); i++) {
            getIsSelected().put(i, false);
        }
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder vh;
        if (convertView == null) {
            vh = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_address_info, null);
            vh.tv_consignee = (TextView) convertView.findViewById(R.id.tv_consignee);
            vh.tv_mobile = (TextView) convertView.findViewById(R.id.tv_mobile);
            vh.tv_address = (TextView) convertView.findViewById(R.id.tv_address);
            vh.tv_detail_address = (TextView) convertView.findViewById(R.id.tv_detail_address);
            vh.tv_edit = (TextView) convertView.findViewById(R.id.tv_edit);
            vh.tv_del = (TextView) convertView.findViewById(R.id.tv_del);
            vh.cb_def_addr = (CheckBox) convertView.findViewById(R.id.cb_def_addr);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        vh.tv_consignee.setText(list.get(position).getConsignee());
        vh.tv_mobile.setText(list.get(position).getMobile());
        vh.tv_address.setText(list.get(position).getP_name() + list.get(position).getC_name() + list.get(position).getE_name());
        vh.tv_detail_address.setText(list.get(position).getAddr());
        vh.tv_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AddAddressActivity.class);
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
                intent.putExtra("address_id", list.get(position).getAddress_id() + "");
                intent.putExtra("user_id", MallApplication.getInstance().getUserId(context));
                intent.putExtra("consignee", list.get(position).getConsignee());
                intent.putExtra("p_name", list.get(position).getP_name());
                intent.putExtra("c_name", list.get(position).getC_name());
                intent.putExtra("e_name", list.get(position).getE_name());
                intent.putExtra("p_id", list.get(position).getP_id());
                intent.putExtra("c_id", list.get(position).getC_id());
                intent.putExtra("e_id", list.get(position).getE_id());
                intent.putExtra("addr", list.get(position).getAddr());
                intent.putExtra("zipcode", list.get(position).getZip_code());
                intent.putExtra("tel", list.get(position).getTel());
                intent.putExtra("mobile", list.get(position).getMobile());
                context.startActivity(intent);
            }
        });
        vh.tv_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomDialog.Builder builder = new CustomDialog.Builder(context);
                builder.setMessage("是否删除该地址？");
                builder.setTitle("温馨提示");
                builder.setPositiveButton("确定", new android.content.DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //用户收货地址删除
                        Map<String, String> map = new HashMap<>();
                        map.put("user_id", MallApplication.getInstance().getUserId(context));
                        map.put("address_id", list.get(position).getAddress_id() + "");
                        XUtils.Post(MallApi.APP_ADDRESS_DEL, map, new MyCallBack<String>() {
                            @Override
                            public void onSuccess(String result) {
                                super.onSuccess(result);
                                if (JsonValidator.validate(result)) {
                                    try {
                                        JSONObject json = new JSONObject(result);
                                        if (json.getString("code").equals("0")) {
//                                            list.remove(position);
//                                            AddressAdapter.this.notifyDataSetChanged();
                                            new Thread() {
                                                @Override
                                                public void run() {
                                                    super.run();
                                                    Message msg = Message.obtain();
                                                    msg.what = 1;
                                                    //数据已改变，告诉主线程刷新UI
                                                    handler.sendMessage(msg);
                                                }
                                            }.start();
                                            Toast.makeText(context, "删除成功", Toast.LENGTH_SHORT).show();
                                        } else {
                                            Toast.makeText(context, json.getString("msg"), Toast.LENGTH_SHORT).show();
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                } else {
                                    Toast.makeText(context, R.string.unable_to_get_data, Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onError(Throwable ex, boolean isOnCallback) {
                                super.onError(ex, isOnCallback);
                            }
                        });
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
        });

        vh.cb_def_addr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 当前点击的cb
                boolean cu = !isSelectedMap.get(position);
                // 先将所有的置为false
                for (Integer p : isSelectedMap.keySet()) {
                    isSelectedMap.put(p, false);
                }
                // 再将当前选择cb的实际状态
                isSelectedMap.put(position, cu);
                AddressAdapter.this.notifyDataSetChanged();
                beSelectedData.clear();
                if (cu) {
                    beSelectedData.add(list.get(position));
                    //设置默认收货地址接口
                    Map<String, String> map = new HashMap<>();
                    map.put("user_id", MallApplication.getInstance().getUserId(context));
                    map.put("addr_id", list.get(position).getAddress_id() + "");
                    XUtils.Post(MallApi.APP_ADDRESS_SET_DEF, map, new MyCallBack<String>() {
                        @Override
                        public void onSuccess(String result) {
                            super.onSuccess(result);
                            if (JsonValidator.validate(result)) {
                                try {
                                    JSONObject json = new JSONObject(result);
                                    if (json.getString("code").equals("0")) {
                                        new Thread() {
                                            @Override
                                            public void run() {
                                                super.run();
                                                Message msg = Message.obtain();
                                                msg.what = 1;
                                                //数据已改变，告诉主线程刷新UI
                                                handler.sendMessage(msg);
                                            }
                                        }.start();
//                                        Toast.makeText(context, "默认地址设置成功", Toast.LENGTH_SHORT).show();
                                        AddressAdapter.this.notifyDataSetChanged();
                                    } else {
                                        Toast.makeText(context, json.getString("msg"), Toast.LENGTH_SHORT).show();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            } else {
                                Toast.makeText(context, R.string.unable_to_get_data, Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onError(Throwable ex, boolean isOnCallback) {
                            super.onError(ex, isOnCallback);
                        }
                    });
                } else {
                    Toast.makeText(context, "选择的收货地址已经是默认收货地址了", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });
        if (list.get(position).getDef_addr().equals("1")) {
            isSelectedMap.put(position, true);
        }
        vh.cb_def_addr.setChecked(isSelectedMap.get(position));

        return convertView;
    }

    class ViewHolder {
        TextView tv_consignee, tv_mobile, tv_address, tv_detail_address, tv_edit, tv_del;
        CheckBox cb_def_addr;
    }

    public HashMap<Integer, Boolean> getIsSelected() {
        return isSelectedMap;
    }

    public void setIsSelected(HashMap<Integer, Boolean> isSelected) {
        isSelectedMap = isSelected;
    }

}
