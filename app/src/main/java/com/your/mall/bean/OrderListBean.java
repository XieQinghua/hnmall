package com.your.mall.bean;

import java.util.List;

/**
 * Created by xieqinghua.
 * 创建时间：2016/10/12
 * 类描述：
 * 修改备注：
 */
public class OrderListBean {


    /**
     * msg : 订单数据获取成功
     * code : 0
     * data : [{"shop_name":"九鼎醇香","tid":"1611071111142468","shop_id":"257","user_id":"132468","itemnum":"1","need_invoice":"0","buyer_rate":"0","status":"待付款","created_time":"2016-11-07 11:11:31","payment":"39.00","total_fee":"39.00","post_fee":"0.00","payed_fee":"0.00","adjust_fee":"0.00","discount_fee":"0.00","receiver_name":"谢庆华","receiver_mobile":"15200917596","pay_type":"online","logi":{"logi_name":"","logi_no":"","corp_code":""},"order":[{"shop_name":"九鼎醇香","status":"待付款","aftersales_status":" ","title":"贵州茅台集团茅台茅乡珍品酒小酒送礼聚会特价包邮","price":"39.00","pic_path":"http://www.hnmall.com/images/a9/85/89/8fdd4ead75450808f9c7de5d7df8de3bf45d9622.jpg","complaints_status":"NOT_COMPLAINTS","spec_nature_info":"","num":"1","tid":"1611071111142468","oid":"1611071111152468","item_id":"5447","shop_id":"257","iscreate_productscoupon":"0"}]},{"shop_name":"九鼎醇香","tid":"1611071110442468","shop_id":"257","user_id":"132468","itemnum":"1","need_invoice":"0","buyer_rate":"0","status":"待付款","created_time":"2016-11-07 11:10:53","payment":"39.00","total_fee":"39.00","post_fee":"0.00","payed_fee":"0.00","adjust_fee":"0.00","discount_fee":"0.00","receiver_name":"谢庆华","receiver_mobile":"15200917596","pay_type":"online","logi":{"logi_name":"","logi_no":"","corp_code":""},"order":[{"shop_name":"九鼎醇香","status":"待付款","aftersales_status":" ","title":"贵州茅台集团茅台茅乡珍品酒小酒送礼聚会特价包邮","price":"39.00","pic_path":"http://www.hnmall.com/images/a9/85/89/8fdd4ead75450808f9c7de5d7df8de3bf45d9622.jpg","complaints_status":"NOT_COMPLAINTS","spec_nature_info":"","num":"1","tid":"1611071110442468","oid":"1611071110452468","item_id":"5447","shop_id":"257","iscreate_productscoupon":"0"}]},{"shop_name":"友阿果园","tid":"1611070259102468","shop_id":"240","user_id":"132468","itemnum":"3","need_invoice":"0","buyer_rate":"0","status":"待付款","created_time":"2016-11-07 02:59:36","payment":"117.00","total_fee":"117.00","post_fee":"0.00","payed_fee":"0.00","adjust_fee":"0.00","discount_fee":"0.00","receiver_name":"谢庆华","receiver_mobile":"15200917596","pay_type":"online","logi":{"logi_name":"","logi_no":"","corp_code":""},"order":[{"shop_name":"友阿果园","status":"待付款","aftersales_status":" ","title":"【友阿果园】泰国带叶桂圆龙眼 2斤 颗颗饱满 ","price":"39.00","pic_path":"http://img.hnmall.com/newimages/201508/source_img/4624/4624_P_1440122331320.jpg","complaints_status":"NOT_COMPLAINTS","spec_nature_info":"","num":"3","tid":"1611070259102468","oid":"1611070259112468","item_id":"4624","shop_id":"240","iscreate_productscoupon":"0"}]},{"shop_name":"友阿果园","tid":"1610291608062468","shop_id":"240","user_id":"132468","itemnum":"1","need_invoice":"0","buyer_rate":"0","status":"已发货","created_time":"2016-10-29 04:08:57","payment":"1.00","total_fee":"1.00","post_fee":"0.00","payed_fee":"1.00","adjust_fee":"0.00","discount_fee":"0.00","receiver_name":"谢庆华","receiver_mobile":"15200917596","pay_type":"online","logi":{"logi_name":"友阿物流","logi_no":"1611051836323283","corp_code":"YAWL","delivery_id":120161107234023400,"receiver_name":"谢庆华"},"order":[{"shop_name":"友阿果园","status":"已发货","aftersales_status":" ","title":"111111","price":"1.00","pic_path":"http://nbh.e9448.com/images/dd/84/d1/362a899766a893ad6778f80647c2afe39bb038fa.jpg","complaints_status":"NOT_COMPLAINTS","spec_nature_info":"商品口味：西瓜味","num":"1","tid":"1610291608062468","oid":"1610291608072468","item_id":"6763","shop_id":"240","iscreate_productscoupon":"0"}]},{"shop_name":"友阿果园","tid":"1610291607312468","shop_id":"240","user_id":"132468","itemnum":"1","need_invoice":"0","buyer_rate":"0","status":"待发货","created_time":"2016-10-29 04:07:40","payment":"1.00","total_fee":"1.00","post_fee":"0.00","payed_fee":"1.00","adjust_fee":"0.00","discount_fee":"0.00","receiver_name":"谢庆华","receiver_mobile":"15200917596","pay_type":"online","logi":{"logi_name":"","logi_no":"","corp_code":""},"order":[{"shop_name":"友阿果园","status":"待发货","aftersales_status":" ","title":"111111","price":"1.00","pic_path":"http://nbh.e9448.com/images/dd/84/d1/362a899766a893ad6778f80647c2afe39bb038fa.jpg","complaints_status":"NOT_COMPLAINTS","spec_nature_info":"商品口味：西瓜味","num":"1","tid":"1610291607312468","oid":"1610291607322468","item_id":"6763","shop_id":"240","iscreate_productscoupon":"0"}]}]
     */

    private String msg;
    private String code;
    /**
     * shop_name : 九鼎醇香
     * tid : 1611071111142468
     * shop_id : 257
     * user_id : 132468
     * itemnum : 1
     * need_invoice : 0
     * buyer_rate : 0
     * status : 待付款
     * created_time : 2016-11-07 11:11:31
     * payment : 39.00
     * total_fee : 39.00
     * post_fee : 0.00
     * payed_fee : 0.00
     * adjust_fee : 0.00
     * discount_fee : 0.00
     * receiver_name : 谢庆华
     * receiver_mobile : 15200917596
     * pay_type : online
     * logi : {"logi_name":"","logi_no":"","corp_code":""}
     * order : [{"shop_name":"九鼎醇香","status":"待付款","aftersales_status":" ","title":"贵州茅台集团茅台茅乡珍品酒小酒送礼聚会特价包邮","price":"39.00","pic_path":"http://www.hnmall.com/images/a9/85/89/8fdd4ead75450808f9c7de5d7df8de3bf45d9622.jpg","complaints_status":"NOT_COMPLAINTS","spec_nature_info":"","num":"1","tid":"1611071111142468","oid":"1611071111152468","item_id":"5447","shop_id":"257","iscreate_productscoupon":"0"}]
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
        private String shop_name;
        private String tid;
        private String shop_id;
        private String user_id;
        private String itemnum;
        private String need_invoice;
        private String buyer_rate;
        private String status;
        private String created_time;
        private String payment;
        private String total_fee;
        private String post_fee;
        private String payed_fee;
        private String adjust_fee;
        private String discount_fee;
        private String receiver_name;
        private String receiver_mobile;
        private String pay_type;
        /**
         * logi_name :
         * logi_no :
         * corp_code :
         */

        private LogiBean logi;
        /**
         * shop_name : 九鼎醇香
         * status : 待付款
         * aftersales_status :
         * title : 贵州茅台集团茅台茅乡珍品酒小酒送礼聚会特价包邮
         * price : 39.00
         * pic_path : http://www.hnmall.com/images/a9/85/89/8fdd4ead75450808f9c7de5d7df8de3bf45d9622.jpg
         * complaints_status : NOT_COMPLAINTS
         * spec_nature_info :
         * num : 1
         * tid : 1611071111142468
         * oid : 1611071111152468
         * item_id : 5447
         * shop_id : 257
         * iscreate_productscoupon : 0
         */

        private List<OrderBean> order;

        public String getShop_name() {
            return shop_name;
        }

        public void setShop_name(String shop_name) {
            this.shop_name = shop_name;
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

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getItemnum() {
            return itemnum;
        }

        public void setItemnum(String itemnum) {
            this.itemnum = itemnum;
        }

        public String getNeed_invoice() {
            return need_invoice;
        }

        public void setNeed_invoice(String need_invoice) {
            this.need_invoice = need_invoice;
        }

        public String getBuyer_rate() {
            return buyer_rate;
        }

        public void setBuyer_rate(String buyer_rate) {
            this.buyer_rate = buyer_rate;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getCreated_time() {
            return created_time;
        }

        public void setCreated_time(String created_time) {
            this.created_time = created_time;
        }

        public String getPayment() {
            return payment;
        }

        public void setPayment(String payment) {
            this.payment = payment;
        }

        public String getTotal_fee() {
            return total_fee;
        }

        public void setTotal_fee(String total_fee) {
            this.total_fee = total_fee;
        }

        public String getPost_fee() {
            return post_fee;
        }

        public void setPost_fee(String post_fee) {
            this.post_fee = post_fee;
        }

        public String getPayed_fee() {
            return payed_fee;
        }

        public void setPayed_fee(String payed_fee) {
            this.payed_fee = payed_fee;
        }

        public String getAdjust_fee() {
            return adjust_fee;
        }

        public void setAdjust_fee(String adjust_fee) {
            this.adjust_fee = adjust_fee;
        }

        public String getDiscount_fee() {
            return discount_fee;
        }

        public void setDiscount_fee(String discount_fee) {
            this.discount_fee = discount_fee;
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

        public String getPay_type() {
            return pay_type;
        }

        public void setPay_type(String pay_type) {
            this.pay_type = pay_type;
        }

        public LogiBean getLogi() {
            return logi;
        }

        public void setLogi(LogiBean logi) {
            this.logi = logi;
        }

        public List<OrderBean> getOrder() {
            return order;
        }

        public void setOrder(List<OrderBean> order) {
            this.order = order;
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

        public static class OrderBean {
            private String shop_name;
            private String status;
            private String aftersales_status;
            private String title;
            private String price;
            private String pic_path;
            private String complaints_status;
            private String spec_nature_info;
            private String num;
            private String tid;
            private String oid;
            private String item_id;
            private String shop_id;
            private String iscreate_productscoupon;

            public String getShop_name() {
                return shop_name;
            }

            public void setShop_name(String shop_name) {
                this.shop_name = shop_name;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getAftersales_status() {
                return aftersales_status;
            }

            public void setAftersales_status(String aftersales_status) {
                this.aftersales_status = aftersales_status;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getPic_path() {
                return pic_path;
            }

            public void setPic_path(String pic_path) {
                this.pic_path = pic_path;
            }

            public String getComplaints_status() {
                return complaints_status;
            }

            public void setComplaints_status(String complaints_status) {
                this.complaints_status = complaints_status;
            }

            public String getSpec_nature_info() {
                return spec_nature_info;
            }

            public void setSpec_nature_info(String spec_nature_info) {
                this.spec_nature_info = spec_nature_info;
            }

            public String getNum() {
                return num;
            }

            public void setNum(String num) {
                this.num = num;
            }

            public String getTid() {
                return tid;
            }

            public void setTid(String tid) {
                this.tid = tid;
            }

            public String getOid() {
                return oid;
            }

            public void setOid(String oid) {
                this.oid = oid;
            }

            public String getItem_id() {
                return item_id;
            }

            public void setItem_id(String item_id) {
                this.item_id = item_id;
            }

            public String getShop_id() {
                return shop_id;
            }

            public void setShop_id(String shop_id) {
                this.shop_id = shop_id;
            }

            public String getIscreate_productscoupon() {
                return iscreate_productscoupon;
            }

            public void setIscreate_productscoupon(String iscreate_productscoupon) {
                this.iscreate_productscoupon = iscreate_productscoupon;
            }
        }
    }
}
