package com.your.mall.bean;

import java.util.List;

/**
 * Created by xieqinghua.
 * 创建时间：2016/9/22
 * 类描述：
 * 修改备注：
 */
public class GoodsListBean {

    /**
     * msg : 商品数据获取成功
     * code : 0
     * data : {"list":[{"sold_quantity":"6","goods_id":"4834","goods_name":"【美国进口】美国姬娜苹果16个装 210g/个","market_price":"159.00","shop_price":"128.00","goods_type":"583","goods_img":"http://img.hnmall.com/newimages/201510/source_img/4834/4834_P_1444969693224.jpg_s.jpg"},{"sold_quantity":"6","goods_id":"4478","goods_name":"【友阿果园】美国姬娜苹果8个装 进口果 约210g/个","market_price":"58.00","shop_price":"69.00","goods_type":"721","goods_img":"http://img.hnmall.com/newimages/201507/source_img/4478/4478_P_1438134293882.jpg_s.jpg"},{"sold_quantity":"5","goods_id":"4479","goods_name":"【友阿果园】新西兰进口红玫瑰苹果6个装约260g/个︱","market_price":"109.00","shop_price":"89.00","goods_type":"722","goods_img":"http://img.hnmall.com/newimages/201507/source_img/4479/4479_P_1437705619257.jpg_s.jpg"},{"sold_quantity":"3","goods_id":"5235","goods_name":"资民堂 苹果酥饼江南地方特产美食传统手工无添加糕点240g包邮","market_price":"35.00","shop_price":"22.50","goods_type":"547","goods_img":"http://www.hnmall.com/images/57/ee/ab/bc98297a669b9bed23d6b2d7b0ad02ace565f7fb.jpg_s.jpg"},{"sold_quantity":"0","goods_id":"6764","goods_name":"沅江苹果爱心捐款","market_price":"0.00","shop_price":"0.00","goods_type":"0","goods_img":"_s.jpg"}],"search_keywords":"苹果","count":5}
     */

    private String msg;
    private String code;
    /**
     * list : [{"sold_quantity":"6","goods_id":"4834","goods_name":"【美国进口】美国姬娜苹果16个装 210g/个","market_price":"159.00","shop_price":"128.00","goods_type":"583","goods_img":"http://img.hnmall.com/newimages/201510/source_img/4834/4834_P_1444969693224.jpg_s.jpg"},{"sold_quantity":"6","goods_id":"4478","goods_name":"【友阿果园】美国姬娜苹果8个装 进口果 约210g/个","market_price":"58.00","shop_price":"69.00","goods_type":"721","goods_img":"http://img.hnmall.com/newimages/201507/source_img/4478/4478_P_1438134293882.jpg_s.jpg"},{"sold_quantity":"5","goods_id":"4479","goods_name":"【友阿果园】新西兰进口红玫瑰苹果6个装约260g/个︱","market_price":"109.00","shop_price":"89.00","goods_type":"722","goods_img":"http://img.hnmall.com/newimages/201507/source_img/4479/4479_P_1437705619257.jpg_s.jpg"},{"sold_quantity":"3","goods_id":"5235","goods_name":"资民堂 苹果酥饼江南地方特产美食传统手工无添加糕点240g包邮","market_price":"35.00","shop_price":"22.50","goods_type":"547","goods_img":"http://www.hnmall.com/images/57/ee/ab/bc98297a669b9bed23d6b2d7b0ad02ace565f7fb.jpg_s.jpg"},{"sold_quantity":"0","goods_id":"6764","goods_name":"沅江苹果爱心捐款","market_price":"0.00","shop_price":"0.00","goods_type":"0","goods_img":"_s.jpg"}]
     * search_keywords : 苹果
     * count : 5
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
        private String search_keywords;
        private int count;
        /**
         * sold_quantity : 6
         * goods_id : 4834
         * goods_name : 【美国进口】美国姬娜苹果16个装 210g/个
         * market_price : 159.00
         * shop_price : 128.00
         * goods_type : 583
         * goods_img : http://img.hnmall.com/newimages/201510/source_img/4834/4834_P_1444969693224.jpg_s.jpg
         */

        private List<ListBean> list;

        public String getSearch_keywords() {
            return search_keywords;
        }

        public void setSearch_keywords(String search_keywords) {
            this.search_keywords = search_keywords;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            private String sold_quantity;
            private String goods_id;
            private String goods_name;
            private String market_price;
            private String shop_price;
            private String goods_type;
            private String goods_img;

            public String getSold_quantity() {
                return sold_quantity;
            }

            public void setSold_quantity(String sold_quantity) {
                this.sold_quantity = sold_quantity;
            }

            public String getGoods_id() {
                return goods_id;
            }

            public void setGoods_id(String goods_id) {
                this.goods_id = goods_id;
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

            public String getGoods_type() {
                return goods_type;
            }

            public void setGoods_type(String goods_type) {
                this.goods_type = goods_type;
            }

            public String getGoods_img() {
                return goods_img;
            }

            public void setGoods_img(String goods_img) {
                this.goods_img = goods_img;
            }
        }
    }
}
