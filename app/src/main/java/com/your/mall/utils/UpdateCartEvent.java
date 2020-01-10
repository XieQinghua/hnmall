package com.your.mall.utils;

/**
 * Created by Hankkin on 16/11/20.
 * 注释:
 */

public class UpdateCartEvent {
    public String id;
    public boolean isChoice;

    public UpdateCartEvent(String id, boolean isChoice) {
        this.id = id;
        this.isChoice = isChoice;
    }
}
