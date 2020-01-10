package com.your.mall.bean;

import java.util.List;

/**
 * Created by xieqinghua.
 * 创建时间：2016/11/14
 * 类描述：砍价bean
 * 修改备注：
 */
public class BargainBean {

    /**
     * msg : 数据获取成功！
     * code : 0
     * data : {"list":[{"activity_id":"190","price":"89.00","activity_number":"0","item_id":"4479","activity_goods_title":"苹果","activity_price":"0.01","activity_stockprice":"94","item_default_image":"http://img.hnmall.com/newimages/201507/source_img/4479/4479_P_1437705619257.jpg","sellout":"0","bargain_id":"452","is_join":"1","count":"118","status":"1"},{"activity_id":"190","price":"59.00","activity_number":"0","item_id":"4907","activity_goods_title":"蓝莓","activity_price":"0.01","activity_stockprice":"0","item_default_image":"http://img.hnmall.com/newimages/201510/source_img/4907/4907_P_1446076628303.jpg","sellout":"1","bargain_id":"0","is_join":"0","count":"62","status":"1"},{"activity_id":"190","price":"149.00","activity_number":"0","item_id":"4458","activity_goods_title":"奇异果","activity_price":"0.01","activity_stockprice":"0","item_default_image":"http://img.hnmall.com/newimages/201507/source_img/4458/4458_P_1436836004208.jpg","sellout":"1","bargain_id":"0","is_join":"0","count":"34","status":"1"}],"about_url":"http://nbh.e9448.com/wap/bargain-about.html","now_time":"2016-11-16 11:45:26","end_time":"2019-04-24 09:00:00","status":"1"}
     */

    private String msg;
    private String code;
    /**
     * list : [{"activity_id":"190","price":"89.00","activity_number":"0","item_id":"4479","activity_goods_title":"苹果","activity_price":"0.01","activity_stockprice":"94","item_default_image":"http://img.hnmall.com/newimages/201507/source_img/4479/4479_P_1437705619257.jpg","sellout":"0","bargain_id":"452","is_join":"1","count":"118","status":"1"},{"activity_id":"190","price":"59.00","activity_number":"0","item_id":"4907","activity_goods_title":"蓝莓","activity_price":"0.01","activity_stockprice":"0","item_default_image":"http://img.hnmall.com/newimages/201510/source_img/4907/4907_P_1446076628303.jpg","sellout":"1","bargain_id":"0","is_join":"0","count":"62","status":"1"},{"activity_id":"190","price":"149.00","activity_number":"0","item_id":"4458","activity_goods_title":"奇异果","activity_price":"0.01","activity_stockprice":"0","item_default_image":"http://img.hnmall.com/newimages/201507/source_img/4458/4458_P_1436836004208.jpg","sellout":"1","bargain_id":"0","is_join":"0","count":"34","status":"1"}]
     * about_url : http://nbh.e9448.com/wap/bargain-about.html
     * now_time : 2016-11-16 11:45:26
     * end_time : 2019-04-24 09:00:00
     * status : 1
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
        private String about_url;
        private String now_time;
        private String end_time;
        private String status;
        /**
         * activity_id : 190
         * price : 89.00
         * activity_number : 0
         * item_id : 4479
         * activity_goods_title : 苹果
         * activity_price : 0.01
         * activity_stockprice : 94
         * item_default_image : http://img.hnmall.com/newimages/201507/source_img/4479/4479_P_1437705619257.jpg
         * sellout : 0
         * bargain_id : 452
         * is_join : 1
         * count : 118
         * status : 1
         */

        private List<ListBean> list;

        public String getAbout_url() {
            return about_url;
        }

        public void setAbout_url(String about_url) {
            this.about_url = about_url;
        }

        public String getNow_time() {
            return now_time;
        }

        public void setNow_time(String now_time) {
            this.now_time = now_time;
        }

        public String getEnd_time() {
            return end_time;
        }

        public void setEnd_time(String end_time) {
            this.end_time = end_time;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            private String activity_id;
            private String price;
            private String activity_number;
            private String item_id;
            private String activity_goods_title;
            private String activity_price;
            private String activity_stockprice;
            private String item_default_image;
            private String sellout;
            private String bargain_id;
            private String is_join;
            private String count;
            private String status;

            public String getActivity_id() {
                return activity_id;
            }

            public void setActivity_id(String activity_id) {
                this.activity_id = activity_id;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getActivity_number() {
                return activity_number;
            }

            public void setActivity_number(String activity_number) {
                this.activity_number = activity_number;
            }

            public String getItem_id() {
                return item_id;
            }

            public void setItem_id(String item_id) {
                this.item_id = item_id;
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

            public String getActivity_stockprice() {
                return activity_stockprice;
            }

            public void setActivity_stockprice(String activity_stockprice) {
                this.activity_stockprice = activity_stockprice;
            }

            public String getItem_default_image() {
                return item_default_image;
            }

            public void setItem_default_image(String item_default_image) {
                this.item_default_image = item_default_image;
            }

            public String getSellout() {
                return sellout;
            }

            public void setSellout(String sellout) {
                this.sellout = sellout;
            }

            public String getBargain_id() {
                return bargain_id;
            }

            public void setBargain_id(String bargain_id) {
                this.bargain_id = bargain_id;
            }

            public String getIs_join() {
                return is_join;
            }

            public void setIs_join(String is_join) {
                this.is_join = is_join;
            }

            public String getCount() {
                return count;
            }

            public void setCount(String count) {
                this.count = count;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }
        }
    }
}
