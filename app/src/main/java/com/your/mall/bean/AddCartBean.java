package com.your.mall.bean;

/**
 * Created by xieqinghua.
 * 创建时间：2016/10/20
 * 类描述：加入购物车
 * 修改备注：
 */
public class AddCartBean {

    /**
     * msg : 成功加入购物车
     * code : 0
     * data : {"cart_num":5}
     */

    private String msg;
    private String code;
    /**
     * cart_num : 5
     */

    private DataBean data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private String cart_num;

        public String getCart_num() {
            return cart_num;
        }

        public void setCart_num(String cart_num) {
            this.cart_num = cart_num;
        }
    }
}
