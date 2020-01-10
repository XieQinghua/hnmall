package com.your.mall.utils;

import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.your.mall.R;
import com.your.mall.view.CircleImageView;

import java.text.SimpleDateFormat;

/**
 * 类描述：图片处理工具
 * 创建人：谢庆华.
 * 创建时间：2015/11/24 17:07
 * 修改人：huangxinqi
 * 修改时间：2015/11/30 12:07
 * 修改备注：添加了两个方法，分别获取矩形和圆形图片，但是url只有HappyiApi.QINIU，没有加参数
 */

/**
 * Created by xieqinghua.
 * 创建时间：2016/9/22
 * 类描述：
 * 修改备注：
 */
public class ImgUtils {
    /**
     * @param imageView 加载图片的imageView
     * @param url   图片路径
     */
    private static com.nostra13.universalimageloader.core.ImageLoader imageLoader;//加载矩形图像imageLoader

    /**
     * 加载方图片
     *
     * @param imageView
     * @param url
     */
    public static void setRectangleImage(ImageView imageView, String url) {
        imageLoader = com.nostra13.universalimageloader.core.ImageLoader.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhh");//如果图片更换则需要1小时刷新
        String time = sdf.format(new java.util.Date());
        String imgUrl = url;
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showStubImage(R.drawable.img_default_goods)         //加载开始默认的图片
                .showImageForEmptyUri(R.drawable.img_default_goods)  //url爲空會显示该图片，自己放在drawable里面的
                .showImageOnFail(R.drawable.img_default_goods)       //加载图片出现问题，会显示该图片
                .cacheInMemory()                                     //缓存用
                .cacheOnDisc()                                       //缓存用
                .build();
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageLoader.displayImage(imgUrl, imageView, options);
    }

    /**
     * 加载方图片(无起始缓存图片)
     *
     * @param imageView
     * @param url
     */
    public static void setRectangleImageNoCache(ImageView imageView, String url) {
        imageLoader = com.nostra13.universalimageloader.core.ImageLoader.getInstance();
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhh");//如果图片更换则需要1小时刷新
//        String time = sdf.format(new java.util.Date());
//        String imgUrl = url + "?" + time;
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .build();
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageLoader.displayImage(url, imageView, options);
    }

    /**
     * 加载圆图片
     *
     * @param view 注意！此控件必须为CircleImageView
     * @param url
     */
    public static void setCircleImage(CircleImageView view, String url) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhh");//如果图片更换则需要1小时刷新
        String time = sdf.format(new java.util.Date());
        String imgUrl = url;
        view.setImageUrl(imgUrl, VolleyHepler.getInstance().getImageLoader());
    }
}
