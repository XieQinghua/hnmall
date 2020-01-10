package com.your.mall.utils;

/**
 * Created by Hankkin on 16/11/20.
 * 注释:
 */

public class UpdateShopEvent {
    public String shopId;
    public boolean isChose;

    public UpdateShopEvent(String shopId, boolean isChose) {
        this.shopId = shopId;
        this.isChose = isChose;
    }
}
