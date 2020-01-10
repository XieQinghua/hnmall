package com.your.mall.view;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.your.mall.R;
import com.your.mall.view.wheelview.OnWheelChangedListener;
import com.your.mall.view.wheelview.OnWheelScrollListener;
import com.your.mall.view.wheelview.WheelView;
import com.your.mall.view.wheelview.adapter.AbstractWheelTextAdapter1;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xieqinghua.
 * 创建时间：2016/9/6
 * 类描述：自定义省份选择器Popwindow
 * 修改备注：
 */
public class ChangeAddressPopupwindow extends PopupWindow implements View.OnClickListener {

    private WheelView wvProvince;
    private WheelView wvCitys;
    private WheelView wvArea;
    private View lyChangeAddress;
    private View lyChangeAddressChild;
    private TextView btnSure;
    private TextView btnCancel;

    private Context context;
    private JSONObject mJsonObj;
    /**
     * 所有省
     */
    private String[] mProvinceDatas;
    /**
     * 所有省ID
     */
    private String[] mProvinceIdDatas;
    /**
     * key - 省 value - 市
     */
    private Map<String, String[]> mCitisDatasMap = new HashMap<String, String[]>();
    /**
     * key - p_id
     * value - c_id
     */
    private Map<String, String[]> mCitisDatasIdMap = new HashMap<String, String[]>();
    /**
     * key - 市 values - 区
     */
    private Map<String, String[]> mAreaDatasMap = new HashMap<String, String[]>();
    /**
     * key - c_id
     * values - e_id
     */
    private Map<String, String[]> mAreaDatasIdMap = new HashMap<String, String[]>();


    private ArrayList<String> arrProvinces = new ArrayList<String>();
    private ArrayList<String> arrCitys = new ArrayList<String>();
    private ArrayList<String> arrAreas = new ArrayList<String>();
    private AddressTextAdapter provinceAdapter;
    private AddressTextAdapter cityAdapter;
    private AddressTextAdapter areaAdapter;

    private ArrayList<String> arrProvincesId = new ArrayList<String>();
    private ArrayList<String> arrCitysId = new ArrayList<String>();
    private ArrayList<String> arrAreasId = new ArrayList<String>();

    private String strProvince = "湖南省";
    private String strCity = "长沙市";
    private String strArea = "芙蓉区";

    private String strProvinceId = "430000";
    private String strCityId = "430100";
    private String strAreaId = "430102";

    private OnAddressCListener onAddressCListener;

    private int maxsize = 14;
    private int minsize = 12;

    public ChangeAddressPopupwindow(final Context context) {
        super(context);
        this.context = context;
        View view = View.inflate(context, R.layout.edit_changeaddress_pop_layout, null);
        wvProvince = (WheelView) view.findViewById(R.id.wv_address_province);
        wvCitys = (WheelView) view.findViewById(R.id.wv_address_city);
        wvArea = (WheelView) view.findViewById(R.id.wv_address_area);
        lyChangeAddress = view.findViewById(R.id.ly_myinfo_changeaddress);
        lyChangeAddressChild = view.findViewById(R.id.ly_myinfo_changeaddress_child);
        btnSure = (TextView) view.findViewById(R.id.btn_myinfo_sure);
        btnCancel = (TextView) view.findViewById(R.id.btn_myinfo_cancel);
        //设置SelectPicPopupWindow的View
        this.setContentView(view);
        //设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        //设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        //设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        //设置SelectPicPopupWindow点击外部消失
        this.setOutsideTouchable(true);
        //实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0x00000000);
        //设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);
        //设置SelectPicPopupWindow弹出窗体动画效果
        lyChangeAddress.startAnimation(AnimationUtils.loadAnimation(context, R.anim.push_bottom_in));
        //因为华为等机型是虚拟按键的,加上以下设置防止挡住按键.
        this.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        lyChangeAddressChild.setOnClickListener(this);
        btnSure.setOnClickListener(this);
        btnCancel.setOnClickListener(this);

        initJsonData();
        initDatas();


        initProvinces();
        provinceAdapter = new AddressTextAdapter(context, arrProvinces, getProvinceItem(strProvince), maxsize, minsize);
        wvProvince.setVisibleItems(5);
        wvProvince.setViewAdapter(provinceAdapter);
        wvProvince.setCurrentItem(getProvinceItem(strProvince));

        initCitys(mCitisDatasMap.get(strProvince), mCitisDatasIdMap.get(strProvinceId));
        cityAdapter = new AddressTextAdapter(context, arrCitys, getCityItem(strCity), maxsize, minsize);
        wvCitys.setVisibleItems(5);
        wvCitys.setViewAdapter(cityAdapter);
        wvCitys.setCurrentItem(getCityItem(strCity));

        initAreas(mAreaDatasMap.get(strCity), mAreaDatasIdMap.get(strCityId));
        areaAdapter = new AddressTextAdapter(context, arrAreas, getAreaItem(strArea), maxsize, minsize);
        wvArea.setVisibleItems(5);
        wvArea.setViewAdapter(areaAdapter);
        wvArea.setCurrentItem(getAreaItem(strArea));

        wvProvince.addChangingListener(new OnWheelChangedListener() {

            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                // TODO Auto-generated method stub
                String currentText = (String) provinceAdapter.getItemText(wheel.getCurrentItem());
                strProvince = currentText;
                setTextviewSize(currentText, provinceAdapter);

                String currentIdText = arrProvincesId.get(wheel.getCurrentItem());
                strProvinceId = currentIdText;

                String[] citys = mCitisDatasMap.get(currentText);
                String[] c_ids = mCitisDatasIdMap.get(currentIdText);

                initCitys(citys, c_ids);
                cityAdapter = new AddressTextAdapter(context, arrCitys, 0, maxsize, minsize);
                wvCitys.setVisibleItems(5);
                wvCitys.setViewAdapter(cityAdapter);
                wvCitys.setCurrentItem(0);
                setTextviewSize("0", cityAdapter);

                //根据市，地区联动
                String[] areas = mAreaDatasMap.get(citys[0]);
                String[] e_ids = mAreaDatasIdMap.get(c_ids[0]);
                initAreas(areas, e_ids);
                areaAdapter = new AddressTextAdapter(context, arrAreas, 0, maxsize, minsize);
                wvArea.setVisibleItems(5);
                wvArea.setViewAdapter(areaAdapter);
                wvArea.setCurrentItem(0);
                setTextviewSize("0", areaAdapter);
            }
        });

        wvProvince.addScrollingListener(new OnWheelScrollListener() {

            @Override
            public void onScrollingStarted(WheelView wheel) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onScrollingFinished(WheelView wheel) {
                // TODO Auto-generated method stub
                String currentText = (String) provinceAdapter.getItemText(wheel.getCurrentItem());
                setTextviewSize(currentText, provinceAdapter);
            }
        });

        wvCitys.addChangingListener(new OnWheelChangedListener() {

            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                // TODO Auto-generated method stub
                String currentText = (String) cityAdapter.getItemText(wheel.getCurrentItem());
                strCity = currentText;
                setTextviewSize(currentText, cityAdapter);

                String currentIdText = arrCitysId.get(wheel.getCurrentItem());
                strCityId = currentIdText;

                //根据市，地区联动
                String[] areas = mAreaDatasMap.get(currentText);
                String[] e_ids = mAreaDatasIdMap.get(currentIdText);
                initAreas(areas, e_ids);
                areaAdapter = new AddressTextAdapter(context, arrAreas, 0, maxsize, minsize);
                wvArea.setVisibleItems(5);
                wvArea.setViewAdapter(areaAdapter);
                wvArea.setCurrentItem(0);
                setTextviewSize("0", areaAdapter);
            }
        });

        wvCitys.addScrollingListener(new OnWheelScrollListener() {

            @Override
            public void onScrollingStarted(WheelView wheel) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onScrollingFinished(WheelView wheel) {
                // TODO Auto-generated method stub
                String currentText = (String) cityAdapter.getItemText(wheel.getCurrentItem());
                setTextviewSize(currentText, cityAdapter);
            }
        });

        wvArea.addChangingListener(new OnWheelChangedListener() {

            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                // TODO Auto-generated method stub
                String currentText = (String) areaAdapter.getItemText(wheel.getCurrentItem());
                strArea = currentText;
                setTextviewSize(currentText, cityAdapter);

                String currentIdText = arrAreasId.get(wheel.getCurrentItem());
                strAreaId = currentIdText;
            }
        });

        wvArea.addScrollingListener(new OnWheelScrollListener() {

            @Override
            public void onScrollingStarted(WheelView wheel) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onScrollingFinished(WheelView wheel) {
                // TODO Auto-generated method stub
                String currentText = (String) areaAdapter.getItemText(wheel.getCurrentItem());
                setTextviewSize(currentText, areaAdapter);
            }
        });


    }


    private class AddressTextAdapter extends AbstractWheelTextAdapter1 {
        ArrayList<String> list;

        protected AddressTextAdapter(Context context, ArrayList<String> list, int currentItem, int maxsize, int minsize) {
            super(context, R.layout.item_birth_year, NO_RESOURCE, currentItem, maxsize, minsize);
            this.list = list;
            setItemTextResource(R.id.tempValue);
        }

        @Override
        public View getItem(int index, View cachedView, ViewGroup parent) {
            View view = super.getItem(index, cachedView, parent);
            return view;
        }

        @Override
        public int getItemsCount() {
            return list.size();
        }

        @Override
        protected CharSequence getItemText(int index) {
            return list.get(index) + "";
        }
    }

    /**
     * 设置字体大小
     *
     * @param curriteItemText
     * @param adapter
     */
    public void setTextviewSize(String curriteItemText, AddressTextAdapter adapter) {
        ArrayList<View> arrayList = adapter.getTestViews();
        int size = arrayList.size();
        String currentText;
        for (int i = 0; i < size; i++) {
            TextView textvew = (TextView) arrayList.get(i);
            currentText = textvew.getText().toString();
            if (curriteItemText.equals(currentText)) {
                textvew.setTextSize(14);
            } else {
                textvew.setTextSize(12);
            }
        }
    }

    public void setAddresskListener(OnAddressCListener onAddressCListener) {
        this.onAddressCListener = onAddressCListener;
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        if (v == btnSure) {
            if (onAddressCListener != null) {
                onAddressCListener.onClick(strProvince, strCity, strArea, strProvinceId, strCityId, strAreaId);
            }
        } else if (v == btnCancel) {

        } else if (v == lyChangeAddressChild) {
            return;
        } else {
//			dismiss();
        }
        dismiss();
    }

    /**
     * 回调接口
     *
     * @author Administrator
     */
    public interface OnAddressCListener {
        public void onClick(String province, String city, String area, String province_id, String city_id, String area_id);
    }

    /**
     * 从文件中读取地址数据
     */
    private void initJsonData() {
        try {
            StringBuffer sb = new StringBuffer();
            InputStream is = context.getClass().getClassLoader().getResourceAsStream("assets/" + "area.json");
            int len = -1;
            byte[] buf = new byte[4096];
            while ((len = is.read(buf)) != -1) {
                sb.append(new String(buf, 0, len, "utf-8"));
            }
            is.close();
            mJsonObj = new JSONObject(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 解析整个Json对象，完成后释放Json对象的内存
     */
    private void initDatas() {
        try {
            JSONArray jsonArray = mJsonObj.getJSONArray("area");
            mProvinceDatas = new String[jsonArray.length()];
            mProvinceIdDatas = new String[jsonArray.length()];
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonP = jsonArray.getJSONObject(i);// 每个省的json对象
                String province = jsonP.getString("value");// 省名字
                String p_id = jsonP.getString("id");// p_id
                mProvinceDatas[i] = province;
                mProvinceIdDatas[i] = p_id;
                JSONArray jsonCs = null;
                try {
                    /**
                     * Throws JSONException if the mapping doesn't exist or is
                     * not a JSONArray.
                     */
                    jsonCs = jsonP.getJSONArray("children");
                } catch (Exception e1) {
                    continue;
                }
                String[] mCitiesDatas = new String[jsonCs.length()];
                String[] mCitiesIdDatas = new String[jsonCs.length()];
                for (int j = 0; j < jsonCs.length(); j++) {
                    JSONObject jsonCity = jsonCs.getJSONObject(j);
                    String city = jsonCity.getString("value");// 市名字
                    String c_id = jsonCity.getString("id");// 市名字
                    mCitiesDatas[j] = city;
                    mCitiesIdDatas[j] = c_id;
                    JSONArray jsonAreas = null;
                    try {
                        /**
                         * Throws JSONException if the mapping doesn't exist or
                         * is not a JSONArray.
                         */
                        jsonAreas = jsonCity.getJSONArray("children");
                    } catch (Exception e) {
                        continue;
                    }

                    String[] mAreasDatas = new String[jsonAreas.length()];// 当前市的所有区
                    String[] mAreasIdDatas = new String[jsonAreas.length()];
                    for (int k = 0; k < jsonAreas.length(); k++) {
                        String area = jsonAreas.getJSONObject(k).getString("value");// 区域的名称
                        String e_id = jsonAreas.getJSONObject(k).getString("id");// 区域的名称
                        mAreasDatas[k] = area;
                        mAreasIdDatas[k] = e_id;
                    }
                    mAreaDatasMap.put(city, mAreasDatas);
                    mAreaDatasIdMap.put(c_id, mAreasIdDatas);
                }

                mCitisDatasMap.put(province, mCitiesDatas);
                mCitisDatasIdMap.put(p_id, mCitiesIdDatas);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        mJsonObj = null;
    }

    /**
     * 初始化省会
     */
    public void initProvinces() {
        int length = mProvinceDatas.length;
        for (int i = 0; i < length; i++) {
            arrProvinces.add(mProvinceDatas[i]);
            arrProvincesId.add(mProvinceIdDatas[i]);
        }
    }

    /**
     * 根据省会，生成该省会的所有城市
     *
     * @param citys
     */
    public void initCitys(String[] citys, String[] c_ids) {
        //初始化城市名
        if (citys != null) {
            arrCitys.clear();
            int length = citys.length;
            for (int i = 0; i < length; i++) {
                arrCitys.add(citys[i]);
            }
        } else {
            String[] city = mCitisDatasMap.get("湖南省");
            arrCitys.clear();
            int length = city.length;
            for (int i = 0; i < length; i++) {
                arrCitys.add(city[i]);
            }
        }
        if (arrCitys != null && arrCitys.size() > 0 && !arrCitys.contains(strCity)) {
            strCity = arrCitys.get(0);
        }
        //初始化城市ID
        if (c_ids != null) {
            arrCitysId.clear();
            int length = c_ids.length;
            for (int i = 0; i < length; i++) {
                arrCitysId.add(c_ids[i]);
            }
        } else {
            String[] c_id = mCitisDatasIdMap.get("430000");
            arrCitysId.clear();
            int length = c_id.length;
            for (int i = 0; i < length; i++) {
                arrCitysId.add(c_id[i]);
            }
        }
        if (arrCitysId != null && arrCitysId.size() > 0 && !arrCitysId.contains(strCityId)) {
            strCityId = arrCitysId.get(0);
        }
    }

    /**
     * 根据城市，生成该城市的所有地区
     *
     * @param areas
     */
    public void initAreas(String[] areas, String[] e_ids) {
        //初始化地区名
        if (areas != null) {
            arrAreas.clear();
            int length = areas.length;
            for (int i = 0; i < length; i++) {
                arrAreas.add(areas[i]);
            }
        } else {
            String[] area = mAreaDatasMap.get("长沙市");
            arrAreas.clear();
            int length = area.length;
            for (int i = 0; i < length; i++) {
                arrAreas.add(area[i]);
            }
        }
        if (arrAreas != null && arrAreas.size() > 0 && !arrAreas.contains(strArea)) {
            strArea = arrAreas.get(0);
        }
        //初始化地区ID
        if (e_ids != null) {
            arrAreasId.clear();
            int length = e_ids.length;
            for (int i = 0; i < length; i++) {
                arrAreasId.add(e_ids[i]);
            }
        } else {
            String[] e_id = mAreaDatasIdMap.get("430100");
            arrAreasId.clear();
            int length = e_id.length;
            for (int i = 0; i < length; i++) {
                arrAreasId.add(e_id[i]);
            }
        }
        if (arrAreasId != null && arrAreasId.size() > 0 && !arrAreasId.contains(strAreaId)) {
            strAreaId = arrAreasId.get(0);
        }
    }

    /**
     * 初始化地点
     *
     * @param province
     * @param city
     */
    public void setAddress(String province, String city, String area) {
        if (province != null && province.length() > 0) {
            this.strProvince = province;
        }
        if (city != null && city.length() > 0) {
            this.strCity = city;
        }

        if (area != null && area.length() > 0) {
            this.strArea = area;
        }
    }

    /**
     * 返回省会索引，没有就返回默认“湖南省”
     *
     * @param province
     * @return
     */
    public int getProvinceItem(String province) {
        int size = arrProvinces.size();
        int provinceIndex = 0;
        boolean noprovince = true;
        for (int i = 0; i < size; i++) {
            if (province.equals(arrProvinces.get(i))) {
                noprovince = false;
                return provinceIndex;
            } else {
                provinceIndex++;
            }
        }
        if (noprovince) {
            strProvince = "湖南省";
            return 19;
        }
        return provinceIndex;
    }

    /**
     * 得到城市索引，没有返回默认“长沙”
     *
     * @param city
     * @return
     */
    public int getCityItem(String city) {
        int size = arrCitys.size();
        int cityIndex = 0;
        boolean nocity = true;
        for (int i = 0; i < size; i++) {
//            System.out.println(arrCitys.get(i));
            if (city.equals(arrCitys.get(i))) {
                nocity = false;
                return cityIndex;
            } else {
                cityIndex++;
            }
        }
        if (nocity) {
            strCity = "长沙市";
            return 2;
        }
        return cityIndex;
    }

    /**
     * 得到地区索引，没有返回默认“芙蓉区”
     *
     * @param area
     * @return
     */
    public int getAreaItem(String area) {
        int size = arrAreas.size();
        int areaIndex = 0;
        boolean noarea = true;
        for (int i = 0; i < size; i++) {
//            System.out.println(arrAreas.get(i));
            if (area.equals(arrAreas.get(i))) {
                noarea = false;
                return areaIndex;
            } else {
                areaIndex++;
            }
        }
        if (noarea) {
            strArea = "芙蓉区";
            return 1;
        }
        return areaIndex;
    }
}