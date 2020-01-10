package com.your.mall.bean;

import java.util.List;

/**
 * Created by xieqinghua.
 * 创建时间：2016/10/13
 * 类描述：购物车list
 * 修改备注：
 */
public class ShoppingCartBean {

    /**
     * msg : 购物车数据获取成功
     * code : 0
     * data : [{"shop_id":"240","shop_name":"友阿果园","total_weight":"0","itemnum":"0","total_fee":"0.00","total_discount":"0.00","total_notactivity_total_fee":"0.00","is_checked":"0","promotion_info":"全场满49.00元免邮","good_list":[{"cart_id":"31450","item_id":"5121","shop_id":"240","sku_id":"5121","obj_type":"item","activity_id":"","cat_id":"741","discount_price":"0.00","price":"89.00","total_price":"89.00","quantity":"1","title":"【周年巨献】手剥巴西松子228g/罐 友阿农博汇监制正品保障","image_default_id":"http://img.hnmall.com/newimages/201512/source_img/5121/5121_P_1449000413217.jpg","weight":"0.23","is_freepostage":"0","is_checked":"0","iscreate_productscoupon":"0","status":"instock","store":"269","selected_promotion":"0","valid":"0","promotions":[{"promotion_id":"0","promotion_name":"不使用活动优惠"},{"promotion_id":"414","promotion_name":"满4件享88折","promotion_tag":"X件Y折"}]},{"cart_id":"31452","item_id":"6935","shop_id":"240","sku_id":"7361","obj_type":"item","activity_id":"","cat_id":"731","discount_price":"0.00","price":"14.90","total_price":"14.90","quantity":"1","title":"【友阿果园】云南特产新鲜有籽红提 2斤装","image_default_id":"http://www.hnmall.com/images/25/45/7e/459e35b917d62135507339e5e596bfc3eb7edfb3.jpg","weight":"1.00","is_freepostage":"0","is_checked":"0","iscreate_productscoupon":"0","status":"onsale","store":"80","selected_promotion":"0","valid":"1","promotions":[{"promotion_id":"0","promotion_name":"不使用活动优惠"},{"promotion_id":"414","promotion_name":"满4件享88折","promotion_tag":"X件Y折"}]},{"cart_id":"31481","item_id":"4830","shop_id":"240","sku_id":"4830","obj_type":"item","activity_id":"","cat_id":"583","discount_price":"0.00","price":"69.00","total_price":"69.00","quantity":"1","title":"【友阿果园】山东富士精美包装 整箱16个装","image_default_id":"http://img.hnmall.com/newimages/201510/source_img/4830/4830_P_1444970019387.jpg","weight":"4.90","is_freepostage":"0","is_checked":"0","iscreate_productscoupon":"0","status":"onsale","store":"995","selected_promotion":"0","valid":"1","promotions":[{"promotion_id":"0","promotion_name":"不使用活动优惠"},{"promotion_id":"414","promotion_name":"满4件享88折","promotion_tag":"X件Y折"}]},{"cart_id":"31482","item_id":"6888","shop_id":"240","sku_id":"7314","obj_type":"item","activity_id":"","cat_id":"735","discount_price":"0.00","price":"19.00","total_price":"19.00","quantity":"1","title":"【友阿果园】佳沃蓝莓1盒装 满68元包邮","image_default_id":"http://www.hnmall.com/images/0b/67/d2/6a22acbddafb894a11b7415b114c7b43a2e2dc85.jpg","weight":"0.50","is_freepostage":"0","is_checked":"0","iscreate_productscoupon":"0","status":"instock","store":"1","selected_promotion":"403","valid":"0","promotions":[]},{"cart_id":"43002","item_id":"6957","shop_id":"240","sku_id":"7387","obj_type":"item","activity_id":"","cat_id":"734","discount_price":"0.00","price":"28.80","total_price":"28.80","quantity":"1","title":"【友阿果园】宁夏中卫沙漠瓜 1个装 18斤以上","image_default_id":"http://www.hnmall.com/images/38/ff/8d/6e279dda9800222ffc52fb01ce02558b4ac3ea38.jpg","weight":"","is_freepostage":"1","is_checked":"0","iscreate_productscoupon":"0","status":"instock","store":"603","selected_promotion":"0","valid":"0","promotions":[]},{"cart_id":"54731","item_id":"4624","shop_id":"240","sku_id":"4624","obj_type":"item","activity_id":"","cat_id":"720","discount_price":"0.00","price":"29.00","total_price":"87.00","quantity":"3","title":"【友阿果园】泰国带叶桂圆龙眼 2斤 颗颗饱满 ","image_default_id":"http://www.hnmall.com/images/af/28/05/9d4205891348389ffcf43348d3660d85f93e7234.jpg","weight":"3.30","is_freepostage":"0","is_checked":"0","iscreate_productscoupon":"0","status":"onsale","store":"511","selected_promotion":"414","valid":"1","promotions":[{"promotion_id":"0","promotion_name":"不使用活动优惠"},{"promotion_id":"414","promotion_name":"满4件享88折","promotion_tag":"X件Y折"}]}]}]
     * moneydata : {"totalAfterDiscount":"0.00","number":"0","totalDiscount":"0","totalPrice":"0"}
     */

    private String msg;
    private String code;
    /**
     * totalAfterDiscount : 0.00
     * number : 0
     * totalDiscount : 0
     * totalPrice : 0
     */

    private MoneydataBean moneydata;
    /**
     * shop_id : 240
     * shop_name : 友阿果园
     * total_weight : 0
     * itemnum : 0
     * total_fee : 0.00
     * total_discount : 0.00
     * total_notactivity_total_fee : 0.00
     * is_checked : 0
     * promotion_info : 全场满49.00元免邮
     * good_list : [{"cart_id":"31450","item_id":"5121","shop_id":"240","sku_id":"5121","obj_type":"item","activity_id":"","cat_id":"741","discount_price":"0.00","price":"89.00","total_price":"89.00","quantity":"1","title":"【周年巨献】手剥巴西松子228g/罐 友阿农博汇监制正品保障","image_default_id":"http://img.hnmall.com/newimages/201512/source_img/5121/5121_P_1449000413217.jpg","weight":"0.23","is_freepostage":"0","is_checked":"0","iscreate_productscoupon":"0","status":"instock","store":"269","selected_promotion":"0","valid":"0","promotions":[{"promotion_id":"0","promotion_name":"不使用活动优惠"},{"promotion_id":"414","promotion_name":"满4件享88折","promotion_tag":"X件Y折"}]},{"cart_id":"31452","item_id":"6935","shop_id":"240","sku_id":"7361","obj_type":"item","activity_id":"","cat_id":"731","discount_price":"0.00","price":"14.90","total_price":"14.90","quantity":"1","title":"【友阿果园】云南特产新鲜有籽红提 2斤装","image_default_id":"http://www.hnmall.com/images/25/45/7e/459e35b917d62135507339e5e596bfc3eb7edfb3.jpg","weight":"1.00","is_freepostage":"0","is_checked":"0","iscreate_productscoupon":"0","status":"onsale","store":"80","selected_promotion":"0","valid":"1","promotions":[{"promotion_id":"0","promotion_name":"不使用活动优惠"},{"promotion_id":"414","promotion_name":"满4件享88折","promotion_tag":"X件Y折"}]},{"cart_id":"31481","item_id":"4830","shop_id":"240","sku_id":"4830","obj_type":"item","activity_id":"","cat_id":"583","discount_price":"0.00","price":"69.00","total_price":"69.00","quantity":"1","title":"【友阿果园】山东富士精美包装 整箱16个装","image_default_id":"http://img.hnmall.com/newimages/201510/source_img/4830/4830_P_1444970019387.jpg","weight":"4.90","is_freepostage":"0","is_checked":"0","iscreate_productscoupon":"0","status":"onsale","store":"995","selected_promotion":"0","valid":"1","promotions":[{"promotion_id":"0","promotion_name":"不使用活动优惠"},{"promotion_id":"414","promotion_name":"满4件享88折","promotion_tag":"X件Y折"}]},{"cart_id":"31482","item_id":"6888","shop_id":"240","sku_id":"7314","obj_type":"item","activity_id":"","cat_id":"735","discount_price":"0.00","price":"19.00","total_price":"19.00","quantity":"1","title":"【友阿果园】佳沃蓝莓1盒装 满68元包邮","image_default_id":"http://www.hnmall.com/images/0b/67/d2/6a22acbddafb894a11b7415b114c7b43a2e2dc85.jpg","weight":"0.50","is_freepostage":"0","is_checked":"0","iscreate_productscoupon":"0","status":"instock","store":"1","selected_promotion":"403","valid":"0","promotions":[]},{"cart_id":"43002","item_id":"6957","shop_id":"240","sku_id":"7387","obj_type":"item","activity_id":"","cat_id":"734","discount_price":"0.00","price":"28.80","total_price":"28.80","quantity":"1","title":"【友阿果园】宁夏中卫沙漠瓜 1个装 18斤以上","image_default_id":"http://www.hnmall.com/images/38/ff/8d/6e279dda9800222ffc52fb01ce02558b4ac3ea38.jpg","weight":"","is_freepostage":"1","is_checked":"0","iscreate_productscoupon":"0","status":"instock","store":"603","selected_promotion":"0","valid":"0","promotions":[]},{"cart_id":"54731","item_id":"4624","shop_id":"240","sku_id":"4624","obj_type":"item","activity_id":"","cat_id":"720","discount_price":"0.00","price":"29.00","total_price":"87.00","quantity":"3","title":"【友阿果园】泰国带叶桂圆龙眼 2斤 颗颗饱满 ","image_default_id":"http://www.hnmall.com/images/af/28/05/9d4205891348389ffcf43348d3660d85f93e7234.jpg","weight":"3.30","is_freepostage":"0","is_checked":"0","iscreate_productscoupon":"0","status":"onsale","store":"511","selected_promotion":"414","valid":"1","promotions":[{"promotion_id":"0","promotion_name":"不使用活动优惠"},{"promotion_id":"414","promotion_name":"满4件享88折","promotion_tag":"X件Y折"}]}]
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

    public MoneydataBean getMoneydata() {
        return moneydata;
    }

    public void setMoneydata(MoneydataBean moneydata) {
        this.moneydata = moneydata;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class MoneydataBean {
        private String totalAfterDiscount;
        private String number;
        private String totalDiscount;
        private String totalPrice;

        public String getTotalAfterDiscount() {
            return totalAfterDiscount;
        }

        public void setTotalAfterDiscount(String totalAfterDiscount) {
            this.totalAfterDiscount = totalAfterDiscount;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public String getTotalDiscount() {
            return totalDiscount;
        }

        public void setTotalDiscount(String totalDiscount) {
            this.totalDiscount = totalDiscount;
        }

        public String getTotalPrice() {
            return totalPrice;
        }

        public void setTotalPrice(String totalPrice) {
            this.totalPrice = totalPrice;
        }
    }

    public static class DataBean {
        private String shop_id;
        private String shop_name;
        private String total_weight;
        private String itemnum;
        private String total_fee;
        private String total_discount;
        private String total_notactivity_total_fee;
        private String is_checked;
        private String promotion_info;
        /**
         * cart_id : 31450
         * item_id : 5121
         * shop_id : 240
         * sku_id : 5121
         * obj_type : item
         * activity_id :
         * cat_id : 741
         * discount_price : 0.00
         * price : 89.00
         * total_price : 89.00
         * quantity : 1
         * title : 【周年巨献】手剥巴西松子228g/罐 友阿农博汇监制正品保障
         * image_default_id : http://img.hnmall.com/newimages/201512/source_img/5121/5121_P_1449000413217.jpg
         * weight : 0.23
         * is_freepostage : 0
         * is_checked : 0
         * iscreate_productscoupon : 0
         * status : instock
         * store : 269
         * selected_promotion : 0
         * valid : 0
         * promotions : [{"promotion_id":"0","promotion_name":"不使用活动优惠"},{"promotion_id":"414","promotion_name":"满4件享88折","promotion_tag":"X件Y折"}]
         */

        private List<GoodListBean> good_list;

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

        public String getTotal_weight() {
            return total_weight;
        }

        public void setTotal_weight(String total_weight) {
            this.total_weight = total_weight;
        }

        public String getItemnum() {
            return itemnum;
        }

        public void setItemnum(String itemnum) {
            this.itemnum = itemnum;
        }

        public String getTotal_fee() {
            return total_fee;
        }

        public void setTotal_fee(String total_fee) {
            this.total_fee = total_fee;
        }

        public String getTotal_discount() {
            return total_discount;
        }

        public void setTotal_discount(String total_discount) {
            this.total_discount = total_discount;
        }

        public String getTotal_notactivity_total_fee() {
            return total_notactivity_total_fee;
        }

        public void setTotal_notactivity_total_fee(String total_notactivity_total_fee) {
            this.total_notactivity_total_fee = total_notactivity_total_fee;
        }

        public String getIs_checked() {
            return is_checked;
        }

        public void setIs_checked(String is_checked) {
            this.is_checked = is_checked;
        }

        public String getPromotion_info() {
            return promotion_info;
        }

        public void setPromotion_info(String promotion_info) {
            this.promotion_info = promotion_info;
        }

        public List<GoodListBean> getGood_list() {
            return good_list;
        }

        public void setGood_list(List<GoodListBean> good_list) {
            this.good_list = good_list;
        }

        public static class GoodListBean {
            private String cart_id;
            private String item_id;
            private String shop_id;
            private String sku_id;
            private String obj_type;
            private String activity_id;
            private String cat_id;
            private String discount_price;
            private String price;
            private String total_price;
            private String quantity;
            private String title;
            private String image_default_id;
            private String weight;
            private String is_freepostage;
            private String is_checked;
            private String iscreate_productscoupon;
            private String status;
            private String store;
            private String selected_promotion;
            private String valid;
            /**
             * promotion_id : 0
             * promotion_name : 不使用活动优惠
             */

            private List<PromotionsBean> promotions;

            public String getCart_id() {
                return cart_id;
            }

            public void setCart_id(String cart_id) {
                this.cart_id = cart_id;
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

            public String getSku_id() {
                return sku_id;
            }

            public void setSku_id(String sku_id) {
                this.sku_id = sku_id;
            }

            public String getObj_type() {
                return obj_type;
            }

            public void setObj_type(String obj_type) {
                this.obj_type = obj_type;
            }

            public String getActivity_id() {
                return activity_id;
            }

            public void setActivity_id(String activity_id) {
                this.activity_id = activity_id;
            }

            public String getCat_id() {
                return cat_id;
            }

            public void setCat_id(String cat_id) {
                this.cat_id = cat_id;
            }

            public String getDiscount_price() {
                return discount_price;
            }

            public void setDiscount_price(String discount_price) {
                this.discount_price = discount_price;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getTotal_price() {
                return total_price;
            }

            public void setTotal_price(String total_price) {
                this.total_price = total_price;
            }

            public String getQuantity() {
                return quantity;
            }

            public void setQuantity(String quantity) {
                this.quantity = quantity;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getImage_default_id() {
                return image_default_id;
            }

            public void setImage_default_id(String image_default_id) {
                this.image_default_id = image_default_id;
            }

            public String getWeight() {
                return weight;
            }

            public void setWeight(String weight) {
                this.weight = weight;
            }

            public String getIs_freepostage() {
                return is_freepostage;
            }

            public void setIs_freepostage(String is_freepostage) {
                this.is_freepostage = is_freepostage;
            }

            public String getIs_checked() {
                return is_checked;
            }

            public void setIs_checked(String is_checked) {
                this.is_checked = is_checked;
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

            public String getStore() {
                return store;
            }

            public void setStore(String store) {
                this.store = store;
            }

            public String getSelected_promotion() {
                return selected_promotion;
            }

            public void setSelected_promotion(String selected_promotion) {
                this.selected_promotion = selected_promotion;
            }

            public String getValid() {
                return valid;
            }

            public void setValid(String valid) {
                this.valid = valid;
            }

            public List<PromotionsBean> getPromotions() {
                return promotions;
            }

            public void setPromotions(List<PromotionsBean> promotions) {
                this.promotions = promotions;
            }

            public static class PromotionsBean {
                private String promotion_id;
                private String promotion_name;

                public String getPromotion_id() {
                    return promotion_id;
                }

                public void setPromotion_id(String promotion_id) {
                    this.promotion_id = promotion_id;
                }

                public String getPromotion_name() {
                    return promotion_name;
                }

                public void setPromotion_name(String promotion_name) {
                    this.promotion_name = promotion_name;
                }
            }
        }
    }
}
