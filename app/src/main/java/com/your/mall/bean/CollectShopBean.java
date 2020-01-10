package com.your.mall.bean;

import java.util.List;

/**
 * Created by xieqinghua.
 * 创建时间：2016/10/8
 * 类描述：用户收货地址列表返回json
 * 修改备注：
 */
public class CollectShopBean {

    /**
     * msg : 收藏店铺获取成功
     * code : 0
     * data : {"shopcount":2,"list":[{"shop_id":240,"user_id":154548,"shop_logo":"http://www.hnmall.com/images/0f/3d/2c/5946dceea07ad89bd97d4f6f85ec39d8224cc8bf.jpg","shop_name":"友阿果园","create_time":"2016-10-11 10:12:43","url":"shop.html?shop_id=240","shop_descript":"友阿果园为友阿农博汇自有品牌，放心水果，自然领鲜，实现湖南省内、国内外果园与消费者无缝对接。派送范围长沙市三环以内。"},{"shop_id":99,"user_id":154548,"shop_logo":"http://hnmall.oss-cn-hangzhou.aliyuncs.com//data/supplier/logo/logo_supplier99.jpg","shop_name":"湖南土佳族专营店","create_time":"2016-10-11 10:12:27","url":"shop.html?shop_id=99","shop_descript":"湖南土佳族电子商务有限公司"}]}
     */

    private String msg;
    private String code;
    /**
     * shopcount : 2
     * list : [{"shop_id":240,"user_id":154548,"shop_logo":"http://www.hnmall.com/images/0f/3d/2c/5946dceea07ad89bd97d4f6f85ec39d8224cc8bf.jpg","shop_name":"友阿果园","create_time":"2016-10-11 10:12:43","url":"shop.html?shop_id=240","shop_descript":"友阿果园为友阿农博汇自有品牌，放心水果，自然领鲜，实现湖南省内、国内外果园与消费者无缝对接。派送范围长沙市三环以内。"},{"shop_id":99,"user_id":154548,"shop_logo":"http://hnmall.oss-cn-hangzhou.aliyuncs.com//data/supplier/logo/logo_supplier99.jpg","shop_name":"湖南土佳族专营店","create_time":"2016-10-11 10:12:27","url":"shop.html?shop_id=99","shop_descript":"湖南土佳族电子商务有限公司"}]
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
        private int shopcount;
        /**
         * shop_id : 240
         * user_id : 154548
         * shop_logo : http://www.hnmall.com/images/0f/3d/2c/5946dceea07ad89bd97d4f6f85ec39d8224cc8bf.jpg
         * shop_name : 友阿果园
         * create_time : 2016-10-11 10:12:43
         * url : shop.html?shop_id=240
         * shop_descript : 友阿果园为友阿农博汇自有品牌，放心水果，自然领鲜，实现湖南省内、国内外果园与消费者无缝对接。派送范围长沙市三环以内。
         */

        private List<ListBean> list;

        public int getShopcount() {
            return shopcount;
        }

        public void setShopcount(int shopcount) {
            this.shopcount = shopcount;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            private int shop_id;
            private int user_id;
            private String shop_logo;
            private String shop_name;
            private String create_time;
            private String url;
            private String shop_descript;

            public int getShop_id() {
                return shop_id;
            }

            public void setShop_id(int shop_id) {
                this.shop_id = shop_id;
            }

            public int getUser_id() {
                return user_id;
            }

            public void setUser_id(int user_id) {
                this.user_id = user_id;
            }

            public String getShop_logo() {
                return shop_logo;
            }

            public void setShop_logo(String shop_logo) {
                this.shop_logo = shop_logo;
            }

            public String getShop_name() {
                return shop_name;
            }

            public void setShop_name(String shop_name) {
                this.shop_name = shop_name;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getShop_descript() {
                return shop_descript;
            }

            public void setShop_descript(String shop_descript) {
                this.shop_descript = shop_descript;
            }
        }
    }
}