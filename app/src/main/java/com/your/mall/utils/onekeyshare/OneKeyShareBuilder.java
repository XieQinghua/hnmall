package com.your.mall.utils.onekeyshare;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.wechat.friends.Wechat;
import cn.sharesdk.wechat.moments.WechatMoments;

/**
 * 完成的主要功能：分享
 * Created by huangxinqi
 * on 2015/11/16-12-07.
 */
public class OneKeyShareBuilder implements PlatformActionListener {

    private ShareDialog shareDialog;
    private Context context;
    //分享标题
    private String title = "";
    //分享图片地址
    private String imageUrl = "";
    //分享文本
    private String text = "";
    //分享网页地址，用于微博，qq，qzone
    private String titleUrl = "";
    //分享网页地址，用于微信，朋友圈
    private String url = "";

    public Context getContext() {
        return context;
    }

    public OneKeyShareBuilder setContext(Context context) {
        this.context = context;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public OneKeyShareBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public OneKeyShareBuilder setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public String getText() {
        return text;
    }

    public OneKeyShareBuilder setText(String text) {
        this.text = text;
        return this;
    }

    public String getTitleUrl() {
        return titleUrl;
    }

    public OneKeyShareBuilder setTitleUrl(String titleUrl) {
        this.titleUrl = titleUrl;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public OneKeyShareBuilder setUrl(String url) {
        this.url = url;
        return this;
    }

    public void showShareDialog() {
        ShareSDK.initSDK(context);
        shareDialog = new ShareDialog(context);
        shareDialog.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                HashMap<String, Object> item = (HashMap<String, Object>) parent.getItemAtPosition(position);
                switch (position) {
                    case 0:
                        Platform.ShareParams wechatShareParam = new Platform.ShareParams();
                        wechatShareParam.setShareType(Platform.SHARE_WEBPAGE);
                        wechatShareParam.setTitle(getTitle());
                        wechatShareParam.setText(getText());
                        wechatShareParam.setImageUrl(getImageUrl());
                        wechatShareParam.setUrl(getUrl());
                        Platform wechat = ShareSDK.getPlatform(Wechat.NAME);
                        wechat.setPlatformActionListener(OneKeyShareBuilder.this);
                        wechat.share(wechatShareParam);
                        break;
                    case 1:
                        Platform.ShareParams wechatMomentShareParam = new Platform.ShareParams();
                        wechatMomentShareParam.setShareType(Platform.SHARE_WEBPAGE);
                        wechatMomentShareParam.setTitle(getTitle());
                        wechatMomentShareParam.setText(getText());
                        wechatMomentShareParam.setImageUrl(getImageUrl());
                        wechatMomentShareParam.setUrl(getUrl());
                        Platform wechatMoments = ShareSDK.getPlatform(WechatMoments.NAME);
                        wechatMoments.setPlatformActionListener(OneKeyShareBuilder.this);
                        wechatMoments.share(wechatMomentShareParam);
                        break;

                }
            }
        });

    }

    /**
     * 根据分享进行的操作，异步更新ui
     */
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    Toast.makeText(context, "微信分享成功", Toast.LENGTH_LONG).show();
                    break;
                case 2:
                    Toast.makeText(context, "朋友圈分享成功", Toast.LENGTH_LONG).show();
                    break;
                default:
                    break;
            }
        }
    };

    /**
     * 根据用户选择的分享平台进行对应分享操作
     *
     * @param platform
     * @param i
     * @param hashMap
     */
    @Override
    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
        if (platform.getName().equals(Wechat.NAME)) {
            handler.sendEmptyMessage(1);
        } else if (platform.getName().equals(WechatMoments.NAME)) {
            handler.sendEmptyMessage(2);
        }
    }

    /**
     * 当分享出现错误时执行的操作
     *
     * @param platform
     * @param i
     * @param throwable
     */
    @Override
    public void onError(Platform platform, int i, Throwable throwable) {
        throwable.printStackTrace();
        Message msg = new Message();
        msg.what = 3;
        msg.obj = throwable.getMessage();
        handler.sendMessage(msg);
    }

    /**
     * 取消分享时的操作
     *
     * @param platform
     * @param i
     */
    @Override
    public void onCancel(Platform platform, int i) {
        handler.sendEmptyMessage(7);
    }
}
