package com.your.mall.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by xieqinghua.
 * 创建时间：2016/10/21
 * 类描述：订单结算
 * 修改备注：
 */
public class OrderTallyBean implements Serializable {

    /**
     * msg : 数据获取成功
     * code : 0
     * data : {"mode":"fastbuy","iscreate_productscoupon":"0","is_def_addr":"1","def_addr":{"addr_id":"87852","name":"谢庆华","zip":"","tel":"","mobile":"15200917596","def_addr":"1","address":"湖南省长沙市天心区创谷国家广告产业园A2栋4楼"},"cartInfo":[{"shop_id":"222","shop_name":"豆肯食品专营店","total_weight":"0.05","itemnum":"1","total_fee":"65.00","total_discount":"0.00","total_notactivity_total_fee":"65.00","is_checked":"1","promotion_info":"","good_list":[{"cart_id":"","item_id":"5249","shop_id":"222","sku_id":"5447","obj_type":"item","activity_id":"0","cat_id":"570","discount_price":"0.00","price":"65.00","total_price":"65.00","quantity":"1","title":"Starbucks 星巴克VIA意大利免煮咖啡(8条装) 26.4g","image_default_id":"http://img.hnmall.com/newimages/201512/source_img/5249/5249_P_1449972304216.jpg","weight":"0.05","is_freepostage":"0","is_checked":"1","iscreate_productscoupon":"0","status":"onsale","store":"100","selected_promotion":"0","valid":"1","promotions":[]}],"couponList":[{"coupon_name":"不使用优惠券","coupon_code":"-1","is_checked":"1","shop_id":"222"}],"dtyList":[{"template_id":"222","name":"按重量派送"}]}],"pTcouponList":[{"coupon_name":"不使用优惠券","coupon_code":"-1","is_checked":"1","pt_shop_id":"240","pt_templet_id":"289"}],"show_deposit":"0"}
     */

    private String msg;
    private String code;
    /**
     * mode : fastbuy
     * iscreate_productscoupon : 0
     * is_def_addr : 1
     * def_addr : {"addr_id":"87852","name":"谢庆华","zip":"","tel":"","mobile":"15200917596","def_addr":"1","address":"湖南省长沙市天心区创谷国家广告产业园A2栋4楼"}
     * cartInfo : [{"shop_id":"222","shop_name":"豆肯食品专营店","total_weight":"0.05","itemnum":"1","total_fee":"65.00","total_discount":"0.00","total_notactivity_total_fee":"65.00","is_checked":"1","promotion_info":"","good_list":[{"cart_id":"","item_id":"5249","shop_id":"222","sku_id":"5447","obj_type":"item","activity_id":"0","cat_id":"570","discount_price":"0.00","price":"65.00","total_price":"65.00","quantity":"1","title":"Starbucks 星巴克VIA意大利免煮咖啡(8条装) 26.4g","image_default_id":"http://img.hnmall.com/newimages/201512/source_img/5249/5249_P_1449972304216.jpg","weight":"0.05","is_freepostage":"0","is_checked":"1","iscreate_productscoupon":"0","status":"onsale","store":"100","selected_promotion":"0","valid":"1","promotions":[]}],"couponList":[{"coupon_name":"不使用优惠券","coupon_code":"-1","is_checked":"1","shop_id":"222"}],"dtyList":[{"template_id":"222","name":"按重量派送"}]}]
     * pTcouponList : [{"coupon_name":"不使用优惠券","coupon_code":"-1","is_checked":"1","pt_shop_id":"240","pt_templet_id":"289"}]
     * show_deposit : 0
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
        private String mode;
        private String iscreate_productscoupon;
        private String is_def_addr;
        /**
         * addr_id : 87852
         * name : 谢庆华
         * zip :
         * tel :
         * mobile : 15200917596
         * def_addr : 1
         * address : 湖南省长沙市天心区创谷国家广告产业园A2栋4楼
         */

        private DefAddrBean def_addr;
        private String show_deposit;
        /**
         * shop_id : 222
         * shop_name : 豆肯食品专营店
         * total_weight : 0.05
         * itemnum : 1
         * total_fee : 65.00
         * total_discount : 0.00
         * total_notactivity_total_fee : 65.00
         * is_checked : 1
         * promotion_info :
         * good_list : [{"cart_id":"","item_id":"5249","shop_id":"222","sku_id":"5447","obj_type":"item","activity_id":"0","cat_id":"570","discount_price":"0.00","price":"65.00","total_price":"65.00","quantity":"1","title":"Starbucks 星巴克VIA意大利免煮咖啡(8条装) 26.4g","image_default_id":"http://img.hnmall.com/newimages/201512/source_img/5249/5249_P_1449972304216.jpg","weight":"0.05","is_freepostage":"0","is_checked":"1","iscreate_productscoupon":"0","status":"onsale","store":"100","selected_promotion":"0","valid":"1","promotions":[]}]
         * couponList : [{"coupon_name":"不使用优惠券","coupon_code":"-1","is_checked":"1","shop_id":"222"}]
         * dtyList : [{"template_id":"222","name":"按重量派送"}]
         */

        private List<CartInfoBean> cartInfo;
        /**
         * coupon_name : 不使用优惠券
         * coupon_code : -1
         * is_checked : 1
         * pt_shop_id : 240
         * pt_templet_id : 289
         */

        private List<PTcouponListBean> pTcouponList;

        public String getMode() {
            return mode;
        }

        public void setMode(String mode) {
            this.mode = mode;
        }

        public String getIscreate_productscoupon() {
            return iscreate_productscoupon;
        }

        public void setIscreate_productscoupon(String iscreate_productscoupon) {
            this.iscreate_productscoupon = iscreate_productscoupon;
        }

        public String getIs_def_addr() {
            return is_def_addr;
        }

        public void setIs_def_addr(String is_def_addr) {
            this.is_def_addr = is_def_addr;
        }

        public DefAddrBean getDef_addr() {
            return def_addr;
        }

        public void setDef_addr(DefAddrBean def_addr) {
            this.def_addr = def_addr;
        }

        public String getShow_deposit() {
            return show_deposit;
        }

        public void setShow_deposit(String show_deposit) {
            this.show_deposit = show_deposit;
        }

        public List<CartInfoBean> getCartInfo() {
            return cartInfo;
        }

        public void setCartInfo(List<CartInfoBean> cartInfo) {
            this.cartInfo = cartInfo;
        }

        public List<PTcouponListBean> getPTcouponList() {
            return pTcouponList;
        }

        public void setPTcouponList(List<PTcouponListBean> pTcouponList) {
            this.pTcouponList = pTcouponList;
        }

        public static class DefAddrBean {
            private String addr_id;
            private String name;
            private String zip;
            private String tel;
            private String mobile;
            private String def_addr;
            private String address;

            public String getAddr_id() {
                return addr_id;
            }

            public void setAddr_id(String addr_id) {
                this.addr_id = addr_id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getZip() {
                return zip;
            }

            public void setZip(String zip) {
                this.zip = zip;
            }

            public String getTel() {
                return tel;
            }

            public void setTel(String tel) {
                this.tel = tel;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public String getDef_addr() {
                return def_addr;
            }

            public void setDef_addr(String def_addr) {
                this.def_addr = def_addr;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }
        }

        public static class CartInfoBean {
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
             * cart_id :
             * item_id : 5249
             * shop_id : 222
             * sku_id : 5447
             * obj_type : item
             * activity_id : 0
             * cat_id : 570
             * discount_price : 0.00
             * price : 65.00
             * total_price : 65.00
             * quantity : 1
             * title : Starbucks 星巴克VIA意大利免煮咖啡(8条装) 26.4g
             * image_default_id : http://img.hnmall.com/newimages/201512/source_img/5249/5249_P_1449972304216.jpg
             * weight : 0.05
             * is_freepostage : 0
             * is_checked : 1
             * iscreate_productscoupon : 0
             * status : onsale
             * store : 100
             * selected_promotion : 0
             * valid : 1
             * promotions : []
             */

            private List<GoodListBean> good_list;
            /**
             * coupon_name : 不使用优惠券
             * coupon_code : -1
             * is_checked : 1
             * shop_id : 222
             */

            private List<CouponListBean> couponList;
            /**
             * template_id : 222
             * name : 按重量派送
             */

            private List<DtyListBean> dtyList;

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

            public List<CouponListBean> getCouponList() {
                return couponList;
            }

            public void setCouponList(List<CouponListBean> couponList) {
                this.couponList = couponList;
            }

            public List<DtyListBean> getDtyList() {
                return dtyList;
            }

            public void setDtyList(List<DtyListBean> dtyList) {
                this.dtyList = dtyList;
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
                private List<?> promotions;

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

                public List<?> getPromotions() {
                    return promotions;
                }

                public void setPromotions(List<?> promotions) {
                    this.promotions = promotions;
                }
            }

            public static class CouponListBean {
                private String coupon_name;
                private String coupon_code;
                private String is_checked;
                private String shop_id;

                public String getCoupon_name() {
                    return coupon_name;
                }

                public void setCoupon_name(String coupon_name) {
                    this.coupon_name = coupon_name;
                }

                public String getCoupon_code() {
                    return coupon_code;
                }

                public void setCoupon_code(String coupon_code) {
                    this.coupon_code = coupon_code;
                }

                public String getIs_checked() {
                    return is_checked;
                }

                public void setIs_checked(String is_checked) {
                    this.is_checked = is_checked;
                }

                public String getShop_id() {
                    return shop_id;
                }

                public void setShop_id(String shop_id) {
                    this.shop_id = shop_id;
                }
            }

            public static class DtyListBean {
                private String template_id;
                private String name;

                public String getTemplate_id() {
                    return template_id;
                }

                public void setTemplate_id(String template_id) {
                    this.template_id = template_id;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }
            }
        }

        public static class PTcouponListBean {
            private String coupon_name;
            private String coupon_code;
            private String is_checked;
            private String pt_shop_id;
            private String pt_templet_id;

            public String getCoupon_name() {
                return coupon_name;
            }

            public void setCoupon_name(String coupon_name) {
                this.coupon_name = coupon_name;
            }

            public String getCoupon_code() {
                return coupon_code;
            }

            public void setCoupon_code(String coupon_code) {
                this.coupon_code = coupon_code;
            }

            public String getIs_checked() {
                return is_checked;
            }

            public void setIs_checked(String is_checked) {
                this.is_checked = is_checked;
            }

            public String getPt_shop_id() {
                return pt_shop_id;
            }

            public void setPt_shop_id(String pt_shop_id) {
                this.pt_shop_id = pt_shop_id;
            }

            public String getPt_templet_id() {
                return pt_templet_id;
            }

            public void setPt_templet_id(String pt_templet_id) {
                this.pt_templet_id = pt_templet_id;
            }
        }
    }
}
