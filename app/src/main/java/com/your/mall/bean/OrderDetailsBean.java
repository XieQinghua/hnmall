package com.your.mall.bean;

import java.util.List;

/**
 * Created by xieqinghua.
 * 创建时间：2016/10/21
 * 类描述：订单详情
 * 修改备注：
 */
public class OrderDetailsBean {

    /**
     * msg : 数据获取成功
     * code : 0
     * data : {"trade":{"tid":"1610170330414548","status":"已关闭","payment":"463.00","post_fee":"0.00","receiver_state":"湖南省","receiver_city":"长沙市","receiver_district":"天心区","ziti_addr":"","ziti_memo":"","receiver_address":"创谷国家广告产业园A2栋4楼","trade_memo":"","receiver_name":"谢庆华","receiver_mobile":"15200917596","total_fee":"463.00","discount_fee":"0.00","buyer_rate":"0","adjust_fee":"0.00","pay_type":"online","cancel_reason":"订单未在下单4320分钟内完成支付,被系统自动关闭。","shop_id":"240","created_time":"2016-10-17 03:30:48","deposit_price":"0.000","orders":[{"oid":"1610170330424548","price":"89.00","num":"1","title":"【友阿果园】泰国金枕头榴莲 4-5斤","aftersales_status":"","complaints_status":"NOT_COMPLAINTS","item_id":"6961","pic_path":"http://www.hnmall.com/images/ae/ad/21/776e14a7b0fe56d806798f848e7e7c3742e011e6.jpg","spec_nature_info":"","iscreate_productscoupon":"0","status":"已关闭","buyer_rate":"1","aftersalesnum":"0","tid":"1610170330414548","shop_id":"240","shop_name":"友阿果园","status_button":""},{"oid":"1610170330434548","price":"39.00","num":"1","title":"【友阿果园】泰国青柚 1个装 2斤左右","aftersales_status":"","complaints_status":"NOT_COMPLAINTS","item_id":"4752","pic_path":"http://img.hnmall.com/newimages/201509/source_img/4752/4752_P_1443119378872.jpg","spec_nature_info":"","iscreate_productscoupon":"0","status":"已关闭","buyer_rate":"1","aftersalesnum":"0","tid":"1610170330414548","shop_id":"240","shop_name":"友阿果园","status_button":""},{"oid":"1610170330444548","price":"89.00","num":"1","title":"【友阿果园】泰国金枕头榴莲4-5斤 ","aftersales_status":"","complaints_status":"NOT_COMPLAINTS","item_id":"4584","pic_path":"http://www.hnmall.com/images/a9/32/17/87a1c5d91bb2f85a65554aa3b45da18b3c36fb0f.jpg","spec_nature_info":"","iscreate_productscoupon":"0","status":"已关闭","buyer_rate":"1","aftersalesnum":"0","tid":"1610170330414548","shop_id":"240","shop_name":"友阿果园","status_button":""},{"oid":"1610170330454548","price":"72.00","num":"1","title":"【友阿果园】泰国带叶桂圆龙眼2kg 颗颗饱满","aftersales_status":"","complaints_status":"NOT_COMPLAINTS","item_id":"4625","pic_path":"http://img.hnmall.com/newimages/201508/source_img/4625/4625_P_1440122403599.jpg","spec_nature_info":"","iscreate_productscoupon":"0","status":"已关闭","buyer_rate":"1","aftersalesnum":"0","tid":"1610170330414548","shop_id":"240","shop_name":"友阿果园","status_button":""},{"oid":"1610170330464548","price":"29.00","num":"6","title":"【友阿果园】泰国带叶桂圆龙眼 2斤 颗颗饱满 ","aftersales_status":"","complaints_status":"NOT_COMPLAINTS","item_id":"4624","pic_path":"http://www.hnmall.com/images/af/28/05/9d4205891348389ffcf43348d3660d85f93e7234.jpg","spec_nature_info":"","iscreate_productscoupon":"0","status":"已关闭","buyer_rate":"1","aftersalesnum":"0","tid":"1610170330414548","shop_id":"240","shop_name":"友阿果园","status_button":""}],"is_buyer_rate":"0","shop_name":"友阿果园"},"logi":""}
     */

    private String msg;
    private String code;
    /**
     * trade : {"tid":"1610170330414548","status":"已关闭","payment":"463.00","post_fee":"0.00","receiver_state":"湖南省","receiver_city":"长沙市","receiver_district":"天心区","ziti_addr":"","ziti_memo":"","receiver_address":"创谷国家广告产业园A2栋4楼","trade_memo":"","receiver_name":"谢庆华","receiver_mobile":"15200917596","total_fee":"463.00","discount_fee":"0.00","buyer_rate":"0","adjust_fee":"0.00","pay_type":"online","cancel_reason":"订单未在下单4320分钟内完成支付,被系统自动关闭。","shop_id":"240","created_time":"2016-10-17 03:30:48","deposit_price":"0.000","orders":[{"oid":"1610170330424548","price":"89.00","num":"1","title":"【友阿果园】泰国金枕头榴莲 4-5斤","aftersales_status":"","complaints_status":"NOT_COMPLAINTS","item_id":"6961","pic_path":"http://www.hnmall.com/images/ae/ad/21/776e14a7b0fe56d806798f848e7e7c3742e011e6.jpg","spec_nature_info":"","iscreate_productscoupon":"0","status":"已关闭","buyer_rate":"1","aftersalesnum":"0","tid":"1610170330414548","shop_id":"240","shop_name":"友阿果园","status_button":""},{"oid":"1610170330434548","price":"39.00","num":"1","title":"【友阿果园】泰国青柚 1个装 2斤左右","aftersales_status":"","complaints_status":"NOT_COMPLAINTS","item_id":"4752","pic_path":"http://img.hnmall.com/newimages/201509/source_img/4752/4752_P_1443119378872.jpg","spec_nature_info":"","iscreate_productscoupon":"0","status":"已关闭","buyer_rate":"1","aftersalesnum":"0","tid":"1610170330414548","shop_id":"240","shop_name":"友阿果园","status_button":""},{"oid":"1610170330444548","price":"89.00","num":"1","title":"【友阿果园】泰国金枕头榴莲4-5斤 ","aftersales_status":"","complaints_status":"NOT_COMPLAINTS","item_id":"4584","pic_path":"http://www.hnmall.com/images/a9/32/17/87a1c5d91bb2f85a65554aa3b45da18b3c36fb0f.jpg","spec_nature_info":"","iscreate_productscoupon":"0","status":"已关闭","buyer_rate":"1","aftersalesnum":"0","tid":"1610170330414548","shop_id":"240","shop_name":"友阿果园","status_button":""},{"oid":"1610170330454548","price":"72.00","num":"1","title":"【友阿果园】泰国带叶桂圆龙眼2kg 颗颗饱满","aftersales_status":"","complaints_status":"NOT_COMPLAINTS","item_id":"4625","pic_path":"http://img.hnmall.com/newimages/201508/source_img/4625/4625_P_1440122403599.jpg","spec_nature_info":"","iscreate_productscoupon":"0","status":"已关闭","buyer_rate":"1","aftersalesnum":"0","tid":"1610170330414548","shop_id":"240","shop_name":"友阿果园","status_button":""},{"oid":"1610170330464548","price":"29.00","num":"6","title":"【友阿果园】泰国带叶桂圆龙眼 2斤 颗颗饱满 ","aftersales_status":"","complaints_status":"NOT_COMPLAINTS","item_id":"4624","pic_path":"http://www.hnmall.com/images/af/28/05/9d4205891348389ffcf43348d3660d85f93e7234.jpg","spec_nature_info":"","iscreate_productscoupon":"0","status":"已关闭","buyer_rate":"1","aftersalesnum":"0","tid":"1610170330414548","shop_id":"240","shop_name":"友阿果园","status_button":""}],"is_buyer_rate":"0","shop_name":"友阿果园"}
     * logi :
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
        /**
         * tid : 1610170330414548
         * status : 已关闭
         * payment : 463.00
         * post_fee : 0.00
         * receiver_state : 湖南省
         * receiver_city : 长沙市
         * receiver_district : 天心区
         * ziti_addr :
         * ziti_memo :
         * receiver_address : 创谷国家广告产业园A2栋4楼
         * trade_memo :
         * receiver_name : 谢庆华
         * receiver_mobile : 15200917596
         * total_fee : 463.00
         * discount_fee : 0.00
         * buyer_rate : 0
         * adjust_fee : 0.00
         * pay_type : online
         * cancel_reason : 订单未在下单4320分钟内完成支付,被系统自动关闭。
         * shop_id : 240
         * created_time : 2016-10-17 03:30:48
         * deposit_price : 0.000
         * orders : [{"oid":"1610170330424548","price":"89.00","num":"1","title":"【友阿果园】泰国金枕头榴莲 4-5斤","aftersales_status":"","complaints_status":"NOT_COMPLAINTS","item_id":"6961","pic_path":"http://www.hnmall.com/images/ae/ad/21/776e14a7b0fe56d806798f848e7e7c3742e011e6.jpg","spec_nature_info":"","iscreate_productscoupon":"0","status":"已关闭","buyer_rate":"1","aftersalesnum":"0","tid":"1610170330414548","shop_id":"240","shop_name":"友阿果园","status_button":""},{"oid":"1610170330434548","price":"39.00","num":"1","title":"【友阿果园】泰国青柚 1个装 2斤左右","aftersales_status":"","complaints_status":"NOT_COMPLAINTS","item_id":"4752","pic_path":"http://img.hnmall.com/newimages/201509/source_img/4752/4752_P_1443119378872.jpg","spec_nature_info":"","iscreate_productscoupon":"0","status":"已关闭","buyer_rate":"1","aftersalesnum":"0","tid":"1610170330414548","shop_id":"240","shop_name":"友阿果园","status_button":""},{"oid":"1610170330444548","price":"89.00","num":"1","title":"【友阿果园】泰国金枕头榴莲4-5斤 ","aftersales_status":"","complaints_status":"NOT_COMPLAINTS","item_id":"4584","pic_path":"http://www.hnmall.com/images/a9/32/17/87a1c5d91bb2f85a65554aa3b45da18b3c36fb0f.jpg","spec_nature_info":"","iscreate_productscoupon":"0","status":"已关闭","buyer_rate":"1","aftersalesnum":"0","tid":"1610170330414548","shop_id":"240","shop_name":"友阿果园","status_button":""},{"oid":"1610170330454548","price":"72.00","num":"1","title":"【友阿果园】泰国带叶桂圆龙眼2kg 颗颗饱满","aftersales_status":"","complaints_status":"NOT_COMPLAINTS","item_id":"4625","pic_path":"http://img.hnmall.com/newimages/201508/source_img/4625/4625_P_1440122403599.jpg","spec_nature_info":"","iscreate_productscoupon":"0","status":"已关闭","buyer_rate":"1","aftersalesnum":"0","tid":"1610170330414548","shop_id":"240","shop_name":"友阿果园","status_button":""},{"oid":"1610170330464548","price":"29.00","num":"6","title":"【友阿果园】泰国带叶桂圆龙眼 2斤 颗颗饱满 ","aftersales_status":"","complaints_status":"NOT_COMPLAINTS","item_id":"4624","pic_path":"http://www.hnmall.com/images/af/28/05/9d4205891348389ffcf43348d3660d85f93e7234.jpg","spec_nature_info":"","iscreate_productscoupon":"0","status":"已关闭","buyer_rate":"1","aftersalesnum":"0","tid":"1610170330414548","shop_id":"240","shop_name":"友阿果园","status_button":""}]
         * is_buyer_rate : 0
         * shop_name : 友阿果园
         */

        private TradeBean trade;
        private LogiBean logi;

        public TradeBean getTrade() {
            return trade;
        }

        public void setTrade(TradeBean trade) {
            this.trade = trade;
        }

        public LogiBean getLogi() {
            return logi;
        }

        public void setLogi(LogiBean logi) {
            this.logi = logi;
        }

        public static class LogiBean {
            private String logi_name;
            private String logi_no;
            private String corp_code;

            public String getLogi_name() {
                return logi_name;
            }

            public void setLogi_name(String logi_name) {
                this.logi_name = logi_name;
            }

            public String getLogi_no() {
                return logi_no;
            }

            public void setLogi_no(String logi_no) {
                this.logi_no = logi_no;
            }

            public String getCorp_code() {
                return corp_code;
            }

            public void setCorp_code(String corp_code) {
                this.corp_code = corp_code;
            }
        }

        public static class TradeBean {
            private String tid;
            private String status;
            private String payment;
            private String post_fee;
            private String receiver_state;
            private String receiver_city;
            private String receiver_district;
            private String ziti_addr;
            private String ziti_memo;
            private String receiver_address;
            private String trade_memo;
            private String receiver_name;
            private String receiver_mobile;
            private String total_fee;
            private String discount_fee;
            private String buyer_rate;
            private String adjust_fee;
            private String pay_type;
            private String cancel_reason;
            private String shop_id;
            private String created_time;
            private String deposit_price;
            private String is_buyer_rate;
            private String shop_name;
            /**
             * oid : 1610170330424548
             * price : 89.00
             * num : 1
             * title : 【友阿果园】泰国金枕头榴莲 4-5斤
             * aftersales_status :
             * complaints_status : NOT_COMPLAINTS
             * item_id : 6961
             * pic_path : http://www.hnmall.com/images/ae/ad/21/776e14a7b0fe56d806798f848e7e7c3742e011e6.jpg
             * spec_nature_info :
             * iscreate_productscoupon : 0
             * status : 已关闭
             * buyer_rate : 1
             * aftersalesnum : 0
             * tid : 1610170330414548
             * shop_id : 240
             * shop_name : 友阿果园
             * status_button :
             */

            private List<OrdersBean> orders;

            public String getTid() {
                return tid;
            }

            public void setTid(String tid) {
                this.tid = tid;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getPayment() {
                return payment;
            }

            public void setPayment(String payment) {
                this.payment = payment;
            }

            public String getPost_fee() {
                return post_fee;
            }

            public void setPost_fee(String post_fee) {
                this.post_fee = post_fee;
            }

            public String getReceiver_state() {
                return receiver_state;
            }

            public void setReceiver_state(String receiver_state) {
                this.receiver_state = receiver_state;
            }

            public String getReceiver_city() {
                return receiver_city;
            }

            public void setReceiver_city(String receiver_city) {
                this.receiver_city = receiver_city;
            }

            public String getReceiver_district() {
                return receiver_district;
            }

            public void setReceiver_district(String receiver_district) {
                this.receiver_district = receiver_district;
            }

            public String getZiti_addr() {
                return ziti_addr;
            }

            public void setZiti_addr(String ziti_addr) {
                this.ziti_addr = ziti_addr;
            }

            public String getZiti_memo() {
                return ziti_memo;
            }

            public void setZiti_memo(String ziti_memo) {
                this.ziti_memo = ziti_memo;
            }

            public String getReceiver_address() {
                return receiver_address;
            }

            public void setReceiver_address(String receiver_address) {
                this.receiver_address = receiver_address;
            }

            public String getTrade_memo() {
                return trade_memo;
            }

            public void setTrade_memo(String trade_memo) {
                this.trade_memo = trade_memo;
            }

            public String getReceiver_name() {
                return receiver_name;
            }

            public void setReceiver_name(String receiver_name) {
                this.receiver_name = receiver_name;
            }

            public String getReceiver_mobile() {
                return receiver_mobile;
            }

            public void setReceiver_mobile(String receiver_mobile) {
                this.receiver_mobile = receiver_mobile;
            }

            public String getTotal_fee() {
                return total_fee;
            }

            public void setTotal_fee(String total_fee) {
                this.total_fee = total_fee;
            }

            public String getDiscount_fee() {
                return discount_fee;
            }

            public void setDiscount_fee(String discount_fee) {
                this.discount_fee = discount_fee;
            }

            public String getBuyer_rate() {
                return buyer_rate;
            }

            public void setBuyer_rate(String buyer_rate) {
                this.buyer_rate = buyer_rate;
            }

            public String getAdjust_fee() {
                return adjust_fee;
            }

            public void setAdjust_fee(String adjust_fee) {
                this.adjust_fee = adjust_fee;
            }

            public String getPay_type() {
                return pay_type;
            }

            public void setPay_type(String pay_type) {
                this.pay_type = pay_type;
            }

            public String getCancel_reason() {
                return cancel_reason;
            }

            public void setCancel_reason(String cancel_reason) {
                this.cancel_reason = cancel_reason;
            }

            public String getShop_id() {
                return shop_id;
            }

            public void setShop_id(String shop_id) {
                this.shop_id = shop_id;
            }

            public String getCreated_time() {
                return created_time;
            }

            public void setCreated_time(String created_time) {
                this.created_time = created_time;
            }

            public String getDeposit_price() {
                return deposit_price;
            }

            public void setDeposit_price(String deposit_price) {
                this.deposit_price = deposit_price;
            }

            public String getIs_buyer_rate() {
                return is_buyer_rate;
            }

            public void setIs_buyer_rate(String is_buyer_rate) {
                this.is_buyer_rate = is_buyer_rate;
            }

            public String getShop_name() {
                return shop_name;
            }

            public void setShop_name(String shop_name) {
                this.shop_name = shop_name;
            }

            public List<OrdersBean> getOrders() {
                return orders;
            }

            public void setOrders(List<OrdersBean> orders) {
                this.orders = orders;
            }

            public static class OrdersBean {
                private String oid;
                private String price;
                private String num;
                private String title;
                private String aftersales_status;
                private String complaints_status;
                private String item_id;
                private String pic_path;
                private String spec_nature_info;
                private String iscreate_productscoupon;
                private String status;
                private String buyer_rate;
                private String aftersalesnum;
                private String tid;
                private String shop_id;
                private String shop_name;
                private String status_button;

                public String getOid() {
                    return oid;
                }

                public void setOid(String oid) {
                    this.oid = oid;
                }

                public String getPrice() {
                    return price;
                }

                public void setPrice(String price) {
                    this.price = price;
                }

                public String getNum() {
                    return num;
                }

                public void setNum(String num) {
                    this.num = num;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public String getAftersales_status() {
                    return aftersales_status;
                }

                public void setAftersales_status(String aftersales_status) {
                    this.aftersales_status = aftersales_status;
                }

                public String getComplaints_status() {
                    return complaints_status;
                }

                public void setComplaints_status(String complaints_status) {
                    this.complaints_status = complaints_status;
                }

                public String getItem_id() {
                    return item_id;
                }

                public void setItem_id(String item_id) {
                    this.item_id = item_id;
                }

                public String getPic_path() {
                    return pic_path;
                }

                public void setPic_path(String pic_path) {
                    this.pic_path = pic_path;
                }

                public String getSpec_nature_info() {
                    return spec_nature_info;
                }

                public void setSpec_nature_info(String spec_nature_info) {
                    this.spec_nature_info = spec_nature_info;
                }

                public String getIscreate_productscoupon() {
                    return iscreate_productscoupon;
                }

                public void setIscreate_productscoupon(String iscreate_productscoupon) {
                    this.iscreate_productscoupon = iscreate_productscoupon;
                }

                public String getStatus() {
                    return status;
                }

                public void setStatus(String status) {
                    this.status = status;
                }

                public String getBuyer_rate() {
                    return buyer_rate;
                }

                public void setBuyer_rate(String buyer_rate) {
                    this.buyer_rate = buyer_rate;
                }

                public String getAftersalesnum() {
                    return aftersalesnum;
                }

                public void setAftersalesnum(String aftersalesnum) {
                    this.aftersalesnum = aftersalesnum;
                }

                public String getTid() {
                    return tid;
                }

                public void setTid(String tid) {
                    this.tid = tid;
                }

                public String getShop_id() {
                    return shop_id;
                }

                public void setShop_id(String shop_id) {
                    this.shop_id = shop_id;
                }

                public String getShop_name() {
                    return shop_name;
                }

                public void setShop_name(String shop_name) {
                    this.shop_name = shop_name;
                }

                public String getStatus_button() {
                    return status_button;
                }

                public void setStatus_button(String status_button) {
                    this.status_button = status_button;
                }
            }
        }
    }
}
