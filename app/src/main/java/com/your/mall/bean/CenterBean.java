package com.your.mall.bean;

public class CenterBean {

    /**
     * msg : 数据获取成功
     * code : 0
     * data : {"user_id":"10103","account":"13520218993","mobile":"13520218993","username":"13520218993","regtime":"1970-01-01","point":"79","grade_id":"1","grade_name":"初级会员","nupay":"1","nudelivery":"0","nuconfirm":"0","unrate":"8","deposit":"0.00","couponCount":"0","title":"会员中心"}
     */

    private String msg;
    private String code;
    /**
     * user_id : 10103
     * account : 13520218993
     * mobile : 13520218993
     * username : 13520218993
     * regtime : 1970-01-01
     * point : 79
     * grade_id : 1
     * grade_name : 初级会员
     * nupay : 1
     * nudelivery : 0
     * nuconfirm : 0
     * unrate : 8
     * deposit : 0.00
     * couponCount : 0
     * title : 会员中心
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
        private String user_id;
        private String account;
        private String mobile;
        private String username;
        private String regtime;
        private String point;
        private String grade_id;
        private String grade_name;
        private String nupay;
        private String nudelivery;
        private String nuconfirm;
        private String unrate;
        private String deposit;
        private String couponCount;
        private String title;

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getAccount() {
            return account;
        }

        public void setAccount(String account) {
            this.account = account;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getRegtime() {
            return regtime;
        }

        public void setRegtime(String regtime) {
            this.regtime = regtime;
        }

        public String getPoint() {
            return point;
        }

        public void setPoint(String point) {
            this.point = point;
        }

        public String getGrade_id() {
            return grade_id;
        }

        public void setGrade_id(String grade_id) {
            this.grade_id = grade_id;
        }

        public String getGrade_name() {
            return grade_name;
        }

        public void setGrade_name(String grade_name) {
            this.grade_name = grade_name;
        }

        public String getNupay() {
            return nupay;
        }

        public void setNupay(String nupay) {
            this.nupay = nupay;
        }

        public String getNudelivery() {
            return nudelivery;
        }

        public void setNudelivery(String nudelivery) {
            this.nudelivery = nudelivery;
        }

        public String getNuconfirm() {
            return nuconfirm;
        }

        public void setNuconfirm(String nuconfirm) {
            this.nuconfirm = nuconfirm;
        }

        public String getUnrate() {
            return unrate;
        }

        public void setUnrate(String unrate) {
            this.unrate = unrate;
        }

        public String getDeposit() {
            return deposit;
        }

        public void setDeposit(String deposit) {
            this.deposit = deposit;
        }

        public String getCouponCount() {
            return couponCount;
        }

        public void setCouponCount(String couponCount) {
            this.couponCount = couponCount;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
