package com.your.mall.utils;

/**
 * Created by Hankkin on 16/11/20.
 * 注释:
 */

public class AmountGoodEvent {
    public String goodId;
    public String nums;

    public AmountGoodEvent(String goodId, String nums) {
        this.goodId = goodId;
        this.nums = nums;
    }
}
