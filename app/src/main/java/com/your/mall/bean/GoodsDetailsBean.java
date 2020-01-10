package com.your.mall.bean;

import java.util.List;

/**
 * Created by xieqinghua.
 * 创建时间：2016/9/29
 * 类描述：
 * 修改备注：
 */
public class GoodsDetailsBean {

    /**
     * msg : 商品数据获取成功
     * code : 0
     * data : {"item":{"item_id":"7004","shop_id":"304","cat_id":"539","brand_id":"652","shop_cat_id":"971","title":"姊红醇杨梅果酒2瓶 1000ml","price":"176.00","cost_price":"106.00","mkt_price":"198.00","list_image":["http://www.hnmall.com/images/49/82/b3/4cc4318f3ab3016e7e490d55cd20e38df8ec0a24.jpg","http://www.hnmall.com/images/85/ad/5d/8bbad5f0872b044c8a16663bf6314dbcbf52f894.jpg","http://www.hnmall.com/images/e8/af/d2/a8ac8e53c8972045f51af7bdafb8237cacba79a8.jpg","http://www.hnmall.com/images/14/cd/0b/0e7b8616a73be05c83b0290e70c0cf65d2f37930.jpg"],"store":"2347","spec_desc":"0","issupport_productscoupon":0,"is_freepostage":"1","is_new":0,"is_purchase":0,"distribution_range":"","short_title":"","is_orchard_store":"","wap_desc":"<br style=\"line-height: 20px;\"><img src=\"http://www.hnmall.com/images/5b/c7/27/0f8b896be2505ca664174d09408b728ddedb5533.jpg\" style=\"line-height: 20px; width: 640px;\">","default_sku_id":"7434","valid":"1","spec":[]},"shop":{"shop_id":"304","shop_name":"姊红醇商贸友阿专营店（自营店铺）","shop_descript":"湖南姊红醇商贸有限公司","shop_type":"self","shop_logo":"","cmd_image":"","cmd_image_url":"","common_dlytmpl":0,"common_weight":0},"promotionDetail":[{"promotion_tag":"免邮","promotion_name":"单品免邮"}],"countRate":{"good":{"num":0,"percentage":"100%"},"neutral":{"num":0,"percentage":"0%"},"bad":{"num":0,"percentage":"0%"},"total":""},"is_collect":"1","cart_num":"11"}
     */

    private String msg;
    private String code;
    /**
     * item : {"item_id":"7004","shop_id":"304","cat_id":"539","brand_id":"652","shop_cat_id":"971","title":"姊红醇杨梅果酒2瓶 1000ml","price":"176.00","cost_price":"106.00","mkt_price":"198.00","list_image":["http://www.hnmall.com/images/49/82/b3/4cc4318f3ab3016e7e490d55cd20e38df8ec0a24.jpg","http://www.hnmall.com/images/85/ad/5d/8bbad5f0872b044c8a16663bf6314dbcbf52f894.jpg","http://www.hnmall.com/images/e8/af/d2/a8ac8e53c8972045f51af7bdafb8237cacba79a8.jpg","http://www.hnmall.com/images/14/cd/0b/0e7b8616a73be05c83b0290e70c0cf65d2f37930.jpg"],"store":"2347","spec_desc":"0","issupport_productscoupon":0,"is_freepostage":"1","is_new":0,"is_purchase":0,"distribution_range":"","short_title":"","is_orchard_store":"","wap_desc":"<br style=\"line-height: 20px;\"><img src=\"http://www.hnmall.com/images/5b/c7/27/0f8b896be2505ca664174d09408b728ddedb5533.jpg\" style=\"line-height: 20px; width: 640px;\">","default_sku_id":"7434","valid":"1","spec":[]}
     * shop : {"shop_id":"304","shop_name":"姊红醇商贸友阿专营店（自营店铺）","shop_descript":"湖南姊红醇商贸有限公司","shop_type":"self","shop_logo":"","cmd_image":"","cmd_image_url":"","common_dlytmpl":0,"common_weight":0}
     * promotionDetail : [{"promotion_tag":"免邮","promotion_name":"单品免邮"}]
     * countRate : {"good":{"num":0,"percentage":"100%"},"neutral":{"num":0,"percentage":"0%"},"bad":{"num":0,"percentage":"0%"},"total":""}
     * is_collect : 1
     * cart_num : 11
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
         * item_id : 7004
         * shop_id : 304
         * cat_id : 539
         * brand_id : 652
         * shop_cat_id : 971
         * title : 姊红醇杨梅果酒2瓶 1000ml
         * price : 176.00
         * cost_price : 106.00
         * mkt_price : 198.00
         * list_image : ["http://www.hnmall.com/images/49/82/b3/4cc4318f3ab3016e7e490d55cd20e38df8ec0a24.jpg","http://www.hnmall.com/images/85/ad/5d/8bbad5f0872b044c8a16663bf6314dbcbf52f894.jpg","http://www.hnmall.com/images/e8/af/d2/a8ac8e53c8972045f51af7bdafb8237cacba79a8.jpg","http://www.hnmall.com/images/14/cd/0b/0e7b8616a73be05c83b0290e70c0cf65d2f37930.jpg"]
         * store : 2347
         * spec_desc : 0
         * issupport_productscoupon : 0
         * is_freepostage : 1
         * is_new : 0
         * is_purchase : 0
         * distribution_range :
         * short_title :
         * is_orchard_store :
         * wap_desc : <br style="line-height: 20px;"><img src="http://www.hnmall.com/images/5b/c7/27/0f8b896be2505ca664174d09408b728ddedb5533.jpg" style="line-height: 20px; width: 640px;">
         * default_sku_id : 7434
         * valid : 1
         * spec : []
         */

        private ItemBean item;
        /**
         * shop_id : 304
         * shop_name : 姊红醇商贸友阿专营店（自营店铺）
         * shop_descript : 湖南姊红醇商贸有限公司
         * shop_type : self
         * shop_logo :
         * cmd_image :
         * cmd_image_url :
         * common_dlytmpl : 0
         * common_weight : 0
         */

        private ShopBean shop;
        /**
         * good : {"num":0,"percentage":"100%"}
         * neutral : {"num":0,"percentage":"0%"}
         * bad : {"num":0,"percentage":"0%"}
         * total :
         */

        private CountRateBean countRate;
        private String is_collect;
        private String cart_num;
        /**
         * promotion_tag : 免邮
         * promotion_name : 单品免邮
         */

        private List<PromotionDetailBean> promotionDetail;

        public ItemBean getItem() {
            return item;
        }

        public void setItem(ItemBean item) {
            this.item = item;
        }

        public ShopBean getShop() {
            return shop;
        }

        public void setShop(ShopBean shop) {
            this.shop = shop;
        }

        public CountRateBean getCountRate() {
            return countRate;
        }

        public void setCountRate(CountRateBean countRate) {
            this.countRate = countRate;
        }

        public String getIs_collect() {
            return is_collect;
        }

        public void setIs_collect(String is_collect) {
            this.is_collect = is_collect;
        }

        public String getCart_num() {
            return cart_num;
        }

        public void setCart_num(String cart_num) {
            this.cart_num = cart_num;
        }

        public List<PromotionDetailBean> getPromotionDetail() {
            return promotionDetail;
        }

        public void setPromotionDetail(List<PromotionDetailBean> promotionDetail) {
            this.promotionDetail = promotionDetail;
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
            private String store;
            private String spec_desc;
            private int issupport_productscoupon;
            private String is_freepostage;
            private int is_new;
            private int is_purchase;
            private String distribution_range;
            private String short_title;
            private String is_orchard_store;
            private String wap_desc;
            private String default_sku_id;
            private String valid;
            private List<String> list_image;
//            private List<?> spec;

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

            public String getStore() {
                return store;
            }

            public void setStore(String store) {
                this.store = store;
            }

            public String getSpec_desc() {
                return spec_desc;
            }

            public void setSpec_desc(String spec_desc) {
                this.spec_desc = spec_desc;
            }

            public int getIssupport_productscoupon() {
                return issupport_productscoupon;
            }

            public void setIssupport_productscoupon(int issupport_productscoupon) {
                this.issupport_productscoupon = issupport_productscoupon;
            }

            public String getIs_freepostage() {
                return is_freepostage;
            }

            public void setIs_freepostage(String is_freepostage) {
                this.is_freepostage = is_freepostage;
            }

            public int getIs_new() {
                return is_new;
            }

            public void setIs_new(int is_new) {
                this.is_new = is_new;
            }

            public int getIs_purchase() {
                return is_purchase;
            }

            public void setIs_purchase(int is_purchase) {
                this.is_purchase = is_purchase;
            }

            public String getDistribution_range() {
                return distribution_range;
            }

            public void setDistribution_range(String distribution_range) {
                this.distribution_range = distribution_range;
            }

            public String getShort_title() {
                return short_title;
            }

            public void setShort_title(String short_title) {
                this.short_title = short_title;
            }

            public String getIs_orchard_store() {
                return is_orchard_store;
            }

            public void setIs_orchard_store(String is_orchard_store) {
                this.is_orchard_store = is_orchard_store;
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

            public String getValid() {
                return valid;
            }

            public void setValid(String valid) {
                this.valid = valid;
            }

            public List<String> getList_image() {
                return list_image;
            }

            public void setList_image(List<String> list_image) {
                this.list_image = list_image;
            }

//            public List<?> getSpec() {
//                return spec;
//            }
//
//            public void setSpec(List<?> spec) {
//                this.spec = spec;
//            }
        }

        public static class ShopBean {
            private String shop_id;
            private String shop_name;
            private String shop_descript;
            private String shop_type;
            private String shop_logo;
            private String cmd_image;
            private String cmd_image_url;
            private int common_dlytmpl;
            private int common_weight;

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

            public String getShop_descript() {
                return shop_descript;
            }

            public void setShop_descript(String shop_descript) {
                this.shop_descript = shop_descript;
            }

            public String getShop_type() {
                return shop_type;
            }

            public void setShop_type(String shop_type) {
                this.shop_type = shop_type;
            }

            public String getShop_logo() {
                return shop_logo;
            }

            public void setShop_logo(String shop_logo) {
                this.shop_logo = shop_logo;
            }

            public String getCmd_image() {
                return cmd_image;
            }

            public void setCmd_image(String cmd_image) {
                this.cmd_image = cmd_image;
            }

            public String getCmd_image_url() {
                return cmd_image_url;
            }

            public void setCmd_image_url(String cmd_image_url) {
                this.cmd_image_url = cmd_image_url;
            }

            public int getCommon_dlytmpl() {
                return common_dlytmpl;
            }

            public void setCommon_dlytmpl(int common_dlytmpl) {
                this.common_dlytmpl = common_dlytmpl;
            }

            public int getCommon_weight() {
                return common_weight;
            }

            public void setCommon_weight(int common_weight) {
                this.common_weight = common_weight;
            }
        }

        public static class CountRateBean {
            /**
             * num : 0
             * percentage : 100%
             */

            private GoodBean good;
            /**
             * num : 0
             * percentage : 0%
             */

            private NeutralBean neutral;
            /**
             * num : 0
             * percentage : 0%
             */

            private BadBean bad;
            private String total;

            public GoodBean getGood() {
                return good;
            }

            public void setGood(GoodBean good) {
                this.good = good;
            }

            public NeutralBean getNeutral() {
                return neutral;
            }

            public void setNeutral(NeutralBean neutral) {
                this.neutral = neutral;
            }

            public BadBean getBad() {
                return bad;
            }

            public void setBad(BadBean bad) {
                this.bad = bad;
            }

            public String getTotal() {
                return total;
            }

            public void setTotal(String total) {
                this.total = total;
            }

            public static class GoodBean {
                private int num;
                private String percentage;

                public int getNum() {
                    return num;
                }

                public void setNum(int num) {
                    this.num = num;
                }

                public String getPercentage() {
                    return percentage;
                }

                public void setPercentage(String percentage) {
                    this.percentage = percentage;
                }
            }

            public static class NeutralBean {
                private int num;
                private String percentage;

                public int getNum() {
                    return num;
                }

                public void setNum(int num) {
                    this.num = num;
                }

                public String getPercentage() {
                    return percentage;
                }

                public void setPercentage(String percentage) {
                    this.percentage = percentage;
                }
            }

            public static class BadBean {
                private int num;
                private String percentage;

                public int getNum() {
                    return num;
                }

                public void setNum(int num) {
                    this.num = num;
                }

                public String getPercentage() {
                    return percentage;
                }

                public void setPercentage(String percentage) {
                    this.percentage = percentage;
                }
            }
        }

        public static class PromotionDetailBean {
            private String promotion_tag;
            private String promotion_name;

            public String getPromotion_tag() {
                return promotion_tag;
            }

            public void setPromotion_tag(String promotion_tag) {
                this.promotion_tag = promotion_tag;
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
