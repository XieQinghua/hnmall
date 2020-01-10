package com.your.mall.bean;

import java.util.List;

/**
 * Created by xieqinghua.
 * 创建时间：2016/11/18
 * 类描述：
 * 修改备注：
 */
public class ZujiBean {

    /**
     * msg : 数据获取成功
     * code : 0
     * data : {"items":[{"price":"39.00","zuji_id":"766787","item_id":"4624","create_time":"2016-10-25","zj_status":"0","zid":"767025","image_default_id":"http://img.hnmall.com/newimages/201508/source_img/4624/4624_P_1440122331320.jpg","sources_url":"","sources":"wap","user_id":"132468","title":"【友阿果园】泰国带叶桂圆龙眼 2斤 颗颗饱满 ","ctime":"17分钟前"},{"price":"48.00","zuji_id":"767017","item_id":"1357","create_time":"2016-11-17","zj_status":"0","zid":"767024","image_default_id":"http://img.hnmall.com/newimages/201502/goods_img/1357/1357_G_1424904910803.jpg","sources_url":"","sources":"wap","user_id":"132468","title":"永康炭雕春节销售冠军\u2014\u2014年年有余活性炭碳雕工艺品家居装饰品摆设","ctime":"10小时前 "},{"price":"72.00","zuji_id":"766788","item_id":"4625","create_time":"2016-10-25","zj_status":"0","zid":"767022","image_default_id":"http://img.hnmall.com/newimages/201508/source_img/4625/4625_P_1440122403599.jpg","sources_url":"","sources":"wap","user_id":"132468","title":"【友阿果园】泰国带叶桂圆龙眼2kg 颗颗饱满","ctime":"14小时前 "},{"price":"5.50","zuji_id":"766909","item_id":"2396","create_time":"2016-11-07","zj_status":"0","zid":"767021","image_default_id":"http://img.hnmall.com/newimages/201504/goods_img/2396/2396_G_1428688773630.jpg","sources_url":"","sources":"wap","user_id":"132468","title":"满55包邮 星扬神奇书法练习布带比划 练习书法","ctime":"14小时前 "},{"price":"1.00","zuji_id":"766797","item_id":"6763","create_time":"2016-10-28","zj_status":"0","zid":"766997","image_default_id":"http://nbh.e9448.com/images/dd/84/d1/362a899766a893ad6778f80647c2afe39bb038fa.jpg","sources_url":"","sources":"wap","user_id":"132468","title":"111111","ctime":"3天前 "},{"price":"79.00","zuji_id":"766932","item_id":"5046","create_time":"2016-11-07","zj_status":"0","zid":"766932","image_default_id":"http://img.hnmall.com/newimages/201511/source_img/5046/5046_P_1447638414796.png","sources_url":"","sources":"wap","user_id":"132468","title":"【友阿果园】墨西哥牛油果4个","ctime":"1周前 "},{"price":"39.00","zuji_id":"766930","item_id":"5447","create_time":"2016-11-07","zj_status":"0","zid":"766931","image_default_id":"http://www.hnmall.com/images/a9/85/89/8fdd4ead75450808f9c7de5d7df8de3bf45d9622.jpg","sources_url":"","sources":"wap","user_id":"132468","title":"贵州茅台集团茅台茅乡珍品酒小酒送礼聚会特价包邮","ctime":"1周前 "},{"price":"12.80","zuji_id":"766811","item_id":"5798","create_time":"2016-10-29","zj_status":"0","zid":"766811","image_default_id":"http://www.hnmall.com/images/83/1d/fc/d427d1ff29deee49b5cdf447ceefa41919ce155b.jpg","sources_url":"","sources":"wap","user_id":"132468","title":"ABD蛋糕干120g 香蕉味牛奶蛋糕面包干 曲奇饼干早餐零食","ctime":"2周前 "}]}
     * count : 111
     * title : 我的足迹
     */

    private String msg;
    private String code;
    private DataBean data;
    private String count;
    private String title;

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

    public static class DataBean {
        /**
         * price : 39.00
         * zuji_id : 766787
         * item_id : 4624
         * create_time : 2016-10-25
         * zj_status : 0
         * zid : 767025
         * image_default_id : http://img.hnmall.com/newimages/201508/source_img/4624/4624_P_1440122331320.jpg
         * sources_url :
         * sources : wap
         * user_id : 132468
         * title : 【友阿果园】泰国带叶桂圆龙眼 2斤 颗颗饱满
         * ctime : 17分钟前
         */

        private List<ItemsBean> items;

        public List<ItemsBean> getItems() {
            return items;
        }

        public void setItems(List<ItemsBean> items) {
            this.items = items;
        }

        public static class ItemsBean {
            private String price;
            private String zuji_id;
            private String item_id;
            private String create_time;
            private String zj_status;
            private String zid;
            private String image_default_id;
            private String sources_url;
            private String sources;
            private String user_id;
            private String title;
            private String ctime;

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getZuji_id() {
                return zuji_id;
            }

            public void setZuji_id(String zuji_id) {
                this.zuji_id = zuji_id;
            }

            public String getItem_id() {
                return item_id;
            }

            public void setItem_id(String item_id) {
                this.item_id = item_id;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public String getZj_status() {
                return zj_status;
            }

            public void setZj_status(String zj_status) {
                this.zj_status = zj_status;
            }

            public String getZid() {
                return zid;
            }

            public void setZid(String zid) {
                this.zid = zid;
            }

            public String getImage_default_id() {
                return image_default_id;
            }

            public void setImage_default_id(String image_default_id) {
                this.image_default_id = image_default_id;
            }

            public String getSources_url() {
                return sources_url;
            }

            public void setSources_url(String sources_url) {
                this.sources_url = sources_url;
            }

            public String getSources() {
                return sources;
            }

            public void setSources(String sources) {
                this.sources = sources;
            }

            public String getUser_id() {
                return user_id;
            }

            public void setUser_id(String user_id) {
                this.user_id = user_id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getCtime() {
                return ctime;
            }

            public void setCtime(String ctime) {
                this.ctime = ctime;
            }
        }
    }
}
