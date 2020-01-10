package com.your.mall.utils;

import com.your.mall.application.MallApplication;
import com.your.mall.http.MallApi;

import org.xutils.common.Callback;
import org.xutils.common.util.KeyValue;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by xieqinghua.
 * 创建时间：2016/9/20
 * 类描述：XUtils网络请求工具
 * 修改备注：
 */
public class XUtils {

    /**
     * 发送get请求
     *
     * @param <T>
     */
    public static <T> Callback.Cancelable Get(String url, Map<String, String> map, Callback.CommonCallback<T> callback) {
        RequestParams params = new RequestParams(url);
        if (null != map) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                params.addQueryStringParameter(entry.getKey(), entry.getValue());
//                Log.e("Map", "键值对:" + entry);
            }

            //参数排序
            List<RequestParam> list = new ArrayList<>();
            List<KeyValue> stringParams = params.getQueryStringParams();//拿到动态的所有参数
            for (int i = 0; i < stringParams.size(); i++) {//遍历动态参数，添加到对象里面
                String paramsname = stringParams.get(i).key;
                String paramsvalue = (String) stringParams.get(i).value;
                RequestParam param = new RequestParam(paramsname, paramsvalue);
                list.add(param);
            }
            Collections.sort(list, new Sort());

            params.addQueryStringParameter("p", MallApplication.getInstance().getCurrentTime());//当前时间14位

//            Log.e("k", list.get(0).getName() + MallApplication.getInstance().getCurrentTime() + APP_KEY);
//            Log.e("k", MD5Utils.code32(list.get(0).getName() + MallApplication.getInstance().getCurrentTime() + APP_KEY, "UTF-8"));

            params.addQueryStringParameter("k", MD5Utils.code32(list.get(0).getName() + MallApplication.getInstance().getCurrentTime() + MallApi.APP_KEY, "UTF-8"));
            params.addQueryStringParameter("uuid", MallApplication.getInstance().getMyUUID());//设备uuid
            params.addQueryStringParameter("client_source", "android");//请求来源
            params.addParameter("version", MallApplication.getInstance().getVersion());
        } else {
            params.addQueryStringParameter("p", MallApplication.getInstance().getCurrentTime());//当前时间14位
            params.addQueryStringParameter("k", MD5Utils.code32(MallApplication.getInstance().getCurrentTime() + MallApi.APP_KEY, "UTF-8"));
            params.addQueryStringParameter("uuid", MallApplication.getInstance().getMyUUID());//设备uuid
            params.addQueryStringParameter("client_source", "android");//请求来源
            params.addParameter("version", MallApplication.getInstance().getVersion());
        }
        Callback.Cancelable cancelable = x.http().get(params, callback);
        return cancelable;
    }


    /**
     * 发送post请求
     *
     * @param <T>
     */
    public static <T> Callback.Cancelable Post(String url, Map<String, String> map, Callback.CommonCallback<T> callback) {
        RequestParams params = new RequestParams(url);
        if (null != map) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                params.addParameter(entry.getKey(), entry.getValue());
//                Log.e("Map", "键值对:" + entry);
            }

            //参数排序
            List<RequestParam> list = new ArrayList<>();
            List<KeyValue> stringParams = params.getQueryStringParams();//拿到动态的所有参数
            for (int i = 0; i < stringParams.size(); i++) {//遍历动态参数，添加到对象里面
                String paramsname = stringParams.get(i).key;
                String paramsvalue = (String) stringParams.get(i).value;
                RequestParam param = new RequestParam(paramsname, paramsvalue);
                list.add(param);
            }
            Collections.sort(list, new Sort());

            params.addParameter("p", MallApplication.getInstance().getCurrentTime());//当前时间14位

//            Log.e("k", list.get(0).getName() + MallApplication.getInstance().getCurrentTime() + MallApi.APP_KEY);
//            Log.e("k", MD5Utils.code32(list.get(0).getName() + MallApplication.getInstance().getCurrentTime() + MallApi.APP_KEY, "UTF-8"));

            params.addParameter("k", MD5Utils.code32(list.get(0).getName() + MallApplication.getInstance().getCurrentTime() + MallApi.APP_KEY, "UTF-8"));
            params.addParameter("uuid", MallApplication.getInstance().getMyUUID());//设备uuid
            params.addParameter("client_source", "android");//请求来源
            params.addParameter("version", MallApplication.getInstance().getVersion());

        }
        Callback.Cancelable cancelable = x.http().post(params, callback);
        return cancelable;
    }

    /**
     * 提交订单接口post方法
     *
     * @param url
     * @param map
     * @param callback
     * @param <T>
     * @return
     */
    public static <T> Callback.Cancelable tradeAddPost(String url, Map<String, String> map, Callback.CommonCallback<T> callback) {
        RequestParams params = new RequestParams(url);
        if (null != map) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                params.addParameter(entry.getKey(), entry.getValue());
            }

            //参数排序
            List<RequestParam> list = new ArrayList<>();
            List<KeyValue> stringParams = params.getQueryStringParams();//拿到动态的所有参数
            for (int i = 0; i < stringParams.size(); i++) {//遍历动态参数，添加到对象里面
                String paramsname = stringParams.get(i).key;
                String paramsvalue = (String) stringParams.get(i).value;
                RequestParam param = new RequestParam(paramsname, paramsvalue);
                list.add(param);
            }
            Collections.sort(list, new Sort());

            params.addParameter("p", MallApplication.getInstance().getCurrentTime());//当前时间14位

//            Log.e("k", list.get(0).getName() + MallApplication.getInstance().getCurrentTime() + MallApi.APP_KEY);
//            Log.e("k", MD5Utils.code32(list.get(0).getName() + MallApplication.getInstance().getCurrentTime() + MallApi.APP_KEY, "UTF-8"));

            params.addParameter("k", MD5Utils.code32(list.get(0).getName() + MallApplication.getInstance().getCurrentTime() + MallApi.APP_KEY, "UTF-8"));
            params.addParameter("uuid", MallApplication.getInstance().getMyUUID());//设备uuid
//            Log.e("uuid", MallApplication.getInstance().getMyUUID());
            params.addParameter("client_source", "a");/**由于后台字段限制，取一位**/
            params.addParameter("version", MallApplication.getInstance().getVersion());

        }
        Callback.Cancelable cancelable = x.http().post(params, callback);
        return cancelable;
    }

    /**
     * 发送get请求
     *
     * @param <T>
     */
    public static <T> Callback.Cancelable ssoGet(String url, Map<String, String> map, Callback.CommonCallback<T> callback) {
        RequestParams params = new RequestParams(url);
        if (null != map) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                params.addQueryStringParameter(entry.getKey(), entry.getValue());
            }

            params.addQueryStringParameter("appId", "hnmall");
            params.addQueryStringParameter("clientId", "hnmall");
            params.addQueryStringParameter("channel", "android");//请求来源
            params.addQueryStringParameter("t", MallApplication.getInstance().getTensTimestamp());//当前时间10位时间戳

            //参数排序
            List<RequestParam> list = new ArrayList<>();
            List<KeyValue> stringParams = params.getQueryStringParams();//拿到动态的所有参数
            for (int i = 0; i < stringParams.size(); i++) {//遍历动态参数，添加到对象里面
                String paramsname = stringParams.get(i).key;
                String paramsvalue = (String) stringParams.get(i).value;
                RequestParam param = new RequestParam(paramsname, paramsvalue);
                list.add(param);
            }
            Collections.sort(list, new Sort());

            String h = "";
            for (int i = 0; i < list.size(); i++) {
                h += (list.get(i).getName() + "=" + list.get(i).getValue() + "&");
            }
            params.addQueryStringParameter("h", MD5Utils.code32(h + "appKey=" + MallApi.YOUA_APP_KEY, "UTF-8"));
//            Log.e("h", h + "appKey=" + MallApi.YOUA_APP_KEY);
//            Log.e("h", MD5Utils.code32(h + MallApi.YOUA_APP_KEY, "UTF-8"));
        }
        Callback.Cancelable cancelable = x.http().get(params, callback);
        return cancelable;
    }


    /**
     * 上传文件
     *
     * @param <T>
     */
    public static <T> Callback.Cancelable UpLoadFile(String url, Map<String, Object> map, Callback.CommonCallback<T> callback) {
        RequestParams params = new RequestParams(url);
        if (null != map) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                params.addParameter(entry.getKey(), entry.getValue());
            }
        }
        params.setMultipart(true);
        Callback.Cancelable cancelable = x.http().get(params, callback);
        return cancelable;
    }

    /**
     * 下载文件
     *
     * @param <T>
     */
    public static <T> Callback.Cancelable DownLoadFile(String url, String filepath, Callback.CommonCallback<T> callback) {
        RequestParams params = new RequestParams(url);
        //设置断点续传
        params.setAutoResume(true);
        params.setSaveFilePath(filepath);
        Callback.Cancelable cancelable = x.http().get(params, callback);
        return cancelable;
    }
}




