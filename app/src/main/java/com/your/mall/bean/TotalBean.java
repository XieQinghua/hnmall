package com.your.mall.bean;

import java.util.List;

/**
 * Created by xieqinghua.
 * 创建时间：2016/10/21
 * 类描述：更新购物车金额接口
 * 修改备注：
 */
public class TotalBean {

    /**
     * msg : 数据获取成功
     * code : 0
     * data : {"deposit":"0","allPayment":"39.00","allDeposit":"0.00","allTotal":"29.00","shop":[{"shop_id":"240","payment":"39.00","total_fee":"29.00","discount_fee":"0.00","obtain_point_fee":"29.00","post_fee":"10.00","itemnum":"1","deposit_price":"0.00","totalWeight":"1.1"}]}
     */

    private String msg;
    private String code;
    /**
     * deposit : 0
     * allPayment : 39.00
     * allDeposit : 0.00
     * allTotal : 29.00
     * shop : [{"shop_id":"240","payment":"39.00","total_fee":"29.00","discount_fee":"0.00","obtain_point_fee":"29.00","post_fee":"10.00","itemnum":"1","deposit_price":"0.00","totalWeight":"1.1"}]
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
        private String deposit;
        private String allPayment;
        private String allDeposit;
        private String allTotal;
        /**
         * shop_id : 240
         * payment : 39.00
         * total_fee : 29.00
         * discount_fee : 0.00
         * obtain_point_fee : 29.00
         * post_fee : 10.00
         * itemnum : 1
         * deposit_price : 0.00
         * totalWeight : 1.1
         */

        private List<ShopBean> shop;

        public String getDeposit() {
            return deposit;
        }

        public void setDeposit(String deposit) {
            this.deposit = deposit;
        }

        public String getAllPayment() {
            return allPayment;
        }

        public void setAllPayment(String allPayment) {
            this.allPayment = allPayment;
        }

        public String getAllDeposit() {
            return allDeposit;
        }

        public void setAllDeposit(String allDeposit) {
            this.allDeposit = allDeposit;
        }

        public String getAllTotal() {
            return allTotal;
        }

        public void setAllTotal(String allTotal) {
            this.allTotal = allTotal;
        }

        public List<ShopBean> getShop() {
            return shop;
        }

        public void setShop(List<ShopBean> shop) {
            this.shop = shop;
        }

        public static class ShopBean {
            private String shop_id;
            private String payment;
            private String total_fee;
            private String discount_fee;
            private String obtain_point_fee;
            private String post_fee;
            private String itemnum;
            private String deposit_price;
            private String totalWeight;

            public String getShop_id() {
                return shop_id;
            }

            public void setShop_id(String shop_id) {
                this.shop_id = shop_id;
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

            public String getDiscount_fee() {
                return discount_fee;
            }

            public void setDiscount_fee(String discount_fee) {
                this.discount_fee = discount_fee;
            }

            public String getObtain_point_fee() {
                return obtain_point_fee;
            }

            public void setObtain_point_fee(String obtain_point_fee) {
                this.obtain_point_fee = obtain_point_fee;
            }

            public String getPost_fee() {
                return post_fee;
            }

            public void setPost_fee(String post_fee) {
                this.post_fee = post_fee;
            }

            public String getItemnum() {
                return itemnum;
            }

            public void setItemnum(String itemnum) {
                this.itemnum = itemnum;
            }

            public String getDeposit_price() {
                return deposit_price;
            }

            public void setDeposit_price(String deposit_price) {
                this.deposit_price = deposit_price;
            }

            public String getTotalWeight() {
                return totalWeight;
            }

            public void setTotalWeight(String totalWeight) {
                this.totalWeight = totalWeight;
            }
        }
    }
}
