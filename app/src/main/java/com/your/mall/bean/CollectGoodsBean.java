package com.your.mall.bean;

import java.util.List;

/**
 * Created by xieqinghua.
 * 创建时间：2016/10/8
 * 类描述：用户收货地址列表返回json
 * 修改备注：
 */
public class CollectGoodsBean {
    /**
     * msg : 收藏商品获取成功
     * code : 0
     * data : {"itemcount":3,"list":[{"goods_id":4453,"user_id":154548,"goods_thumb":"http://img.hnmall.com/newimages/201507/source_img/4453/4453_P_1438023449182.jpg","goods_name":"【友阿果园】越南进口红心火龙果2.5kg ","market_price":"69.00","shop_price":"69.00","url":"item.html?item_id=4453"},{"goods_id":6486,"user_id":154548,"goods_thumb":"http://www.hnmall.com/images/d7/cc/6c/387d6167224355da33393a411ccf49ae2c4dae7d.png","goods_name":"【友阿果园】墨西哥牛油果2个","market_price":"26.00","shop_price":"26.00","url":"item.html?item_id=6486"},{"goods_id":4830,"user_id":154548,"goods_thumb":"http://img.hnmall.com/newimages/201510/source_img/4830/4830_P_1444970019387.jpg","goods_name":"【友阿果园】山东富士精美包装 整箱16个装","market_price":"69.00","shop_price":"69.00","url":"item.html?item_id=4830"}]}
     */

    private String msg;
    private String code;
    /**
     * itemcount : 3
     * list : [{"goods_id":4453,"user_id":154548,"goods_thumb":"http://img.hnmall.com/newimages/201507/source_img/4453/4453_P_1438023449182.jpg","goods_name":"【友阿果园】越南进口红心火龙果2.5kg ","market_price":"69.00","shop_price":"69.00","url":"item.html?item_id=4453"},{"goods_id":6486,"user_id":154548,"goods_thumb":"http://www.hnmall.com/images/d7/cc/6c/387d6167224355da33393a411ccf49ae2c4dae7d.png","goods_name":"【友阿果园】墨西哥牛油果2个","market_price":"26.00","shop_price":"26.00","url":"item.html?item_id=6486"},{"goods_id":4830,"user_id":154548,"goods_thumb":"http://img.hnmall.com/newimages/201510/source_img/4830/4830_P_1444970019387.jpg","goods_name":"【友阿果园】山东富士精美包装 整箱16个装","market_price":"69.00","shop_price":"69.00","url":"item.html?item_id=4830"}]
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
        private int itemcount;
        /**
         * goods_id : 4453
         * user_id : 154548
         * goods_thumb : http://img.hnmall.com/newimages/201507/source_img/4453/4453_P_1438023449182.jpg
         * goods_name : 【友阿果园】越南进口红心火龙果2.5kg
         * market_price : 69.00
         * shop_price : 69.00
         * url : item.html?item_id=4453
         */

        private List<ListBean> list;

        public int getItemcount() {
            return itemcount;
        }

        public void setItemcount(int itemcount) {
            this.itemcount = itemcount;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            private int goods_id;
            private int user_id;
            private String goods_thumb;
            private String goods_name;
            private String market_price;
            private String shop_price;
            private String url;

            public int getGoods_id() {
                return goods_id;
            }

            public void setGoods_id(int goods_id) {
                this.goods_id = goods_id;
            }

            public int getUser_id() {
                return user_id;
            }

            public void setUser_id(int user_id) {
                this.user_id = user_id;
            }

            public String getGoods_thumb() {
                return goods_thumb;
            }

            public void setGoods_thumb(String goods_thumb) {
                this.goods_thumb = goods_thumb;
            }

            public String getGoods_name() {
                return goods_name;
            }

            public void setGoods_name(String goods_name) {
                this.goods_name = goods_name;
            }

            public String getMarket_price() {
                return market_price;
            }

            public void setMarket_price(String market_price) {
                this.market_price = market_price;
            }

            public String getShop_price() {
                return shop_price;
            }

            public void setShop_price(String shop_price) {
                this.shop_price = shop_price;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }
    }
}
