package com.your.mall.bean;

/**
 * Created by xieqinghua.
 * 创建时间：2016/10/8
 * 类描述：登录服务平台返回用户参数
 * 修改备注：
 */
public class LoginTepinBean {
    /**
     * msg : 获取成功
     * code : 0
     * data : {"user_id":"10103","user_name":"13520218993","mobile_phone":"13520218993","reg_time":"","cart_num":"5"}
     */

    private String msg;
    private String code;
    /**
     * user_id : 10103
     * user_name : 13520218993
     * mobile_phone : 13520218993
     * reg_time :
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
        private String user_id;
        private String user_name;
        private String mobile_phone;
        private String reg_time;
        private String cart_num;

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getMobile_phone() {
            return mobile_phone;
        }

        public void setMobile_phone(String mobile_phone) {
            this.mobile_phone = mobile_phone;
        }

        public String getReg_time() {
            return reg_time;
        }

        public void setReg_time(String reg_time) {
            this.reg_time = reg_time;
        }

        public String getCart_num() {
            return cart_num;
        }

        public void setCart_num(String cart_num) {
            this.cart_num = cart_num;
        }
    }

//    /**
//     * msg : 获取成功
//     * code : 0
//     * data : {"user_id":"154548","user_name":"15200917596","mobile_phone":"15200917596","reg_time":1470709881,"cart_num":"1"}
//     */
//
//    private String msg;
//    private String code;
//    /**
//     * user_id : 154548
//     * user_name : 15200917596
//     * mobile_phone : 15200917596
//     * reg_time : 1470709881
//     * cart_num : 1
//     */
//
//    private DataBean data;
//
//    public String getMsg() {
//        return msg;
//    }
//
//    public void setMsg(String msg) {
//        this.msg = msg;
//    }
//
//    public String getCode() {
//        return code;
//    }
//
//    public void setCode(String code) {
//        this.code = code;
//    }
//
//    public DataBean getData() {
//        return data;
//    }
//
//    public void setData(DataBean data) {
//        this.data = data;
//    }
//
//    public static class DataBean {
//        private String user_id;
//        private String user_name;
//        private String mobile_phone;
//        private int reg_time;
//        private String cart_num;
//
//        public String getUser_id() {
//            return user_id;
//        }
//
//        public void setUser_id(String user_id) {
//            this.user_id = user_id;
//        }
//
//        public String getUser_name() {
//            return user_name;
//        }
//
//        public void setUser_name(String user_name) {
//            this.user_name = user_name;
//        }
//
//        public String getMobile_phone() {
//            return mobile_phone;
//        }
//
//        public void setMobile_phone(String mobile_phone) {
//            this.mobile_phone = mobile_phone;
//        }
//
//        public int getReg_time() {
//            return reg_time;
//        }
//
//        public void setReg_time(int reg_time) {
//            this.reg_time = reg_time;
//        }
//
//        public String getCart_num() {
//            return cart_num;
//        }
//
//        public void setCart_num(String cart_num) {
//            this.cart_num = cart_num;
//        }
//    }

}
