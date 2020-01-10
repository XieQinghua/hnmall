package com.your.mall.utils;

import java.util.Collection;

/**
 * Created by Hankkin on 16/11/20.
 * 注释:
 */

public class StringUtils {
    public static String stringJoin(Collection list, String separator) {
        StringBuilder sb = new StringBuilder();
//        for (Iterator i = list.iterator(); i.hasNext(); ) {
//            sb.append(i.next());
//            sb.append(separator);
//        }
        Object[] l = list.toArray();
        for (int i = 0; i < l.length; i++) {
            sb.append(l[i]);
            if (i < list.size() - 1) {
                sb.append(separator);
            }
        }
        return sb.toString();
    }
}
