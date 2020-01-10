package com.your.mall.utils.onekeyshare;

import android.app.AlertDialog;
import android.content.Context;
import android.view.Display;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;

import com.your.mall.R;
import com.your.mall.adapter.ShareGridViewAdapter;

import java.util.ArrayList;

/**
 * 完成的主要功能：分享控件
 * Created by huangxinqi
 * on 2015/11/15-01-43.
 */
public class ShareDialog {
    private AlertDialog alertDialog;
    private GridView gridView;
    private ArrayList<ShareCell> list = new ArrayList<>();

    /**
     * 设置gridView的布局图片，并设置adapter
     *
     * @param context
     */
    public ShareDialog(Context context) {
        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.show();


        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams lp = alertDialog.getWindow().getAttributes();
        lp.width = (int)(display.getWidth()); //设置宽度
        alertDialog.getWindow().setAttributes(lp);

        Window window = alertDialog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        window.setContentView(R.layout.dialog_share);
        gridView = (GridView) window.findViewById(R.id.share_gridView);

        list.add(new ShareCell(R.drawable.icon_wechat, "微信好友"));
        list.add(new ShareCell(R.drawable.icon_momment, "朋友圈"));
        ShareGridViewAdapter myShareGridViewAdapter = new ShareGridViewAdapter(context, list);
        gridView.setAdapter(myShareGridViewAdapter);
    }

    /**
     * 关闭对话框操作
     */
    public void dismiss() {
        alertDialog.dismiss();
    }

    /**
     * 监听按gridView上的item时的动作
     *
     * @param listener
     */
    public void setOnItemClickListener(AdapterView.OnItemClickListener listener) {
        gridView.setOnItemClickListener(listener);
    }

    public class ShareCell {
        private int imageId;
        private String name;

        public ShareCell(int imageId, String name) {
            this.imageId = imageId;
            this.name = name;
        }

        public int getImageId() {
            return imageId;
        }

        public void setImageId(int imageId) {
            this.imageId = imageId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
