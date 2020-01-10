package com.your.mall.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by xieqinghua.
 * 创建时间：2016/8/30
 * 类描述：SharedPreferences操作帮助类，增加了 删除指定字段 和 根据key找对应的value的方法
 * 修改备注：
 */
public class SharedPreferencesUtil {
    //存储的sharedpreferences文件名
    private static final String FILE_NAME = "mall";

    /**
     * 保存数据到文件
     *
     * @param context
     * @param key
     * @param data
     */
    public static void saveData(Context context, String key, Object data) {

        String type = data.getClass().getSimpleName();
        SharedPreferences sharedPreferences = context
                .getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        if ("Integer".equals(type)) {
            editor.putInt(key, (Integer) data);
        } else if ("Boolean".equals(type)) {
            editor.putBoolean(key, (Boolean) data);
        } else if ("String".equals(type)) {
            editor.putString(key, (String) data);
        } else if ("Float".equals(type)) {
            editor.putFloat(key, (Float) data);
        } else if ("Long".equals(type)) {
            editor.putLong(key, (Long) data);
        }

        editor.commit();
    }

    /**
     * 从文件中读取数据
     *
     * @param context
     * @param key
     * @param defValue
     * @return
     */
    public static Object getData(Context context, String key, Object defValue) {

        String type = defValue.getClass().getSimpleName();
        SharedPreferences sharedPreferences = context.getSharedPreferences
                (FILE_NAME, Context.MODE_PRIVATE);

        //defValue为为默认值，如果当前获取不到数据就返回它
        if ("Integer".equals(type)) {
            return sharedPreferences.getInt(key, (Integer) defValue);
        } else if ("Boolean".equals(type)) {
            return sharedPreferences.getBoolean(key, (Boolean) defValue);
        } else if ("String".equals(type)) {
            return sharedPreferences.getString(key, (String) defValue);
        } else if ("Float".equals(type)) {
            return sharedPreferences.getFloat(key, (Float) defValue);
        } else if ("Long".equals(type)) {
            return sharedPreferences.getLong(key, (Long) defValue);
        }

        return null;
    }

    /**
     * 删除某个键值的方法
     */
    public static void remove(Context context, String keyName) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(keyName);
        editor.commit();
    }

    /**
     * 清除sharedPrefrence的数据
     *
     * @param context
     */
    public static void clear(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(FILE_NAME,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();
    }

    /**
     * 查询某个key是否已经存在
     *
     * @param context
     * @param key
     * @return
     */
    public static boolean contains(Context context, String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(FILE_NAME,
                Context.MODE_PRIVATE);
        return sharedPreferences.contains(key);
    }
}
