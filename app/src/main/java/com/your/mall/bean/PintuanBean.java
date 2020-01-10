package com.your.mall.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/11/21.
 */
public class PintuanBean {

    /**
     * msg : 数据获取成功
     * code : 0
     * data : {"items_pintuancj":[{"activity_lx":"6","activity_id":"186","activity_goods_property":["拼团抽奖奖品2"],"price":"49.00","activity_number":"2","item_id":"4476","activity_goods_title":"拼团抽奖奖品2","activity_goods_desc":"拼团抽奖奖品","activity_goods_banner":"http://nbh.e9448.com/images/b2/23/47/21f54a041214a5f690d6d2f86ae22cf2bf825e77.jpg","activity_price":"0.01","end_time":"2016-12-31 00:00","id":"1194","zhekou":"0.0"},{"activity_lx":"6","activity_id":"186","activity_goods_property":[""],"price":"999.00","activity_number":"2","item_id":"6762","activity_goods_title":"","activity_goods_desc":"","activity_goods_banner":"","activity_price":"0.01","end_time":"2016-12-31 00:00","id":"1193","zhekou":"0.0"},{"activity_lx":"6","activity_id":"186","activity_goods_property":["拼团抽奖奖品1"],"price":"1,111.00","activity_number":"2","item_id":"6782","activity_goods_title":"拼团抽奖奖品1","activity_goods_desc":"拼团抽奖奖品1","activity_goods_banner":"http://nbh.e9448.com/images/45/f3/40/d98942f031116540449b982177271fddfc228450.jpg","activity_price":"88.00","end_time":"2016-12-31 00:00","id":"1192","zhekou":"0.8"}],"items_pintuan":[{"activity_lx":"1","activity_id":"191","activity_goods_property":[""],"price":"69.90","activity_number":"2","item_id":"4830","activity_goods_title":"红富士","activity_goods_desc":"红富士","activity_goods_banner":"http://nbh.e9448.com/images/dc/76/5d/76390ea2afd460697350c0353b261214a12ff4ab.jpg","activity_price":"0.01","end_time":"2017-06-04 00:00","id":"1210","zhekou":"0.0"},{"activity_lx":"1","activity_id":"191","activity_goods_property":[""],"price":"49.00","activity_number":"2","item_id":"6565","activity_goods_title":"蜜瓜","activity_goods_desc":"蜜瓜","activity_goods_banner":"http://nbh.e9448.com/images/ef/97/34/fafa7672333e5d2c50e03fc954eb3f532fa1811c.jpg","activity_price":"0.01","end_time":"2017-06-04 00:00","id":"1209","zhekou":"0.0"},{"activity_lx":"1","activity_id":"191","activity_goods_property":[""],"price":"65.00","activity_number":"2","item_id":"6584","activity_goods_title":"芒果","activity_goods_desc":"芒果","activity_goods_banner":"","activity_price":"0.01","end_time":"2017-06-04 00:00","id":"1211","zhekou":"0.0"}]}
     */

    private String msg;
    private String code;
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
         * activity_lx : 6
         * activity_id : 186
         * activity_goods_property : ["拼团抽奖奖品2"]
         * price : 49.00
         * activity_number : 2
         * item_id : 4476
         * activity_goods_title : 拼团抽奖奖品2
         * activity_goods_desc : 拼团抽奖奖品
         * activity_goods_banner : http://nbh.e9448.com/images/b2/23/47/21f54a041214a5f690d6d2f86ae22cf2bf825e77.jpg
         * activity_price : 0.01
         * end_time : 2016-12-31 00:00
         * id : 1194
         * zhekou : 0.0
         */

        private List<ItemsPintuancjBean> items_pintuancj;
        /**
         * activity_lx : 1
         * activity_id : 191
         * activity_goods_property : [""]
         * price : 69.90
         * activity_number : 2
         * item_id : 4830
         * activity_goods_title : 红富士
         * activity_goods_desc : 红富士
         * activity_goods_banner : http://nbh.e9448.com/images/dc/76/5d/76390ea2afd460697350c0353b261214a12ff4ab.jpg
         * activity_price : 0.01
         * end_time : 2017-06-04 00:00
         * id : 1210
         * zhekou : 0.0
         */

        private List<ItemsPintuanBean> items_pintuan;

        public List<ItemsPintuancjBean> getItems_pintuancj() {
            return items_pintuancj;
        }

        public void setItems_pintuancj(List<ItemsPintuancjBean> items_pintuancj) {
            this.items_pintuancj = items_pintuancj;
        }

        public List<ItemsPintuanBean> getItems_pintuan() {
            return items_pintuan;
        }

        public void setItems_pintuan(List<ItemsPintuanBean> items_pintuan) {
            this.items_pintuan = items_pintuan;
        }

        public static class ItemsPintuancjBean {
            private String activity_lx;
            private String activity_id;
            private String price;
            private String activity_number;
            private String item_id;
            private String activity_goods_title;
            private String activity_goods_desc;
            private String activity_goods_banner;
            private String activity_price;
            private String end_time;
            private String id;
            private String zhekou;
            private List<String> activity_goods_property;

            public String getActivity_lx() {
                return activity_lx;
            }

            public void setActivity_lx(String activity_lx) {
                this.activity_lx = activity_lx;
            }

            public String getActivity_id() {
                return activity_id;
            }

            public void setActivity_id(String activity_id) {
                this.activity_id = activity_id;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getActivity_number() {
                return activity_number;
            }

            public void setActivity_number(String activity_number) {
                this.activity_number = activity_number;
            }

            public String getItem_id() {
                return item_id;
            }

            public void setItem_id(String item_id) {
                this.item_id = item_id;
            }

            public String getActivity_goods_title() {
                return activity_goods_title;
            }

            public void setActivity_goods_title(String activity_goods_title) {
                this.activity_goods_title = activity_goods_title;
            }

            public String getActivity_goods_desc() {
                return activity_goods_desc;
            }

            public void setActivity_goods_desc(String activity_goods_desc) {
                this.activity_goods_desc = activity_goods_desc;
            }

            public String getActivity_goods_banner() {
                return activity_goods_banner;
            }

            public void setActivity_goods_banner(String activity_goods_banner) {
                this.activity_goods_banner = activity_goods_banner;
            }

            public String getActivity_price() {
                return activity_price;
            }

            public void setActivity_price(String activity_price) {
                this.activity_price = activity_price;
            }

            public String getEnd_time() {
                return end_time;
            }

            public void setEnd_time(String end_time) {
                this.end_time = end_time;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getZhekou() {
                return zhekou;
            }

            public void setZhekou(String zhekou) {
                this.zhekou = zhekou;
            }

            public List<String> getActivity_goods_property() {
                return activity_goods_property;
            }

            public void setActivity_goods_property(List<String> activity_goods_property) {
                this.activity_goods_property = activity_goods_property;
            }
        }

        public static class ItemsPintuanBean {
            private String activity_lx;
            private String activity_id;
            private String price;
            private String activity_number;
            private String item_id;
            private String activity_goods_title;
            private String activity_goods_desc;
            private String activity_goods_banner;
            private String activity_price;
            private String end_time;
            private String id;
            private String zhekou;
            private List<String> activity_goods_property;

            public String getActivity_lx() {
                return activity_lx;
            }

            public void setActivity_lx(String activity_lx) {
                this.activity_lx = activity_lx;
            }

            public String getActivity_id() {
                return activity_id;
            }

            public void setActivity_id(String activity_id) {
                this.activity_id = activity_id;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getActivity_number() {
                return activity_number;
            }

            public void setActivity_number(String activity_number) {
                this.activity_number = activity_number;
            }

            public String getItem_id() {
                return item_id;
            }

            public void setItem_id(String item_id) {
                this.item_id = item_id;
            }

            public String getActivity_goods_title() {
                return activity_goods_title;
            }

            public void setActivity_goods_title(String activity_goods_title) {
                this.activity_goods_title = activity_goods_title;
            }

            public String getActivity_goods_desc() {
                return activity_goods_desc;
            }

            public void setActivity_goods_desc(String activity_goods_desc) {
                this.activity_goods_desc = activity_goods_desc;
            }

            public String getActivity_goods_banner() {
                return activity_goods_banner;
            }

            public void setActivity_goods_banner(String activity_goods_banner) {
                this.activity_goods_banner = activity_goods_banner;
            }

            public String getActivity_price() {
                return activity_price;
            }

            public void setActivity_price(String activity_price) {
                this.activity_price = activity_price;
            }

            public String getEnd_time() {
                return end_time;
            }

            public void setEnd_time(String end_time) {
                this.end_time = end_time;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getZhekou() {
                return zhekou;
            }

            public void setZhekou(String zhekou) {
                this.zhekou = zhekou;
            }

            public List<String> getActivity_goods_property() {
                return activity_goods_property;
            }

            public void setActivity_goods_property(List<String> activity_goods_property) {
                this.activity_goods_property = activity_goods_property;
            }
        }
    }
}
