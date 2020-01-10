package com.your.mall.bean;

import java.util.List;

/**
 * Created by xieqinghua.
 * 创建时间：2016/11/17
 * 类描述：
 * 修改备注：
 */
public class ShareInfoBean {

    /**
     * msg : 数据获取成功！
     * code : 0
     * data : {"price":"89.00","activity_id":"190","item_id":"4479","activity_price":"0.01","activity_stockprice":"94","activity_goods_banner":"http://nbh.e9448.com/images/dc/76/5d/76390ea2afd460697350c0353b261214a12ff4ab.jpg","title":"【友阿果园】新西兰进口红玫瑰苹果6个装约260g/个︱","activity_goods_title":"苹果","default_sku_id":"4479","activity_goods_banner_hight":"189","max_bargain":"2","activity_goods_image":"http://img.hnmall.com/newimages/201507/source_img/4479/4479_P_1437705619257.jpg_s.jpg","shareList":{"shareText":"苹果","shareImage":"http://img.hnmall.com/newimages/201507/source_img/4479/4479_P_1437705619257.jpg_s.jpg","shareType":"0,1","shareUrl":"http://nbh.e9448.com/wap/bargain-share.html?bargain_id=452","shareTitle":"帮我砍价，还差1位好友帮砍就可0.01元（底价）抢到89.00元的苹果"},"bargain_price":"82.29","bargain_status":"1","headimgurl":"http://nbh.e9448.com//app/topshop/statics/images/touxiang.jpg","nickname":"匿名","bargain_id":"452","people":[{"user_id":"","openid":"o89OowXRDg50icwX0uHWjG2PWFss","content":"哈哈\u201c贫贱之交\u201d说的就是我们！","price":"6.71","nickname":"Flappy","headimgurl":"http://wx.qlogo.cn/mmopen/R2krJGicsdkTkyqmhJRmx5daVda8nsHNiaOcXnicgDbnZSqZqN4icjhNh4Gc1hzYwwgeIjNPFicnVD6TEjHJj1UPib7pz1MicxVyKFO/0"}],"people_number":"1","isorder":"0","about_url":"http://nbh.e9448.com/wap/bargain-about.html"}
     */

    private String msg;
    private String code;
    /**
     * price : 89.00
     * activity_id : 190
     * item_id : 4479
     * activity_price : 0.01
     * activity_stockprice : 94
     * activity_goods_banner : http://nbh.e9448.com/images/dc/76/5d/76390ea2afd460697350c0353b261214a12ff4ab.jpg
     * title : 【友阿果园】新西兰进口红玫瑰苹果6个装约260g/个︱
     * activity_goods_title : 苹果
     * default_sku_id : 4479
     * activity_goods_banner_hight : 189
     * max_bargain : 2
     * activity_goods_image : http://img.hnmall.com/newimages/201507/source_img/4479/4479_P_1437705619257.jpg_s.jpg
     * shareList : {"shareText":"苹果","shareImage":"http://img.hnmall.com/newimages/201507/source_img/4479/4479_P_1437705619257.jpg_s.jpg","shareType":"0,1","shareUrl":"http://nbh.e9448.com/wap/bargain-share.html?bargain_id=452","shareTitle":"帮我砍价，还差1位好友帮砍就可0.01元（底价）抢到89.00元的苹果"}
     * bargain_price : 82.29
     * bargain_status : 1
     * headimgurl : http://nbh.e9448.com//app/topshop/statics/images/touxiang.jpg
     * nickname : 匿名
     * bargain_id : 452
     * people : [{"user_id":"","openid":"o89OowXRDg50icwX0uHWjG2PWFss","content":"哈哈\u201c贫贱之交\u201d说的就是我们！","price":"6.71","nickname":"Flappy","headimgurl":"http://wx.qlogo.cn/mmopen/R2krJGicsdkTkyqmhJRmx5daVda8nsHNiaOcXnicgDbnZSqZqN4icjhNh4Gc1hzYwwgeIjNPFicnVD6TEjHJj1UPib7pz1MicxVyKFO/0"}]
     * people_number : 1
     * isorder : 0
     * about_url : http://nbh.e9448.com/wap/bargain-about.html
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
        private String activity_goods_title;
        private String default_sku_id;
        private String activity_goods_banner_hight;
        private String max_bargain;
        private String activity_goods_image;
        /**
         * shareText : 苹果
         * shareImage : http://img.hnmall.com/newimages/201507/source_img/4479/4479_P_1437705619257.jpg_s.jpg
         * shareType : 0,1
         * shareUrl : http://nbh.e9448.com/wap/bargain-share.html?bargain_id=452
         * shareTitle : 帮我砍价，还差1位好友帮砍就可0.01元（底价）抢到89.00元的苹果
         */

        private ShareListBean shareList;
        private String bargain_price;
        private String bargain_status;
        private String headimgurl;
        private String nickname;
        private String bargain_id;
        private String people_number;
        private String isorder;
        private String about_url;
        /**
         * user_id :
         * openid : o89OowXRDg50icwX0uHWjG2PWFss
         * content : 哈哈“贫贱之交”说的就是我们！
         * price : 6.71
         * nickname : Flappy
         * headimgurl : http://wx.qlogo.cn/mmopen/R2krJGicsdkTkyqmhJRmx5daVda8nsHNiaOcXnicgDbnZSqZqN4icjhNh4Gc1hzYwwgeIjNPFicnVD6TEjHJj1UPib7pz1MicxVyKFO/0
         */

        private List<PeopleBean> people;

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

        public String getActivity_goods_title() {
            return activity_goods_title;
        }

        public void setActivity_goods_title(String activity_goods_title) {
            this.activity_goods_title = activity_goods_title;
        }

        public String getDefault_sku_id() {
            return default_sku_id;
        }

        public void setDefault_sku_id(String default_sku_id) {
            this.default_sku_id = default_sku_id;
        }

        public String getActivity_goods_banner_hight() {
            return activity_goods_banner_hight;
        }

        public void setActivity_goods_banner_hight(String activity_goods_banner_hight) {
            this.activity_goods_banner_hight = activity_goods_banner_hight;
        }

        public String getMax_bargain() {
            return max_bargain;
        }

        public void setMax_bargain(String max_bargain) {
            this.max_bargain = max_bargain;
        }

        public String getActivity_goods_image() {
            return activity_goods_image;
        }

        public void setActivity_goods_image(String activity_goods_image) {
            this.activity_goods_image = activity_goods_image;
        }

        public ShareListBean getShareList() {
            return shareList;
        }

        public void setShareList(ShareListBean shareList) {
            this.shareList = shareList;
        }

        public String getBargain_price() {
            return bargain_price;
        }

        public void setBargain_price(String bargain_price) {
            this.bargain_price = bargain_price;
        }

        public String getBargain_status() {
            return bargain_status;
        }

        public void setBargain_status(String bargain_status) {
            this.bargain_status = bargain_status;
        }

        public String getHeadimgurl() {
            return headimgurl;
        }

        public void setHeadimgurl(String headimgurl) {
            this.headimgurl = headimgurl;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getBargain_id() {
            return bargain_id;
        }

        public void setBargain_id(String bargain_id) {
            this.bargain_id = bargain_id;
        }

        public String getPeople_number() {
            return people_number;
        }

        public void setPeople_number(String people_number) {
            this.people_number = people_number;
        }

        public String getIsorder() {
            return isorder;
        }

        public void setIsorder(String isorder) {
            this.isorder = isorder;
        }

        public String getAbout_url() {
            return about_url;
        }

        public void setAbout_url(String about_url) {
            this.about_url = about_url;
        }

        public List<PeopleBean> getPeople() {
            return people;
        }

        public void setPeople(List<PeopleBean> people) {
            this.people = people;
        }

        public static class ShareListBean {
            private String shareText;
            private String shareImage;
            private String shareType;
            private String shareUrl;
            private String shareTitle;

            public String getShareText() {
                return shareText;
            }

            public void setShareText(String shareText) {
                this.shareText = shareText;
            }

            public String getShareImage() {
                return shareImage;
            }

            public void setShareImage(String shareImage) {
                this.shareImage = shareImage;
            }

            public String getShareType() {
                return shareType;
            }

            public void setShareType(String shareType) {
                this.shareType = shareType;
            }

            public String getShareUrl() {
                return shareUrl;
            }

            public void setShareUrl(String shareUrl) {
                this.shareUrl = shareUrl;
            }

            public String getShareTitle() {
                return shareTitle;
            }

            public void setShareTitle(String shareTitle) {
                this.shareTitle = shareTitle;
            }
        }

        public static class PeopleBean {
            private String user_id;
            private String openid;
            private String content;
            private String price;
            private String nickname;
            private String headimgurl;

            public String getUser_id() {
                return user_id;
            }

            public void setUser_id(String user_id) {
                this.user_id = user_id;
            }

            public String getOpenid() {
                return openid;
            }

            public void setOpenid(String openid) {
                this.openid = openid;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getHeadimgurl() {
                return headimgurl;
            }

            public void setHeadimgurl(String headimgurl) {
                this.headimgurl = headimgurl;
            }
        }
    }
}
