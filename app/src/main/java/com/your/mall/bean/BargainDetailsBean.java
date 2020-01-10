package com.your.mall.bean;

/**
 * Created by xieqinghua.
 * 创建时间：2016/11/14
 * 类描述：砍价详情bean
 * 修改备注：
 */
public class BargainDetailsBean {

    /**
     * msg : 数据获取成功！
     * code : 0
     * data : {"price":"89.00","activity_id":"190","item_id":"4479","activity_price":"0.01","activity_stockprice":"94","activity_goods_banner":"http://nbh.e9448.com/images/dc/76/5d/76390ea2afd460697350c0353b261214a12ff4ab.jpg","title":"【友阿果园】新西兰进口红玫瑰苹果6个装约260g/个︱","activity_goods_title_weixin":"苹果","activity_goods_desc_weixin":"苹果","item_default_image":"http://img.hnmall.com/newimages/201507/source_img/4479/4479_P_1437705619257.jpg","activity_goods_title":"苹果","default_sku_id":"4479","activity_goods_banner_hight":"189","activity_goods_image":"http://img.hnmall.com/newimages/201507/source_img/4479/4479_P_1437705619257.jpg_s.jpg","headimgurl":"","bargain_id":"","activity_goods_url_weixin":"http://nbh.e9448.com/wap/bargain-share.html?bargain_id=","isorder":"0","about_url":"http://nbh.e9448.com/wap/bargain-about.html"}
     */

    private String msg;
    private String code;
    /**
     * price : 89.00
     * activity_id : 190
     * item_id : 4479
     * activity_price : 0.01
     * activity_stockprice : 94
     * activity_goods_banner : http://nbh.e9448.com/images/dc/76/5d/76390ea2afd460697350c0353b261214a12ff4ab.jpg
     * title : 【友阿果园】新西兰进口红玫瑰苹果6个装约260g/个︱
     * activity_goods_title_weixin : 苹果
     * activity_goods_desc_weixin : 苹果
     * item_default_image : http://img.hnmall.com/newimages/201507/source_img/4479/4479_P_1437705619257.jpg
     * activity_goods_title : 苹果
     * default_sku_id : 4479
     * activity_goods_banner_hight : 189
     * activity_goods_image : http://img.hnmall.com/newimages/201507/source_img/4479/4479_P_1437705619257.jpg_s.jpg
     * headimgurl :
     * bargain_id :
     * activity_goods_url_weixin : http://nbh.e9448.com/wap/bargain-share.html?bargain_id=
     * isorder : 0
     * about_url : http://nbh.e9448.com/wap/bargain-about.html
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
        private String price;
        private String activity_id;
        private String item_id;
        private String activity_price;
        private String activity_stockprice;
        private String activity_goods_banner;
        private String title;
        private String activity_goods_title_weixin;
        private String activity_goods_desc_weixin;
        private String item_default_image;
        private String activity_goods_title;
        private String default_sku_id;
        private String activity_goods_banner_hight;
        private String activity_goods_image;
        private String headimgurl;
        private String bargain_id;
        private String activity_goods_url_weixin;
        private String isorder;
        private String about_url;

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
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

        public String getActivity_price() {
            return activity_price;
        }

        public void setActivity_price(String activity_price) {
            this.activity_price = activity_price;
        }

        public String getActivity_stockprice() {
            return activity_stockprice;
        }

        public void setActivity_stockprice(String activity_stockprice) {
            this.activity_stockprice = activity_stockprice;
        }

        public String getActivity_goods_banner() {
            return activity_goods_banner;
        }

        public void setActivity_goods_banner(String activity_goods_banner) {
            this.activity_goods_banner = activity_goods_banner;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getActivity_goods_title_weixin() {
            return activity_goods_title_weixin;
        }

        public void setActivity_goods_title_weixin(String activity_goods_title_weixin) {
            this.activity_goods_title_weixin = activity_goods_title_weixin;
        }

        public String getActivity_goods_desc_weixin() {
            return activity_goods_desc_weixin;
        }

        public void setActivity_goods_desc_weixin(String activity_goods_desc_weixin) {
            this.activity_goods_desc_weixin = activity_goods_desc_weixin;
        }

        public String getItem_default_image() {
            return item_default_image;
        }

        public void setItem_default_image(String item_default_image) {
            this.item_default_image = item_default_image;
        }

        public String getActivity_goods_title() {
            return activity_goods_title;
        }

        public void setActivity_goods_title(String activity_goods_title) {
            this.activity_goods_title = activity_goods_title;
        }

        public String getDefault_sku_id() {
            return default_sku_id;
        }

        public void setDefault_sku_id(String default_sku_id) {
            this.default_sku_id = default_sku_id;
        }

        public String getActivity_goods_banner_hight() {
            return activity_goods_banner_hight;
        }

        public void setActivity_goods_banner_hight(String activity_goods_banner_hight) {
            this.activity_goods_banner_hight = activity_goods_banner_hight;
        }

        public String getActivity_goods_image() {
            return activity_goods_image;
        }

        public void setActivity_goods_image(String activity_goods_image) {
            this.activity_goods_image = activity_goods_image;
        }

        public String getHeadimgurl() {
            return headimgurl;
        }

        public void setHeadimgurl(String headimgurl) {
            this.headimgurl = headimgurl;
        }

        public String getBargain_id() {
            return bargain_id;
        }

        public void setBargain_id(String bargain_id) {
            this.bargain_id = bargain_id;
        }

        public String getActivity_goods_url_weixin() {
            return activity_goods_url_weixin;
        }

        public void setActivity_goods_url_weixin(String activity_goods_url_weixin) {
            this.activity_goods_url_weixin = activity_goods_url_weixin;
        }

        public String getIsorder() {
            return isorder;
        }

        public void setIsorder(String isorder) {
            this.isorder = isorder;
        }

        public String getAbout_url() {
            return about_url;
        }

        public void setAbout_url(String about_url) {
            this.about_url = about_url;
        }
    }
}
