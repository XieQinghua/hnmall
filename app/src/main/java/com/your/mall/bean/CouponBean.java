package com.your.mall.bean;

import java.util.List;

/**
 * Created by xieqinghua.
 * 创建时间：2016/10/12
 * 类描述：优惠券
 * 修改备注：
 */
public class CouponBean {

    /**
     * msg : 优惠券获取成功
     * code : 0
     * data : [{"tid":"","obtain_time":"2016-08-09","canuse_start_time":"2016-07-27","canuse_end_time":"2016-08-31","coupon_code":"B74522233100D3D","user_id":154548,"shop_id":240,"coupon_id":206,"obtain_desc":"免费领取","is_valid":"2","used_platform":"0","limit_money":"5.00","deduct_money":"5.00","coupon_name":"新人5元无门槛","coupon_desc":"新人5元无门槛","coupon_platform":"友阿果园通用","shop_logo":"http://www.hnmall.com/images/0f/3d/2c/5946dceea07ad89bd97d4f6f85ec39d8224cc8bf.jpg"},{"tid":"","obtain_time":"2016-08-09","canuse_start_time":"2016-07-27","canuse_end_time":"2016-08-31","coupon_code":"BYXJ558C7000D3D","user_id":154548,"shop_id":240,"coupon_id":205,"obtain_desc":"免费领取","is_valid":"2","used_platform":"0","limit_money":"148.00","deduct_money":"20.00","coupon_name":"新人满148元减20元","coupon_desc":"新人满148元减20元","coupon_platform":"友阿果园通用","shop_logo":"http://www.hnmall.com/images/0f/3d/2c/5946dceea07ad89bd97d4f6f85ec39d8224cc8bf.jpg"},{"tid":"","obtain_time":"2016-08-09","canuse_start_time":"2016-07-27","canuse_end_time":"2016-08-31","coupon_code":"BHDHSA4A8E00D3D","user_id":154548,"shop_id":240,"coupon_id":204,"obtain_desc":"免费领取","is_valid":"2","used_platform":"0","limit_money":"78.00","deduct_money":"10.00","coupon_name":"新人78元减10元","coupon_desc":"新人78元减10元","coupon_platform":"友阿果园通用","shop_logo":"http://www.hnmall.com/images/0f/3d/2c/5946dceea07ad89bd97d4f6f85ec39d8224cc8bf.jpg"}]
     */

    private String msg;
    private String code;
    /**
     * tid :
     * obtain_time : 2016-08-09
     * canuse_start_time : 2016-07-27
     * canuse_end_time : 2016-08-31
     * coupon_code : B74522233100D3D
     * user_id : 154548
     * shop_id : 240
     * coupon_id : 206
     * obtain_desc : 免费领取
     * is_valid : 2
     * used_platform : 0
     * limit_money : 5.00
     * deduct_money : 5.00
     * coupon_name : 新人5元无门槛
     * coupon_desc : 新人5元无门槛
     * coupon_platform : 友阿果园通用
     * shop_logo : http://www.hnmall.com/images/0f/3d/2c/5946dceea07ad89bd97d4f6f85ec39d8224cc8bf.jpg
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
        private String tid;
        private String obtain_time;
        private String canuse_start_time;
        private String canuse_end_time;
        private String coupon_code;
        private int user_id;
        private int shop_id;
        private int coupon_id;
        private String obtain_desc;
        private String is_valid;
        private String used_platform;
        private String limit_money;
        private String deduct_money;
        private String coupon_name;
        private String coupon_desc;
        private String coupon_platform;
        private String shop_logo;

        public String getTid() {
            return tid;
        }

        public void setTid(String tid) {
            this.tid = tid;
        }

        public String getObtain_time() {
            return obtain_time;
        }

        public void setObtain_time(String obtain_time) {
            this.obtain_time = obtain_time;
        }

        public String getCanuse_start_time() {
            return canuse_start_time;
        }

        public void setCanuse_start_time(String canuse_start_time) {
            this.canuse_start_time = canuse_start_time;
        }

        public String getCanuse_end_time() {
            return canuse_end_time;
        }

        public void setCanuse_end_time(String canuse_end_time) {
            this.canuse_end_time = canuse_end_time;
        }

        public String getCoupon_code() {
            return coupon_code;
        }

        public void setCoupon_code(String coupon_code) {
            this.coupon_code = coupon_code;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public int getShop_id() {
            return shop_id;
        }

        public void setShop_id(int shop_id) {
            this.shop_id = shop_id;
        }

        public int getCoupon_id() {
            return coupon_id;
        }

        public void setCoupon_id(int coupon_id) {
            this.coupon_id = coupon_id;
        }

        public String getObtain_desc() {
            return obtain_desc;
        }

        public void setObtain_desc(String obtain_desc) {
            this.obtain_desc = obtain_desc;
        }

        public String getIs_valid() {
            return is_valid;
        }

        public void setIs_valid(String is_valid) {
            this.is_valid = is_valid;
        }

        public String getUsed_platform() {
            return used_platform;
        }

        public void setUsed_platform(String used_platform) {
            this.used_platform = used_platform;
        }

        public String getLimit_money() {
            return limit_money;
        }

        public void setLimit_money(String limit_money) {
            this.limit_money = limit_money;
        }

        public String getDeduct_money() {
            return deduct_money;
        }

        public void setDeduct_money(String deduct_money) {
            this.deduct_money = deduct_money;
        }

        public String getCoupon_name() {
            return coupon_name;
        }

        public void setCoupon_name(String coupon_name) {
            this.coupon_name = coupon_name;
        }

        public String getCoupon_desc() {
            return coupon_desc;
        }

        public void setCoupon_desc(String coupon_desc) {
            this.coupon_desc = coupon_desc;
        }

        public String getCoupon_platform() {
            return coupon_platform;
        }

        public void setCoupon_platform(String coupon_platform) {
            this.coupon_platform = coupon_platform;
        }

        public String getShop_logo() {
            return shop_logo;
        }

        public void setShop_logo(String shop_logo) {
            this.shop_logo = shop_logo;
        }
    }
}
