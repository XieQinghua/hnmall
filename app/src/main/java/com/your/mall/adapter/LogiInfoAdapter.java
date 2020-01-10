package com.your.mall.adapter;

import android.content.Context;
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
import com.your.mall.bean.LogiInfoBean;
import com.your.mall.http.MallApi;
import com.your.mall.utils.JsonValidator;
import com.your.mall.utils.MyCallBack;
import com.your.mall.utils.XUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xieqinghua.
 * 创建时间：2016/10/10
 * 类描述：物流信息
 * 修改备注：
 */
public class LogiInfoAdapter extends BaseAdapter {
    private ArrayList<LogiInfoBean.DataBean> list;
    private Context context;


    public LogiInfoAdapter(Context context, ArrayList<LogiInfoBean.DataBean> list) {
        this.context = context;
        this.list = list;

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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_logi_info, null);
            vh.tv_acceptstation = (TextView) convertView.findViewById(R.id.tv_acceptstation);
            vh.tv_accepttime = (TextView) convertView.findViewById(R.id.tv_accepttime);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        vh.tv_acceptstation.setText(list.get(position).getAcceptStation());
        vh.tv_accepttime.setText(list.get(position).getAcceptTime());

        return convertView;
    }

    class ViewHolder {
        TextView tv_acceptstation, tv_accepttime;
    }
}
