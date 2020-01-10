package com.your.mall.utils;

import java.util.Comparator;

/**
 * Created by xieqinghua.
 * 创建时间：2016/9/20
 * 类描述：
 * 修改备注：
 */

public class Sort implements Comparator<RequestParam> {

    @Override
    public int compare(RequestParam p1, RequestParam p2) {

        char[] a = p1.getName().toCharArray();
        char[] b = p2.getName().toCharArray();

        int length = Math.min(a.length, b.length);

        for (int i = 0; i < length; i++) {
            if (a[i] != b[i]) {
                return a[i] - b[i];
            }
        }
        return a.length - b.length;
    }
}
