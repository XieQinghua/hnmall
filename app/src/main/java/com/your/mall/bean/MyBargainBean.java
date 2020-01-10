package com.your.mall.bean;

import java.util.List;

/**
 * Created by xieqinghua.
 * 创建时间：2016/11/14
 * 类描述：砍价bean
 * 修改备注：
 */
public class MyBargainBean {

    /**
     * msg : 数据获取成功！
     * code : 0
     * data : [{"number":"1","activity_id":"190","item_id":"4479","status":"1","bargain_id":"452","price":"89.00","activity_goods_title":"苹果","activity_price":"89.00","image_default_id":"http://img.hnmall.com/newimages/201507/source_img/4479/4479_P_1437705619257.jpg"}]
     */

    private String msg;
    private String code;
    /**
     * number : 1
     * activity_id : 190
     * item_id : 4479
     * status : 1
     * bargain_id : 452
     * price : 89.00
     * activity_goods_title : 苹果
     * activity_price : 89.00
     * image_default_id : http://img.hnmall.com/newimages/201507/source_img/4479/4479_P_1437705619257.jpg
     */

    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private String number;
        private String activity_id;
        private String item_id;
        private String status;
        private String bargain_id;
        private String price;
        private String activity_goods_title;
        private String activity_price;
        private String image_default_id;

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public String getActivity_id() {
            return activity_id;
        }

        public void setActivity_id(String activity_id) {
            this.activity_id = activity_id;
        }

        public String getItem_id() {
            return item_id;
        }

        public void setItem_id(String item_id) {
            this.item_id = item_id;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getBargain_id() {
            return bargain_id;
        }

        public void setBargain_id(String bargain_id) {
            this.bargain_id = bargain_id;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getActivity_goods_title() {
            return activity_goods_title;
        }

        public void setActivity_goods_title(String activity_goods_title) {
            this.activity_goods_title = activity_goods_title;
        }

        public String getActivity_price() {
            return activity_price;
        }

        public void setActivity_price(String activity_price) {
            this.activity_price = activity_price;
        }

        public String getImage_default_id() {
            return image_default_id;
        }

        public void setImage_default_id(String image_default_id) {
            this.image_default_id = image_default_id;
        }
    }
}
