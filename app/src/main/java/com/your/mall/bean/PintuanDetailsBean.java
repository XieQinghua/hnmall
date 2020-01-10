package com.your.mall.bean;

import java.util.List;

/**
 * Created by xieqinghua.
 * 创建时间：2016/11/14
 * 类描述：拼团bean
 * 修改备注：
 */
public class PintuanDetailsBean {

    /**
     * msg : 数据获取成功
     * code : 0
     * data : {"price":"69.90","activity_id":"191","item_id":"4830","activity_price":"0.01","activity_stockprice":"7","activity_goods_banner":"http://nbh.e9448.com/images/dc/76/5d/76390ea2afd460697350c0353b261214a12ff4ab.jpg","title":"【友阿果园】山东富士16个装","activity_goods_property":["红富士"],"item_default_image":"http://img.hnmall.com/newimages/201510/source_img/4830/4830_P_1444970019387.jpg","activity_goods_title":"红富士","activity_number":"2","pathtopm":"http://nbh.e9448.com/app/topm/statics","oauthurl":"https://ssl.e9448.com/score/","nologin":"1","user_id":"132468","now_time":"2016-11-21 14:40","next_page":"","join_number":"","sold_quantity":"0","item":{"item_id":"4830","shop_id":"240","cat_id":"583","brand_id":"534","shop_cat_id":"844","title":"【友阿果园】山东富士16个装","price":"69.90","cost_price":"103.700","mkt_price":"139.000","list_image":"http://img.hnmall.com/newimages/201510/source_img/4830/4830_P_1444970019387.jpg,http://img.hnmall.com/newimages/201510/source_img/4830/4830_P_1444970408435.jpg,http://img.hnmall.com/newimages/201510/source_img/4830/4830_P_1444970408641.jpg,http://img.hnmall.com/newimages/201510/source_img/4830/4830_P_1444970408289.jpg","store":"1160","spec_desc":false,"wap_desc":"<br><p style=\"TEXT-ALIGN: center\"><img title=\"山东红富士整箱16.jpg\" src=\"http://img.hnmall.com/bdimages/upload1/20151016/1444998772508937.jpg\"><\/p><p><br><\/p>","default_sku_id":"4830","spec":[],"endtime":"1970-01-01 08:00"},"str1":"支付开团并邀请1人参团，人数不足自动退款，详见下方拼团玩法","str2":"快速成团秘籍：支付成功后，请到个人中心-我的团购 中邀请您的好友来参团。","buy_number":"1"}
     */

    private String msg;
    private String code;
    /**
     * price : 69.90
     * activity_id : 191
     * item_id : 4830
     * activity_price : 0.01
     * activity_stockprice : 7
     * activity_goods_banner : http://nbh.e9448.com/images/dc/76/5d/76390ea2afd460697350c0353b261214a12ff4ab.jpg
     * title : 【友阿果园】山东富士16个装
     * activity_goods_property : ["红富士"]
     * item_default_image : http://img.hnmall.com/newimages/201510/source_img/4830/4830_P_1444970019387.jpg
     * activity_goods_title : 红富士
     * activity_number : 2
     * pathtopm : http://nbh.e9448.com/app/topm/statics
     * oauthurl : https://ssl.e9448.com/score/
     * nologin : 1
     * user_id : 132468
     * now_time : 2016-11-21 14:40
     * next_page :
     * join_number :
     * sold_quantity : 0
     * item : {"item_id":"4830","shop_id":"240","cat_id":"583","brand_id":"534","shop_cat_id":"844","title":"【友阿果园】山东富士16个装","price":"69.90","cost_price":"103.700","mkt_price":"139.000","list_image":"http://img.hnmall.com/newimages/201510/source_img/4830/4830_P_1444970019387.jpg,http://img.hnmall.com/newimages/201510/source_img/4830/4830_P_1444970408435.jpg,http://img.hnmall.com/newimages/201510/source_img/4830/4830_P_1444970408641.jpg,http://img.hnmall.com/newimages/201510/source_img/4830/4830_P_1444970408289.jpg","store":"1160","spec_desc":false,"wap_desc":"<br><p style=\"TEXT-ALIGN: center\"><img title=\"山东红富士整箱16.jpg\" src=\"http://img.hnmall.com/bdimages/upload1/20151016/1444998772508937.jpg\"><\/p><p><br><\/p>","default_sku_id":"4830","spec":[],"endtime":"1970-01-01 08:00"}
     * str1 : 支付开团并邀请1人参团，人数不足自动退款，详见下方拼团玩法
     * str2 : 快速成团秘籍：支付成功后，请到个人中心-我的团购 中邀请您的好友来参团。
     * buy_number : 1
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
        private String item_default_image;
        private String activity_goods_title;
        private String activity_number;
        private String pathtopm;
        private String oauthurl;
        private String nologin;
        private String user_id;
        private String now_time;
        private String next_page;
        private String join_number;
        private String sold_quantity;
        /**
         * item_id : 4830
         * shop_id : 240
         * cat_id : 583
         * brand_id : 534
         * shop_cat_id : 844
         * title : 【友阿果园】山东富士16个装
         * price : 69.90
         * cost_price : 103.700
         * mkt_price : 139.000
         * list_image : http://img.hnmall.com/newimages/201510/source_img/4830/4830_P_1444970019387.jpg,http://img.hnmall.com/newimages/201510/source_img/4830/4830_P_1444970408435.jpg,http://img.hnmall.com/newimages/201510/source_img/4830/4830_P_1444970408641.jpg,http://img.hnmall.com/newimages/201510/source_img/4830/4830_P_1444970408289.jpg
         * store : 1160
         * spec_desc : false
         * wap_desc : <br><p style="TEXT-ALIGN: center"><img title="山东红富士整箱16.jpg" src="http://img.hnmall.com/bdimages/upload1/20151016/1444998772508937.jpg"></p><p><br></p>
         * default_sku_id : 4830
         * spec : []
         * endtime : 1970-01-01 08:00
         */

        private ItemBean item;
        private String str1;
        private String str2;
        private String buy_number;
        private List<String> activity_goods_property;

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

        public String getActivity_number() {
            return activity_number;
        }

        public void setActivity_number(String activity_number) {
            this.activity_number = activity_number;
        }

        public String getPathtopm() {
            return pathtopm;
        }

        public void setPathtopm(String pathtopm) {
            this.pathtopm = pathtopm;
        }

        public String getOauthurl() {
            return oauthurl;
        }

        public void setOauthurl(String oauthurl) {
            this.oauthurl = oauthurl;
        }

        public String getNologin() {
            return nologin;
        }

        public void setNologin(String nologin) {
            this.nologin = nologin;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getNow_time() {
            return now_time;
        }

        public void setNow_time(String now_time) {
            this.now_time = now_time;
        }

        public String getNext_page() {
            return next_page;
        }

        public void setNext_page(String next_page) {
            this.next_page = next_page;
        }

        public String getJoin_number() {
            return join_number;
        }

        public void setJoin_number(String join_number) {
            this.join_number = join_number;
        }

        public String getSold_quantity() {
            return sold_quantity;
        }

        public void setSold_quantity(String sold_quantity) {
            this.sold_quantity = sold_quantity;
        }

        public ItemBean getItem() {
            return item;
        }

        public void setItem(ItemBean item) {
            this.item = item;
        }

        public String getStr1() {
            return str1;
        }

        public void setStr1(String str1) {
            this.str1 = str1;
        }

        public String getStr2() {
            return str2;
        }

        public void setStr2(String str2) {
            this.str2 = str2;
        }

        public String getBuy_number() {
            return buy_number;
        }

        public void setBuy_number(String buy_number) {
            this.buy_number = buy_number;
        }

        public List<String> getActivity_goods_property() {
            return activity_goods_property;
        }

        public void setActivity_goods_property(List<String> activity_goods_property) {
            this.activity_goods_property = activity_goods_property;
        }

        public static class ItemBean {
            private String item_id;
            private String shop_id;
            private String cat_id;
            private String brand_id;
            private String shop_cat_id;
            private String title;
            private String price;
            private String cost_price;
            private String mkt_price;
            private String list_image;
            private String store;
            private boolean spec_desc;
            private String wap_desc;
            private String default_sku_id;
            private String endtime;
            private List<?> spec;

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

            public String getCat_id() {
                return cat_id;
            }

            public void setCat_id(String cat_id) {
                this.cat_id = cat_id;
            }

            public String getBrand_id() {
                return brand_id;
            }

            public void setBrand_id(String brand_id) {
                this.brand_id = brand_id;
            }

            public String getShop_cat_id() {
                return shop_cat_id;
            }

            public void setShop_cat_id(String shop_cat_id) {
                this.shop_cat_id = shop_cat_id;
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

            public String getCost_price() {
                return cost_price;
            }

            public void setCost_price(String cost_price) {
                this.cost_price = cost_price;
            }

            public String getMkt_price() {
                return mkt_price;
            }

            public void setMkt_price(String mkt_price) {
                this.mkt_price = mkt_price;
            }

            public String getList_image() {
                return list_image;
            }

            public void setList_image(String list_image) {
                this.list_image = list_image;
            }

            public String getStore() {
                return store;
            }

            public void setStore(String store) {
                this.store = store;
            }

            public boolean isSpec_desc() {
                return spec_desc;
            }

            public void setSpec_desc(boolean spec_desc) {
                this.spec_desc = spec_desc;
            }

            public String getWap_desc() {
                return wap_desc;
            }

            public void setWap_desc(String wap_desc) {
                this.wap_desc = wap_desc;
            }

            public String getDefault_sku_id() {
                return default_sku_id;
            }

            public void setDefault_sku_id(String default_sku_id) {
                this.default_sku_id = default_sku_id;
            }

            public String getEndtime() {
                return endtime;
            }

            public void setEndtime(String endtime) {
                this.endtime = endtime;
            }

            public List<?> getSpec() {
                return spec;
            }

            public void setSpec(List<?> spec) {
                this.spec = spec;
            }
        }
    }
}
