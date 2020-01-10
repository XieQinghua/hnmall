package com.your.mall.utils;

/**
 * Created by xieqinghua.
 * 创建时间：2016/9/20
 * 类描述：
 * 修改备注：
 */

public class RequestParam {

    private String name;
    private String value;

    public RequestParam(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }


}
