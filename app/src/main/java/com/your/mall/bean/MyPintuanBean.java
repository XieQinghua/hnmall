package com.your.mall.bean;

import java.util.List;

/**
 * Created by xieqinghua.
 * 创建时间：2016/11/14
 * 类描述：我的团购列表
 * 修改备注：
 */
public class MyPintuanBean {

    /**
     * msg : 数据获取成功
     * code : 0
     * data : {"trades":[{"is_buyer_rate":false,"activity_id":"191","item_id":"4830","kaituan_id":"369","shop_id":"240","shop_name":"友阿果园","activity_number":"2","activity_lx":"1","is_success":"1","title":"【友阿果园】山东富士16个装","pic_path":"http://img.hnmall.com/newimages/201510/source_img/4830/4830_P_1444970019387.jpg","price":"0.01"},{"is_buyer_rate":false,"activity_id":"186","item_id":"4476","kaituan_id":"368","shop_id":"240","shop_name":"友阿果园","activity_number":"2","activity_lx":"6","is_success":"1","title":"【友阿果园】美国进口红蛇果8个装 艳丽夺目约180g/个","pic_path":"http://img.hnmall.com/newimages/201507/source_img/4476/4476_P_1437706065898.jpg","price":"0.01"}],"pagers":{"link":"http://nbh.e9448.com/wap/ajaxtradeshow.html?mobile%2Fapp_member_pintuan_html=&user_id=132468&p=20161123142130&k=61447175b78e500f3d83e92da388b01b&uuid=00000000-3322-b1ad-c102-14b60033c587&client_source=android&version=2.0.0&s=0","current":1,"total":null},"count":"","title":"我的拼团","status":"0","pathtopm":"http://nbh.e9448.com/app/topm/statics"}
     */

    private String msg;
    private String code;
    /**
     * trades : [{"is_buyer_rate":false,"activity_id":"191","item_id":"4830","kaituan_id":"369","shop_id":"240","shop_name":"友阿果园","activity_number":"2","activity_lx":"1","is_success":"1","title":"【友阿果园】山东富士16个装","pic_path":"http://img.hnmall.com/newimages/201510/source_img/4830/4830_P_1444970019387.jpg","price":"0.01"},{"is_buyer_rate":false,"activity_id":"186","item_id":"4476","kaituan_id":"368","shop_id":"240","shop_name":"友阿果园","activity_number":"2","activity_lx":"6","is_success":"1","title":"【友阿果园】美国进口红蛇果8个装 艳丽夺目约180g/个","pic_path":"http://img.hnmall.com/newimages/201507/source_img/4476/4476_P_1437706065898.jpg","price":"0.01"}]
     * pagers : {"link":"http://nbh.e9448.com/wap/ajaxtradeshow.html?mobile%2Fapp_member_pintuan_html=&user_id=132468&p=20161123142130&k=61447175b78e500f3d83e92da388b01b&uuid=00000000-3322-b1ad-c102-14b60033c587&client_source=android&version=2.0.0&s=0","current":1,"total":null}
     * count :
     * title : 我的拼团
     * status : 0
     * pathtopm : http://nbh.e9448.com/app/topm/statics
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
         * link : http://nbh.e9448.com/wap/ajaxtradeshow.html?mobile%2Fapp_member_pintuan_html=&user_id=132468&p=20161123142130&k=61447175b78e500f3d83e92da388b01b&uuid=00000000-3322-b1ad-c102-14b60033c587&client_source=android&version=2.0.0&s=0
         * current : 1
         * total : null
         */

        private PagersBean pagers;
        private String count;
        private String title;
        private String status;
        private String pathtopm;
        /**
         * is_buyer_rate : false
         * activity_id : 191
         * item_id : 4830
         * kaituan_id : 369
         * shop_id : 240
         * shop_name : 友阿果园
         * activity_number : 2
         * activity_lx : 1
         * is_success : 1
         * title : 【友阿果园】山东富士16个装
         * pic_path : http://img.hnmall.com/newimages/201510/source_img/4830/4830_P_1444970019387.jpg
         * price : 0.01
         */

        private List<TradesBean> trades;

        public PagersBean getPagers() {
            return pagers;
        }

        public void setPagers(PagersBean pagers) {
            this.pagers = pagers;
        }

        public String getCount() {
            return count;
        }

        public void setCount(String count) {
            this.count = count;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getPathtopm() {
            return pathtopm;
        }

        public void setPathtopm(String pathtopm) {
            this.pathtopm = pathtopm;
        }

        public List<TradesBean> getTrades() {
            return trades;
        }

        public void setTrades(List<TradesBean> trades) {
            this.trades = trades;
        }

        public static class PagersBean {
            private String link;
            private int current;
            private Object total;

            public String getLink() {
                return link;
            }

            public void setLink(String link) {
                this.link = link;
            }

            public int getCurrent() {
                return current;
            }

            public void setCurrent(int current) {
                this.current = current;
            }

            public Object getTotal() {
                return total;
            }

            public void setTotal(Object total) {
                this.total = total;
            }
        }

        public static class TradesBean {
            private boolean is_buyer_rate;
            private String activity_id;
            private String item_id;
            private String kaituan_id;
            private String shop_id;
            private String shop_name;
            private String activity_number;
            private String activity_lx;
            private String is_success;
            private String title;
            private String pic_path;
            private String price;

            public boolean isIs_buyer_rate() {
                return is_buyer_rate;
            }

            public void setIs_buyer_rate(boolean is_buyer_rate) {
                this.is_buyer_rate = is_buyer_rate;
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

            public String getKaituan_id() {
                return kaituan_id;
            }

            public void setKaituan_id(String kaituan_id) {
                this.kaituan_id = kaituan_id;
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

            public String getActivity_number() {
                return activity_number;
            }

            public void setActivity_number(String activity_number) {
                this.activity_number = activity_number;
            }

            public String getActivity_lx() {
                return activity_lx;
            }

            public void setActivity_lx(String activity_lx) {
                this.activity_lx = activity_lx;
            }

            public String getIs_success() {
                return is_success;
            }

            public void setIs_success(String is_success) {
                this.is_success = is_success;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getPic_path() {
                return pic_path;
            }

            public void setPic_path(String pic_path) {
                this.pic_path = pic_path;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }
        }
    }
}
