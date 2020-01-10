package com.your.mall.bean;

/**
 * Created by xieqinghua.
 * 创建时间：2016/9/28
 * 类描述：商品分类bean
 * 修改备注：
 */
public class Categroy {
    private String id;
    private String imageUrl;
    private String name;

    public Categroy(String id, String imageUrl, String name) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
