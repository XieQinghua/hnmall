package com.your.mall.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.util.LruCache;

import com.android.volley.toolbox.ImageLoader.ImageCache;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * update by huangxinqi 2015.11.24 15:30
 * 修改记录：添加了图片url的前缀 并且添加了一个临时使用的加载网络图片的方法
 */
public class AppImageCache implements ImageCache {
    private static AppImageCache imageCache;

    static LruCache<String, Bitmap> lruCache;
    static HashMap<String, SoftReference<Bitmap>> hashMap;

    //图片url的前缀：
    public static final String IMAGE_URL_PREV="http://7xnqi4.com2.z0.glb.qiniucdn.com/";

    static {
        int maxMemory = (int) Runtime.getRuntime().maxMemory();
        int maxSize = maxMemory / 8;
        hashMap = new LinkedHashMap<String, SoftReference<Bitmap>>();
        lruCache = new LruCache<String, Bitmap>(maxSize) {

            /**
             * 图片数量
             */
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes() * value.getHeight();
            }
        };

    }

    private AppImageCache() {

    }

    public static AppImageCache getInstance() {
        if (imageCache == null) {
            imageCache = new AppImageCache();
        }
        return imageCache;
    }

    @Override
    public Bitmap getBitmap(String url) {
        return lruCache.get(url);
    }

    @Override
    public void putBitmap(String url, Bitmap bitmap) {
        lruCache.put(url, bitmap);
    }

    /**
     * 获取网落图片资源（临时使用）
     * @param url
     * @return
     */
    public Bitmap returnBitMap(String url){
        URL myFileUrl = null;
        Bitmap bitmap = null;
        try {
            myFileUrl = new URL(IMAGE_URL_PREV+url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            HttpURLConnection conn = (HttpURLConnection) myFileUrl
                    .openConnection();
            conn.setDoInput(true);
            conn.connect();
            InputStream is = conn.getInputStream();
            bitmap = BitmapFactory.decodeStream(is);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }
}
